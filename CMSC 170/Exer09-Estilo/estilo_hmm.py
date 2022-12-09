# Created by: Jan Coleen S. Estilo
# Exercise 09: Hidden Markov Chains

def main():
    # read the input file
    finput = open("data/hmm.in", "r")
    # how many strings
    stringsAmount = int(finput.readline().strip("\n"))
    # the strings
    strings = []
    for _ in range(stringsAmount):
        strings.append(finput.readline().strip("\n"))
    states = []
    states.append(finput.readline().strip("\n").split(" "))
    print(states)
main()
