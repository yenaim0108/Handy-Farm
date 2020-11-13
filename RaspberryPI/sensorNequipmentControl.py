import paho.mqtt.client as mqtt
import time

ip = "192.168.100.3" # 서버 ip 주소
serial = "10000000c366d002" # 라즈베리파이 시리얼 번호
client = mqtt.Client("controlRaspverryPI") # mqtt.Client() 인스턴스 생성

def on_connect(client, userdata, flags, rc) :
    if rc == 0 : # 연결 성공 시,
        client.subscribe('sensor') # sensor 토픽 구독, 센서값 받아오기
        client.subscribe('equipmentSensorValue') # equipmentSensorValue 토픽 구독, JSP한테 현재 센서값, 기준 센서값 받아오기
        client.subscribe('controlStatus') # controlStatus 토픽 구독, ESP32한테 설비, 설비 상태 받아오기
    else: # 연결 실패 시,
        print("Connection failed")

def on_message(client, userdata, message) :
    msg = str(message.payload) # 값 받기
    
    if (msg.find(' : ') != -1) : # sensor 토픽에 값이 들어온 경우
        msg = msg.split(' : ') # 콜론(:)을 기준으로 센서 Type이랑 값 나누기
        tmp = msg[0].split("'") # b'을 제거하기 위한 split
        msg[0] = tmp[1] # b'을 제거한 sensor_type 저장
        tmp = msg[1].split("'") # '을 제거하기 위한 split
        msg[1] = tmp[0] # '을 제거한 sensor_value 저장
        msg = "r_sensor, " + serial + ", " + msg[0] + ", " + msg[1] # 시리얼번호, 센서 타입, 센서 값 하나의 메시지로 합치기
        client.publish('r_sensor', msg)  # r_sensor 토픽에 msg 보내기
    elif (msg.find('/') != -1) : # equipmentSensorValue 토픽에 값이 들어온 경우
        msg = msg.split(" / ") # 슬래시(/)를 기준으로 값 나누기

        value = msg[0].split(", ") # 센서 값 (온도, 습도, 이산화탄소)
        standard = msg[1].split(", ") # 비교 기준 값 (min온도, max온도, min습도, max습도, min이산화탄소)
        status = None # 온도, 습도, 이산화탄소의 상태 값
        tmp = value[0].split("'") # b' 를 제거하기 위한 split
        value[0] = tmp[1] # b'가 제거된 온도값 저장
        tmp = standard[8].split("'") # ' 를 제거하기 위한 split
        standard[8] = tmp[0] # '가 제거된 이산화탄소 기준값 저장
        
        # 온도 상태
        if int(value[0]) < int(standard[0]) : # 온도가 min온도 보다 작을 때
            status = "1"
        elif int(value[0]) > int(standard[1]) : # 온도가 max온도 보다 클 때
            status = "2"
        else : # 온도가 적정 기준일 때
            status = "0"

        # 습도 상태
        if int(value[1]) < int(standard[2]) : # 습도가 min습도 보다 작을 때
            status += "1"
        elif int(value[1]) > int(standard[3]) : # 습도가 max습도 보다 클 때
            status += "2"
        else : # 습도가 적정 기준일 때
            status += "0"

        # 이산화탄소 상태
        if int(value[2]) < int(standard[4]):  # 이산화탄소가 min이산화탄소 보다 작을 때
            status += "1"
        else : # 이산화탄소가 적정 기준일 때
            status += "0"

        result = control(status) # status로 설비 제어하는 경우의수 (18가지)

        if result != "-1" :
            msg = result.split(", ") # 제어문 나누기
            # ESP32 한테 설비 제어 메시지 보내기
            for i in range(4) :
                client.publish('control', msg[i])  # control 토픽에 msg 보내기
                print("control publishing message: ", msg[i])
                time.sleep(2)

        # 토양수분도 값으로 스프링쿨러 제어
        if int(value[3]) > int(standard[5]) : # 토양수분도가 min토양수분도 보다 작을 때
            client.publish('control', "sm:1")  # control 토픽에 sm:1 보내기
            print("control publishing message: ", "sm:1")
        elif int(value[3]) < int(standard[6]) : # 토양수분도가 max토양수분도 보다 클 때
            client.publish('control', "sm:0")  # control 토픽에 sm:0 보내기
            print("control publishing message: ", "sm:0")

        # 조도 값으로 led 제어
        if int(value[4]) < int(standard[7]): # 토양수분도가 min토양수분도 보다 작을 때
            client.publish('control', "led:1")  # control 토픽에 led:1 보내기
            print("control publishing message: ", "led:1")
        elif int(value[4]) > int(standard[8]) : # 토양수분도가 max토양수분도 보다 클 때
            client.publish('control', "led:0")  # control 토픽에 led:0 보내기
            print("control publishing message: ", "led:0")
    else : # controlStatus 토픽에 값이 들어온 경우
        msg = msg.split(':') # 콜론(:)을 기준으로 센서 Type이랑 값 나누기
        tmp = msg[0].split("'") # b'을 제거하기 위한 split
        msg[0] = tmp[1] # b'을 제거한 sensor_type 저장
        tmp = msg[1].split("'") # '을 제거하기 위한 split
        msg[1] = tmp[0] # '을 제거한 sensor_value 저장
        msg = "equipmentStatus, " + serial + ", " + msg[0] + ", " + msg[1];  # 시리얼번호, 센서 타입, 센서 값 하나의 메시지로 합치기
        client.publish('equipmentStatus', msg)  # equipmentStatus 토픽에 msg 보내기

def control(s) :
    return {
        '000' : 'cold:0, hot:0, air:0, humi:1',
        '001' : 'cold:0, hot:0, air:1, humi:1',
        '020' : 'cold:0, hot:0, air:1, humi:1',
        '010' : 'cold:0, hot:0, air:0, humi:0',
        '021' : 'cold:0, hot:0, air:1, humi:1',
        '011' : 'cold:0, hot:0, air:0, humi:0',
        '200' : 'cold:1, hot:0, air:0, humi:1',
        '201' : 'cold:1, hot:0, air:0, humi:1',
        '220' : 'cold:1, hot:0, air:0, humi:1',
        '221' : 'cold:1, hot:0, air:0, humi:1',
        '210' : 'cold:1, hot:0, air:0, humi:0',
        '211' : 'cold:1, hot:0, air:0, humi:0',
        '100' : 'cold:0, hot:1, air:0, humi:1',
        '101' : 'cold:0, hot:1, air:0, humi:1',
        '120' : 'cold:0, hot:1, air:0, humi:1',
        '121' : 'cold:0, hot:1, air:0, humi:1',
        '110' : 'cold:0, hot:1, air:0, humi:0',
        '111' : 'cold:0, hot:1, air:0, humi:0',
    }.get(s, -1)

print("run equipmentControl.py") 

client.connect(ip, port=1883) # mqtt 브로커에 연결
client.on_connect = on_connect
client.on_message = on_message # 브로커로부터 메시지가 오면 동작하는 callback 함수
client.loop_forever() # 지속적으로 버퍼를 체크하고 이벤트를 발생 시키기
