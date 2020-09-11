#_*_coding:utf-8_*_
import cv2
import numpy as np

img = cv2.imread('.\\tomato\\tomato.jpg') # 이미지 컬러로 읽어오기
gimg = cv2.imread('.\\tomato\\tomato.jpg', 0) # 이미지 흑백으로 읽어오기
gimg = cv2.medianBlur(gimg, 5)
cimg = cv2.cvtColor(gimg, cv2.COLOR_GRAY2BGR)

cmask = np.zeros(img.shape) # 이미지 마스크 (검은 배경 이미지)

circles = cv2.HoughCircles(gimg, cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=15, maxRadius=50)

circles = np.uint16(np.around(circles))

for i in circles[0,:]:
    cv2.circle(cmask, (i[0], i[1]), i[2], (255, 255, 255), -1)  # 검은 이미지에 원만 흰색으로 칠하기

cv2.imwrite('cmask.jpg',cmask) # numpy 배열인 cmask를 jpg 이미지로 변환.
cmask = cv2.imread('cmask.jpg') # jpg 이미지로 변환한 마스크 이미지를 다시 읽어온다.

crops = cv2.bitwise_and(img, cmask) # 원본 컬러 이미지와 마스크 이미지를 결합하여 추출된 원 부분을 제외하고 다른 부분을 모두 검정으로 칠한 이미지를 만든다.

cv2.imshow('원본이미지', img)
cv2.imshow('마스크 이미지', cmask) # 검은 배경 이미에 추출한 원 흰색으로 표시 (마스크 이미지)
cv2.imshow('결과 이미지', crops) # 이미지 중 추출한 원을 제외한 나머지 부분은 검정색으로 표시

cv2.waitKey(0)

cv2.destroyAllWindows()