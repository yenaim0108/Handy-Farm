#_*_coding:utf-8_*_
import cv2
import numpy as np

oimg = cv2.imread('.\\tomato\\tomato81.jpg') # 이미지 컬러로 읽어오기
img = cv2.imread('.\\tomato\\tomato81.jpg', 0) # 이미지 흑백으로 읽어오기
img = cv2.medianBlur(img, 5) # 이미지 블러링으로 노이즈 제거
cimg = cv2.cvtColor(img, cv2.COLOR_GRAY2BGR) # 흑백 이미지를 BGR 이미지로 변경 (변경하지 않으면 원의 중심점과 둘레를 표시할 때 흑백으로 표시됨)

cmask = np.zeros(img.shape) # 이미지 마스크 (검은 배경 이미지, numpy 배열)

circles = cv2.HoughCircles(img, cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=15, maxRadius=40) # 이미지에서 원 추출
circles = np.uint16(np.around(circles)) # np.around() 함수로 circles의 값들을 반올림/반내림하고 이를 UNIT16으로 변환한다.

for i in circles[0,:]:
    cv2.circle(cmask, (i[0], i[1]), i[2], (255, 255, 255), -1)  # 검은 배경 이미지에 원만 흰색으로 칠하기
    cv2.circle(cimg, (i[0], i[1]), i[2], (0, 255, 0), 2)  # 흑백 이미지에 추출한 원 그리기
    cv2.circle(cimg, (i[0], i[1]), 2, (0, 0, 255), 3)  # 흑백 이미지에 원 중심점 그리기

cv2.imwrite('cmask.jpg',cmask) # numpy 배열인 cmask를 jpg 이미지로 변환.
cmask = cv2.imread('cmask.jpg') # jpg 이미지로 변환한 마스크 이미지를 다시 읽어온다.
crops = cv2.bitwise_and(oimg, cmask) # 원본 컬러 이미지와 마스크 이미지를 결합하여 추출된 원 부분을 제외하고 다른 부분을 모두 검정으로 칠한 이미지를 만든다.

# cv2.namedWindow('orginal img', cv2.WINDOW_NORMAL) # 원본 이미지 크기로 윈도우를 생성하되 사용자가 크기 조절 가능
# cv2.namedWindow('crops extraction', cv2.WINDOW_NORMAL) # 원본 이미지 크기로 윈도우를 생성하되 사용자가 크기 조절 가능
# cv2.namedWindow('mask img', cv2.WINDOW_NORMAL) # 원본 이미지 크기로 윈도우를 생성하되 사용자가 크기 조절 가능
# cv2.namedWindow('result img', cv2.WINDOW_NORMAL) # 원본 이미지 크기로 윈도우를 생성하되 사용자가 크기 조절 가능

cv2.imshow('orginal img', oimg) # 원본 이미지
cv2.imshow('crops extraction', cimg) # 이미지에 추출한 원과 중심점 표시
cv2.imshow('mask img', cmask) # 검은 배경 이미에 추출한 원 흰색으로 표시 (마스크 이미지)
cv2.imshow('result img', crops) # 이미지 중 추출한 원을 제외한 나머지 부분은 검정색으로 표시

cv2.waitKey(0) # key 입력이 있을 때까지 무한 대기

cv2.destroyAllWindows() # 화면에 나타난 윈도우를 종료