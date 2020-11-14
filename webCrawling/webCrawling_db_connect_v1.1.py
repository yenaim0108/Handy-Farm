import urllib.request
from bs4 import BeautifulSoup
import time
import datetime
import MySQLdb
import sys
import io

#자바에서 호출하여 처리하기 위한 코드
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding='utf-8')

# 페이지 종료 처리를 위한 마지막 내용 클래스 초기화
oldresult = BeautifulSoup("", 'html.parser')

item = 1  # item = 180

try:
    # db 연결
    conn = MySQLdb.connect(db="handyfarm", host="localhost", user="farmplant", passwd="handyfarm", charset="utf8")
    cur = conn.cursor()

    # 초기화 : 테이블 데이터 del
    query = "delete from crawling"
    cur.execute(query)
    conn.commit()

except MySQLdb.Error as err:
    print("***오류발생 : "+err)

going = True

while going:

    try:

        # 검색 조건에 최신순, 최근 6개월
        # 검색 시작일과 종료일을 주고 검색, 출력은 최신순으로 Naver에서 하므로, 별도의 sort 필요없음.
        today = datetime.datetime.now()
        start = today - datetime.timedelta(days=60)

        Start_date = (str(start.year) + '.' + str(start.month) + '.' + str(start.day))
        End_date = (str(today.year) + '.' + str(today.month) + '.' + str(today.day))

        # url = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=article&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&oquery=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&tqi=U2XLvdp0YidssKp64UlssssstXZ-520687&nso=so%3Add%2Cp%3Afrom20200701to20200924%2Ca%3Aall&date_from=' \
        #       + Start_date + '&date_option=6&date_to=' + End_date + '&dup_remove=1&prdtype=0&srchby=text&st=date&start='
        # url = url + str(item)

        # 카페  url 입력
        #url = "https://search.naver.com/search.naver?where=article&query=%ED%86%A0%EB%A7%88%ED%86%A0%20%EB%B3%91%EC%B6%A9%ED%95%B4&ie=utf8&st=date&date_option=4&date_from=&date_to=&board=&srchby=text&dup_remove=1&cafe_url=&without_cafe_url=&sm=tab_opt&nso=so%3Add%2Cp%3A6m%2Ca%3Aall&t=0&mson=0&prdtype=0"
        url = "https://search.naver.com/search.naver?where=view&sm=tab_viw.blog&query=%ED%86%A0%EB%A7%88%ED%86%A0%20%EB%B3%91%EC%B6%A9%ED%95%B4&nso=so%3Add%2Cp%3A6m%2Ca%3Aall"
        req = urllib.request.urlopen(url)

    except:
        break

    time.sleep(1)

    res = req.read()

    soup = BeautifulSoup(res, 'html.parser')

    result = soup.find('ul', class_='lst_total')  # 수정 --> 전체 포스트

    result = result.find_all('li', class_='bx')  # 수정 --> result 에 개별 포스트 검색 결과 보관

    # img = soup.find('span', class_='thumb_fix')#수정
    # rint("그림입니다")
    # print(img)

    # target Text가 포함된 게시물만 가져오기 위해 정해줌.
    TargetText = "병충해"

    # 검색 (10개씩) 된 페이지의 마지막 내용이 지난번 페이지의 마지막과 같으면 종료.
    # 아니면, 마지막을  oldmsg 에 보관
    if oldresult == result:
        going = False
        print("---------------크롤링 종료----------------")
        break

    else:
        oldresult = result

    for msg in result:
        # print (msg)
        # continue

        dates = msg.find("span", class_="sub_time")  # 수정

        # content = soup.find_all('li', class_='bx')#수정
        content = msg.find('div', class_='total_group')
        # print (content)

        content_1 = content.find('div', class_='total_dsc_wrap')
        # print (content_1)
        content_2 = content_1.find('a', class_='total_dsc')
        # print (content_2)
        content_real = content_2.find('div', class_='api_txt_lines dsc_txt')

        # get thumb nail image
        try:
            image = msg.find("a", class_="thumb_single")  # 수정
            image_loc = image.find("span")  # 수정
            image_link = image_loc.find("img")
            image_URL = image_link['src']
        except:
            image_URL = ""

        post = msg.find("div", class_="total_wrap api_ani_send")  # 수정
        title = post.find("a", class_="api_txt_lines total_tit")  # 수정

        # '병충해'가 포함된 게시물을 가져온다.
        # if TargetText in title.text:
        if True:
            c_date = dates.text  # 날짜
            c_title = title.text  # 제목
            print(c_date, end="  :  ")
            print(c_title)

            print(">>>이미지 : " + image_URL)

            # for pure in content:
            #    c_content = pure.text  # 내용
            #    print(">>>내용 : " + c_content)

            strx = content_real.text.replace('\'', '')
            strx = strx.replace('\"', '')

            print(">> 내용 : " + strx)

            c_link = title['href']  # 링크
            print(">>>연결 링크 : " + c_link + '\n')
            print('\n')




            try:
                query = "insert  into `crawling` " \
                        "(`link`,`category`,`views`, `title`, `content`, `c_date`, `img`) ",
                        "values (' " + c_link + "','병충해', '0', '" + c_title + " ', ' " + strx + "', ' " + c_date + "', '" + image_URL + "');"
                cur.execute(query)
                conn.commit()


                print("!!데이터 insert 성공입니다!!\n")

            except MySQLdb.Error as err:
                print("***오류발생 : "+err)

    item += 10  # Next 10 items

