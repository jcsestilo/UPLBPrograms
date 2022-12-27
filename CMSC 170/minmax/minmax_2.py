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
    # findBestMove(states)

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
        findBestMove(states)
    # print(player)
    # print(b)
    # print(states)

# def utility(winner):

def terminal(board):
    winner = checkIfWin(board)
    if(winner == PlayerUser):
        print(10)
        return +1, True
    if(winner == PlayerAI):
        print(-10)
        return -1, True
    if(winner == "draw"):
        print(0)
        return 0, True
    else:
        return None, False

def minimax(board, depth, isMax, alpha, beta):
    # print("minimax function")

    val, isTerminal = terminal(board)
    # print(val, isTerminal)
    if(isTerminal):
        return val
    if isMax:
        best = NEG_INF

        for i in range(3):
            for j in range(3):
                # Check if cell is empty
                if (board[i][j]=='_') :
                 
                    # Make the move
                    board[i][j] = player
 
                    # Call minimax recursively and choose
                    # the maximum value
                    v = minimax(board, depth + 1, not isMax, alpha, beta)
                    best = max( best, v )
 
                    # Undo the move
                    board[i][j] = '_'

                    if v >= beta:
                        return best
                    alpha = max(alpha, best)
        return best
    else:
        best = POS_INF
 
        # Traverse all cells
        for i in range(3) :        
            for j in range(3) :
              
                # Check if cell is empty
                if (board[i][j] == '_') :
                 
                    # Make the move
                    board[i][j] = player
 
                    # Call minimax recursively and choose
                    # the minimum value
                    v = minimax(board, depth + 1, not isMax, alpha, beta)
                    best = min(best, v)

                    # Undo the move
                    board[i][j] = '_'

                    if v <= alpha:
                        return best
                    beta = min(beta, best)
 
        return best


# This will return the best possible move for the player
def findBestMove(b):
    print("findBestMove function")
    # print(board)
    # global player
    global board
    global states
    bestVal = -1000
    bestMove = (-1, -1)
    for i in range(3):    
        for j in range(3):
            # Check if cell is empty
            if (b[i][j] == '_'):
            
                # Make the move
                b[i][j] = player

                # compute evaluation function for this
                # move.
                moveVal = minimax(b, 0, False, NEG_INF, POS_INF)

                # Undo the move
                b[i][j] = '_'

                # If the value of the current move is
                # more than the best value, then update
                # best/
                print("moveVal", moveVal)
                if (moveVal > bestVal):               
                        bestMove = (i, j)
                        # states[i][j] = player
                        bestVal = moveVal
    print("The value of the best Move is :", bestVal)
    print("The best Move is :", bestMove)
    board[bestMove[0]][bestMove[1]].configure(text="O")
    states[bestMove[0]][bestMove[1]] = player
    print(states)
    changePlayer()
    return bestMove

for i in range(3):
    for j in range(3):
                                            
        board[i][j] = Button(
                        height = 4, width = 8,
                        font = ("Helvetica","20"),
                        command = lambda r = i, c = j : clicked(r,c))
        board[i][j].grid(row = i, column = j)
 
 
mainloop()