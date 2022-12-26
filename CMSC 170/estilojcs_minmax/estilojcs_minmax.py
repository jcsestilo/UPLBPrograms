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
# print(player)

# Design window
#Creating the Canvas
root = Tk()
# Title of the window            
root.title("Tic Tac Toe") 
# Attributes
root.resizable(0,0)
root.attributes('-topmost',True)

# s = [states] # start of the game
# p = ['P1', 'P2']

def result(s,a): # -> s' : s’ are the new states made when action a is done to a state s.
     # a = tuple (x,y); s = the state
     print("result function")
     print(s)
     s_prime = s.copy()
     if(player==PlayerUser):
          s_prime[a[0]][a[1]] = "X"
     elif(player==PlayerAI):
          s_prime[a[0]][a[1]] = "O"
     print("s_prime", s_prime)
     return s_prime

def actions(s): # provides the set of possible actions/moves for a player.
     print("actions function")
     possible_actions = [] # is a list containing tuples (x,y) of the possible moves
     
     # loop through the whole states array
     for i in range(0,3):
          for j in range(0,3):
               if s[i][j] == '_': # if the certain grid has not yet been clicked
                    possible_actions.append((i, j))
     # print(possible_actions)
     return possible_actions

def successors(s): # function that gets the children of state
     print("successors function")
     # get all the possible actions for a given state
     # actionsList = actions(s)
     # get all the resulting states for each action
     successorsList = []
     for act in actions(s):
          successorsList.append(result(s,act))
     # print(successorsList)
     for i in successorsList:
          print(i)
     return successorsList

def terminal(s): # It returns true when the state is terminal or there is an end result or false otherwise.
     print("terminal function")
     if(stop_game):
          return True
     else:
          return False

def utility(s,p): # Returns the current value of the state s to a player p
     # It is expresses the value of the current game state to
     # the player. It is usually expressed as +/- numbers or
     # 0's or 1's
     print("utility function")
     check_if_win()
     if winner==PlayerUser:
          return +1
     elif winner==PlayerAI:
          return -1
     elif winner=="draw":
          return 0
     else:
          print("winner:", winner)

def clicked(r,c):
     #player
     global player
     global b
     global states
     print("clicked function")
     # global stop_game
     # print(player, states[r], stop_game)
     if player == "X" and states[r][c] == '_' and stop_game == False:
          b[r][c].configure(text = "X")
          states[r][c] = 'X'
          player=PlayerAI
          check_if_win()
          if not stop_game:
               bestMove = findBestMove(states)
               states[bestMove[0]][bestMove[1]] = "O"

     if player == 'O' and states[r][c] == '_' and stop_game == False:
          b[r][c].configure(text = 'O')
          states[r][c] = "O"
          player = PlayerUser

     # value(states)

# def changePlayer():
#      global player
#      if player==PlayerUser:
#           player=PlayerAI
#      else: 
#           player=PlayerUser

def check_if_win():
     print("check_if_win function")
     global stop_game
     global winner
     
     for i in range(3):
          if states[i][0] == states[i][1] == states[i][2] !='_':
               stop_game = True
               winner = str(states[i][0])
               # disableAllButton()
               break
     
     # for j in range(3):
          elif states [0][i] == states[1][i] == states[2][i] != '_':
               stop_game = True
               winner = str(states[0][i])
               break
     
          elif states[0][0] == states[1][1] == states[2][2] !='_':
               stop_game = True
               winner = str(states[0][0])
               break
     
          elif states[0][2] == states[1][1] == states[2][0] !='_':
               stop_game = True
               winner = str(states[0][2])
               break
     
          elif states[0][0] and states[0][1] and states[0][2] and states[1][0] and states[1][1] and states[1][2] and states[2][0] and states[2][1] and states[2][2] != '_':
               stop_game = True
               winner = "draw"

# def isMaxNode():
#      if (player==PlayerUser):
#           return True
#      else:
#           return False

# def isMinNode():
#      if(player==PlayerAI):
#           return True
#      else:
#           return False

def value(s, depth, isMaximizingPlayer):
     print("value function")
     if(terminal(s)):
          return utility(s)
     elif(isMaximizingPlayer):
          m = NEG_INF
          # //action, a, s’ = result(s, a)
          for successor in successors(s):
               v = value(successor, depth+1, not isMaximizingPlayer)
               m = max(m, v)
          return m

     elif(not isMaximizingPlayer):
          m = POS_INF
          for successor in successors(s):
               v = value(successor, depth+1, not isMaximizingPlayer)
               m = min(m, v)
          return m

# This will return the best possible move for the player
def findBestMove(board):
     print("findBestMove function")
     global player
     bestVal = -99999
     bestMove = (-1, -1)
     for i in range(3):    
          for j in range(3):
               # Check if cell is empty
               if (board[i][j] == '_'):
               
                    # Make the move
                    board[i][j] = player

                    # compute evaluation function for this
                    # move.
                    moveVal = value(board, 0, False)

                    # Undo the move
                    board[i][j] = '_'

                    # If the value of the current move is
                    # more than the best value, then update
                    # best/
                    # print(moveVal)
                    if (moveVal > bestVal):               
                         bestMove = (i, j)
                         # states[i][j] = player
                         bestVal = moveVal
     print("The value of the best Move is :", bestVal)
     print("The best Move is :", bestMove)
     b[bestMove[0]][bestMove[1]].configure(text="O")
     player=PlayerUser
     return bestMove

#Button
b = [
     ['_','_','_'],
     ['_','_','_'],
     ['_','_','_']]

#text for buttons
states = [
     ['_','_','_'],
     ['_','_','_'],
     ['_','_','_']]

def main():

     for i in range(3):
          for j in range(3):
                                                  
               b[i][j] = Button(
                              height = 4, width = 8,
                              font = ("Helvetica","20"),
                              command = lambda r = i, c = j : clicked(r,c))
               b[i][j].grid(row = i, column = j)
 
 
     mainloop()        

main()