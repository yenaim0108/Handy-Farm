import urllib.request
from bs4 import BeautifulSoup
import time
import datetime
import MySQLdb

# 페이지 종료 처리를 위한 마지막 내용 클래스 초기화
oldmsg = BeautifulSoup("", 'html.parser')

item = 1 # item = 180

try:
    # db 연결
    conn = MySQLdb.connect(db="handyfarm", host="192.168.0.29", user="farmplant", passwd="handyfarm", charset="utf8")
    cur = conn.cursor()

    # 초기화 : 테이블 데이터 del
    query = "delete from real_time_info"
    cur.execute(query)
    conn.commit()

except MySQLdb.Error as err:
    print("***오류발생 : "+err)

while True:
    try:

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

    # target Text가 포함된 게시물만 가져오기 위해 정해줌.
    TargetText = "병충해"

    for msg in result:
        dates = msg.find("dd", "txt_inline")
        content = msg.find_all('dd', class_='sh_cafe_passage')

        # get thumb nail image
        try:
            image = msg.find("div", class_="thumb")
            image_loc = image.find("a")
            image_link = image_loc.find("img")
            image_URL = image_link['src']
        except:
            image_URL = ""

        post = msg.find("dl")
        title = post.find("a")

        # '병충해'가 포함된 게시물을 가져온다.
        if TargetText in title.text:
            c_date = dates.text  # 날짜
            c_title = title.text  # 제목
            print(c_date, end="  :  ")
            print(c_title)

            print(">>>이미지 : " + image_URL)

            for pure in content:
                c_content = pure.text  # 내용
                print(">>>내용 : " + c_content)

            c_link = title['href']  # 링크
            print(">>>연결 링크 : " + c_link + '\n')
            print('\n')
            try:
                query = "insert  into `real_time_info`" \
                        "(`link`,`category`,`views`, `title`, `content`, `date`, `img`)" \
                        "values (' " + c_link + "','병충해', '0', '" + c_title + " ', ' " + c_content + "', ' " + c_date + "', '" + image_URL + "');"
                cur.execute(query)
                conn.commit()


                print("!!데이터 insert 성공입니다!!\n")

            except MySQLdb.Error as err:
                print("***오류발생 : "+err)

    item += 10  # Next 10 items

    # 검색 (10개씩) 된 페이지의 마지막 내용이 지난번 페이지의 마지막과 같으면 종료.
    # 아니면, 마지막을  oldmsg 에 보관
    if oldmsg == msg:
        print("---------------크롤링 종료----------------")
        break

    else:
        oldmsg = msg