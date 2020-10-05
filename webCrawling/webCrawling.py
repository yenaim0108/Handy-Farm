#검색을 최근 1달로 잡고 실행하느 코드!

import urllib.request
from bs4 import BeautifulSoup
import time


item = 170
#item = 180
while True :
    try:
        url = 'https://search.naver.com/search.naver?date_from=20030520&date_option=4'\ 
              '&date_to=20200914&dup_remove=1&nso=p%3A1m&post_blogurl=&post_blogurl_without='\
              '&query=%ED%86%A0%EB%A7%88%ED%86%A0%20%EB%B3%91%EC%B6%A9%ED%95%B4'\
              '&sm=tab_pge&srchby=all&st=sim&where=post&start='
        url = url + str(item)
        req = urllib.request.urlopen(url)
        
    except:
        break
        
    time.sleep (1)

    res = req.read()

    soup = BeautifulSoup(res, 'html.parser')

    result = soup.find_all('li', class_='sh_blog_top')

    content = soup.find_all('dd', class_='sh_blog_passage')

    TargetText="문의" #'문의'가 포함된 게시물을 가져오지 않는다.

    for msg in result :
        dates = msg.find("dd", "txt_inline")
        content = msg.find_all('dd', class_='sh_blog_passage')
    #     print (dates.text, end="  :  ")

        post = msg.find("dl")

        title = post.find("a")

        if TargetText not in title.text:
            #print(item, end = "  :  ")
            print(dates.text, end="  :  ")
            print(title.text, '\n')
            
            for pure in content :
                print(pure.text)
                print("\n")
    

    item += 10  # Next 10 items
    break
