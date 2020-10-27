package com.handyfarm.mqtt;
import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.*;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmMQTT {
	// userName, brokerUrl, conOpt, client, timeInterval 멤버 변수 지정
	String userName = "HandyFarm";
	String clientId = "HandyFarm";
	String brokerUrl = "tcp://localhost:1883";
	MqttConnectOptions conOpt;
	MqttClient client;
	long timeInterval = 10000; // 10초
	
	// MQTT 연결하는 생성자
	public HandyFarmMQTT() {
		try {
			String tmpDir = System.getProperty("java.io.tmpdir");
			MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
			
			conOpt = new MqttConnectOptions();
			conOpt.setCleanSession(true);
			
			client = new MqttClient(brokerUrl, userName, dataStore); // 클라이언트 설정
			
			client.connect(conOpt); // mqtt 서버에 연결
			
			System.out.println("Connecting to broker: " + brokerUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로보가 보낸 수확 가능 비율을 가져와서 DB에 저장하는 MQTT
	public Thread harvestableMqtt = new Thread(() -> {
		try {
			String topicName = "harvestable";
			
			System.out.println("[Debug] Subscribing to topic \"" + topicName);
			client.subscribe(topicName); // 토픽 구독
			
			// 콜백 함수
			client.setCallback( new MqttCallback() {
				// 메세지가 도착했을 때 호출되는 콜백 함수
				public void messageArrived(String topic, MqttMessage message) throws MqttException {
					Timestamp time = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
					String str = new String(message.getPayload()); // msg String 형으로 변환
					String[] msg = str.split(", "); // 시리얼 번호와 수확 가능 비율 나누기
					String serial = msg[0]; // 시리얼 번호 저장
					float harvestable = Float.parseFloat(msg[1]); // 수확 가능 비율 저장
					System.out.println(msg);
					
					// DB 연결
					HandyFarmDAO dao = new HandyFarmDAO();
					
					// harvestable 테이블에 데이터 넣기
					dao.insertHarvestable(time, serial, harvestable);
				}
				
				// 메세지가 전달되었을 때 호출되는 콜백 함수
				public void deliveryComplete(IMqttDeliveryToken token) {}
				
				// 서버와 연결이 끊어지면 호출되는 콜백 함수
				public void connectionLost(Throwable cause) {
					System.out.println("Connection to " + "localhost " + topicName + " lost!" + cause); // 서버와 연결이 끊어졌다는 msg 출력
					System.exit(1); // 시스템 종료
				}
			});
		} catch (Exception e) {
			HandyFarmMQTT mqtt = new HandyFarmMQTT();
			mqtt.harvestableMqtt.start();
			
			e.printStackTrace();
		}

	});
	
	// 로보가 보낸 센서값을 가져와서 DB에 저장하는 MQTT
	public Thread sensorMqtt = new Thread(() -> {
		try {
			String topicName = "sensor";
			
			System.out.println("[Debug] Subscribing to topic \"" + topicName);
			client.subscribe(topicName); // 토픽 구독
			
			// 콜백 함수
			client.setCallback( new MqttCallback() {
				// 메세지가 도착했을 때 호출되는 콜백 함수
				public void messageArrived(String topic, MqttMessage message) throws MqttException {
					Timestamp time = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
					String str = new String(message.getPayload()); // msg String 형으로 변환
					System.out.println(str);
					String[] msg = str.split(", "); // 시리얼 번호, 센서 Type, 센서 값 나누기
					String serial = msg[0]; // 시리얼 번호 저장
					String sensor_type = msg[1]; // 센서 Type 저장
					float sensor_value = Float.parseFloat(msg[2]); // 센서 값 저장
					
					// DB 연결
					HandyFarmDAO dao = new HandyFarmDAO();
					
					// sensor_value 테이블에 데이터 넣기
					dao.insertSensorValue(time, serial, sensor_type, sensor_value);
				}
				
				// 메세지가 전달되었을 때 호출되는 콜백 함수
				public void deliveryComplete(IMqttDeliveryToken token) {}
				
				// 서버와 연결이 끊어지면 호출되는 콜백 함수
				public void connectionLost(Throwable cause) {
					System.out.println("Connection to " + "localhost " + topicName + " lost!" + cause); // 서버와 연결이 끊어졌다는 msg 출력
					System.exit(1); // 시스템 종료
				}
			});
		} catch (Exception e) {
			HandyFarmMQTT mqtt = new HandyFarmMQTT();
			mqtt.sensorMqtt.start();
			
			e.printStackTrace();
		}
	});
	
	// 10초마다 한 번씩 로보에게 현재 온도, 습도, 이산화탄소 값과 설비 제어 기준값 보내는 MQTT
	public Thread equipmentControlMqtt = new Thread(() -> {
		while(true) {
			try {
				String topic = "equipmentSensorValue";
				String content = null;
				
				// DB 연결
				HandyFarmDAO dao = new HandyFarmDAO();
				
				// 온도, 습도, 이산화탄소 값 가져오기
				content = dao.equipmentSensorValueSelect("10000000c366d002");
				System.out.println("Publishing message: " + content);
				
				MqttMessage message = new MqttMessage(content.getBytes()); // String을 Byte 단위로 변환하기
				client.publish(topic, message); // topic에 메시지 보내기
				System.out.println("Message published");
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
			} catch(InterruptedException e) {
				HandyFarmMQTT mqtt = new HandyFarmMQTT();
				mqtt.equipmentControlMqtt.start();
				
				e.printStackTrace();
			}
		}
	});
	
	// ESP한테 설비 상태값을 받아서 DB에 저장하는 MQTT
	public Thread equipmentStatusMqtt = new Thread(() -> {
		try {				
			String topicName = "equipmentStatus";
			
			
			System.out.println("[Debug] Subscribing to topic \"" + topicName);
			client.subscribe(topicName); // 토픽 구독
			
			// 콜백 함수
			client.setCallback( new MqttCallback() {
				// 메세지가 도착했을 때 호출되는 콜백 함수
				public void messageArrived(String topic, MqttMessage message) throws MqttException {
					Timestamp time = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
					String str = new String(message.getPayload()); // msg String 형으로 변환
					System.out.println("나능 바보가 아니야!" + str);
					String[] msg = str.split(", "); // 시리얼 번호, 센서 Type, 센서 값 나누기
					
					String serial = msg[0]; // 시리얼 번호 저장
					String equipment_name = null; // 변수 초기화
					if (msg[1].equals("cold")) {
						equipment_name = "냉풍기";
					} else if (msg[1].equals("hot")) {
						equipment_name = "온풍기";
					} else if (msg[1].equals("air")) {
						equipment_name = "환풍기";
					} else if (msg[1].equals("humi")) {
						equipment_name = "가습기";
					} else {
						equipment_name = "led";
					}
					boolean control_status = Boolean.parseBoolean(msg[2]); // 제어 상태 저장
					
					// DB 연결
					HandyFarmDAO dao = new HandyFarmDAO();
					
					// sensor_value 테이블에 데이터 넣기
					dao.equipmentStatusInsert(time, serial, equipment_name, control_status);;
				}
				
				// 메세지가 전달되었을 때 호출되는 콜백 함수
				public void deliveryComplete(IMqttDeliveryToken token) {}
				
				// 서버와 연결이 끊어지면 호출되는 콜백 함수
				public void connectionLost(Throwable cause) {
					System.out.println("Connection to " + "localhost " + topicName + " lost!" + cause); // 서버와 연결이 끊어졌다는 msg 출력
					System.exit(1); // 시스템 종료
				}
			});
		} catch (Exception e){
			HandyFarmMQTT mqtt = new HandyFarmMQTT();
			mqtt.equipmentStatusMqtt.start();
			
			e.printStackTrace();
		}
	});
}
