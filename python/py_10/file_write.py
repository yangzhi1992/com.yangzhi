# 写入文件
# 第一个实参也是要打开的文件的名称；
# 第二个实参（'w' ）告诉Python，
# 我们要以写入模式 写入模式 打开这个文件。打开文件 时，
# 可指定读取模式（'r' ）、
# 写入模式（'w' ）、
# 附加模式（'a' ）
# 或让你能够读取和写入文件的模式（'r+' ）。
# 如果你省略了模式实参，Python将以默认的只读模式打 开文件。
# 如果你要写入的文件不存在，函数open() 将自动创建它。
# 然而，以写入（'w' ）模式打开文件时千万要小心，
# 因为如果指定的文件已经存在，Python将在返回文件对象前清空 该文件。
# 注意 注意 　Python只能将字符串写入文本文件。要将数值数据存储到文本文件中，必须先使用函数str() 将其转换为字符串格式。
with open('D:\\NEWS1.txt', 'w') as file_object:
    file_object.write("I love programming.")
    file_object.write("yangz")

# 附加到文件
# 如果你要给文件添加内容，而不是覆盖原有的内容，可以附加模式 附加模式 打开文件。你以附加模式打开文件时，Python不会在返回文件对象前清空文件，而你写入到文件的行都将添加 到文件末尾。如果指定的文件不存在，Python将为你创建一个空文件。
with open('D:\\NEWS1.txt', 'a') as file_object:
    file_object.write("I love programming.\n")
    file_object.write("yangz\n")
