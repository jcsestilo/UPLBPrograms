# references:
# https://github.com/Rabbid76/PyGameExamplesAndAnswers/blob/master/documentation/pygame/pygame_ui_elements.md#menu
# https://stackoverflow.com/questions/68592269/how-can-i-make-a-button-that-goes-to-website-in-pygame/68592403#68592403


# import and initialize pygame
import pygame, copy
from button import Button
from optionbox import OptionBox
pygame.init()

COLOR_WHITE = (255, 255, 255) # screen color and 0 cell
COLOR_PEACH = (255, 229, 180) # non-0 cell color
COLOR_MINTGREEN = (62, 180, 137)
COLOR_BLACK = (0, 0, 0) # font color


methodList = OptionBox(
    40, 360, 100, 30, COLOR_PEACH, COLOR_WHITE, pygame.font.SysFont(None, 15), 
    ["BFS", "DFS"])

button1 = Button(150, 360, 100, 30, pygame.font.SysFont(None, 15), "Solution")
grpBtn1 = pygame.sprite.Group(button1)
nextBtn = Button(150, 400, 100, 30, pygame.font.SysFont(None, 15), "Next")
grpNextBtn = pygame.sprite.Group(nextBtn)

# sizes of the cells
WIDTH = 100
HEIGHT = 100
MARGIN = 5

# set up the drawing wndow
screen = pygame.display.set_mode([320, 470])
clock = pygame.time.Clock()
# fill background with COLOR_WHITE
screen.fill(COLOR_WHITE)

# font
font = pygame.font.SysFont("Corbel", 20)

# check if solution button not yet clicked
isPlayable = True
isNextable = False

# file
f = open("puzzle.in", "r")

# grid: 2d array
grid = []
initialGrid = []

for row in f:
    # append to grid per row, splitting for every space
    grid.append(row.strip("\n").split(" "))
    initialGrid.append(row.strip("\n").split(" "))

f.close()

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
    # print(neighbors)
    return neighbors

def findEmpty(grid):
    for i in range(0, 3):
        for j in range(0,3):
            if(grid[i][j]=='0'):
                return(i, j)

def GoalTest(state):
    testState = state['board'] # get the 2d array configuration of the board
    if(testState[0]==['1', '2', '3'] and testState[1] == ['4', '5', '6'] and testState[2] == ['7', '8', '0']):
        return True
    else:
        return False

def Actions(state):
    # get the coordinate of the blank cell
    emptyCell = state['blank'] # this returns a tuple
    x = emptyCell[0]
    y = emptyCell[1]

    # get the neighbors of the empty cell where we can make actions to
    neighbors = checkNeighbors(x, y)
    # print(neighbors)
    actions = []
    for neighbor in neighbors:
        if(neighbor[0] == x-1 and neighbor[1]==y):
            actions.append("U")
        elif(neighbor[0] == x+1 and neighbor[1]==y):
            actions.append("D")
        elif(neighbor[0] == x and neighbor[1]==y-1):
            actions.append("L")
        elif(neighbor[0] == x and neighbor[1]==y+1):
            actions.append("R")
    # print("Actions:", actions)
    return actions

def Result(state, action):
    # print(state)
    temp = copy.deepcopy(state)
    board = temp['board']
    blank = temp['blank']
    move = action
    # return the resulting state, same structure like dictionary
    # print("Board before: ", board)
    if(move=="L"):
        board[blank[0]][blank[1]], board[blank[0]][blank[1]-1] = board[blank[0]][blank[1]-1], board[blank[0]][blank[1]]
    elif(move=="R"):
        board[blank[0]][blank[1]], board[blank[0]][blank[1]+1] = board[blank[0]][blank[1]+1], board[blank[0]][blank[1]]
    elif(move=="U"):
        board[blank[0]][blank[1]], board[blank[0]-1][blank[1]] = board[blank[0]-1][blank[1]], board[blank[0]][blank[1]]
    elif(move=="D"):
        board[blank[0]][blank[1]], board[blank[0]+1][blank[1]] = board[blank[0]+1][blank[1]], board[blank[0]][blank[1]]
    else:
        print("An error occured.")
    # print("Board after", board)
    
    resultingState = {
        "board": board,
        "blank": findEmpty(board),
        "move": move
    }

    return resultingState

def pathCost(statesInPath):
    pathCtr=0
    moves=[]
    for state in reversed(statesInPath):
        if(state['move']=="N/A"): # if it is the root node, don't count it
            continue
        else:
            moves.append(state['move'])
            pathCtr+=1
    print("Path count: ",pathCtr)
    print("Moves:", moves)
    return pathCtr, moves

def generatePath(explored, currentState):
    # for states in explored:
    #     print("State:", states)
    # print("Current State", currentState)
    statesInPath = [] # here we will get the states used for the right path, last element is the root
    statesInPath.append(currentState)
    state=copy.deepcopy(currentState)
    findingRoot=True
    # first, let's get all the board configurations in explored
    exploredBoardConfigs = []
    for exploredState in explored:
        exploredBoardConfigs.append(exploredState['board'])

    while(findingRoot):
        # if the state move is R, do L. vice versa. same with U and D
        if(state['move']=="L"):
            state = Result(state, "R")
        elif(state['move']=="R"):
            state = Result(state, "L")
        elif(state['move']=="D"):
            state = Result(state, "U")
        elif(state['move']=="U"):
            state = Result(state, "D")

        # after we swap, check if the new board configuration is 1. in explored 2. is the root
        # print(explored)
        # print(state)
        
        if(state['board'] in exploredBoardConfigs):
            exploredIndex = exploredBoardConfigs.index(state['board'])
            state = copy.deepcopy(explored[exploredIndex])
            statesInPath.append(state)
            if(state['board'] == initialGrid and state['move']=="N/A"):
                # print("Found the root!")
                findingRoot=False
                pathCtr, moves = pathCost(statesInPath)
                # print(statesInPath)
    # write the moves to puzzle.out
    f = open("puzzle.out", "w")
    for move in moves:
        f.write("%s " % move)
    f.close()
    return pathCtr

def BFSearch():
    # print("BFS")

    # frontier={initial}, the grid list is a 2D ARRAY
    initialState = {
        "board": initialGrid.copy(),
        "blank": findEmpty(initialGrid),
        "move": "N/A"
    }

    frontier = []
    frontier.append(initialState)
    # explored = {}
    explored = []
    # while(frontier is not empty)
    iterationCtr = 0
    while(frontier):
        currentState = frontier.pop(0) # currentState = frontier.dequeue();
        explored.append(currentState) # explored.add(currentState)
        # if(GoalTest(currentState)) return currentState;
        if(GoalTest(currentState)):
            print("Done!")
            print("No. of Iterations: ", iterationCtr)
            pathCtr = generatePath(explored, currentState)
            return pathCtr
            
        else:
            # Actions(currentState)
            for action in Actions(currentState):
                result = Result(currentState, action)
                if(result not in explored or result not in frontier):
                    frontier.append(Result(currentState, action))
                    # print("Frontier:", frontier)
        iterationCtr+=1

def DFSearch():
    print("DFS")

    # frontier={initial}, the grid list is a 2D ARRAY
    initialState = {
        "board": initialGrid.copy(),
        "blank": findEmpty(initialGrid),
        "move": "N/A"
    }

    frontier = []
    frontier.append(initialState)
    # explored = {}
    explored = []
    # while(frontier is not empty)
    iterationCtr = 0
    while(frontier):
        currentState = frontier.pop() # currentState = frontier.pop();
        explored.append(currentState) # explored.add(currentState)
        # if(GoalTest(currentState)) return currentState;
        if(GoalTest(currentState)):
            print("Done!")
            print("No. of Iterations: ", iterationCtr)
            pathCtr = generatePath(explored, currentState)
            return pathCtr
            
        else:
            # Actions(currentState)
            for action in Actions(currentState):
                result = Result(currentState, action)
                if(result not in explored or result not in frontier):
                    frontier.append(Result(currentState, action))
                    # print("Frontier:", frontier)
        iterationCtr+=1

def solution():
    # print("Option box active option", methodList.selected)
    # in methodList: 0=BFS; 1=DFS
    selectedMethod = methodList.selected

    if(selectedMethod==0):
        pathCtr = BFSearch()
    elif(selectedMethod==1):
        pathCtr = DFSearch()
    
    return pathCtr

def drawGrid():

    for row in range(0,3):
        for column in range(0,3):
            color = COLOR_PEACH

            # for the empty space, if input is 0
            if grid[row][column] == "0":
                color = COLOR_WHITE
            rect = pygame.Rect((MARGIN+WIDTH)*column+MARGIN, (MARGIN+HEIGHT)*row+MARGIN, WIDTH, HEIGHT)

            pygame.draw.rect(screen, color, rect)
            
            # for the printing of the number in the cell
            numberImg = font.render(grid[row][column], True, COLOR_BLACK)
            
            # draw the font
            screen.blit(numberImg, rect.center)
          
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

def proceedSolution(pathCtr, nextClickCounter, grid):
    print("Proceed Solution")
    moves = []
    f = open("puzzle.out", "r")
    line = f.readline().rstrip()
    for letter in line.split(" "):
        moves.append(letter)
    print("Moves Array:", moves)
    print("Next Click Counter: ", nextClickCounter)
    print("Move according to the click: ", moves[nextClickCounter])

    # let us use the Results function in swapping the cell
    state = {
        "board": grid.copy(),
        "blank": findEmpty(grid) # the action slot can be left out since it is not needed
    }

    resultingState = Result(state, moves[nextClickCounter])
    newGrid = copy.deepcopy(resultingState['board']) # pass the new configuration of the resulting state
    return newGrid

# run until the user asks to quit
running = True
nextClickCounter=0

w, h=pygame.display.get_surface().get_size()
if checkIfSolvable():
    isSolvableImg = font.render("It is solvable", True, COLOR_BLACK)
else:
    isSolvableImg = font.render("It is not solvable", True, COLOR_BLACK)

while running:
    clock.tick(60)

    # print the isSolvableImg to the screen
    screen.blit(isSolvableImg, (50, (h//2)+100))

    # Check if the player wins
    if checkIfWin():
        screen.fill(COLOR_WHITE)
        # clear the screen, and put "you win" in the center
        youWinImg = font.render("YOU WIN", True, COLOR_BLACK)
        path = "Path Count: " + str(nextClickCounter)
        pathCounter = font.render(path, True, COLOR_BLACK)

        # draw the font
        screen.blit(youWinImg, (w//2, h//2))
        screen.blit(pathCounter, (w//2-100, h//2+100))

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        
    else:
        # draw the 3x3 grid
        drawGrid()

        # if user clicked window close button
        eventList = pygame.event.get()
        for event in eventList:
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                # get mouse position
                pos = pygame.mouse.get_pos()
                if pos[0]>= (MARGIN+WIDTH)*3 or pos[1]>=(MARGIN+HEIGHT)*3:
                    screen.fill(COLOR_WHITE)
                    buttonHover = button1.rect.collidepoint(pos) # if mouse button down+hovering on button
                    nextButtonHover = nextBtn.rect.collidepoint(pos) # if mouse button clicked "next"
                    if buttonHover:
                        isPlayable = False
                        # put the grid to the initial state
                        grid = initialGrid.copy()
                        pathCtr = solution()
                        isNextable = True
                    if(nextButtonHover and isNextable):
                        newGrid = proceedSolution(pathCtr, nextClickCounter, grid)
                        nextClickCounter+=1 # this will be used to determine where we are on the path step
                        grid = newGrid.copy()
                else:
                    if isPlayable:
                        # convert coordinates to grid indexes
                        column = pos[0] // (WIDTH+MARGIN)
                        row = pos[1] // (HEIGHT+MARGIN)

                        # if the grid clicked has the 0 value, do nothing
                        if grid[row][column] == "0":
                            pass
                        else:
                            print("Click ", pos, "Grid coordinates: ", row, column)
                            swapCells(row, column)
        
        selected_option = methodList.update(eventList)
        # if selected_option >= 0:
        #     print(selected_option)

        if(grpBtn1.update()):
            solution()

        methodList.draw(screen)
        grpBtn1.draw(screen)
        if(isNextable):
            grpNextBtn.draw(screen)
    pygame.display.update()

    # Flip the display
    pygame.display.flip()

# if exit, quit
pygame.quit()
