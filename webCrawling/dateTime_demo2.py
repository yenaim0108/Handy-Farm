from datetime import date

today = date.today()
print(today.year)
print(today.month)
print(today.day)

#문자열로 변환
print(str(today.year) + '.' + str(today.month) + '.' + str(today.day))