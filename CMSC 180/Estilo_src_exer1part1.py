# Jan Coleen S. Estilo
# CMSC 180 - T7L
# Exercise 1 Part 1

import numpy as np
import random
import time

def terrain_inter(M, size):
    print(M)

    # Interpolate Elevation of points within the Bounding Box
    for i in range(0, size):
        for j in range(0, size):
            if not(i%10 == 0 and j%10 == 0):
                if i%10 == 0: # If the variable that's the same is the i 
                    x1 = (j//10)*10 # the left corner or the upper corner
                    x2 = x1+10 # the right corner or the lower corner
                    if(x2 >= size):
                        x2 = size-1
                    y1 = M[i][x1]
                    y2 = M[i][x2]
                    M[i][j] = y1 +  ((j-x1)/(x2-x1))* (y2-y1)
                elif j%10 == 0: # If the variable that's the same is the j
                    x1 = (i//10)*10 # the left corner or the upper corner
                    x2 = x1+10 # the right corner or the lower corner
                    if(x2 >= size):
                        x2 = size-1
                    y1 = M[x1][j]
                    y2 = M[x2][j]
                    M[i][j] = y1 +  ((i-x1)/(x2-x1))* (y2-y1)

    # Interpolate Elevation of points inside the Bounding Box
    for i in range(0, size):
        for j in range(0, size):
            if (i%10 != 0 and j%10 != 0):
                if i%10 != 0:
                    x1 = (j//10)*10
                    x2 = x1+10
                    if(x2 >= size):
                        x2 = size-1
                    y1 = M[i][x1]
                    y2 = M[i][x2]
                    M[i][j] = y1 +  ((j-x1)/(x2-x1))* (y2-y1)
                elif j%10 != 0:
                    x1 = (i//10)*10
                    x2 = x1+10
                    if(x2 >= size):
                        x2 = size-1
                    y1 = M[x1][j]
                    y2 = M[x2][j]
                    M[i][j] = y1 +  ((i-x1)/(x2-x1))* (y2-y1)

    return M

# Read n as a user input
n = int(input("Enter n: "))
size = n+1
# Create an empty matrix of size n
M = np.zeros((size,size))

# Assign random values of o to 1000 for grid points divisible by 10
for i in range(0, size):
    for j in range(0, size):
        if i%10==0 and j%10==0: # If the grid point x and y is divisible by 10
            M[i][j] = random.randint(0, 1000) # Get a random value from 0 to 1000

time_before = time.time() # time before
M = terrain_inter(M, size)
time_after = time.time() # time after
time_elapsed = time_after - time_before

print("lab01 < ", n)
print("time elapsed: ", time_elapsed)
print(M)