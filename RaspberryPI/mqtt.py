import socket
import struct
import paho.mqtt.client as mqtt

def on_connect(client, userdata, flags, rc):
    print("Connected with result codde " + str(rc))
    client.subscribe("mood1")  # 채널 구독

def on_message(client, userdata, msg):
    print(msg.topic + " " + str(msg.payload))

port = 1883

client = mqtt.Client('RaspberryPI')
client.on_connect = on_connect
client.on_message = on_message

client.connect('192.168.137.103', port, 60)
client.loop_forever()