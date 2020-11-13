#define uS_TO_S_FACTOR 1000000  //Conversion factor for micro seconds to seconds
#define TIME_TO_SLEEP  30        //Time ESP32 will go to sleep (in seconds)

RTC_DATA_ATTR int bootCount = 0;

void setup(){
 Serial.begin(115200);
 delay(1000); //Take some time to open up the Serial Monitor

 //Increment boot number and print it every reboot
 ++bootCount;
 Serial.println("Boot number: " + String(bootCount));

 //Print the wakeup reason for ESP32
 print_wakeup_reason();

 //Set timer to 5 seconds
 esp_sleep_enable_timer_wakeup(TIME_TO_SLEEP * uS_TO_S_FACTOR);
 Serial.println("Setup ESP32 to sleep for every " + String(TIME_TO_SLEEP) +
 " Seconds");

 //Go to sleep now
 esp_deep_sleep_start();
}

void loop(){}

//Function that prints the reason by which ESP32 has been awaken from sleep
void print_wakeup_reason(){
 esp_sleep_wakeup_cause_t wakeup_reason;
 wakeup_reason = esp_sleep_get_wakeup_cause();
 switch(wakeup_reason)
 {
  case 1  : Serial.println("Wakeup caused by external signal using RTC_IO"); break;
  case 2  : Serial.println("Wakeup caused by external signal using RTC_CNTL"); break;
  case 3  : Serial.println("Wakeup caused by timer"); break;
  case 4  : Serial.println("Wakeup caused by touchpad"); break;
  case 5  : Serial.println("Wakeup caused by ULP program"); break;
  default : Serial.println("Wakeup was not caused by deep sleep"); break;
 }
}
