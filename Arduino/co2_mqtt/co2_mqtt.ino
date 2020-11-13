#include <WiFi.h>
#include <PubSubClient.h>
#include <sstream>      // std::stringstream
#include "RX9Simple.h"
#define EMF_pin 12   // RX-9 E with A0 of arduino
#define THER_pin 13  // RX-9 T with A1 of arduino
#define ADCvolt 3.3
#define ADCResol 4096
#define Base_line 432
#define meti 60  
#define mein 120 //Automotive: 120, Home or indoor: 1440
#define COUNT 3

int rxArray[COUNT];


//CO2 Step range
#define cr1  700      // Base_line ~ cr1
#define cr2  1000     // cr1 ~ cr2
#define cr3  2000     // cr2 ~ cr3
#define cr4  4000     // cr3 ~ cr4 and over cr4

// Thermister constant
// RX-9 have thermistor inside of sensor package. this thermistor check the temperature of sensor to compensate the data
// don't edit the number
#define C1 0.00230088
#define C2 0.000224
#define C3 0.00000002113323296
float Resist_0 = 15;

//Timing
unsigned int time_s = 0;
unsigned int time_s_prev = 0;
unsigned int time_s_set = 1;

extern volatile unsigned long timer0_millis;

//교수님 연구실 와파 비번 22042206
unsigned int co2_step = 0;
float EMF = 0;
float THER = 0;
RX9Simple RX9(Base_line, meti, mein, cr1, cr2, cr3, cr4);
const char* ssid = "HL0C";
const char* password = "22042206";
const char* mqtt_server = "192.168.0.55";

//const char* ssid = "LAPTOP-ImYena";
//const char* password = "Iyn1023!@";
//const char* mqtt_server = "192.168.137.1";

//const char* ssid = "Reborn";
//const char* password = "ming0218!";
//const char* mqtt_server = "192.168.0.2";

const int mqttPort = 1883;
const char* mqttUser = "DHT11";
const char* mqttPassword = "1234";
const char* topic_sensor = "sensor";
const char* topic_co2 = "co2";
const char* topic_sub = "messagebox2";

WiFiClient espClient;
PubSubClient client(espClient);
long lastMsg = 0;
char msg[50];
String packet;
int IDX =0;
int avg_co2;



//CO2 Value
int status_sensor = 0;


void setup() {
  Serial.begin(9600);
  setup_wifi();
  client.setServer(mqtt_server, mqttPort);
  client.setCallback(callback);

  for(int i=0; i<COUNT; i++){
    rxArray[i] = 1;
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
  Serial.print(topic_sub);
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
      client.subscribe(topic_sub);
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


float getrx() { //이산화탄소를 받아오는 함수
  // put your main code here, to run repeatedly:
  time_s = millis()/1000;
  if(time_s - time_s_prev >= time_s_set){
    time_s_prev = time_s;
    //every 1 sec
    //read EMF data from RX-9, RX-9 Simple START-->
    EMF = analogRead(EMF_pin);
    delay(1);
    EMF = EMF / (ADCResol - 1);
    EMF = EMF * ADCvolt;
    EMF = EMF / 6;
    EMF = EMF * 1000;
    // <-- read EMF data from RX-9, RX-9 Simple END
    
    //read THER data from RX-9, RX-9 Simple START-->
    THER = analogRead(THER_pin);
    delay(1);
    THER = 1/(C1+C2*log((Resist_0*THER)/(ADCResol-THER))+C3*pow(log((Resist_0*THER)/(ADCResol-THER)),3))-273.15;
    // <-- read THER data from RX-9, RX-9 Simple END
    
    status_sensor =  RX9.status_co2();//read status_sensor, status_sensor = 0 means warming up, = 1 means stable
    RX9.cal_co2(EMF,THER); //calculation carbon dioxide gas concentration. 
    co2_step = RX9.step_co2(); //read steps of carbon dioixde gas concentration. you can edit the step range with cr1~cr4 above.
    Serial.print("# "); //Starting letter
    if(status_sensor){
      // you can edit below code to change color of LED or text message by co2_step
      if(co2_step == 0){
      Serial.print("Step 1(~cr1)");
        //400 ppm ~cr1
      }
      else if(co2_step == 1){
        Serial.print("Step 2(cr1~cr2)");
        //cr1 ~ cr2 range
      }
      else if(co2_step == 2){
        Serial.print("Step 3(cr2~cr3)");
        //cr2 ~ cr3 range
      }
      else if(co2_step == 3){
        Serial.print("Step 4(cr3~cr4)");
        //cr3 ~ cr4 range
      }
      else{
        Serial.print("Step 5(cr4~)");
        //over cr4 range
      } 
    }
    else{
      Serial.print("Warming up"); 
      //it takes about 3 minutes to heating the sensor.
      //while warming up. data is not correct. 
    }   
    Serial.println(""); //CR LF
  }
  
    return(co2_step);
}


void mqtt_publish(int co2_step ){
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;

    // co2 통신 문자열과 숫자를 합친다.
    packet = "co2 : " + String(co2_step); 
    packet.toCharArray(msg, 50); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    Serial.print("Publish message: ");
    Serial.println(msg);
    client.publish(topic_sensor, msg);

    // co2 mqtt 따로
    if(co2_step == 0){//0단계면 worming up 전송
      packet = "Worm-up"; 
      packet.toCharArray(msg, 50); 
      Serial.print("topic_co2: ");
      Serial.println(msg);
      client.publish(topic_co2, msg);
    }else{//0이 아니면 단계전송
      packet = String(co2_step) + " 단계"; 
      packet.toCharArray(msg, 50); 
      Serial.print("topic_co2: ");
      Serial.println(msg);
      client.publish(topic_co2, msg);
    }

  }
}

void loop() {

int co2_sum;
  
  co2_step = getrx();//이산화탄소 단계를 받아옴
  
  rxArray[IDX] = co2_step;

  IDX = ++IDX % COUNT;

  co2_sum = 0;
  
  for(int i=0; i<COUNT; i++){
    co2_sum += rxArray[i];
  }
    avg_co2 = co2_sum/COUNT;
    
  Serial.print("avg_co2: "); Serial.print(avg_co2); Serial.println();
  mqtt_publish(avg_co2);// 값을 함수에 넣어서 해당 값을 통신을 통해서 전송한다.
  delay(2000);
}
