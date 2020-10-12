# -*- coding:utf-8 -*-
import MySQLdb

try:

    conn = MySQLdb.connect(db="handyfarm", host="127.0.0.1", user="farmplant", passwd="handyfarm", charset="utf8")
    cur = conn.cursor()

    query = "select * from real_time_info"
    cur.execute(query)

    for data2 in cur.fetchall():
        print(data2)
    conn.close()

except  MySQLdb.Error as err:
    print(err)
