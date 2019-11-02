#def 定义一个函数 函数定义
def greet_user():
    """显示简单的问候语"""
    print("Hello!")
greet_user()

def greet_user(name):
    print(name)
greet_user("yangzhi")
greet_user(name = "yangzhi2")

#添加参数默认值
def greet_user1(age,name='zhi'):
    print(name)
greet_user1(age='',)

#返回简单值
def greet_user2(age,name='zhi'):
    print(name)
    return 'return'

retult = greet_user2(age='')
print(retult)

#禁止函数修改列表
list = ['a']
list2 = list[:]
def print_models(list):
    print("s")

print_models(list2)