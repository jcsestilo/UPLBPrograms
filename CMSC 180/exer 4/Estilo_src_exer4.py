import random
import threading
import socket 
from time import time

class ThreadArgs:
    def __init__(self, startingRow, endingRow, size, M):
        self.startingRow = startingRow
        self.endingRow = endingRow
        self.size = size
        self.M = M

def FCCBoundary(M, size):
    # Interpolation for within the Bounding Box FCC method
    for i in range(size):
        for j in range(size):
            if i % 10 != 0 or j % 10 != 0:
                if i % 10 == 0:
                    x1 = (j // 10) * 10
                    x2 = x1 + 10
                    if x2 >= size:
                        x2 = size - 1
                    y1 = M[i * size + x1]
                    y2 = M[i * size + x2]
                    M[i * size + j] = y1 + ((j - x1) / (x2 - x1)) * (y2 - y1)
                elif j % 10 == 0:
                    x1 = (i // 10) * 10
                    x2 = x1 + 10
                    if x2 >= size:
                        x2 = size - 1
                    y1 = M[x1 * size + j]
                    y2 = M[x2 * size + j]
                    M[i * size + j] = y1 + ((i - x1) / (x2 - x1)) * (y2 - y1)

def terrain_inter(args):
    # Interpolation for inside the Bounding Box FCC method
    for i in range(args.startingRow, args.endingRow + 1):
        for j in range(1, args.size):
            if i % 10 != 0 and j % 10 != 0:
                if i % 10 != 0:
                    x1 = (j // 10) * 10
                    x2 = x1 + 10
                    if x2 >= args.size:
                        x2 = args.size - 1
                    y1 = args.M[i * args.size + x1]
                    y2 = args.M[i * args.size + x2]
                    args.M[i * args.size + j] = y1 + ((j - x1) / (x2 - x1)) * (y2 - y1)
                elif j % 10 != 0:
                    x1 = (i // 10) * 10
                    x2 = x1 + 10
                    if x2 >= args.size:
                        x2 = args.size - 1
                    y1 = args.M[x1 * args.size + j]
                    y2 = args.M[x2 * args.size + j]
                    args.M[i * args.size + j] = y1 + ((i - x1) / (x2 - x1)) * (y2 - y1)

def main():
    input_size = int(input("Enter size n: "))
    if input_size % 10 != 0:
        print("Please enter n only divisible by 10.")
        return

    size = input_size + 1

    port = int(input("Enter p (port number): "))
    status = int(input("Enter s (0 for master and 1 for slave): "))
    
    if status==0:

        M = [0] * (size * size)

        # Generate random values from 0 to 1000 in every grid points divisible by 10
        random.seed()
        for i in range(size):
            for j in range(size):
                if i % 10 == 0 and j % 10 == 0:
                    M[i * size + j] = random.randint(0, 1000)
        
        # Read the configuration file to determine the IP addresses and ports of the slaves and the number of slaves t-1
        filename = "masterConfig.txt"
        file = open(filename, 'r')
        IPAddress = str(file.readline().strip()) # IP Address of the server
        numPorts = int(file.readline().strip())
        slavePorts = []
        for _ in range(numPorts):
            slavePorts.append(int(file.readline().strip()))

        FCCBoundary(M, size) # Interpolate on the within the boundary

        socket = socket.socket() # Create a socket object
        print("Socket successfully created.")
        socket.bind((IPAddress, port)) # Bind the the port to the IP Address
        print ("Socket binded to port %s and IP Address %s" %(port, IPAddress))
        socket.listen(numPorts) # Listen for incoming connection requests from Slave
        print ("socket is listening to %d ports." %(numPorts))

        while True:
    
            # Establish connection with client.
            c, addr = s.accept()    
            print ('Got connection from', addr )
            
            # send a thank you message to the client. encoding to send byte type.
            c.send('Thank you for connecting'.encode())
            
            # Close the connection with the client
            c.close()
            
            # Breaking once connection closed
            break

    # Take note of the system time_before
    start = time()


    # PRINT MATRIX
    # for i in range(size):
    #     for j in range(size):
    #         print(M[i*size + j], end=" ")
    #     print()
    # print()

    t = numPorts + 1 # Number of slaves
    if input_size % t != 0:
        print("Cannot create t submatrices. Size not divisible by t.")
        return

    threads = []

    rowsAmount = ((size - 2) // t) + 1
    lastPreviousRow = 0
    for _ in range(t):
        startingRow = lastPreviousRow + 1
        endingRow = lastPreviousRow + rowsAmount
        lastPreviousRow = endingRow

        if endingRow == size - 1:
            endingRow -= 1

        args = ThreadArgs(startingRow, endingRow, size, M)
        thread = threading.Thread(target=terrain_inter, args=(args,))
        thread.start()
        threads.append(thread)

    for thread in threads:
        thread.join()

    stop = time()
    print(f"lab02 < {size}")
    print(f"time elapsed: {stop - start}")

if __name__ == "__main__":
    main()
