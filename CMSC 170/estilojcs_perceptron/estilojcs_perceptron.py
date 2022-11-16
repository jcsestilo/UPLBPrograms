# Created by: Jan Coleen S. Estilo
# Section: X-1L
# Exercise 6: Perceptron

def main():
    # open the input file
    f = open("input.txt", "r")
    # open the output file
    fout = open("output.txt", "w")
    iterationCounter = 1

    # read the values
    learningRate = float(f.readline().strip("\n"))
    threshold = float(f.readline().strip("\n"))
    bias = int(f.readline().strip("\n"))

    matrix = []
    for row in f:
        matrix.append(list(map(int, row.strip("\n").split(" "))))
    # current state of array
    # x1, ..., xn, z

    # make the bias, weights, a, and y columns
    numOfRows = len(matrix)
    numOfX = len(matrix[0])-1 # get the number of X
    # check if valid number of x
    if(numOfX<2):
        print("Invalid amount of X.")
        exit()
    # mat = [[0 for _ in range(cols)] for _ in range(rows)]

    biasMatrix = [bias for _ in range(0,numOfRows)] # create a 1d array with bias only
    weightsMatrix = [[0 for _ in range(0, numOfX+1)] for _ in range(0, numOfRows+1)] # 2d array in weights & weights bias, 
    # additional row for the last adjusted weight
    aMatrix = [0 for _ in range(0,numOfRows)]  # create a 1d array of 'a' matrix
    yMatrix = [0 for _ in range(0,numOfRows)] # create a 1d array of 'y' matrix

    converged = False
    rowCounter = 0
    # print(matrix)
    # print(biasMatrix[rowCounter][0])
    while converged==False:

        for _ in range(0,numOfRows):

            # STEP 2. A. COMPUTE THE PERCEPTRON VALUE
            a = 0
            for i in range(0, numOfX): # the number of x == weights
                a = a + (matrix[rowCounter][i] * weightsMatrix[rowCounter][i])
            # after computing for the weights, add computation for bias
            a = round(a + (biasMatrix[rowCounter] * weightsMatrix[rowCounter][-1]), 1)
            # change the value of a for that row
            aMatrix[rowCounter] = a
            
            # STEP 2. B. DETERMINE Y CLASSIFICATION
            if(float(aMatrix[rowCounter]) >= threshold):
                yMatrix[rowCounter] = 1

            # STEP 2. C. ADJUST THE WEIGHTS
            adjustedWeightRow = rowCounter+1 # move onto the next row
            for j in range(0,len(weightsMatrix[0])-1): # we will not compute the adjusted bias here
                # adjust the weights of the next row
                weightsMatrix[adjustedWeightRow][j] = round(weightsMatrix[rowCounter][j] + ( learningRate * matrix[rowCounter][j] * (matrix[rowCounter][-1] - yMatrix[rowCounter])), 1)
            # after adjusting weights, adjust the weight bias
            weightsMatrix[adjustedWeightRow][-1] = round(weightsMatrix[rowCounter][-1] + (learningRate * biasMatrix[rowCounter] * (matrix[rowCounter][-1] - yMatrix[rowCounter])), 1)
            rowCounter += 1 # move onto the next row
        # STEP 3. CHECK IF THE WEIGHTS HAVE CONVERGED
        # for every column  in the weights matrix
        convergedList = [] # for every column, we store here the boolean values if the column converges or not
        for i in range(0, len(weightsMatrix[0])): # column
            column = [weightsMatrix[j][i] for j in range(1, len(weightsMatrix))] # get the ith column 2nd row to the last row
            # check if the elements in column are all the same
            res = False
            res = all(element == column[0] for element in column)
            convergedList.append(res)
            # print(column)
        
        # print to our output file
        fout.write("Iteration %s\n" % (iterationCounter))
        # print the headers
        for i in range(0,numOfX):
            fout.write("x%d\t\t" % (i))
        fout.write("b\t\t")
        for i in range(0, numOfX):
            fout.write("w%d\t\t" % (i))
        fout.write("wb\t\ta\t\ty\t\tz\n")
        iterationCounter += 1
        # print the matrix
        for i in range(0,numOfRows):
            for element in matrix[i][:-1]:
                fout.write("%s\t\t" % (element))
            fout.write("%s\t\t" % (bias)) # since value of bias is the same all throughout
            for element in weightsMatrix[i]:
                fout.write("%s\t\t" % (element))
            fout.write("%s\t\t%s\t\t%s" % (aMatrix[i], yMatrix[i], matrix[i][-1]))
            fout.write("\n")
        # fout.write("Final Weights: %s\n" % (weightsMatrix[-1]))

        # loop through the convergedList, if we encounter 1 false, not yet converged
        for boolean in convergedList:
            if boolean==False:
                converged=False
                rowCounter = 0
                # make the last row of weightsMatrix = first row of weightsMatrix
                # weightsMatrix[0] = weightsMatrix[-1]
                adjustedWeights = weightsMatrix[-1]
                weightsMatrix.clear()
                weightsMatrix.append(adjustedWeights)
                for _ in range(0, numOfRows):
                    weightsMatrix.append([0 for _ in range(0,numOfX+1)])
                # also reset a and y matrix
                aMatrix.clear()
                yMatrix.clear()
                aMatrix = [0 for _ in range(0,numOfRows)]
                yMatrix = [0 for _ in range(0,numOfRows)]
                break
            else:
                converged=True
        # print("Converged: ", converged)
        # print("Weights matrix: ", weightsMatrix)

        

    f.close()
    fout.close()
    

main()