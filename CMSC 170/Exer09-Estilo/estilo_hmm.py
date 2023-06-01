# Created by: Jan Coleen S. Estilo
# Exercise 09: Hidden Markov Chains
# References:
# https://www.geeksforgeeks.org/python-count-overlapping-substring-in-a-given-string/

def CountOccurrences(string, substring):
  
    # Initialize count and start to 0
    count = 0
    start = 0
  
    # Search through the string till
    # we reach the end of it
    while start < len(string):
  
        # Check if a substring is present from
        # 'start' position till the end
        pos = string.find(substring, start)
  
        if pos != -1:
            # If a substring is present, move 'start' to
            # the next position from start of the substring
            start = pos + 1
  
            # Increment the count
            count += 1
        else:
            # If no further substring is present
            break
    # return the value of count
    return count

def main():
    finput = open("data/hmm.in", "r")
    foutput = open("hmm.out", "w")
    # READ INPUT FILE
    # how many strings
    stringsAmount = int(finput.readline().strip("\n"))
    # strings
    strings = []
    for _ in range(stringsAmount):
        strings.append(finput.readline().strip("\n"))
    # states
    states = finput.readline().strip("\n").split(" ")
    # measurement variables
    measurement_variables = finput.readline().strip("\n").split(" ")
    # the probabilities
    probabilities = [] # list of tuples ("_|_", value)
    for i in range(len(states)):
        values = finput.readline().strip("\n").split(' ')
        for j in range(len(measurement_variables)):
            probabilities.append((f"p{measurement_variables[j]}given{states[i]}", float(values[j])))
    cases = int(finput.readline())
    tests = []
    for _ in range(cases):
        tests.append(finput.readline().strip())

    # get the max subscript among all the test cases because from here we will solve the pS<n>, pT<n>, etc
    max_subscript = int(tests[0][1]) # 'S1 given E1', will take 1
    for case in tests:
        temp = int(case[1])
        if(temp>max_subscript):
            max_subscript = temp

    # probabilities list: the values we get from the file input
    # computed_prob list: the values that we compute of the first state; changes for every string
    # START FUNCTIONAL LOOP
    for myStr in strings:
        # write the string to the output file
        foutput.write(f"{myStr}\n")

        computed_prob = []
        computed_prob = probabilities.copy()
        # get probability of S sub 0 and T sub 0
        if(myStr[0] == 'S'):
            computed_prob.append(("pS0", 1))
            computed_prob.append(("pT0", 0))
        else:
            computed_prob.append(("pS0", 0))
            computed_prob.append(("pT0", 1))
        # get the transition values
        computed_prob.append(("pSgivenS", CountOccurrences(myStr, 'SS')/(CountOccurrences(myStr, 'SS') + CountOccurrences(myStr, 'ST'))))
        computed_prob.append(("pTgivenS", CountOccurrences(myStr, 'ST')/(CountOccurrences(myStr, 'SS') + CountOccurrences(myStr, 'ST'))))
        computed_prob.append(("pTgivenT", CountOccurrences(myStr, 'TT')/(CountOccurrences(myStr, 'TS') + CountOccurrences(myStr, 'TT'))))
        computed_prob.append(("pSgivenT", CountOccurrences(myStr, 'TS')/(CountOccurrences(myStr, 'TS') + CountOccurrences(myStr, 'TT'))))
        
        # FORMULAS
        # pS<n> = pS<n>givenS<n-1> * pS<n-1> + pS<n>givenT<n-1> * pT<n-1>
        # pT<n> = pT<n>givenS<n-1> * pS<n-1> + pT<n>givenT<n-1> * pT<n-1>
        # pS<n>givenE<n> = (pE<n>given<S> * pS<n>) / pE<n>
        # pE<n> = (pE<n>givenS<n> * pS<n>) + (pE<n>givenT<n> * pT<n>)
        # pF<n> = (pF<n>givenS<n> * pS<n>) + (pF<n>givenT<n> * pT<n>)

        # print(*computed_prob, sep="\n")
        # print("=======")
        # solve for the pS<n> and pT<n> until we are finished solving to the max_subscript
        for i in range(1,max_subscript+1):
            #          first             second     third              fourth
            # pS<n> = pS<n>givenS<n-1> * pS<n-1> + pS<n>givenT<n-1> * pT<n-1>
            # solve for pS<i> and append it to computed_prob
            first_S = [item[1] for item in computed_prob if item[0] == "pSgivenS"][0]
            second_S = [item[1] for item in computed_prob if item[0] == f"pS{i-1}"][0]
            third_S = [item[1] for item in computed_prob if item[0] == "pSgivenT"][0]
            fourth_S = [item[1] for item in computed_prob if item[0] == f"pT{i-1}"][0]
            computed_prob.append((f"pS{i}", (first_S * second_S)+(third_S * fourth_S) ))

            #          first             second     third              fourth
            # pT<n> = pT<n>givenS<n-1> * pS<n-1> + pT<n>givenT<n-1> * pT<n-1>
            # solve for pS<i> and append it to computed_prob
            first_T = [item[1] for item in computed_prob if item[0] == "pTgivenS"][0]
            second_T = [item[1] for item in computed_prob if item[0] == f"pS{i-1}"][0]
            third_T = [item[1] for item in computed_prob if item[0] == "pTgivenT"][0]
            fourth_T = [item[1] for item in computed_prob if item[0] == f"pT{i-1}"][0]
            computed_prob.append((f"pT{i}", (first_T * second_T)+(third_T * fourth_T) ))


        # solve for the pE<n> and pF<n> until we are finished solving to the max_subscript
        for i in range(1,max_subscript+1):
            #          first            second     third            fourth
            # pE<n> = (pE<n>givenS<n> * pS<n>) + (pE<n>givenT<n> * pT<n>)
            # pF<n> = (pF<n>givenS<n> * pS<n>) + (pF<n>givenT<n> * pT<n>)
            first_E = [item[1] for item in computed_prob if item[0] == "pEgivenS"][0]
            second_E = [item[1] for item in computed_prob if item[0] == f"pS{i}"][0]
            third_E = [item[1] for item in computed_prob if item[0] == "pEgivenT"][0]
            fourth_E = [item[1] for item in computed_prob if item[0] == f"pT{i}"][0]
            computed_prob.append((f"pE{i}", (first_E * second_E)+(third_E * fourth_E) ))

            first_F = [item[1] for item in computed_prob if item[0] == "pFgivenS"][0]
            second_F = [item[1] for item in computed_prob if item[0] == f"pS{i}"][0]
            third_F = [item[1] for item in computed_prob if item[0] == "pFgivenT"][0]
            fourth_F = [item[1] for item in computed_prob if item[0] == f"pT{i}"][0]
            computed_prob.append((f"pF{i}", (first_F * second_F)+(third_F * fourth_F) ))

        # the test cases
        # we will put here the results per string
        for case in tests:
            vars = case.split(' ') # format example: ['S1', 'given', 'E1']

            #                  first            second    third
            # pS<n>givenE<n> = (pE<n>given<S> * pS<n>) / pE<n>

            # get the pE<n>given<S> part in the formula
            first = [item[1] for item in probabilities if item[0] == f"p{vars[2][0]}given{vars[0][0]}"][0]
            second = [item[1] for item in computed_prob if item[0] == f"p{vars[0]}"][0]
            third = [item[1] for item in computed_prob if item[0] == f"p{vars[2]}"][0]

            result = format((first * second) / third, '.10f')
            foutput.write(f'{case} = {result[0:6]}\n')

    print("Done! Check hmm.out")
    finput.close()
    foutput.close()

main()
