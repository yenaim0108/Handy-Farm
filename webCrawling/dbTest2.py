# -*- coding:utf-8 -*-
import MySQLdb

try:

    conn = MySQLdb.connect(db="handyfarm", host="127.0.0.1", user="farmplant", passwd="handyfarm", charset="utf8")
    cur = conn.cursor()
    c_link = "www.naver.com/kkkkkkk"
    c_title = "제발 성공해라"
    c_content = "너무 어렵네요,,졸업하기가 너무 힘들다"
    c_date = "2020.07.20."
    image_URL = "이미지링크입니다."


    query = "insert into real_time_info values(' " + c_link + "','병충해', '0', '" + c_title + " ', ' " + c_content + "', ' " + c_date + "', '" + image_URL + "');"
    # query = "insert into real_time_info values('test link', '병충해', '0', '제목입니다', '너무어렵네요', '20.02.01', '이미지')"
    cur.execute(query)
    conn.commit()
    print("성공")

except  MySQLdb.Error as err:
    print(err)
