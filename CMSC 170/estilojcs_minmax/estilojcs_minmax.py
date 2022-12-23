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
 
for i in range(3):
    for j in range(3):
                                          
        b[i][j] = Button(
                        height = 4, width = 8,
                        font = ("Helvetica","20"),
                        command = lambda r = i, c = j : clicked(r,c))
        b[i][j].grid(row = i, column = j)
 
 
mainloop()        