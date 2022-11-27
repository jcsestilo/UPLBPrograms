# global counter;
counter = 13
def checker(x,y):
	global counter
	for i in range(x,y):
		if(i%2==0):
			counter += 1


checker(7,11)
print(counter)