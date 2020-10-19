import paho.mqtt.client as mqtt

ip = "192.168.137.1" # 서버 ip 주소
client = mqtt.Client("RaspverryPI") # mqtt.Client() 인스턴스 생성   

def topicSub() :
    client.connect(ip) # mqtt 브로커에 연결
    
    # JSP한테 현재 센서값, 기준 센서값 받아오기
    client.subscribe('equipmentSensorValue') # equipmentSensorValue 토픽 구독
    # ESP32한테 설비, 설비 상태 받아오기
    client.subscribe('controlStatus') # controlStatus 토픽 구독

    client.on_message = equipmentContorl # 브로커로부터 메시지가 오면 동작하는 callback 함수
    client.loop_forever() # 지속적으로 버퍼를 체크하고 이벤트를 발생 시키키

def equipmentContorl(client, userdata, msg) :
    # equipmentSensorValue 토픽에 값이 들어온 경우
    if (msg.find(':') != -1) :
        msg = str(msg.payload) # 값 받기
        msg = msg.split(" / ") # 슬래시(/)를 기준으로 값 나누기

        value = msg[0].split(", ") # 센서 값 (온도, 습도, 이산화탄소)
        standard = msg[1].split(", ") # 비교 기준 값 (min온도, max온도, min습도, max습도, min이산화탄소)
        status = None # 온도, 습도, 이산화탄소의 상태 값
        tmp = value[0].split("'") # b' 를 제거하기 위한 split
        value[0] = tmp[1] # b'가 제거된 온도값 저장
        tmp = standard[4].split("'") # ' 를 제거하기 위한 split
        standard[4] = tmp[0] # '가 제거된 이산화탄소 기준값 저장
        
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
                print("Publishing message: ", msg[i])
    # controlStatus 토픽에 값이 들어온 경우
    else : 
        msg = str(msg.payload) # 센서 Type이랑 값 받기
        msg = msg.split(' : ') # 콜론(:)을 기준으로 센서 Type이랑 값 나누기
        tmp = msg[0].split("'") # b'을 제거하기 위한 split
        msg[0] = tmp[1] # b'을 제거한 sensor_type 저장
        tmp = msg[1].split("'") # '을 제거하기 위한 split
        msg[1] = tmp[0] # '을 제거한 sensor_value 저장
        msg = "10000000c366d002, " + msg[0] + ", " + msg[1];  # 시리얼번호, 센서 타입, 센서 값 하나의 메시지로 합치기
        client.publish('equipmentStatus', msg)  # sensor 토픽에 msg 보내기

def control(s) :
    return {
        '000' : 'cold:0, hot:0, air:0, humi:0',
        '001' : 'cold:0, hot:0, air:1, humi:0',
        '020' : 'cold:0, hot:0, air:1, humi:0',
        '010' : 'cold:0, hot:0, air:0, humi:1',
        '021' : 'cold:0, hot:0, air:1, humi:0',
        '011' : 'cold:0, hot:0, air:0, humi:1',
        '200' : 'cold:1, hot:0, air:0, humi:0',
        '201' : 'cold:1, hot:0, air:0, humi:0',
        '220' : 'cold:1, hot:0, air:0, humi:0',
        '221' : 'cold:1, hot:0, air:0, humi:0',
        '210' : 'cold:1, hot:0, air:0, humi:1',
        '211' : 'cold:1, hot:0, air:0, humi:1',
        '100' : 'cold:0, hot:1, air:0, humi:0',
        '101' : 'cold:0, hot:1, air:0, humi:0',
        '120' : 'cold:0, hot:1, air:0, humi:0',
        '121' : 'cold:0, hot:1, air:0, humi:0',
        '110' : 'cold:0, hot:1, air:0, humi:1',
        '111' : 'cold:0, hot:1, air:0, humi:1',
    }.get(s, -1)

print("run equipmentControl.py") 

# 토픽 구독
topicSub()