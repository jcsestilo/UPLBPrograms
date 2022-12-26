# Created by: Jan Coleen S. Estilo
# Date created: Dec. 23, 2022
# Exercise 10: MinMax Algorithm
# References:
# https://www.geeksforgeeks.org/tic-tac-toe-game-with-gui-using-tkinter-in-python/
# https://www.youtube.com/watch?v=l-hh51ncgDI
# https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
# https://levelup.gitconnected.com/mastering-tic-tac-toe-with-minimax-algorithm-3394d65fa88f

from tkinter import *
from tkinter import messagebox

PlayerUser = 'X'
PlayerAI = 'O'
stop_game = False
winner = ""
NEG_INF = -1000
POS_INF = 1000

player = messagebox.askquestion("Tic Tac Toe", "Should the user go first?\nYes - User (X)\nNo - AI (O)")

if(player=="yes"):
    player = PlayerUser
elif(player=="no"):
    player = PlayerAI


# Design window
#Creating the Canvas
root = Tk()
# Title of the window            
root.title("Tic Tac Toe") 
# Attributes
root.resizable(0,0)
root.attributes('-topmost',True)

#Button
board = [
    ['_','_','_'],
    ['_','_','_'],
    ['_','_','_']]

#text for buttons
states = [
    ['_','_','_'],
    ['_','_','_'],
    ['_','_','_']]

def checkIfWin(b):
    # Checking for Rows for X or O victory.
    for row in range(3) :    
        if (b[row][0] == b[row][1] and b[row][1] == b[row][2]):       
            if (b[row][0] == PlayerUser):
                return "X"
            elif (b[row][0] == PlayerAI) :
                return "O"
    # Checking for Columns for X or O victory.
    for col in range(3):
        if (b[0][col] == b[1][col] and b[1][col] == b[2][col]) :
         
            if (b[0][col] == PlayerUser) :
                return "X"
            elif (b[0][col] == PlayerAI) :
                return "O"
 
    # Checking for Diagonals for X or O victory.
    if (b[0][0] == b[1][1] and b[1][1] == b[2][2]) :
     
        if (b[0][0] == PlayerUser) :
            return "X"
        elif (b[0][0] == PlayerAI) :
            return "O"
 
    if (b[0][2] == b[1][1] and b[1][1] == b[2][0]) :
     
        if (b[0][2] == PlayerUser) :
            return "X"
        elif (b[0][2] == PlayerAI) :
            return "0"
    if b[0][0] != "_" and b[0][1] != "_" and b[0][2] != "_" and b[1][0] != "_" and b[1][1] != "_" and b[1][2] != "_" and b[2][0] != "_" and b[2][1] != "_" and b[2][2] != "_":
        return "draw"
    # Else if none of them have won then return 0
    return 0

def changePlayer():
    global player
    if player==PlayerUser:
        player=PlayerAI
    else: 
        player=PlayerUser

def clicked(r,c):
    print("clicked function")
    # global player
    global board
    global states

    if player == "X" and states[r][c] == '_' and stop_game == False:
        board[r][c].configure(text = "X")
        states[r][c] = 'X'
        # player=PlayerAI
        # check_if_win()
        #   if not stop_game:
        #        bestMove = findBestMove(states)
        #        states[bestMove[0]][bestMove[1]] = "O"

    elif player == 'O' and states[r][c] == '_' and stop_game == False:
        board[r][c].configure(text = 'O')
        states[r][c] = "O"
        # player = PlayerUser
    
    winner = checkIfWin(states)
    # print(winner)
    if(winner==PlayerUser):
        print("player user won")
    elif(winner==PlayerAI):
        print("player ai won")
    elif(winner=="draw"):
        print("draw!")
    else:
        changePlayer()
    # print(player)
    # print(b)
    # print(states)



for i in range(3):
    for j in range(3):
                                            
        board[i][j] = Button(
                        height = 4, width = 8,
                        font = ("Helvetica","20"),
                        command = lambda r = i, c = j : clicked(r,c))
        board[i][j].grid(row = i, column = j)
 
 
mainloop()