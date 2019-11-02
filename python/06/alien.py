alien_0 = {'color': 'green', 'points': 5}
print(alien_0['color'])
print(alien_0['points'])
print(alien_0)

new_points = alien_0['points']
print("You just earned " + str(new_points) + " points!")

alien_0['x_position'] = 0
alien_0['y_position'] = 25
print(alien_0)

#添加字段
alien_0 = {}
alien_0['color'] = 'green'
alien_0['points'] = 5
print(alien_0)

#删除字典
del alien_0['points']
print(alien_0)

#遍历字典
#历所有的键 　遍历所有的键—值对 值对
for k,v in alien_0.items():
    print("k:"+k)
    print("v:"+v)

#遍历字典中的所有键
for k in alien_0.keys():
    print("k:"+k)

#安装顺序遍历字典中的所有键
for k in sorted(alien_0.keys()):
    print("k:"+k)

#安装顺序遍历字典中的所有值
for v in sorted(alien_0.values()):
    print("v:"+v)

#修改字典

alien_0['color'] = 'color5'
print(alien_0)




