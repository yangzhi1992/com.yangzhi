student_name = ['a', 'b', 'c'];

print(student_name[0]);
print(student_name[0].title());

# Python为访问最后一个列表元素提供了一种特殊语法。通过将索引指定为-1 ，可让Python返回最后一个列表元素：
print(student_name[-1].title());

# 修改列表元素
student_name[0] = 'aa';
print(student_name[0]);

# 在列表末尾添加元素
student_name.append("dd");
print(student_name);

# 在列表中插入元素:insert() 可在列表的任何位置添加新元素
student_name.insert(1, "ab");
print(student_name);

# 从列表中删除元素
del student_name[0];
print(student_name);

# 使用方法pop() 删除元素:方法pop() 可删除列表末尾的元素，并让你能够接着使用它。术语弹弹出出 （pop）源自这样的类比：列表就像一个栈，而删除列表末尾的元素相当于弹出栈顶元素。
pop_s = student_name.pop(1);
print(pop_s);
print(student_name);

# 根据值删除元素
student_name.remove('dd');
print(student_name);

# 使用方法sort() 对列表进行永久性排序
student_name = ['c', 'd', 'b', 'c'];
student_name.sort();
print(student_name);

# 使用方法sort() 对列表进行进行临时排序
student_name = ['c', 'd', 'b', 'c'];
sorted(student_name);
print(student_name);

# 倒着打印列表
student_name.reverse()
print(student_name);

# 确定列表的长度
print(len(student_name));

# 遍历整个列表
for s in student_name: print(s);
for s in student_name: print(s + "end");
print("end")

# 使用函数range()
for value in range(1, 5):
    print("1"+str(value));

for value in range(2, 9, 1):
    print(value);

#对数字列表执行简单的统计计算
print(min(student_name))

print(max(student_name))

print(sum(range(1, 5)))
