#_*_coding:utf-8_*_

import cv2
import numpy as np
import paho.mqtt.client as mqtt
import sys
import socket

def ipcheck():
	return socket.gethostbyname(socket.getfqdn()) # 로컬 IP 값 return

def mqtt_ask_illuminance() :
    client = mqtt.Client() # mqtt.Client() 인스턴스 생성
    client.connect('192.168.137.103', 1883) # 브로커에 연결
    client.publish('ask_illuminance', 'c') # ask_illuminance 토픽에 메시지 보내기
    client.disconnect() # 연결 종료

def mqtt_illuminance() :
    broker_address = '192.168.137.103' # 브로커 IP 주소
    client = mqtt.Client("RaspverryPI") # mqtt.Client() 인스턴스 생성
    client.connect(broker_address) # 브로커에 연결
    client.subscribe('illuminance') # 토픽 구독
    client.on_message = illuminance # 브로커로부터 메시지가 오면 동작하는 callback 함수
    client.loop_forever() # 지속적으로 버퍼를 체크하고 이벤트를 발생 시키키

def illuminance(client, userdata, msg) :
    iv = int(msg.payload) # 조도값 받기
    if (iv < 100) :
        sys.exit() # 조도값이 100 이하면 프로그램 종료

def hough_circle(img) :
    gimg = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) # 컬러 이미지를 흑백 이미지로 변환
    gimg = cv2.medianBlur(gimg, 5)  # 이미지 블러링으로 노이즈 제거
    copy = cv2.copyMakeBorder(img, 0, 0, 0, 0, cv2.BORDER_REPLICATE) # 이미지 복사
    cmask = np.zeros(img.shape)  # 이미지 마스크 (검은 배경 이미지, numpy 배열)

    circles = cv2.HoughCircles(gimg, cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=20, maxRadius=45)  # 이미지에서 원 추출
    circles = np.uint16(np.around(circles))  # np.around() 함수로 circles의 값들을 반올림/반내림하고 이를 UNIT16으로 변환한다.

    for i in circles[0, :]:
        cv2.circle(cmask, (i[0], i[1]), i[2], (255, 255, 255), -1)  # 검은 배경 이미지에 원만 흰색으로 칠하기
        cv2.circle(copy, (i[0], i[1]), i[2], (0, 255, 0), 2)  # 복사한 이미지에 추출한 원 그리기
        cv2.circle(copy, (i[0], i[1]), 2, (0, 0, 255), 3)  # 복사한 이미지에 원 중심점 그리기

    cv2.imwrite('cmask.jpg', cmask)  # numpy 배열인 cmask를 jpg 이미지로 변환.
    cmask = cv2.imread('cmask.jpg')  # jpg 이미지로 변환한 마스크 이미지를 다시 읽어온다.
    crops = cv2.bitwise_and(img, cmask)  # 원본 컬러 이미지와 마스크 이미지를 결합하여 추출된 원 부분을 제외하고 다른 부분을 모두 검정으로 칠한 이미지를 만든다.

    cv2.imshow('crops', copy) # 감지한 원을 표시한 이미지 보여주기

    return crops # 감지한 농작물만 추출한 사진 return

def white_balance(img) :
    result = cv2.cvtColor(img, cv2.COLOR_BGR2LAB) # 컬러 이미지를 LAB 색공간으로 변환
    avg_a = np.average(result[:, :, 1]) # 가중 평균 계산
    avg_b = np.average(result[:, :, 2]) # 가중 평균 계산
    result[:, :, 1] = result[:, :, 1] - ((avg_a - 128) * (result[:, :, 0] / 255.0) * 1.1) # 화이트 밸런스 처리
    result[:, :, 2] = result[:, :, 2] - ((avg_b - 128) * (result[:, :, 0] / 255.0) * 1.1) # 화이트 밸런스 처리
    result = cv2.cvtColor(result, cv2.COLOR_LAB2BGR) # LAB 색공간 컬러 이미지로 변환
    return result # 화이트 밸런스 처리한 이미지 return

def total_area(img) :
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) # 컬러 이미지를 흑백 이미지로 변환
    total = cv2.countNonZero(gray) # 농작물 전체 면적(검정색이 아닌 부분)을 계산

    return total

def highlight_remove(img) :
    lower = np.array([0, 0, 230]) # hsv(최솟값, 최솟값, 230)
    upper = np.array([360, 255, 255]) # hsv(최댓값, 최댓값, 최댓값)
    mask = cv2.inRange(img, lower, upper) # 하이라이트(빛 반사 된 밝은 부분)만 흰색으로 칠하기

    cv2.imshow("highlight", mask) # 하이라이트(빛 반사된 부분)만 칠한 마스크 이미지 보여주기

    highlight = cv2.countNonZero(mask) # 하이라이트(빛 반사된 부분) 면적 계산하기

    return highlight # 하이라이트(빛 반사된 부분) 면적 값 return

def color_extraction(img) :
    img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV) # cvtColor 함수를 이용하여 hsv 색공간으로 변환
    img_mask = 0 # img_mask 0으로 초기화

    # 붉은색
    for i in range(0, 10) : # 오차범위 ± 10, 가장 적은 숫자를 나타낼때는 + 180을 해주어 조정항 lower, upper 값
        lower_blue1 = np.array([i - 10 + 180, 30, 30])
        upper_blue1 = np.array([180, 255, 255])
        lower_blue2 = np.array([0, 30, 30])
        upper_blue2 = np.array([i, 255, 255])
        lower_blue3 = np.array([i, 30, 30])
        upper_blue3 = np.array([i + 10, 255, 255])

        # 범위 값으로 hsv 이미지에서 마스크 이미지 생성
        img_mask1 = cv2.inRange(img_hsv, lower_blue1, upper_blue1)
        img_mask2 = cv2.inRange(img_hsv, lower_blue2, upper_blue2)
        img_mask3 = cv2.inRange(img_hsv, lower_blue3, upper_blue3)

        # 3장의 마스크 이비지 비트 or 연산으로 합치기
        img_mask = img_mask | img_mask1 | img_mask2 | img_mask3

    img_result = cv2.bitwise_and(img, img, mask=img_mask) # bitwise_and 연산으로 원본이미지에서 범위값에 해당하는 부분 추출

    gray = cv2.cvtColor(img_result, cv2.COLOR_BGR2GRAY) # 컬러 이미지 흑백 이미지로 변환
    ripe = cv2.countNonZero(gray) # 추출한 색 부분 면적 계산

    cv2.imshow('color_extraction', img_result) # 색 추출한 부분만 가져온 이미지 보여주기

    return ripe # 추출한 색 부분 면적 계산한 값 return

def mqtt_ask_ip() :
    client = mqtt.Client()  # mqtt.Client() 인스턴스 생성
    client.connect('192.168.137.103', 1883)  # 브로커에 연결
    client.publish('ask_illuminance', 'c')  # ask_illuminance 토픽에 메시지 보내기
    client.disconnect()  # 연결 종료

def mqtt_harvestable(ip, serial, harvestable) :
    client = mqtt.Client()  # mqtt.Client() 인스턴스 생성
    client.connect(ip, 1883)  # 브로커에 연결
    msg = serial + ", " + str(harvestable); # 수확 가능 비율과 시리얼 번호 하나의 메시지로 합치기
    client.publish('harvestable', msg)  # harvestable 토픽에 수확 가능 비율 보내기
    client.disconnect()  # 연결 종료

# 로컬 IP주소 가져오기
ip = ipcheck()

# 이미지 읽어오기
img = cv2.imread(".\\tomato\\tomato42.jpg")
# 이미지 사이즈 재조정
img = cv2.resize(img, (0, 0), fx=0.6, fy=0.6)

# 원본 이미지 보여주기
cv2.imshow('original', img)

# # 조도 값 요청하기
# mqtt_ask_illuminance()
#
# # mqtt 통신으로 조도값을 받아와서 일정 값 이하면 수확시기 판단 중지
# mqtt_illuminance()

# 농작물 감지
img = hough_circle(img)

# 화이트 밸런스 처리
img = white_balance(img)

# 감지한 농작물만 추출한 사진 보여주기
cv2.imshow('crops2', img)

# 마스크 이미지로 변환해서 농작물의 전체 면적 계산하기
total = total_area(img)

# 빛 반사 부분 면적 계산하기
highlight = highlight_remove(img)

# 완숙도가 70% 이상인 색 추출
ripe = color_extraction(img)

# 수확 가능 비율 구하기
harvestable = (ripe / (total - highlight)) * 100

# mqtt 통신으로 서버에 수확 가능 비율 보내기
mqtt_harvestable(ip, "10000000c366d002", harvestable)

print("(숙성기준색 면적 / (마스크 전체 면적 - 하이라이트 면적)) * 100 = 숙성도%\n", "(", ripe, " / (", total, " - ", highlight, ")) * 100 = ", harvestable, "%")

# key 입력이 있을 때까지 무한 대기
cv2.waitKey(0)