import urllib.request
from bs4 import BeautifulSoup
import time
from datetime import date
import sys

# 페이지 종료 처리를 위한 마지막 내용 클래스 초기화
oldmsg = BeautifulSoup("", 'html.parser')

today = date.today()
item = 1
# item = 180
while True:
    try:
        # 검색 조건에 최신순, 최근 6개월
        # url = 'https://search.naver.com/search.naver?where=article&ie=utf8&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&prdtype=0&t=0&st=date&date_option=4&date_from=20200628000000&date_to=20200924235959&srchby=text&dup_remove=1&cafe_url=&without_cafe_url=&board=&sm=tab_pge&nso=so:dd,p:6m,a:all&start='

        # 검색 시작일과 종료일을 주고 검색, 출력은 최신순으로 Naver에서 하므로, 별도의 sort 필요없음.
        now = datetime.now()
        Start_date = '2020.07.01'
        End_date = (str(today.year) + '.' + str(today.month) + '.' + str(today.day))

        url = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=article&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&oquery=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&tqi=U2XLvdp0YidssKp64UlssssstXZ-520687&nso=so%3Add%2Cp%3Afrom20200701to20200924%2Ca%3Aall&date_from=' \
              + Start_date + '&date_option=6&date_to=' + End_date + '&dup_remove=1&prdtype=0&srchby=text&st=date&start='
        url = url + str(item)
        req = urllib.request.urlopen(url)

    except:
        break

    time.sleep(1)

    res = req.read()

    soup = BeautifulSoup(res, 'html.parser')

    result = soup.find_all('li', class_='sh_cafe_top')

    content = soup.find_all('dd', class_='sh_cafe_passage')

    TargetText = "병충해"

    for msg in result:
        dates = msg.find("dd", "txt_inline")
        content = msg.find_all('dd', class_='sh_cafe_passage')
        #     print (dates.text, end="  :  ")

        post = msg.find("dl")

        title = post.find("a")

        # '문의'가 포함된 게시물을 가져온다.
        # 문의 --> 병충해
        if TargetText in title.text:
            # print(item, end = "  :  ")

            print(dates.text, end="  :  ")
            print(title.text, '\n')
            # cnt=cnt+1

            for pure in content:
                print(pure.text)
                print("\n")
            print(title['href'])
            print('\n')

    # 필요없는 부분
    # page = soup.find ('div', 'paging')
    # next = page.find_all('a', 'next')

    item += 10  # Next 10 items

    # 검색 (10개씩) 된 페이지의 마지막 내용이 지난번 페이지의 마지막과 같으면 종료.
    # 아니면, 마지막을  oldmsg 에 보관
    if oldmsg == msg:
        break;
    else:
        oldmsg = msg

