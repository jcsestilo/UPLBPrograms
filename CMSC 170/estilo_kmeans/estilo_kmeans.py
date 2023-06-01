# Created by: Jan Coleen S. Estilo
# Section: X-1L
# Exercise 8: KMeans Clustering
# References:
# https://www.tutorialspoint.com/how-to-make-a-scatter-plot-for-clustering-in-python

import math
import random
import matplotlib.pyplot as plt

def main():
    # open and read the file
    file = open("data/Wine.csv", "r")
    # get the attributes
    attributes = file.readline().strip("\n").split(",")
    # print the attributes
    print("===== ATTRIBUTES =====")
    for i in range(0, len(attributes)):
        print(f"[{i}] - {attributes[i]}")
    print()

    # user input
    attribute = []
    attribute.append(int(input("Enter attribute 1: ")))
    attribute.append(int(input("Enter attribute 2: ")))
    k = int(input("Enter n clusters: "))

    # validation
    if(attribute[0] == attribute[1]):
        print("Attribute 1 and Attribute 2 should not be the same.")
        exit(0)
    elif((attribute[0] >= len(attributes) or attribute[0] < 0) or (attribute[1] >= len(attributes) or attribute[1] < 0)):
        print("Please enter the right attribute index.")
        exit(0)
    
    # a list containing the plots in our set
    plot = []
    lines = file.readlines()
    for l in lines:
        table = list(map(float, l.strip("\n").split(",")))
        # print(table)
        plot.append([table[attribute[0]], table[attribute[1]]])

    # randomize k plots
    centroidsIndex = []     # contains the indexes of our randomized k plots
    centroids = []
    # randomize k plots
    while(len(centroidsIndex) != k):
        randomized = random.randint(0, len(plot))
        if randomized in centroidsIndex:
            continue
        centroidsIndex.append(randomized)
        centroids.append(plot[randomized])
    # centroidsIndex.append(11.76)
    # centroidsIndex.append(2)
    # centroids.append([11.76, 2.68])
    # centroids.append([13.77, 1.9])
    print(centroids)
    # print(centroidsIndex)
    orig_centroids = centroids.copy() # will contain the original centroids because centroids list will be changing per loop
    centroid_history = []
    centroidsChanging = True
    # print(centroids, centroidsIndex)
    while(centroidsChanging):
        clusters = [[orig_centroids[i]] for i in range(0,len(centroids))] # 2d array, per element is a list containing the plots belonging to that centroid
        # print(clusters)
        # a. Correspond data points to the nearest cluster (compute distance to each cluster’s centroid, then choose closest cluster).
        # compute distance to each cluster’s centroid
        # print(centroids)
        # print(plot)
        for i in range(0, len(plot)):
            distances = []

            for d in range(0, len(centroidsIndex)):
                # if we are evaluating a point that is also that centroid
                # if (centroidsIndex[d] == i):
                #     continue

                distance = 0.0
                # euclidean distance
                for j in range(0, len(centroids[0])-1):
                    distance = distance + (plot[i][j] - centroids[d][j])**2
                distance = math.sqrt(distance)
                
                # append a tuple, where d is the index for that particular centroid
                distances.append((centroidsIndex[d], distance))
            print(distances)
            # then choose closest cluster
            # sort the distances list (list of tuples) according to the second element of each tuple (distance)
            sorted_distances = sorted(distances, key=lambda t: t[1])
            print(sorted_distances)
            closest_centroid = sorted_distances[0] # first element is the closest centroid (index in plot of the closest centroid, distance)
            
            # centroidsIndex.index(closest_centroid[0])
            #     - 0 to k. gives the index of the value in the centroidsIndex array
            #     - ex. plot[i] is closest to centroid 0. therefore, append it to clusters list in index 0
            clusters[centroidsIndex.index(closest_centroid[0])].append(plot[i])
        # print(clusters)
        # b. Update centroids by averaging corresponding coordinates of the feature vectors. 
        new_centroids = []
        # clusters is a 3d array, each element is a list of points for that particular cluster. [   [   [],[],[]    ] , [   [],[],[]    ]   ]
        for q in range(len(clusters)):
            class_count = len(clusters[q]) # get the class count
            averages = []
            for g in range(len(clusters[q][0])): # per element, x, y
                sum = 0.0
                for p in range(0, len(clusters[q])): # per column
                    sum += clusters[q][p][g]
                averages.append(sum/class_count)
            new_centroids.append(averages)

        # check if the centroids are changing
        for c in range(len(centroids)):
            if(centroids[c] != new_centroids[c]):
                centroidsChanging = True
                break
            else:
                centroidsChanging = False
        # print(clusters)
        centroid_history.append(new_centroids)
        if(centroidsChanging):
            centroids.clear()
            clusters.clear()
            centroids = new_centroids.copy()
    
    # print to output.csv
    foutput = open("output.csv", "w", newline='')
    foutput.write(f"Attr1:{attributes[attribute[0]]}\n")
    foutput.write(f"Attr2:{attributes[attribute[1]]}\n")
    foutput.write(f"n = {k}\n")
    foutput.write(f"Initial,{orig_centroids}\n")
    for i in range(len(centroid_history)):
        foutput.write(f"{i+2}th Centroid: {centroid_history[i]}\n")
    for i in range(len(centroids)):
        foutput.write(f"Centroid: {i},{centroids[i]}")
        for j in range(len(clusters[i])):
            foutput.write(f"{clusters[i][j]}\n")
    
    # generate scatterplot
    fig = plt.figure()
    colors = []
    x = []
    y = []
    for i in range(len(clusters)):
        for point in clusters[i]:
            # print(point)
            x.append(point[0])
            y.append(point[1])
            colors.append(i)
    ax = fig.add_subplot(111)
    ax.scatter(x, y, c=colors, s=10)
    plt.savefig("scatterplot")

main()