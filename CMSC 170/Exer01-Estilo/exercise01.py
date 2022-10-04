# references:
# http://programarcadegames.com/index.php?lang=en&chapter=array_backed_grids
# https://www.codegrepper.com/code-examples/python/grid+in+pygame
# https://stackoverflow.com/questions/62560902/how-to-draw-numbers-in-each-tile-grid-in-pygame
# https://www.programcreek.com/python/?CodeExample=get+adjacent+cells
# https://math.stackexchange.com/questions/293527/how-to-check-if-a-8-puzzle-is-solvable

# import and initialize pygame
import pygame
pygame.init()

WHITE = (255, 255, 255) # screen color and 0 cell
PEACH = (255, 229, 180) # non-0 cell color
BLACK = (0, 0, 0) # font color

# sizes of the cells
WIDTH = 100
HEIGHT = 100
MARGIN = 5

# dropdown attributes
COLOR_INACTIVE = (100, 80, 255)
COLOR_ACTIVE = (100, 200, 255)
COLOR_LIST_INACTIVE = (255, 100, 100)
COLOR_LIST_ACTIVE = (255, 150, 150)


# set up the drawing wndow
screen = pygame.display.set_mode([320, 470])
# fill background with white
screen.fill(WHITE)

# font
font = pygame.font.SysFont("Arial", 24)

# file
f = open("puzzle.in", "r")

# grid: 2d array
grid = []

for row in f:
    # append to grid per row, splitting for every space
    grid.append(row.strip("\n").split(" "))

f.close()

def drawGrid():

    for row in range(0,3):
        for column in range(0,3):
            color = PEACH

            # for the empty space, if input is 0
            if grid[row][column] == "0":
                color = WHITE
            rect = pygame.Rect((MARGIN+WIDTH)*column+MARGIN, (MARGIN+HEIGHT)*row+MARGIN, WIDTH, HEIGHT)

            pygame.draw.rect(screen, color, rect)
            
            # for the printing of the number in the cell
            numberImg = font.render(grid[row][column], True, BLACK)
            
            # draw the font
            screen.blit(numberImg, rect.center)
          
def checkNeighbors(x, y):
    coord = [x, y]
    neighbors = []

    if coord[0] > 0: # if cell is not in leftmost
        neighbors.append([coord[0]-1, coord[1]]) # get the adjacent left, same row
    if coord[0] < 2: # if cell is not in rightmost
        neighbors.append([coord[0]+1, coord[1]]) # get the adjacent right, same row
    if coord[1] > 0: # if cell is not in topmost
        neighbors.append([coord[0], coord[1]-1]) # get the adjacent top, same column
    if coord[1] < 2: # if cell is not in bottom
        neighbors.append([coord[0], coord[1]+1]) # get the adjacent bottom, same column\
    # print("Neighbors:", neighbors)
    return neighbors

def swapCells(row, column):
    x = row
    y = column

    # check the neighboring cells
    neighbors = checkNeighbors(x, y)

    # let's check every neighboring cell, if they have the 0 value
    for cell in neighbors:
        
        if grid[cell[0]][cell[1]] == "0":
            # swap the two elements
            grid[x][y], grid[cell[0]][cell[1]] = grid[cell[0]][cell[1]], grid[x][y]

def checkIfWin():
    # let's check if the grid array is in order
    if grid[0] == ['1', '2', '3'] and grid[1] == ['4', '5', '6'] and grid[2] == ['7', '8', '0']:
        return True
    else:
        return False

def checkIfSolvable():
    # for the checking is solvable or not
    inversions=0

    # copy the 2d array to a 1d array
    grid_1d = []
    for i in range(0,3):
        for j in range(0,3):
            grid_1d.append(int(grid[i][j]))
    
    # print(grid)
    # print(grid_1d)

    # get the inversions
    for i in range(0,9):
        for j in range(i+1, 9): # i+1 since it should be the next to the ith character
            if grid_1d[i] > grid_1d[j]: 
                if grid_1d[j]==0: # if ith element > jth element and the jth element is equal to 0, do not count
                    pass
                else:
                    inversions+=1
    
    # print(inversions)

    if inversions%2==0:
        return True
    else: 
        return False

    
# run until the user asks to quit
running = True


w, h=pygame.display.get_surface().get_size()
if checkIfSolvable():
    isSolvableImg = font.render("It is solvable", True, BLACK)
else:
    isSolvableImg = font.render("It is not solvable", True, BLACK)

while running:

    # print the isSolvableImg to the screen
    screen.blit(isSolvableImg, (50, (h//2)+100))

    # Check if the player wins
    if checkIfWin():
        screen.fill(WHITE)
        # clear the screen, and put "you win" in the center
        youWinImg = font.render("YOU WIN", True, BLACK)

        # draw the font
        screen.blit(youWinImg, (w//2, h//2))

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
    else:
        # draw the 3x3 grid
        drawGrid()

        # if user clicked window close button
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                # get mouse position
                pos = pygame.mouse.get_pos()
                if pos[0]>= (MARGIN+WIDTH)*3 or pos[1]>=(MARGIN+HEIGHT)*3:
                    pass
                else:
                    # convert coordinates to grid indexes
                    column = pos[0] // (WIDTH+MARGIN)
                    row = pos[1] // (HEIGHT+MARGIN)

                    # if the grid clicked has the 0 value, do nothing
                    if grid[row][column] == "0":
                        pass
                    else:
                        # print("Click ", pos, "Grid coordinates: ", row, column)
                        swapCells(row, column)

    # Flip the display
    pygame.display.flip()

# if exit, quit
pygame.quit()
