import cv2
import numpy as np

img = cv2.imread('.\\tomato\\tomato61.jpg') # 이미지 컬러로 읽어오기
img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV) # cvtColor 함수를 이용하여 hsv 색공간으로 변환

lower_blue1 = np.array([1-10+180, 30, 30])
upper_blue1 = np.array([180, 255, 255])
lower_blue2 = np.array([0, 30, 30])
upper_blue2 = np.array([1, 255, 255])
lower_blue3 = np.array([1, 30, 30])
upper_blue3 = np.array([1+10, 255, 255])

# 범위 값으로 HSV 이미지에서 마스크를 생성합니다.
img_mask1 = cv2.inRange(img_hsv, lower_blue1, upper_blue1)
# cv2.namedWindow('img_mask1', cv2.WINDOW_NORMAL)
# cv2.imshow('img_mask1', img_mask1)
img_mask2 = cv2.inRange(img_hsv, lower_blue2, upper_blue2)
# cv2.namedWindow('img_mask2', cv2.WINDOW_NORMAL)
# cv2.imshow('img_mask2', img_mask2)
img_mask3 = cv2.inRange(img_hsv, lower_blue3, upper_blue3)
# cv2.namedWindow('img_mask3', cv2.WINDOW_NORMAL)
# cv2.imshow('img_mask3', img_mask3)
img_mask = img_mask1 | img_mask2 | img_mask3 # 3장의 마스크 이미지 비트 or 연산으로 합치기

# 마스크 이미지로 원본 이미지에서 범위값에 해당되는 영상 부분을 획득합니다.
img_result = cv2.bitwise_and(img, img, mask=img_mask)

mean = cv2.mean(img_result)
multiplier = float(img_mask.size)/cv2.countNonZero(img_mask)
mean = tuple([multiplier * x for x in mean])
print("평균 = ", mean)

# cv2.namedWindow('img', cv2.WINDOW_NORMAL)
# cv2.namedWindow('img_result', cv2.WINDOW_NORMAL)

cv2.imshow('img', img)
cv2.imshow('img_result', img_result)

cv2.waitKey(0)
cv2.destroyAllWindows()