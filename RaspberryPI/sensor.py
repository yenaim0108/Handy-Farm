import paho.mqtt.client as mqtt

ip = "192.168.137.1" # 서버 ip 주소

def mqtt_sub() :
    print("run sensor.py")
    broker_address = ip # 브로커 IP 주소
    client = mqtt.Client("RaspverryPI") # mqtt.Client() 인스턴스 생성
    client.connect(broker_address) # 브로커에 연결
    client.subscribe('sensor') # 토픽 구독
    client.on_message = sensor # 브로커로부터 메시지가 오면 동작하는 callback 함수
    client.loop_forever() # 지속적으로 버퍼를 체크하고 이벤트를 발생 시키키

def sensor(client, userdata, msg) :
    msg = str(msg.payload) # 센서 Type이랑 값 받기
    msg = msg.split(' : ') # 콜론(:)을 기준으로 센서 Type이랑 값 나누기
    tmp = msg[0].split("'") # b'을 제거하기 위한 split
    msg[0] = tmp[1] # b'을 제거한 sensor_type 저장
    tmp = msg[1].split("'") # '을 제거하기 위한 split
    msg[1] = tmp[0] # '을 제거한 sensor_value 저장
    mqtt_pub("10000000c366d002", msg[0], msg[1]) # 서버한테 값 보내기

def mqtt_pub(serial, stype, svalue) :
    client = mqtt.Client()  # mqtt.Client() 인스턴스 생성
    client.connect(ip, 1883)  # 브로커에 연결
    msg = serial + ", " + stype + ", " + svalue;  # 시리얼번호, 센서 타입, 센서 값 하나의 메시지로 합치기
    client.publish('sensor', msg)  # sensors 토픽에 msg 보내기
    client.disconnect()  # 연결 종료

# ESP32한테 센서 Type, 값 받아오기
mqtt_sub()