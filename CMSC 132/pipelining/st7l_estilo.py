# Created by: Jan Coleen Estilo
# Section: ST-7L
# Description: Pipelining Program

def main():
    fp = open("instruction.txt", "r")
    lines = fp.readlines()
    instructions = []
    pipeline = []
    stages = 5
    stagesInstruct = ['F', 'D', 'E', 'M', 'W']
    instructionsOut = [] # made another array for the instructions, this is for the printing of the output only
    for line in lines:
        instructionsOut.append(line.strip())
        instructions.append(line.strip("^[add |sub |mul |div |\n]+$").split(", "))

    # for the first instruction, it will execute as normal
    pipeline.append([stagesInstruct[i] for i in range(0,stages)])
    for i in range(1, len(instructions)):
        # append '-' i times
        pipe = ['-' for _ in range(0,i)]
        pipe.append(stagesInstruct[0]) # 'F'
        # check for data hazard
        dataHazard = False
        destPrevInstruction = instructions[i-1][0] # the destination operand for the previous instruction
        if(destPrevInstruction in instructions[i]): # if the destination operand from the previous instruction is being read/written in our current instruction, there is a data hazard
            dataHazard = True
        if(dataHazard==True): # if there is a data hazard, we append S until instruction i-1 finishes executing
            for _ in range(0,len(instructions[i-1])):
                pipe.append('S')
            # afterwards, append D E M W or according to the amount of stages
            for j in range(1,stages):
                pipe.append(stagesInstruct[j])
        else:
            # we will append the right stages right away, after checking if the i-1th instruction is not using that same stage
            j=1
            # print("dumaan dito")
            while(j != stages):
                if(len(pipe) < len(pipeline[i-1])): # if the previous instruction has not finished executing yet
                    previous =  pipeline[i-1][len(pipe)] # get the stage for that column for the previous instruction
                    if(stagesInstruct != previous):
                        pipe.append(stagesInstruct[j])
                        j += 1 # we go to the next stage
                    else:
                        pipe.append('S')
                        # we do not go to the next stage yet
                else:
                    pipe.append(stagesInstruct[j])
                    j += 1 # we go to the next stage
        pipeline.append(pipe)

    # print the output
    for i in range(0,len(instructionsOut)):
        pipelineOut = ' '.join(map(str, pipeline[i]))
        print(instructionsOut[i] + "\t" + pipelineOut)
    print("<end>")




main()