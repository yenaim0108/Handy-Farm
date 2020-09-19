#_*_coding:utf-8_*_
import cv2
import numpy as np

# 사진에서 농작물 추출
def hough_circle(img) :
    oimg = cv2.imread('.\\tomato\\tomato.jpg')  # 이미지 컬러로 읽어오기
    img = cv2.imread('.\\tomato\\tomato.jpg', 0)  # 이미지 흑백으로 읽어오기
    img = cv2.medianBlur(img, 5)  # 이미지 블러링으로 노이즈 제거

    cmask = np.zeros(img.shape)  # 이미지 마스크 (검은 배경 이미지)

    circles = cv2.HoughCircles(img, cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=35, maxRadius=60) # 이미지에서 원 추출
    circles = np.uint16(np.around(circles))  # np.around() 함수로 circles의 값들을 반올림/반내림하고 이를 UNIT16으로 변환한다.

    for i in circles[0, :]:
        cv2.circle(cmask, (i[0], i[1]), i[2], (255, 255, 255), -1)  # 검은 배경 이미지에 원만 흰색으로 칠하기

    cv2.imwrite('cmask.jpg', cmask)  # numpy 배열인 cmask를 jpg 이미지로 변환.
    cmask = cv2.imread('cmask.jpg')  # jpg 이미지로 변환한 마스크 이미지를 다시 읽어온다.
    crops = cv2.bitwise_and(oimg, cmask)  # 원본 컬러 이미지와 마스크 이미지를 결합하여 추출된 원 부분을 제외하고 다른 부분을 모두 검정으로 칠한 이미지를 만든다.

    return crops

cv2.waitKey(0) # key 입력이 있을 때까지 무한 대기

cv2.destroyAllWindows() # 화면에 나타난 윈도우를 종료