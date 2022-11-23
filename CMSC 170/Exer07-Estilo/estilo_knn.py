# Created by Jan Coleen S. Estilo
# Date: Nov 24, 2022
# Description: Exercise 7: K Nearest Neighbors

import math
from collections import Counter

def main():
    file_input = open("data/input.in", "r")
    file_diabetes = open("data/diabetes.csv", "r")

    # file_input = open("data02/input.in", "r")
    # file_diabetes = open("data02/fruits.csv", "r")

    file_output = open("output.txt", "w")

    # STEP 1. Provide a k and a feature vector x.
    k = int(input("Enter k: "))
    x = [] # this is our feature vector

    # read the input.in file
    # The test file contains information regarding the number of pregnancies, glucose value, blood pressure, skin thickness, insulin value, bmi, diabetes pedigree function, age, and outcome of a person
    rows_input = file_input.readlines()
    for row in rows_input:
        x.append(list(map(float, row.strip("\n").split(","))))
    
    # read the diabetes.csv file
    rows_diabetes = file_diabetes.readlines()
    diabetes = []
    for row in rows_diabetes:
        diabetes.append(list(map(float,row.strip("\n").split(","))))
    
    # STEP 2. For each feature vector v in the training set, compute the Euclidean distance.
    distances = []
    for item in x:
        for d in range(0,len(diabetes)): # for all the data in diabetes.csv, determine their distance
            distance = 0.0
            # euclidean distance
            for i in range(0,len(item)):
                distance = distance + (item[i]-diabetes[d][i])**2
            distance = math.sqrt(distance)
            # append a tuple, where d is the index for that particular distance
            distances.append((d, distance))
            diabetes[d].append((d, distance))

        # STEP 3. If it is one of the k nearest neighbors, it is remembered.
        # sort the distances list (list of tuples) according to the second element of each tuple (distance)
        sorted_distances = sorted(distances, key=lambda t: t[1])
        # now that we have sorted the distances, let us get the 0 to k element to determine the k nearest neighbors
        k_nearest_neighbors = []
        for i in range(0,k):
            k_nearest_neighbors.append(sorted_distances[i])
        
        # STEP 4. The class with the maximum count will be the classiﬁcation of x.
        classifications = []
        for pair in k_nearest_neighbors:
            index = pair[0] # pair = (index of distance, distance)
            classification = diabetes[index][-2] # second to the last element is the classification
            classifications.append(classification)
        
        # https://docs.python.org/3/library/collections.html#collections.Counter 
        # the first element in class_count is the most common
        class_count = Counter(classifications).most_common(k)
        item.append(class_count[0][0])

        # print to the output file
        for data in item:
            file_output.write("%s, " % (data))
        file_output.write("\n")
        # print(item)
        
        # afterwards, pop the last element in diabetes (the distance) and clean distances list
        for d in diabetes:
            d.pop()
        distances.clear()
        
    print("Done! Check out output.txt")
    file_input.close()
    file_diabetes.close()
    file_output.close()
main()