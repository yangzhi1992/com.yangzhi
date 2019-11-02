squares = [value**2 for value in range(1,11)]
print(squares)

#切片
players = ['charles', 'martina', 'michael', 'florence', 'eli']
print(players[0:3])

print(players[:4])

print(players[2:])

#复制列表

my_foods = ['pizza', 'falafel', 'carrot cake']
friend_foods = my_foods[:]
print("My favorite foods are:")
print(my_foods)
print("\nMy friend's favorite foods are:")
print(friend_foods)
