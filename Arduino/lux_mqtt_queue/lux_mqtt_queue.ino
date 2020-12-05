#include <WiFi.h>
#include <PubSubClient.h>
#include <Wire.h>  
#include <BH1750.h>  
#include <i2cdetect.h>
//#include "time.h"

BH1750 lightMeter;
#define COUNT 3
int luxArray[COUNT];

//const char* ssid = "HL0C";
//const char* password = "22042206";
//const char* mqtt_server = "192.168.0.55";

//이거 안됨 (핫스팟)
//const char* ssid = "MJ";
//const char* password = "mj0218!!!!";
//const char* mqtt_server = "192.168.0.2";

//const char* ssid = "Reborn";
//const char* password = "ming0218!";
//const char* mqtt_server = "192.168.0.2";

const char* ssid = "hhyu2";
const char* password = "";
const char* mqtt_server = "192.168.100.15";

//const char* ssid = "LAPTOP-ImYena";
//const char* password = "Iyn1023!@";
//const char* mqtt_server = "192.168.137.1";
const int mqttPort = 1883;
const char* mqttUser = "lux";
const char* mqttPassword = "1234";
const char* topic_pub = "sensor";
const char* topic_lux = "lux";
//const char* topic_call = "illuminance";

WiFiClient espClient;
PubSubClient client(espClient);
long lastMsg = 0;
char msg[50];
String packet, tm;
uint16_t lux;
int IDX =0;
int avglux;
char call;




void setup() {
  Serial.begin(9600);
  Wire.begin(22,21);//SCL,SDA
  Serial.println("Running...");
  Serial.print("Scanning address range 0x03-0x77\n\n");
  i2cdetect();
  lightMeter.begin();  
  setup_wifi();
  client.setServer(mqtt_server, mqttPort);
//  client.setCallback(callback);

  for(int i=0; i<COUNT; i++){
    luxArray[i] = 200;
  }
  
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

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("ESP32Client")) {
      Serial.println("connected");
      // Once connected, publish an announcement...
      // ... and resubscribe
      //client.subscribe(topic_call);
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


int getlux() { //lux를 받아오는 함수
  uint16_t h = lightMeter.readLightLevel();
  
  Serial.print("lux: ");
  Serial.print(h);
  return(h);
}

void mqtt_publish(int lux){
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;

    packet = "sunshine : " + String(lux); 
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    Serial.print("Publish message: ");
    Serial.println(msg);
    client.publish(topic_pub, msg);

    packet = String(lux)+" Lux"; 
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    client.publish(topic_lux, msg);
  }
}

void mqtt_publish2(int lux){
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;

    packet = String(lux); 
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    Serial.print("Publish message: ");
    Serial.println(msg);
//    client.publish(topic_call, msg);
  }
  delay(1000); //5초 단위로 Publishing (조정가능)
}



void loop() {
  int luxsum;
  
  lux = getlux(); //습도를 받아서 변수에 입력

  if(lux == 65534){
    luxArray[IDX] = luxArray[1];
  }else{
    luxArray[IDX] = lux;
  }
  
  IDX = ++IDX % COUNT;

  luxsum = 0;
  
  for(int i=0; i<COUNT; i++){
    luxsum += luxArray[i];
  }
    avglux = luxsum/COUNT;


  //Serial.print("avglux: "); Serial.print(avglux); Serial.println();
    
  mqtt_publish(avglux);
  //함수에 넣어서 해당 값을 통신을 통해서 전송한다.

  
  delay(2000);
}
