package com.handyfarm.mqtt;
import java.sql.Timestamp;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmMQTT {
	// userName, brokerUrl, conOpt, client, timeInterval 멤버 변수 지정
	String userName = "HandyFarm";
	String clientId = "HandyFarm";
	String brokerUrl = "tcp://localhost:1883";
	MqttConnectOptions conOpt;
	MqttClient client;
	long timeInterval = 15000; // 15초
	
	// MQTT 연결하는 생성자
	public HandyFarmMQTT() {
		try {
			String tmpDir = System.getProperty("java.io.tmpdir");
			MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
			
			conOpt = new MqttConnectOptions();
			conOpt.setCleanSession(true);
			
			client = new MqttClient(brokerUrl, userName, dataStore); // 클라이언트 설정
			
			client.connect(conOpt); // mqtt 서버에 연결
			
			client.subscribe("harvestable"); // harvestable 토픽 구독
			client.subscribe("r_sensor"); // r_sensor 토픽 구독
			client.subscribe("equipmentStatus"); // equipmentStatus 토픽 구독
			
			System.out.println("Connecting to broker: " + brokerUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 값 받아서 DB에 저장하는 MQTT
	public Thread subMqtt = new Thread(() -> {
		try {
			client.setCallback( new MqttCallback() {
				String topicName;
				// 메세지가 도착했을 때 호출되는 콜백 함수
	            public void messageArrived(String topic, MqttMessage message) throws MqttException {
	               Timestamp time = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
	               String str = new String(message.getPayload()); // msg String 형으로 변환
	               String[] msg = str.split(", "); // msg 토픽 나누기
	               topicName = msg[0]; // 토픽 저장
	               String serial = msg[1]; // 시리얼 번호 저장
	               String equipment_name = null; // 설비 이름 저장
	               boolean control_status = false; // 설비 제어 상태 저장
	               HandyFarmDAO dao = new HandyFarmDAO(); // DB 연결
	               
	               if (topicName.equals("harvestable")) {
	            	   float harvestable = Float.parseFloat(msg[2]); // 수확 가능 비율 저장
	            	   
	                   dao.insertHarvestable(time, serial, harvestable); // harvestable 테이블에 데이터 넣기
	               } else if (topicName.equals("r_sensor")) {
	            	   String sensor_type = msg[2]; // 센서 Type 저장
	                   float sensor_value = Float.parseFloat(msg[3]); // 센서 값 저장
	                   
	                   dao.insertSensorValue(time, serial, sensor_type, sensor_value); // sensor_value 테이블에 데이터 넣기
	               } else if (topicName.equals("equipmentStatus")) {
	            	   switch (msg[2]) {
	            	   		case "cold" :
	            	   			equipment_name = "냉풍기";
	            	   			break;
	            	   		case "hot" :
	            	   			equipment_name = "온풍기";
	            	   			break;
	            	   		case "air" :
	            	   			equipment_name = "환풍기";
	            	   			break;
	            	   		case "humi" :
	            	   			equipment_name = "가습기";
	            	   			break;
	            	   		case "led" :
	            	   			equipment_name = "led";
	            	   			break;
	            	   		case "sm" :
	            	   			equipment_name = "수관";
	            	   }

	            	   switch (msg[3]) {
	            	   		case "1" :
	            	   			control_status = true;
	            	   			break;
	            	   		case "0" :
	            	   			control_status = false;
	            	   }
	            	   
	                   dao.equipmentStatusInsert(time, serial, equipment_name, control_status); // sensor_value 테이블에 데이터 넣기
	               }
	               
	               System.out.println(topicName + " message: " + Arrays.deepToString(msg));
	            }
				
				// 메세지가 전달되었을 때 호출되는 콜백 함수
	            public void deliveryComplete(IMqttDeliveryToken token) {}
	            
	            // 서버와 연결이 끊어지면 호출되는 콜백 함수
				public void connectionLost(Throwable cause) {
					System.out.println("Connection to " + "localhost" + topicName + "lost!" + cause); // 서버와 연결이 끊어졌다는 msg 출력
					System.exit(1); // 시스템 종료
				}
			});
		} catch (Exception e) {
	         e.printStackTrace();
		}
	});
	
	// 값을 보내는 MQTT
	public Thread pubMqtt = new Thread(() -> {
	      while (true) {
	         try {
	            String topic = "equipmentSensorValue";
	            String content = null;
	            
	            // DB 연결
	            HandyFarmDAO dao = new HandyFarmDAO();
	            
	            // 온도, 습도, 이산화탄소 값 가져오기
	            content = dao.equipmentSensorValueSelect("10000000c366d002");
	            
	            MqttMessage message = new MqttMessage(content.getBytes()); // String을 Byte 단위로 변환하기
	            client.publish(topic, message); // topic에 메시지 보내기
	            System.out.println("Publishing message: " + content);
	            } catch (MqttException me) {
	               System.out.println("reason " + me.getReasonCode());
	               System.out.println("msg " + me.getMessage());
	               System.out.println("loc " + me.getLocalizedMessage());
	               System.out.println("cause " + me.getCause());
	               System.out.println("excep " + me);
	               me.printStackTrace();
	         }
	         
	         // timeInterval 만큼 sleep
	         try {
	            Thread.sleep(timeInterval);
	         } catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	   });
}
