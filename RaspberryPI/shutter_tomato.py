import urllib.request
from bs4 import BeautifulSoup
import time

url = 'https://www.shutterstock.com/ko/search/similar/245807926'
req = urllib.request.urlopen(url)

time.sleep(1)

res = req.read()

soup = BeautifulSoup(res, 'html.parser')

result = soup.find('div', class_='z_g_d65b1')

img = result.find_all('div', class_='z_g_63ded')

serial = 51
for tomato in img:

    try:
        link = tomato.find('img', class_='z_h_9d80b')

        print(link['alt'], end='  ====  ')

        image = link['src']
        print(image)
        time.sleep(1)


    except:

        break

    urllib.request.urlretrieve(image, "D:\document\\hywu\\graduation portfolio\\04. development\\RaspberryPI\\tomato\\tomato" + str(serial) + ".jpg")
    serial += 1