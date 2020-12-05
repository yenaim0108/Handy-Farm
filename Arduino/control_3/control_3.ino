#include <WiFi.h>
#include <PubSubClient.h>
#include <sstream>      // std::stringstream


//LAPTOP-ImYena
//Iyn1023!@
//const char* ssid = "LAPTOP-ImYena";
//const char* password = "Iyn1023!@";
//const char* mqtt_server = "192.168.137.1";

const char* ssid = "hhyu2";
const char* password = "";
const char* mqtt_server = "192.168.100.15";

//const char* ssid = "HL0C";
//const char* password = "22042206";
//const char* mqtt_server = "192.168.0.10";

//const char* ssid = "Reborn";
//const char* password = "ming0218!";
//const char* mqtt_server = "192.168.0.2";

//const char* ssid = "MJ";
//const char* password = "mj0218!!!!";
//const char* mqtt_server = "192.168.43.227";

const int mqttPort = 1883;
const char* topic_pub = "controlStatus";
const char* topic_sub = "control";
const char* topic_handy = "controlhandy";


WiFiClient espClient;
PubSubClient client(espClient);
long lastMsg = 0;
char msg[50];
String packet;
int forsize = 8;
String handy = "수동";
String nonhandy = "자동";
const char* control_on[9]={"cold:1","hot:1","air:1","humi:1","led:1","sm:1","sair:1","8:1","\0"};
const char* control_off[9]={"cold:0","hot:0","air:0","humi:0","led:0","sm:0","sair:0","8:0","\0"};
String handystatus = "";
int Relaypin[8] = {4,5,13,16,15,14,17,18};
int a = 0;
//6,7,10 쓰면 안됨 131415161718
//지금 1번이 LED 2번이 난방 3번 팬 4번 수관 5번 가습 14qjs 16qjs


void setup() {
  int i=0;
  
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, mqttPort);
  client.setBufferSize(1024);
  for(i=0; i<forsize; i++){
    pinMode(Relaypin[i],OUTPUT);
  }

  for(i=0; i<8; i++){
    digitalWrite(Relaypin[i],HIGH);
  }
  digitalWrite(Relaypin[3],LOW);
  
  client.setCallback(callback);

}

void setup_wifi() {

  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

}


void callback(char* topic, byte* payload, unsigned int length) {

  char callmsg[9]={'\0','\0','\0','\0','\0','\0','\0','\0','\0'};
  String control_msg[9]={"cold:0","hot:0","air:0","humi:0","led:0","sm:0","7:0","8:0","\0"};
    
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
    callmsg[i] = (char)payload[i];
  }
  
  //기술문서는 100명이 보면 다 이해하도록
  //
  Serial.println();
  Serial.print(callmsg);
  Serial.println("------=---받아옴-------------");
  
  //우선 cmsg에 String 형태로 callmsg를 넣어줌
  String cmsg(callmsg);

  if(cmsg == handy){
    handystatus = cmsg;
  }
  
  if(cmsg == nonhandy){
    handystatus = cmsg;
  }
  
  Serial.println(handystatus);
  
  if(handystatus == nonhandy){
    Serial.println("Automatic mode started");
    if(!strcmp(topic,"control")){
      for(int i=0; i<forsize; i++){
        if(!strcmp(callmsg, control_on[i])){
          //digitalWrite(Relaypin[i],HIGH);//기본 1채널
          digitalWrite(Relaypin[i],LOW); //4채널 반전
          control_msg[i] = cmsg;
        }else if(!strcmp(callmsg, control_off[i])){
          //digitalWrite(Relaypin[i],LOW); //1채널 반전
          digitalWrite(Relaypin[i],HIGH);//기본 4채널
          control_msg[i] = cmsg;
        }
      }
    }
    Serial.println("Port toggled.");
  }

  if(handystatus == handy){
    Serial.println("Manual mode started");
    if(!strcmp(topic,"controlhandy")){
      for(int i=0; i<forsize; i++){
        if(!strcmp(callmsg, control_on[i])){
          //digitalWrite(Relaypin[i],HIGH);//기본 1채널
          digitalWrite(Relaypin[i],LOW); //4채널 반전
          control_msg[i] = cmsg;
        }else if(!strcmp(callmsg, control_off[i])){
          //digitalWrite(Relaypin[i],LOW); //1채널 반전
          digitalWrite(Relaypin[i],HIGH);//기본 4채널
          control_msg[i] = cmsg;
        }
      }
    }
    Serial.println("Port toggled.");
  }
  
    
  //mqtt_publish_msg_보냄
  //for(int i=0; i<forsize; i++){
  //  mqtt_publish(control_msg[i]);
  //}
}

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("ESP32Client")) {
      Serial.println("connected");
      // Once connected, publish an announcement...
      // ... and resubscribe
      //client.subscribe(topic_sub);
      client.subscribe(topic_handy);
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void mqtt_publish(String cmsg){
  //if (!client.connected()) {
  //  reconnect();
  //}
  //client.loop();
  
  packet = String(cmsg); 
  //문자열과 숫자를 합친다.
  packet.toCharArray(msg, 50); 
  //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
  //Serial.print("Publish message: ");
  Serial.println(msg);
  //Serial.println();
  client.publish(topic_pub, msg);
}



void loop() {
   if(!client.connected()) {
    reconnect();
  }
  client.loop();
 
}
