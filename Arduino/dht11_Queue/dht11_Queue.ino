#include <WiFi.h>
#include <PubSubClient.h>
#include <sstream>      // std::stringstream
#include "DHT.h"
#define DHTPIN 4 //DHT11을 4번핀에 연결한다.
#define DHTTYPE DHT11
#define COUNT 3
int HumiArray[COUNT] ;
int TempArray[COUNT];



//const char* ssid = "HL0C";
//const char* password = "22042206";
//const char* mqtt_server = "192.168.0.55";

//const char* ssid = "Reborn";
//const char* password = "ming0218!";
//const char* mqtt_server = "192.168.0.2";

//const char* ssid = "LAPTOP-ImYena";
//const char* password = "Iyn1023!@";
//const char* mqtt_server = "192.168.137.1";

const char* ssid = "hhyu2";
const char* password = "";
const char* mqtt_server = "192.168.100.15";

//const char* ssid = "MJ";
//const char* password = "mj0218!!!!";
//const char* mqtt_server = "192.168.0.2";
const int mqttPort = 1883;
const char* mqttUser = "DHT11";
const char* mqttPassword = "1234";
const char* topic_sensor = "sensor";
const char* topic_pub_Temp = "Temp";
const char* topic_pub_Humi = "Humi";
const char* topic_temp = "temperature";
const char* topic_humi = "humidity";
//const char* topic_sub = "control";

WiFiClient espClient;
PubSubClient client(espClient);
long lastMsg = 0;
char msg[50];
String packet;
float Humi,Temp;
int IDX =0;
float avgHumi, avgTemp;

DHT dht(DHTPIN, DHTTYPE);



void setup() {
  Serial.begin(115200);
  dht.begin();
  setup_wifi();
  client.setServer(mqtt_server, mqttPort);
  client.setCallback(callback);

  for(int i=0; i<COUNT; i++){
    HumiArray[i] = 40;
    TempArray[i] = 27;
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

void callback(char* topic, byte* payload, unsigned int length) {
   
  Serial.print("Message arrived [");
//  Serial.print(topic_sub);
  Serial.print("] ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
  Serial.println("-----------------------");

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
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


float getHumi() { //DHT11 습도를 받아오는 함수
  float h = dht.readHumidity();
  float t = dht.readTemperature();

  if (isnan(h) || isnan(t)) {
    Serial.println("Failed to read from DHT sensor!");
  }

  Serial.print("Humidity: ");
  Serial.print(h);
  Serial.print(" %\t");
  return(h);
}

float getTemp() {//DHT11 온도를 받아오는 함수
  
  float t = dht.readTemperature();

  Serial.print("Temperature: ");
  Serial.print(t);
  Serial.println(" *C ");

  return(t);
}

void mqtt_publish(float Humi, float Temp){
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;

    if(Humi == -715827840.00 || Humi == 715827904.00){
      
    }else if(Temp == -715827840.00 || Temp == 715827904.00){
        
    }else{
      //temp 먼저 통신
    //문자열과 숫자를 합친다.
    packet = "Temperature : " + String(Temp); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    packet.toCharArray(msg, 50);
    Serial.print("topic_sensor : ");
    Serial.println(msg);
    client.publish(topic_sensor, msg);

    //humi 통신
    packet = "Humidity : " + String(Humi); 
    packet.toCharArray(msg, 50);
    Serial.print("topic_sensor : ");
    Serial.println(msg);
    client.publish(topic_sensor, msg);


    //아래는 dashboard용
    //temp mqtt 따로
    packet = String(Temp) + "ºC"; 
    packet.toCharArray(msg, 50);
    Serial.print("topic_temp : ");
    Serial.println(msg);
    client.publish(topic_temp, msg);

    //값만 비교 제어 코드에 활용
    packet = String(Temp);
    packet.toCharArray(msg, 50);
    Serial.println(msg);
    client.publish(topic_pub_Temp, msg);

    //값만 비교 제어 코드에 활용 humi
    packet = String(Humi);
    packet.toCharArray(msg, 50);
    Serial.println(msg);
    client.publish(topic_pub_Humi, msg);

    //humi mqtt 따로

    packet = String(Humi) + "% "; 
    packet.toCharArray(msg, 50); 
    Serial.print("topic_humi : ");
    Serial.println(msg);
    client.publish(topic_humi, msg);
       
    }
  }
}

void loop() {
  int Humisum, Tempsum;
  
  Humi = getHumi(); //습도를 받아서 변수에 입력
  Temp = getTemp(); //온도를 받아서 변수에 입력

  if(Humi == -715827840.00 || Humi == 715827904.00){
    HumiArray[IDX] = HumiArray[1];
  }else{
    HumiArray[IDX] = Humi;
  }

  if(Temp == -715827840.00 || Temp == 715827904.00){
    TempArray[IDX] = TempArray[1];
  }else{
    TempArray[IDX] = Temp;
  }
  
  IDX = ++IDX % COUNT;

  Humisum = 0;
  Tempsum = 0;

  for(int i=0; i<COUNT; i++){
    Humisum += HumiArray[i];
    Tempsum += TempArray[i];
  }
    avgHumi = Humisum/COUNT;
    avgTemp = Tempsum/COUNT;


    Serial.print("avgHumi: "); Serial.print(avgHumi); Serial.println();
    Serial.print("avgTemp: "); Serial.print(avgTemp); Serial.println();
    
    mqtt_publish(avgHumi, avgTemp);// 온습도의 값을 함수에 넣어서 해당 값을 통신을 통해서 전송한다.
    
  delay(2000);
}
