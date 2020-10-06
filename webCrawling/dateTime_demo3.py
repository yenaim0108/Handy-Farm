import datetime

today = datetime.datetime.now()
start = today-datetime.timedelta(days=5)
print(today)
print(start)

print(str(today.year) + '.' + str(today.month) + '.' + str(today.day))
print(str(start.year) + '.' + str(start.month) + '.' + str(start.day))