
# Author: Jan Coleen S. Estilo
# Exercise 4: Bag of Words

# tokenize
# clean
# count
from operator import index
import re

words = []
wordsCleaned = []
myWordsTable = [] # list of dictionaries {word, frequency}
wordsInWordsTable = []

def openInputFile():
    f = open("data/002.txt", "r")
    line = []
    for row in f:
        line.clear()
        line = row.split()
        for word in line:
            words.append(word)
    f.close()

def clean():
    pattern = re.compile('[^a-zA-Z0-9]')
    # print(words)
    for i in range(0,len(words)):
        # change that element into the cleaned version
        modified = re.sub(pattern, '', words[i])
        words[i] = modified[:]
    for word in words:
        if word:
            wordsCleaned.append(word.lower())

def count():
    for word in wordsCleaned:
        if(word not in wordsInWordsTable): # if the word is newly encountered...
            wordsInWordsTable.append(word) # we mark this word as encountered

            # create a dictionary
            dictionary = {
                "word": word,
                "frequency": 1
            }

            # append this newly created dictionary to myWordsTable
            myWordsTable.append(dictionary)
        else: # if we already encountered this word
            # get the index of this word in encountered words since they have the same index with dict list
            wordIndex = wordsInWordsTable.index(word)
            myWordsTable[wordIndex]["frequency"] += 1
    # print(myWordsTable)

def outputToFile():
    f = open("output.txt", "w")
    f.write("Dictionary Size: " + str(len(myWordsTable)) + "\n")
    f.write("Total Number of Words: " + str(len(wordsCleaned)) + "\n")
    for dictionary in myWordsTable:
        f.write(dictionary['word'] + " " + str(dictionary['frequency']) + "\n")


def main():
    # open the file
    openInputFile()

    # clean the words
    clean()

    # count the words
    count()

    # print to the output file
    outputToFile()
main()