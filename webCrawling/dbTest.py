 # -*- coding:utf-8 -*-
import   MySQLdb

try :
    
    db = MySQLdb.connect(db="test", host="127.0.0.1", user="root", passwd="s218210050")

    cs = db.cursor()

    cs.execute("select count(*) from tbl")

    data = cs.fetchall()

    print (data)

except  MySQLdb.Error as err :
    print (err)
