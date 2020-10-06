##실행 전 주의!!! 무한루프로 인해 멈추지 않음!!##
import urllib.request
from bs4 import BeautifulSoup
import time

item = 1
while True:
    try:
        url = 'https://search.naver.com/search.naver?' \
              'date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=' \
              '&post_blogurl_without=&query=' \
              '%ED%86%A0%EB%A7%88%ED%86%A0%20%EB%B3%91%EC%B6%A9%ED%95%B4&sm=' \
              'tab_pge&srchby=all&st=sim&where=post&start='
        url = url + str(item)
        req = urllib.request.urlopen(url)

    except:
        break

    time.sleep(1)

    res = req.read()

    soup = BeautifulSoup(res, 'html.parser')

    result = soup.find_all('li', class_='sh_blog_top')

    content = soup.find_all('dd', class_='sh_blog_passage')

    TargetText = "문의"  # '문의'가 포함된 게시물을 가져오지 않는다.

    for msg in result:
        dates = msg.find("dd", "txt_inline")
        content = msg.find_all('dd', class_='sh_blog_passage')
        #     print (dates.text, end="  :  ")

        post = msg.find("dl")

        title = post.find("a")

        if TargetText not in title.text:
            print(dates.text, end="  :  ")
            print(title.text, '\n')

            for pure in content:
                print(pure.text)
                print("\n")
            print(title['href'])
            print('\n')

    page = soup.find('div', 'paging')
    next = page.find_all('a', 'next')
    item += 10  # Next 10 items

