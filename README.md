## 농업 활성화를 위한 작은 스마트팜 'Handy Farm'

- **프로젝트 개요**
  - 오늘날 대부분의 농가들은 스마트하고 간편하게 농장을 관리하기 원한다.
  **하지만, 스마트팜이라는 시스템을 도입하고 그에 맞는 온실을 새로 짓고 고가의 비용들을 감수하기란 불가능한 경우가 많고,** 가능하다고 할지라도 부담이 되어 쉽게 결정할 수 없는 부분이다.
  또한, 이런 스마트팜은 농가의 빈익빈 부익부 구조를 야기할 수 있다고 생각해 현재 농사 조합들은 스마트팜 단지 등의 건설을 반대하는 추세다.
  좋은 걸 알지만, **대부분의 농가들이 시행할 수 없기에 반대할 수밖에 없는 아이러니한 상황**이 온 것이다.
  우리는 이런 모순적인 부분들을 해결하고 농가들이 부담할 수 있는 비용으로 널리 보급할 수 있으며, 스마트하게 농장을 관리할 수 있는, 농가들에게 실질적으로 도움이 될 수 있는 **새로운 형태의 스마트팜 도입의 필요성을 느꼈다.**
  
- **프로젝트 소개**
  - **‘Handy Farm’ 프로젝트**는 비닐하우스를 일부 개조하는 형태로 스마트 생장 로봇을 제작해 센서와 센싱하여 생장 환경 **정보를 수치화해 제공**하고, 농작물을 인식해 **수확량을 예측**하며
  수확시기가 되면 사용자에게 알려준다. **시기별 수확량 통계**를 보여주고, 여러 농사 정보들을 제공함으로써 감으로 농사를 짓던 기존 농가들에게는 **명확한 농사 체계가 잡히도록 도움을 주며,**
  초심자들에겐 방향성을 제시해 줄 수 있어 **농사의 접근성이 낮아지고 나아가 농업 활성화를 기대**해 볼 수 있다.<br>
  **‘Handy Farm’은 농가들이 부담 가능한 비용으로 스마트하게 농장을 관리할 수 있게 할 프로젝트이다.**
- **주요기능**
  기능 | 설명
  :---:|---
  생장환경 구축 | ・ 아두이노와 여러 센서들을 연결해 측정된 정보들을 가져와 사용자가 지정한 환경으로 설정.<br>・ 어플에서 원하는 농작물을 선택하면 생장환경에 맞게 자동으로 설정되는 기능을 구축해 편리함 제공.<br>・ 사용자들에게 모든 정보들을 수치화하여 나타내 명확한 정보를 제공함.
  수확 가능 과실 인식<br>및 알림 | ・ 카메라로 과실의 크기와 색상을 인식하여 수확 가능한 과실의 비율을 보여주고, 일정 비율 이상 시 알림.
  시기별 수확량 및<br> 농사 관련 정보 제공 | ・ 빅데이터를 이용하여 농작물의 수확량을 통계 내어 보여줌.<br>・ 통계된 자료를 시기별로 볼 수 있게 하여 간편하게 비교 가능.<br>・ 농작물 각각의 비료시기와 기본적인 수확 시기를 제공.
  
- **핵심기술**
  기술 | 설명
  :---:|---
  라즈베리 파이 및 센싱 | ・ 하드웨어에 연결된 여러 센서들이 생장 환경을 측정함.
  이미지 인식 딥러닝 | ・ 이미지 기반 이진 분류 기법을 이용해 농작물의 크기와 색상을 측정함.<br>・ 지속적으로 이미지 데이터를 학습시켜 이미지 인식의 정확도를 향상시킴.
  빅데이터 | ・ 시기별 수확량과 생장 환경을 비교 · 통계를 내서 사용자에게 보여줌.
  3D Print | ・ 스마트 생장 로봇의 외형 구현
  
- **예상결과물**
  예상결과물 이미지 | 설명
  :---:|---
  ![센싱을 통한 정보 제공 및 분석정보](https://i1.wp.com/sapstoryhub.co.kr/wp-content/uploads/2018/07/from-production-to-sales-smart-farm-digitize-and-automate-everything-5.png?resize=519%2C354&ssl=1) | ・ 로봇과의 센싱을 통해 온도, 습도, 일조량, 토양 수분도, 이산화탄소 등의  정보를 제공한다.<br><br>・ 농작물의 크기 및 색상을 측정해 수확 가능 과실의 비율을 그래프로 보여준다.
  ![간단한 앱 UI](https://www.aitimes.kr/news/photo/201701/7635_7471_733.jpg) | ・ 깔끔한 UI 화면을 구현해 사용자가 사용이 용이하게 한다.<br><br>・ 농장의 전체적인 정보를 보여준다.
  
- **기대효과 및 활용분야**
  기대효과 및 활용분야 | 설명
  :---:|---
  편의성 제공 | ・ 생장 환경 정보를 애플리케이션으로 제공 받아 편의성 증대<br>・ 농작물의 감지 및 색택을 통해 수확 가능한 농작물의 비율을 보여줌.
  농업의 활성화 | ・ 농사 초심자에게 농작물에 대한 정보와 아이디어를 제공함으로써 귀농에 대한 접근성을 낮춤
  농업  지원책 | ・ 현재 스마트팜을 경영하려는 농업인들을 대상으로 국가의 활발한 지원이 이루어지고 있다. 따라서 'Handy Farm'을 이용하는 농업인들에 대한 국가의 지원도 기대해 볼 수 있다.
  비용 절감 | ・기존 농가의 시스템과 연결해서 제어 기능이 가능하도록 스마트화하여 비용 절감을 추구함.
