import 'dart:ui';

double deviceScale = setDeviceScaler();

// 화면 해상도별 deviceScale 지정하는 함수
double setDeviceScaler() {
  double devicePoints; // 실제 디바이스 화소
  int isSmartphone; // 스마트폰인지 아닌지 확인

  // 스마트폰인지 태블릿/노트북/컴퓨터인지 확인
  if (window.physicalSize.height > window.physicalSize.width) {
    devicePoints = (window.physicalSize.height / window.devicePixelRatio);
    isSmartphone = 1;
  }
  else {
    devicePoints = (window.physicalSize.width / window.devicePixelRatio);
    isSmartphone = 0;
  }

  // 실제 디바이스 화소를 기준으로 deviceScale 지정
  switch (isSmartphone) {
    case 1 : // 스마트폰일 때
    case 1 : // 스마트폰일 때
      if (devicePoints <= 720.0) {
        deviceScale = 0.8;
      }
      else if (devicePoints <= 900.0) {
        deviceScale = 1.0;
      }
      else if (devicePoints <= 1080.0) {
        deviceScale = 1.2;
      }
      else if (devicePoints <= 1440.0) {
        deviceScale = 1.6;
      }
      break;
    case 0 : // 스마트폰이 아닐 때
      if (devicePoints <= 720.0) {
        deviceScale = 0.49;
      }
      else if (devicePoints <= 900.0) {
        deviceScale = 0.61;
      }
      else if (devicePoints <= 1080.0) {
        deviceScale = 0.74;
      }
      else if (devicePoints <= 1440.0) {
        deviceScale = 0.99;
      }
  }

  // 변수 값 출력
  print('is Smartphone? : ' + isSmartphone.toString() + '\nDevice Points : ' + devicePoints.toString() + '\nDevice Scale : ' + deviceScale.toString());

  return deviceScale;
}