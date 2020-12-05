#include <WiFi.h>
#include <PubSubClient.h>
#include <OneWire.h>
#include <DallasTemperature.h>
//#include <sstream>      // std::stringstream
#define senpin 32
#define temppin 15
#define COUNT 3

//연구실
//const char* ssid = "HL0C";
//const char* password = "22042206";
//const char* mqtt_server = "192.168.0.55";

//집
//const char* ssid = "Reborn";
//const char* password = "ming0218!";
//const char* mqtt_server = "192.168.0.2";


const char* ssid = "hhyu2";
const char* password = "";
const char* mqtt_server = "192.168.100.15";

//라즈베리파이
//const char* ssid = "LAPTOP-ImYena";
//const char* password = "Iyn1023!@";
//const char* mqtt_server = "192.168.137.1";

//요거 안댐
//const char* ssid = "MJ";
//const char* password = "mj0218!!!!";
//const char* mqtt_server = "192.168.0.2";


const int mqttPort = 1883;
const char* mqttUser = "SEN0193";
const char* mqttPassword = "1234";
const char* topic_pub = "sensor";
const char* topic_Moisture = "soil-moisture";
const char* topic_Moisture_num = "soil-moisturenum";
const char* topic_Solitemp = "soil-Temp";

WiFiClient espClient;
PubSubClient client(espClient);
OneWire oneWire(temppin);
DallasTemperature DS18B20(&oneWire);
long lastMsg = 0;
char msg[50];
String packet;
float SMoisture,STemp;
int IDX =0;
float avgSMoisture,avgSTemp;


int SMoistureArray[COUNT];
int STempArray[COUNT];

void setup() {
  Serial.begin(9600);
  setup_wifi();
  DS18B20.begin();
  client.setServer(mqtt_server, mqttPort);
  //client.setCallback(callback);

  //토양수분도와 토양온도에 초반 값 설정 -- 카운트 후 제대로 보정된 값 출력
  for(int i=0; i<COUNT; i++){
    SMoistureArray[i] = 1780;
    STempArray[i] = 25;
  }
}

//와이파이 연결코드
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
  Serial.println("avgMoisture");
}

//통신 실패시 다시 통신
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

//토양수분도
float getSMoisture() {
  
  float t = analogRead(senpin);

  return(t);
}

//토양온도
void read_temperature() {
    float temp;
    do {
       DS18B20.requestTemperatures();
       temp = DS18B20.getTempCByIndex(0);
       Serial.print("Temperature: ");
       Serial.println(temp);
    } while (temp == 85.0 || temp == (-127.0));
}

//통신 코드
void mqtt_publish(float SMoisture, float STemp){
  String Moisturelevel;
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;

    packet = "soil-moisture:" + String(SMoisture); 
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    Serial.println(msg);
    client.publish(topic_pub, msg);

    if(SMoisture >= 0 && SMoisture <=2200){ //0~2200의 저항 값이면 굉장히 젖은 상태
       Moisturelevel = "Very Wet";
    }else if(SMoisture >= 2201 && SMoisture <= 2600){ // 적정한 상태
      Moisturelevel = "Wet";
    }else{ //그 이상이면 마른 상태
      Moisturelevel = "Dry";
    }
    packet = String(Moisturelevel); //대시보드용 상태표기
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    Serial.print("Publish message: "); Serial.println(msg);
    client.publish(topic_Moisture, msg);

    packet = String(SMoisture); //대시보드 그래프용 값
    packet.toCharArray(msg, 50); 
    Serial.println(msg);
    client.publish(topic_Moisture_num, msg);

    packet = "Soil-Temp:" + String(STemp); 
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    //mqtt publishing이 char형으로만 보낼 수 있기때문에 toCharArray로 변환한다.
    Serial.print("Publish message: ");Serial.println(msg);
    client.publish(topic_pub, msg);

    packet = String(STemp); 
    //문자열과 숫자를 합친다.
    packet.toCharArray(msg, 50); 
    //Serial.print("Publish message: "); Serial.println(msg);
    client.publish(topic_Solitemp, msg);
    
  }
}

void loop() {
  int SMoisturesum, STempsum;
  
  SMoisture = getSMoisture(); //토양수분도를 받아서 변수에 입력
  read_temperature();         //토양온도를 받아서 변수에 입력

  DS18B20.requestTemperatures();
  STemp = DS18B20.getTempCByIndex(0);

  //---------- 순환 큐 ------------
  SMoistureArray[IDX] = SMoisture;
  STempArray[IDX] = STemp;

  IDX = ++IDX % COUNT;  //순환 큐를 위해 주소를 순환

  SMoisturesum = 0;     //누적 변수 초기화
  STempsum = 0;         //누적 변수 초기화

  //이동평균법 구현
  for(int i=0; i<COUNT; i++){   
    SMoisturesum += SMoistureArray[i];
    STempsum += STempArray[i];
  }
    avgSMoisture = SMoisturesum/COUNT;  //누적 값을 평균
    avgSTemp = STempsum/COUNT;          //누적 값을 평균


    //통신
    mqtt_publish(avgSMoisture, avgSTemp);
       
  delay(1000);
}
