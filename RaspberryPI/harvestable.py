#_*_coding:utf-8_*_

import cv2
import numpy as np
import paho.mqtt.client as mqtt
import sys
import datetime

def white_balance(img) :
    result = cv2.cvtColor(img, cv2.COLOR_BGR2LAB)
    avg_a = np.average(result[:, :, 1])
    avg_b = np.average(result[:, :, 2])
    result[:, :, 1] = result[:, :, 1] - ((avg_a - 128) * (result[:, :, 0] / 255.0) * 1.1)
    result[:, :, 2] = result[:, :, 2] - ((avg_b - 128) * (result[:, :, 0] / 255.0) * 1.1)
    result = cv2.cvtColor(result, cv2.COLOR_LAB2BGR)
    return result

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

# # 빛에 반사된 부분 제거
# def highlight_remove(img) :
#
# # 수확 가능한 작물이 있는지 판별(붉은색이 없으면 토마토를 찾아도 의미가 없으므로 판단 중단)
# def harvestable_crops(img) :
#
# # 붉은 색이 있는 부분을 사각형으로 인식
# def harvestable_area(img) :
#
# # 농작물 인식
# def hough_circle(img) :

# 수확 가능 비율
def harvestable_percent(img) :
    img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV) # cvtColor 함수를 이용하여 hsv 색공간으로 변환
    img_mask = 0

    # 붉은색
    for i in range(-11, 10) :
        lower_blue1 = np.array([i - 10 + 180, 30, 30])
        upper_blue1 = np.array([180, 255, 255])
        lower_blue2 = np.array([0, 30, 30])
        upper_blue2 = np.array([i, 255, 255])
        lower_blue3 = np.array([i, 30, 30])
        upper_blue3 = np.array([i + 10, 255, 255])

        # 범위 값으로 hsv 이미지에서 마스크를 생성합니다.
        img_mask1 = cv2.inRange(img_hsv, lower_blue1, upper_blue1)
        img_mask2 = cv2.inRange(img_hsv, lower_blue2, upper_blue2)
        img_mask3 = cv2.inRange(img_hsv, lower_blue3, upper_blue3)

        # 3장의 마스크 이비지 비트 or 연산으로 합치기
        img_mask = img_mask | img_mask1 | img_mask2 | img_mask3

    img_result = cv2.bitwise_and(img, img, mask=img_mask)

    cv2.namedWindow('img', cv2.WINDOW_NORMAL)
    cv2.namedWindow('img_result', cv2.WINDOW_NORMAL)

    cv2.imshow('img', img)
    cv2.imshow('img_result', img_result)

    cv2.waitKey(0)
    cv2.destroyAllWindows()

def mqtt_harvestable() :
    broker_address = '192.168.0.3' # 브로커 IP 주소
    client = mqtt.Client("RaspverryPI") # mqtt.Client() 인스턴스 생성
    client.connect(broker_address) # 브로커에 연결
    client.subscribe('harvestable') # 토픽 구독
    client.on_message = harvestable # 브로커로부터 메시지가 오면 동작하는 callback 함수
    client.loop_forever() # 지속적으로 버퍼를 체크하고 이벤트를 발생 시키키

def harvestable(client, userdata, msg) :
    iv = msg.payload
    print(iv)
    if (iv < 700) :
        sys.exit()

#main
img = cv2.imread(".\\tomato\\tomato.jpg")

# cv2.namedWindow('img', cv2.WINDOW_NORMAL)
# cv2.imshow('img', img)

mqtt_ask_illuminance() # 조도 값 요청하기

mqtt_illuminance() # mqtt 통신으로 조도값을 받아와서 일정 값 이하면 수확시기 판단 중지