import paho.mqtt.client as mqtt

client = mqtt.Client()
client.connect('192.168.137.103', 1883)

client.publish('ask_illuminance', 'call')

client.disconnect()