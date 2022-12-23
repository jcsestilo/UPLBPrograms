# Created by: Jan Coleen S. Estilo
# Date created: Dec. 23, 2022
# Exercise 10: MinMax Algorithm
# References:
# https://www.geeksforgeeks.org/tic-tac-toe-game-with-gui-using-tkinter-in-python/

from tkinter import *
from tkinter import messagebox

PlayerUser = 'X'
PlayerAI = 'O'
stop_game = False
NEG_INF = float('-inf')
POS_INF = float('inf')

player = messagebox.askquestion("Tic Tac Toe", "Should the user go first?\nYes - User (X)\nNo - AI (O)")

# Design window
#Creating the Canvas
root = Tk()
# Title of the window            
root.title("Tic Tac Toe") 
# Attributes
root.resizable(0,0)
root.attributes('-topmost',True)
#Button
b = [
     [0,0,0],
     [0,0,0],
     [0,0,0]]
 
#text for buttons
states = [
     [0,0,0],
     [0,0,0],
     [0,0,0]]

s = [states] # start of the game
p = ['P1', 'P2']

def result(s,a): # -> s' : s’ are the new states made when action a is done to a state s.
     print("result function")

def actions(s,p): # provides the set of possible actions/moves for a player.
     print("actions function")

def terminal(): # It returns true when the state is terminal or there is an end result or false otherwise.
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

def check_if_win():
    global stop_game
    # count = 0
 
    for i in range(3):
        if states[i][0] == states[i][1] == states[i][2] !=0:
          stop_game = True

          # winner = messagebox.showinfo("Winner", states[i][0] + " Won")
          # disableAllButton()
          break
 
    # for j in range(3):
        elif states [0][i] == states[1][i] == states[2][i] != 0:
          stop_game = True

          # winner = messagebox.showinfo("Winner", states[0][i]+ " Won!")
          break
 
        elif states[0][0] == states[1][1] == states[2][2] !=0:
          stop_game = True

          # winner = messagebox.showinfo("Winner", states[0][0]+ " Won!")
          break
 
        elif states[0][2] == states[1][1] == states[2][0] !=0:
          stop_game = True

          # winner = messagebox.showinfo("Winner" , states[0][2]+ " Won!")
          break
 
        elif states[0][0] and states[0][1] and states[0][2] and states[1][0] and states[1][1] and states[1][2] and states[2][0] and states[2][1] and states[2][2] != 0:
          stop_game = True

          # winner = messagebox.showinfo("tie", "Tie")
 
for i in range(3):
    for j in range(3):
                                          
        b[i][j] = Button(
                        height = 4, width = 8,
                        font = ("Helvetica","20"),
                        command = lambda r = i, c = j : clicked(r,c))
        b[i][j].grid(row = i, column = j)
 
 
mainloop()        