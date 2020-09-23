import cv2
import numpy as np


hsv = 0
lower_blue1 = 0
upper_blue1 = 0
lower_blue2 = 0
upper_blue2 = 0
lower_blue3 = 0
upper_blue3 = 0


def mouse_callback(event, x, y, flags, param):
    global hsv, lower_blue1, upper_blue1, lower_blue2, upper_blue2, lower_blue3, upper_blue3

    # 마우스 왼쪽 버튼 누를시 위치에 있는 픽셀값을 읽어와서 HSV로 변환합니다.
    if event == cv2.EVENT_LBUTTONDOWN:
        print(img_color[y, x])
        color = img_color[y, x] # 클릭한 위치의 픽셀 값 가져오기

        one_pixel = np.uint8([[color]]) # 가져온 픽셀 값을 unit8 타입으로 변경
        hsv = cv2.cvtColor(one_pixel, cv2.COLOR_BGR2HSV) # RGB를 HSV 색공간으로 변경
        hsv = hsv[0][0] # 픽셀값을 가져옴

        # HSV 색공간에서 마우스 클릭으로 얻은 픽셀값과 유사한 필셀값의 범위를 정합니다.
        if hsv[0] < 10: # 빨간색 주변일 경우, 오차범위 ± 10, 가장 적은 숫자를 나타낼때는 + 180을 해주어 조정한다.
            print("case1")
            lower_blue1 = np.array([hsv[0]-10+180, 30, 30]) 
            upper_blue1 = np.array([180, 255, 255])
            lower_blue2 = np.array([0, 30, 30])
            upper_blue2 = np.array([hsv[0], 255, 255])
            lower_blue3 = np.array([hsv[0], 30, 30])
            upper_blue3 = np.array([hsv[0]+10, 255, 255])
            #     print(i-10+180, 180, 0, i)
            #     print(i, i+10)

        elif hsv[0] > 170: # 파란색 주변일 경우, 180을 넘어가게 되면 변환 전의 값이 360을 넘어가기 때문에 -180을 해주어 조정한다.
            print("case2")
            lower_blue1 = np.array([hsv[0], 30, 30])
            upper_blue1 = np.array([180, 255, 255])
            lower_blue2 = np.array([0, 30, 30])
            upper_blue2 = np.array([hsv[0]+10-180, 255, 255])
            lower_blue3 = np.array([hsv[0]-10, 30, 30])
            upper_blue3 = np.array([hsv[0], 255, 255])
            #     print(i, 180, 0, i+10-180)
            #     print(i-10, i)
        else: # 나머지
            print("case3")
            lower_blue1 = np.array([hsv[0], 30, 30])
            upper_blue1 = np.array([hsv[0]+10, 255, 255])
            lower_blue2 = np.array([hsv[0]-10, 30, 30])
            upper_blue2 = np.array([hsv[0], 255, 255])
            lower_blue3 = np.array([hsv[0]-10, 30, 30])
            upper_blue3 = np.array([hsv[0], 255, 255])
            #     print(i, i+10)
            #     print(i-10, i)

        print(hsv[0])
        print("@1", lower_blue1, "~", upper_blue1)
        print("@2", lower_blue2, "~", upper_blue2)
        print("@3", lower_blue3, "~", upper_blue3)

cv2.namedWindow('img_color')
cv2.setMouseCallback('img_color', mouse_callback)

while(True):
    img_color = cv2.imread('.\\tomato\\tomato81.jpg')
    height, width = img_color.shape[:2]

    # 원본 영상을 HSV 영상으로 변환합니다.
    img_hsv = cv2.cvtColor(img_color, cv2.COLOR_BGR2HSV)

    # 범위 값으로 HSV 이미지에서 마스크를 생성합니다.
    img_mask1 = cv2.inRange(img_hsv, lower_blue1, upper_blue1)
    # cv2.imshow('img_mask1', img_mask1)
    img_mask2 = cv2.inRange(img_hsv, lower_blue2, upper_blue2)
    # cv2.imshow('img_mask2', img_mask2)
    img_mask3 = cv2.inRange(img_hsv, lower_blue3, upper_blue3)
    # cv2.imshow('img_mask3', img_mask3)
    img_mask = img_mask1 | img_mask2 | img_mask3 # 3장의 마스크 이미지 비트 or 연산으로 합치기

    # 마스크 이미지로 원본 이미지에서 범위값에 해당되는 영상 부분을 획득합니다.
    img_result = cv2.bitwise_and(img_color, img_color, mask=img_mask)

    cv2.imshow('img_color', img_color)
    cv2.imshow('img_mask', img_mask)
    cv2.imshow('img_result', img_result)


    # ESC 키누르면 종료
    if cv2.waitKey(1) & 0xFF == 27:
        break

cv2.destroyAllWindows()