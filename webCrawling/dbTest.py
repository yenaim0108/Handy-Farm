 # -*- coding:utf-8 -*-
import MySQLdb

try :
    
    db = MySQLdb.connect(db="handyfarm", host="127.0.0.1", user="root", passwd="s218210050", charset="utf8")
    cur = db.cursor()


    query = "select * from real_time_info"
    cur.execute(query)
    data = cur.fetchall()

    print(data)

except  MySQLdb.Error as err :
    print(err)
