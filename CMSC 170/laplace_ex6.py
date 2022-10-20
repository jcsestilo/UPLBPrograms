# Author: Jan Coleen S. Estilo
# Exercise 4: Spam Filtering Using A Naïve Bayes Classifier with Laplace Smoothing

import os

import numpy
import decimal
import re

wordsCleaned = []
words = []
myWordsTable = [] # list of dictionaries {word, frequency}
wordsInWordsTable = []
ham_words = []
ham_wordsCleaned = []
ham_myWordsTable = [] # list of dictionaries {word, frequency}
ham_wordsInWordsTable = []
spam_words = []
spam_wordsCleaned = []
spam_myWordsTable = [] # list of dictionaries {word, frequency}
spam_wordsInWordsTable = []
classifyFiles = []
hamDictionarySize = 0
hamTotalWords = 0
spamDictionarySize = 0
spamTotalWords = 0

pSpam = 0.0
pHam = 0.0

wordsCleanedSet = {}

def openInputFile(path,fileName):
    f = open(path+"/"+fileName, "r")
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
            if(word=='subject'):
                next
            else:
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

def reset():
    # reset
    words.clear()
    wordsCleaned.clear()
    myWordsTable.clear()
    wordsInWordsTable.clear()

def main():
    # get k
    k = int(input("Enter k: "))
    data = "data01"
    # get the BOW of ham
    # get all the files in the ham folder
    path = ".//data//"+data+"//ham"
    hamFiles = os.listdir(path)

    # first, get all the words in each file
    for filename in hamFiles:
        openInputFile(path, filename)
    # put the words in ham_words
    ham_words = words.copy()
    # clean the words
    clean()
    ham_wordsCleaned = wordsCleaned.copy()
    # count
    count()
    ham_myWordsTable = myWordsTable.copy()
    ham_wordsInWordsTable = wordsInWordsTable.copy()
    hamDictionarySize = len(ham_myWordsTable)
    hamTotalWords = len(wordsCleaned)

    reset()

    # done getting the bag of words of ham, now spam
    path = ".//data//"+data+"//spam"
    spamFiles = os.listdir(path)
    for filename in spamFiles:
        openInputFile(path, filename)
    spam_words = words.copy()
    clean()
    spam_wordsCleaned = wordsCleaned.copy()
    count()
    spam_myWordsTable = myWordsTable.copy()
    spam_wordsInWordsTable = wordsInWordsTable.copy()
    spamDictionarySize = len(spam_myWordsTable)
    spamTotalWords = len(wordsCleaned)
    
    reset()

    path = ".//data//"+data+"//classify"
    classifyFiles = os.listdir(path)

    # probability of spam = count of spam + k / count of spam union ham + 2k
    # get the union of ham and spam
    spamSet = set(spam_wordsInWordsTable)
    hamSet = set(ham_wordsInWordsTable)
    spamUnionHam = spamSet | hamSet
    dictionarySize = len(spamUnionHam)
    pSpam = decimal.Decimal(spamTotalWords+k) / decimal.Decimal((dictionarySize) + (2*k))
    pHam = 1 - pSpam

    print("Spam Dictionary Size: ", spamDictionarySize, "Ham Dictionary Size: ", hamDictionarySize)
    print("total no. of words spam:", spamTotalWords, "total no. of words ham:", hamTotalWords)
    for filename in classifyFiles:
        fileNumber = filename[:4]
        pWGivenSpam = [] # list of all the pWGivenSpam per word
        pMessageGivenSpam = 0.0 # probability of P(message|Spam)
        countWInSpam = []
        pWGivenHam = []
        pMessageGivenHam = 0.0
        countWInHam = []
        pMessage = 0.0
        pSpamGivenMessage = 0.0

        # open file
        openInputFile(path, filename)
        clean()
        # our cleaned words in the classify file are now in wordsCleaned

        # P(w|Spam)
        # get the count of new words
        countOfNewWords = 0
        for word in wordsCleaned:
            if(word in spam_wordsInWordsTable):
                countOfNewWords += 1
                next
            elif (word in ham_wordsInWordsTable):
                countOfNewWords += 1
                next
        
        # get the word count of the word in spam dictionary
        for word in wordsCleaned:
            if(word in spam_wordsInWordsTable):
                index = spam_wordsInWordsTable.index(word)
                countWInSpam.append(spam_myWordsTable[index]["frequency"])
            else:
                countWInSpam.append(0) # the count of that word in spam is 0
        
        # for every word, get the P(w|Spam)
        for i in range(0, len(wordsCleaned)):
            pWSpam = (countWInSpam[i] + k) / spamDictionarySize + (k * (dictionarySize + countOfNewWords))
            pWGivenSpam.append(pWSpam)

        # after, get the P(message|Spam)
        pMessageGivenSpam = numpy.prod(pWGivenSpam)

        # next, compute for P(w|Ham)
        for word in wordsCleaned:
            if(word in ham_wordsInWordsTable):
                index = ham_wordsInWordsTable.index(word)
                countWInHam.append(ham_myWordsTable[index]["frequency"])
            else:
                countWInHam.append(0)
        # for every word, get the P(w|Ham)
        for i in range(0, len(wordsCleaned)):
            pwHam = (countWInHam[i] + k) / hamDictionarySize + (k * (dictionarySize + countOfNewWords))
            pWGivenHam.append(pwHam)
        
        #after, get the P(message|Ham)
        pMessageGivenHam = numpy.prod(pWGivenHam)

        # after getting the P(message|Spam) and P(message|Ham), get the P(message)
        pMessage = (pMessageGivenSpam * pSpam) + (pMessageGivenHam * pHam)

        # next, P(Spam|message)
        pSpamGivenMessage = (pMessageGivenSpam * pSpam) / pMessage
        print(fileNumber, pSpamGivenMessage)
        break

main()
