#웹 크롤링 초기코드
import urllib.request
from bs4 import BeautifulSoup

url = 'https://search.naver.com/search.naver?where=post&sm=tab_jum&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4'
req = urllib.request.urlopen(url)
res = req.read()

soup = BeautifulSoup(res, 'html.parser')

result = soup.find_all('li', class_='sh_blog_top')

for msg in result:

    content = msg.find_all('dd', class_='sh_blog_passage')

    TargetText = "문의"  # '문의'가 포함된 게시물을 가져오지 않는다.

    # for msg in result :

    dates = msg.find("dd", "txt_inline")
    #     print (dates.text, end="  :  ")

    post = msg.find("dl")

    title = post.find("a")

    if TargetText not in title.text:
        print(dates.text, end="  :  ")
        print(title.text, '\n')
        print(content)
        print(title['href'])
        print('\n')

#     print(title.text, '\n')
#     print(title['href'])
#     print(content)
#     #print(post)
#     print('\n')