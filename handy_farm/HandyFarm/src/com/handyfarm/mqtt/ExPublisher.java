package com.handyfarm.mqtt;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ExPublisher{

	public static void main(String[] args) {

		String topic = "mood";
		String content = null;
		int qos = 1;
		String broker = "tcp://localhost:1883";
		String clientId = "temporary-device";
		MemoryPersistence persistence = new MemoryPersistence();

		if(args.length > 1)
			broker = "tcp://"+args[0] + ":1883";
		
		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			
			System.out.println("Connecting to broker: " + broker);
			
			sampleClient.connect(connOpts);
			
			System.out.println("Connected");
			
			System.out.print("Input a Message : ");
			Scanner sc = new Scanner(System.in);
			content = sc.nextLine();
		
			System.out.println("Publishing message: " + content);
			
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			sampleClient.publish(topic, message);
			
			System.out.println("Message published");
			
			sampleClient.disconnect();
			
			System.out.println("Disconnected");
			System.exit(0);
			
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}