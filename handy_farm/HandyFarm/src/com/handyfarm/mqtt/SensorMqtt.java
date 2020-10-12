package com.handyfarm.mqtt;
import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import com.handyfarm.dao.HandyFarmDAO;

public class SensorMqtt {
	private static String userName = "HandyFarm";
	private static String brokerUrl = "tcp://localhost:1883";
	private static String topicName = "sensor";
	
	public static void main(String[] args) throws Exception {
		if(args.length > 1)
			brokerUrl = "tcp://" + args[0] + ":1883";
		
		String tmpDir = System.getProperty("java.io.tmpdir");
		MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
		
		String uri = System.getenv("CLOUDMQTT_URL");
		
		MqttConnectOptions conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(true);
		
		MqttClient client = new MqttClient(brokerUrl, userName, dataStore); // 클라이언트 설정
		
		// 콜백 함수
		client.setCallback( new MqttCallback() {
			// 메세지가 도착했을 때 호출되는 콜백 함수
			public void messageArrived(String topic, MqttMessage message) throws MqttException {
				Timestamp time = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
				// DB 연결
				HandyFarmDAO dao = new HandyFarmDAO();

				// sensor_value 테이블에 데이터 넣기
			}
			
			// 메세지가 전달되었을 때 호출되는 콜백 함수
			public void deliveryComplete(IMqttDeliveryToken token) {}
			
			// 서버와 연결이 끊어지면 호출되는 콜백 함수
			public void connectionLost(Throwable cause) {
				System.out.println("Connection to " + "localhost" + " lost!" + cause); // 서버와 연결이 끊어졌다는 msg 출력
				System.exit(1); // 시스템 종료
			}
		});
			
		client.connect(conOpt); // mqtt 서버에 연결
		
		MqttMessage msg = new MqttMessage("Thankyou".getBytes());
		
		int qos = 1 ; 
		msg.setQos(qos);
	
		client.publish(topicName, msg);
	
		log("Subscribing to topic \"" + topicName + "\" qos " + qos);
		client.subscribe(topicName, qos); // 토픽 구독
	} // end main
	
	public static void log(String log) {
		System.out.println("[Debug]" + log);
	}
	
	static void printHelp() {
		System.out.println("Syntax:\n\n" + "    Sample [-h] [-a publish|subscribe] [-t <topic>] [-m <message text>]\n"
				+ "            [-s 0|1|2] -b <hostname|IP address>] [-p <brokerport>] [-i <clientID>]\n\n"
				+ "    -h  Print this help text and quit\n" + "    -q  Quiet mode (default is false)\n"
				+ "    -a  Perform the relevant action (default is publish)\n"
				+ "    -t  Publish/subscribe to <topic> instead of the default\n"
				+ "            (publish: \"Sample/Java/v3\", subscribe: \"Sample/#\")\n"
				+ "    -m  Use <message text> instead of the default\n"
				+ "            (\"Message from MQTTv3 Java client\")\n"
				+ "    -s  Use this QoS instead of the default (2)\n"
				+ "    -b  Use this name/IP address instead of the default (localhost)\n"
				+ "    -p  Use this port instead of the default (1883)\n\n"
				+ "    -i  Use this client ID instead of SampleJavaV3_<action>\n"
				+ "    -c  Connect to the server with a clean session (default is false)\n"
				+ "     \n\n Security Options \n" + "     -u Username \n" + "     -z Password \n"
				+ "     \n\n SSL Options \n" + "    -v  SSL enabled; true - (default is false) "
				+ "    -k  Use this JKS format key store to verify the client\n"
				+ "    -w  Passpharse to verify certificates in the keys store\n"
				+ "    -r  Use this JKS format keystore to verify the server\n"
				+ " If javax.net.ssl properties have been set only the -v flag needs to be set\n"
				+ "Delimit strings containing spaces with \"\"\n\n"
				+ "Publishers transmit a single message then disconnect from the server.\n"
				+ "Subscribers remain connected to the server and receive appropriate\n"
				+ "messages until <enter> is pressed.\n\n");
	}
}