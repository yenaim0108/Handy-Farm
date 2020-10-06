import urllib.request
from bs4 import BeautifulSoup
url = 'https://search.naver.com/search.naver?where=post&sm=tab_jum&query=%ED%86%A0%EB%A7%88%ED%86%A0+%EB%B3%91%EC%B6%A9%ED%95%B4'
req = urllib.request.urlopen(url)
res = req.read()

soup = BeautifulSoup(res, 'html.parser')

result = soup.find_all('li', class_='sh_blog_top')

for msg in result:

    content = msg.find_all('dd', class_='sh_blog_passage')

    for pure in content :

        print(pure.text)
        print("\n")