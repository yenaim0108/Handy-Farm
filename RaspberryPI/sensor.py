import paho.mqtt.client as mqtt

def mqtt() :
    broker_address = '192.168.137.103' # 브로커 IP 주소
    client = mqtt.Client("RaspverryPI") # mqtt.Client() 인스턴스 생성
    client.connect(broker_address) # 브로커에 연결
    client.subscribe('sensor') # 토픽 구독
    client.on_message = sensor # 브로커로부터 메시지가 오면 동작하는 callback 함수
    client.loop_forever() # 지속적으로 버퍼를 체크하고 이벤트를 발생 시키키

def sensor() :
    