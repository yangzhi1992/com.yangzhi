# 使用try-except 代码块
try:
    print(5/0)
except ZeroDivisionError:
    print("You can't divide by zero!")

try:
    result = 5/1;
except ZeroDivisionError:
    print("You can't divide by zero!")
else:
    print(result)

print("Give me two numbers, and I'll divide them.")
print("Enter 'q' to quit.")
while True:
    first_number = input("\nFirst number: ")
    if first_number == 'q':
        break
    second_number = input("Second number: ")
    if second_number == 'q':
        break
    answer = int(first_number) / int(second_number)
    print(answer)

