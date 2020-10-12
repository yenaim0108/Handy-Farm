import urllib.request
from bs4 import BeautifulSoup
import time
import datetime
import MySQLdb

# 페이지 종료 처리를 위한 마지막 내용 클래스 초기화
oldmsg = BeautifulSoup("", 'html.parser')

item = 1
# item = 180

while True:
    try:
        #db 연결
        db = MySQLdb.connect(db="handyfarm", host="127.0.0.1", user="root", passwd="s218210050", charset="utf8")
        cur = db.cursor()

        # 검색 조건에 최신순, 최근 6개월
        # 검색 시작일과 종료일을 주고 검색, 출력은 최신순으로 Naver에서 하므로, 별도의 sort 필요없음.
        today = datetime.datetime.now()
        start = today - datetime.timedelta(days=60)

        Start_date = (str(start.year) + '.' + str(start.month) + '.' + str(start.day))
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

    img = soup.find('a', class_='sp_thmb thmb80')

    #target Text가 포함된 게시물만 가져오기 위해 정해줌.
    TargetText = "병충해"

    for msg in result:
        dates = msg.find("dd", "txt_inline")
        content = msg.find_all('dd', class_='sh_cafe_passage')

        post = msg.find("dl")
        title = post.find("a")


        # '병충해'가 포함된 게시물을 가져온다.
        if TargetText in title.text:
            c_date = dates.text #날짜
            c_title = title.text #제목
            print(c_date, end="  :  ")
            # print(c_title, '\n')
            print(c_title)

            imgURL = img.find("img")['src']
            print(">>>이미지 : " + imgURL)

            for pure in content:
                c_content = pure.text #내용
                print(">>>내용 : " + c_content)

            c_link = title['href']  # 링크
            print(">>>연결 링크 : " + c_link + '\n')
            # print('\n')


    #     query = "insert  into `real_time_info`" \
    #             "(`link`,`category`,`views`, `title`, `content`, `date`, `img`)" \
    #             "values("+ c_link + ", "+ TargetText +", '0'"+ c_title + ", "+ c_content + ", "+ c_date +", '이미지');"


    item += 10  # Next 10 items

    # 검색 (10개씩) 된 페이지의 마지막 내용이 지난번 페이지의 마지막과 같으면 종료.
    # 아니면, 마지막을  oldmsg 에 보관
    if oldmsg == msg:
        break;
    else:
        oldmsg = msg
