cars = ['audi', 'bmw', 'subaru', 'toyota']
for car in cars:
    if car == 'bmw':
        print(car.upper())
    else:
        print(car.title())

# 使用and 检查多个条件
age = 8
print(age == 8 and age == 8)
# 使用or 检查多个条件
print(age == 8 or age == 9)

#检查特定值是否包含在列表中
print('audi' in cars)
print('s' in cars)
#检查特定值是否不包含在列表中
if 's' not in cars:
    print("s not in")

car = 'subaru'
print("Is car == 'subaru'? I predict True.")
print(car == 'subaru')
print("\nIs car == 'audi'? I predict False.")
print(car == 'audi')

if age > 7 :
    print("age > 7")
elif age < 6:
    print("age <6")
else:
    print("else")


