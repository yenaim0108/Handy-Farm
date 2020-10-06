import time
import sys
import datetime

# 웹 크롤링 모듈
import urllib.request
from bs4 import BeautifulSoup

# 한글 NLP 모듈
from konlpy.tag import Kkma
from konlpy.utils import pprint
from konlpy.tag import Komoran
from collections import Counter

# 워드클라우드 모듈
import matplotlib.pyplot as plt
from wordcloud import WordCloud
from PIL import Image
import numpy as np

# 페이지 종료 처리를 위한 마지막 내용 클래스 초기화
oldmsg = BeautifulSoup("", 'html.parser')

# 검색 조건에 최신순, 최근 6개월
# url = 'https://search.naver.com/search.naver?where=article&ie=utf8&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&prdtype=0&t=0&st=date&date_option=4&date_from=20200628000000&date_to=20200924235959&srchby=text&dup_remove=1&cafe_url=&without_cafe_url=&board=&sm=tab_pge&nso=so:dd,p:6m,a:all&start='

# 검색 시작일과 종료일을 주고 검색, 출력은 최신순으로 Naver에서 하므로, 별도의 sort 필요없음.
# Start_date = '2020-07-01'
# End_date = '2020-09-24'

# 오늘 부터 60일 전부터 검색
today = datetime.datetime.now()

Start = today - datetime.timedelta(days=60)

Start_date = str(Start.year) + '.' + str(Start.month) + '.' + str(Start.day)
End_date = str(today.year) + '.' + str(today.month) + '.' + str(today.day)

# print (Start.date(), today.date())
print(Start_date, End_date)

# koNLP를 위한 title, detail 저장 변수 --> size 가 커지면 file로 저장하는 것이 좋을 듯.

Title_text = ""
Detail_text = ""
Result_Set = []

item = 1
# item = 180
while True:

    if item > 200: break

    try:
        url = 'https://search.naver.com/search.naver?sm=tab_hty.top&where=article&query=%EB%B0%A9%EC%A0%9C%2B%ED%86%A0%EB%A7%88%ED%86%A0&date_option=4&date_to=20200930235959&dup_remove=1&srchby=text&st=rel&start='

        # 최신순
        # url = 'https://search.naver.com/search.naver?where=article&ie=utf8&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&prdtype=0&t=0&st=date&date_option=3&start='
        # 관련도순
        # url = 'https://search.naver.com/search.naver?where=article&ie=utf8&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4&prdtype=0&t=0&st=rel&date_option=3&start='
        # url = 'https://search.naver.com/search.naver?where=article&ie=utf8&query=%EB%B3%91%EC%B6%A9%ED%95%B4&prdtype=0&t=0&st=date&date_option=3&start='

        # url = 'https://search.naver.com/search.naver?where=article&query=%ED%86%A0%EB%A7%88%ED%86%A0%20%EB%B3%91%EC%B6%A9%ED%95%B4&ie=utf8&st=date&date_option=6&date_from='\
        #       +Start_date +'&date_to='+End_date + '&board=&srchby=text&dup_remove=1&cafe_url=&without_cafe_url=&sm=tab_opt&nso=so%3Add%2Cp%3Afrom20200819to20200929%2Ca%3Aall&t=0&mson=0&prdtype=0&start='
        url = url + str(item)
        req = urllib.request.urlopen(url)

    except:
        break

    time.sleep(1)

    res = req.read()

    soup = BeautifulSoup(res, 'html.parser')

    result = soup.find_all('li', class_='sh_cafe_top')

    content = soup.find_all('dd', class_='sh_cafe_passage')

    Page = []
    for msg in result:

        Post = []
        dates = msg.find("dd", "txt_inline")
        content = msg.find_all('dd', class_='sh_cafe_passage')
        post = msg.find("dl")
        title = post.find("a")

        Post.append(dates.text)
        Post.append(title.text)
        Post.append(title['href'])
        Post.append(content)

        Page.append(Post)

        try:
            # 포스팅의 타이틀에 대하여 처리 - 타이틀을 NLP를 위하여 누적
            Title_text = Title_text + title.text + " "
            print(dates.text, title.text)

            # Post 내용 처리 for NLP
            for pure in content:
                Detail_text = Detail_text + pure.text + " "
                # print (pure.text)
        except:
            continue

    # 검색 (10개씩) 된 페이지의 마지막 내용이 지난번 페이지의 마지막과 같으면 종료.
    # 아니면, 마지막을  oldmsg 에 보관
    if oldmsg == msg:
        break;

    print(item, "  # of Post in Page = ", len(Page))

    # Processing Page

    Result_Set = Result_Set + Page

    oldmsg = msg
    item += 10  # Next 10 items

print("Result = ", len(Result_Set))

# print (Title_text)

# kkma = Kkma()
# noun_list = kkma.nouns(Title_text)

komoran = Komoran()
noun_list = komoran.nouns(Title_text)

words = Counter(noun_list)
most = words.most_common(100)
print(most)

tags = {}
for n, c in most:
    if (len(n) > 1):
        tags[n] = c
        print(n, tags[n])

tags["토마토"] = 0
tags["방제"] = 0

mask = np.array(Image.open('./heart.jpg'))

wc1 = WordCloud(font_path='C:/Windows/Fonts/gulim.ttc', \
                background_color="white", \
                mask=mask, \
                random_state=42, \
                width=mask.shape[1], height=mask.shape[0], \
                max_words=100, \
                max_font_size=200)

# koNLP에서 추출한 명사로 처리 --> 에러, 대신 위에어 우선 full 텍스트로 처리
wc1.generate_from_frequencies(tags)

noun_list = komoran.nouns(Detail_text)

words = Counter(noun_list)
most = words.most_common(100)
print(most)

tags = {}
for n, c in most:
    if (len(n) > 1):
        tags[n] = c
        print(n, tags[n])

tags["토마토"] = 0
tags["방제"] = 0

mask = np.array(Image.open('./heart.jpg'))

wc2 = WordCloud(font_path='C:/Windows/Fonts/gulim.ttc', \
                background_color="white", \
                mask=mask, \
                random_state=42, \
                width=mask.shape[1], height=mask.shape[0], \
                max_words=100, \
                max_font_size=200)

# koNLP에서 추출한 명사로 처리 --> 에러, 대신 위에어 우선 full 텍스트로 처리
wc2.generate_from_frequencies(tags)

fig = plt.figure()
plt.subplot(1, 2, 1)
plt.title("from Title")
plt.imshow(wc1, interpolation='bilinear')
plt.axis('off')
plt.subplot(1, 2, 2)
plt.title("from Posting")
plt.imshow(wc2, interpolation='bilinear')
plt.axis('off')
plt.show()