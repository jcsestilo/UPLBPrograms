#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>  
#include<malloc.h>
#include<sys/time.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#include <pthread.h>

struct thread_args{
    int startingRow;
    int endingRow;
    int size;
    float *M;
};

// todo: yung submatrix na first row and last row dapat ay nakakapag interpolate din

void FCCBoundary(float *M, int size){
    int x1, x2, y1, y2, i, j;
    char str[20];

    // Interpolation for within the Bounding Box FCC method
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            if( !(i%10==0 && j%10==0) ){
                if (i%10==0){
                    x1 = (j/10)*10;
                    x2 = x1 + 10;
                    if (x2 >= size)
                    {
                        x2 = size-1;
                    }
                    y1 = M[i*size+x1];
                    y2 = M[i*size+x2];
                    sprintf(str, "%d.%d", (j-x1)/(x2-x1), (j-x1)%(x2-x1));
                    M[i*size+j] = y1 +  ((atof(str)) * (y2-y1));
                } else if(j%10==0) {
                    x1 = (i/10)*10;
                    x2 = x1 + 10;
                    if(x2 >= size){
                        x2 = size-1;
                    }
                    y1 = M[x1*size+j];
                    y2 = M[x2*size+j];
                    sprintf(str, "%d.%d", (i-x1)/(x2-x1), (i-x1)%(x2-x1));
                    M[i*size+j] = y1 +  (atof(str)) * (y2-y1);
                }
            }
        }
        
    }
}

//float *M, int size, int startingRow, int endingRow
void* terrain_inter(float *M, int size, int startingRow, int endingRow){
    int x1, x2, y1, y2, i, j;
    char str[20];

    // struct thread_args* args = (struct thread_args*) args_ptr;
    // int startingRow = args->startingRow;
    // int endingRow = args->endingRow;
    // int size = args->size;
    // float *M = args->M;

    // Interpolation for inside the Bounding Box FCC method
    for (int i = startingRow; i <= endingRow; i++)
    {
        for (int j = 1; j < size; j++)
        {
            if( (i%10!=0 && j%10!=0) ){
                if (i%10!=0){
                    x1 = (j/10)*10;
                    x2 = x1 + 10;
                    if (x2 >= size)
                    {
                        x2 = size-1;
                    }
                    y1 = M[i*size+x1];
                    y2 = M[i*size+x2];
                    sprintf(str, "%d.%d", (j-x1)/(x2-x1), (j-x1)%(x2-x1));
                    M[i*size+j] = y1 +  ((atof(str)) * (y2-y1));
                } else if(j%10!=0) {
                    x1 = (i/10)*10;
                    x2 = x1 + 10;
                    if(x2 >= size){
                        x2 = size-1;
                    }
                    y1 = M[x1*size+j];
                    y2 = M[x2*size+j];
                    sprintf(str, "%d.%d", (i-x1)/(x2-x1), (i-x1)%(x2-x1));
                    M[i*size+j] = y1 +  (atof(str)) * (y2-y1);
                }
            }
        }
        
    }
    
}

int main(){
    int size, t=2, port, status;
    int startingRow, endingRow, lastPreviousRow=0, rowsAmount;
    struct timeval stop, start;

    // Get user input
    printf("Enter size n: ");
    scanf("%d", &size);
    if(size%10 != 0){
        printf("Please enter n only divisible by 10.\n");
        return 0;
    }
    size = size + 1;

    printf("Enter p (port number): ");
    scanf("%d", &port);

    printf("Enter s (0 for master and 1 for slave): ");
    scanf("%d", &status);

    if(status==0){
        int clientSocket;
        int numIPAddresses, numPorts, *ports;
        char **IPAddresses;
        struct sockaddr_in serverAddress;
        char ipAddress[20];
        char buffer[1024];
        int bytesReceived;

        // Initialize matrix
        float* M = (float *)malloc((size*size) * sizeof(float));
        memset(M, 0, size*size*sizeof(float));

        // Generate random values from 0 to 1000 in every grid points divisible by 10
        srand ( time(NULL) ); // seeding
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i%10==0 && j%10==0) {
                    M[i * size + j] = rand() % 1001;
                }                
            }            
        }

        // Open and read the contents in the configuration file
        const char* filename = "masterConfig.txt";
        FILE* file = fopen(filename, "r");
        if (file == NULL) {
            printf("Error opening file: %s\n", filename);
            return 0;
        }
        fscanf(file, "%s", ipAddress);
        fscanf(file, "%d", &numPorts);
        numIPAddresses = numPorts;
        // Allocate memory for the ports (and IPAddresses) array
        ports = (int *)malloc(numPorts * sizeof(int));
        IPAddresses = (char**)malloc(numIPAddresses * sizeof(char*));
        // Allocate memory for each string
        for (int i = 0; i < numIPAddresses; i++) {
            IPAddresses[i] = (char*)malloc(20 * sizeof(char));
        }
        // Read the ports
        for (int i = 0; i < numPorts; i++) {
            fscanf(file, "%s", IPAddresses[i]);
            fscanf(file, "%d", &ports[i]);
        }
        fclose(file);

        // time_before
        gettimeofday(&start, NULL);

        // Compute for the within the boundary values
        FCCBoundary(M, size); 

        // PRINT MATRIX
        // for (int i = 0; i < size; i++) {
        //         for (int j = 0; j < size; j++)
        //             printf("%f ", M[i*size + j]);
        //         printf("\n");
        //     }
        // printf("\n");

        rowsAmount = ((size - 2)/numPorts) + 1;
        for (int i = 0; i < numPorts; i++)
        {
            // Create a socket for the client
            clientSocket = socket(AF_INET, SOCK_STREAM, 0);
            if (clientSocket == -1) {
                perror("Failed to create socket");
                return 1;
            }
            // Set up the server address
            memset(&serverAddress, 0, sizeof(serverAddress));
            serverAddress.sin_family = AF_INET;
            serverAddress.sin_port = htons(ports[i]);
            // serverAddress.sin_addr.s_addr = inet_addr(IPAddresses[i]);
            serverAddress.sin_addr.s_addr = INADDR_ANY; 
            
            // Convert IP address from string to binary form
            if (inet_pton(AF_INET, ipAddress, &(serverAddress.sin_addr)) <= 0) {
                perror("Failed to convert IP address");
                close(clientSocket);
                return 1;
            }

            // Connect to the server
            if (connect(clientSocket, (struct sockaddr *)&serverAddress, sizeof(serverAddress)) == -1) {
                perror("Failed to connect to server");
                close(clientSocket);
                return 1;
            }


            printf("Connected to the slave\n");

            // Send data to the slave
            // char *message = "Hello, slave!";
            // if (send(clientSocket, message, strlen(message), 0) == -1) {
            //     perror("Failed to send data");
            //     close(clientSocket);
            //     return 1;
            // }
            // Start dividing the matrix
            startingRow = lastPreviousRow + 1;
            endingRow = lastPreviousRow + rowsAmount;
            lastPreviousRow = endingRow;

            // Send the amount of rows, and the amount of columns to the slave side
            // For it to create a dynamic 2d array
            if (send(clientSocket, &rowsAmount, sizeof(int), 0) == -1) {
                perror("Failed to send rowsAmount");
                close(clientSocket);
                return 1;
            }
            if (send(clientSocket, &size, sizeof(int), 0) == -1) {
                perror("Failed to send column size");
                close(clientSocket);
                return 1;
            }
            
            // The ending row should never be a boundary coordinate
            if (endingRow==size-1)
            {
                endingRow = endingRow - 1;
            }

            // Per point in our 'submatrix', send it to the slave side
            for(int row=startingRow; row<=endingRow; row++){
                for(int col=0; col<size; col++){

                    if (send(clientSocket, &M[row*size + col], sizeof(float), 0) == -1) {
                        perror("Failed to send data");
                        close(clientSocket);
                        return 1;
                    }

                }

            }
            // // Set arguments
            // struct thread_args args;
            // args.endingRow = endingRow;
            // args.startingRow = startingRow;
            // args.M = M;
            // args.size = size;
            // pthread_create(&threads[i], NULL, terrain_inter, (void *) &args);
            // // Wait for thread to finish
            // pthread_join(threads[i], NULL);

            float done = -1.0;
            if (send(clientSocket, &done, sizeof(float), 0) == -1) {
                perror("Failed to send data");
                close(clientSocket);
                return 1;
            }
            printf("Data sent to the slave\n");

            // Receive data from the slave
            bytesReceived = recv(clientSocket, buffer, 1024, 0);
            if (bytesReceived == -1) {
                perror("Failed to receive data");
                close(clientSocket);
                return 1;
            }

            // Null-terminate the received data
            buffer[bytesReceived] = '\0';

            printf("Received data from the slave %d: %s\n", i, buffer);
            
            // Close the client socket
            close(clientSocket);
        }
        
        // time_after
        gettimeofday(&stop, NULL); 
        printf("lab04 < %d\n", size);
        // printing of time elapsed
        printf("time elapsed: %f\n", (double)((stop.tv_sec - start.tv_sec) * 1000000 + stop.tv_usec - start.tv_usec) / 1000000);

        // Free memory
        free(M);
        free(ports);
        for (int i = 0; i < numIPAddresses; i++) {
            free(IPAddresses[i]);
        }
        free(IPAddresses);

    } else if (status==1){
        int clientSocket, serverSocket;
        struct sockaddr_in serverAddress, clientAddress;
        char masterAddress[20];
        int masterPort;
        
        int bytesReceived, clientAddressLength;

        // Read from the configuration file what is the IP address of the master
        const char* filename_s = "slaveConfig.txt";
        FILE* file = fopen(filename_s, "r");
        if (file == NULL) {
            printf("Error opening file: %s\n", filename_s);
            return 0;
        }
        fscanf(file, "%s", masterAddress);
        fscanf(file, "%d", &masterPort);
        fclose(file);

        // Create client socket
        serverSocket = socket(AF_INET, SOCK_STREAM, 0);
        if (serverSocket == -1) {
            perror("Failed to create client socket");
            return 1;
        }

        // Set up server address
        memset(&serverAddress, 0, sizeof(serverAddress));
        serverAddress.sin_family = AF_INET;
        serverAddress.sin_port = htons(port);
        serverAddress.sin_addr.s_addr = INADDR_ANY;

        // Bind the socket to the server address
        if (bind(serverSocket, (struct sockaddr *)&serverAddress, sizeof(serverAddress)) == -1) {
            perror("Failed to bind socket");
            close(serverSocket);
            return 1;
        }

        if (listen(serverSocket, 5) == -1) {
            perror("Failed to listen");
            close(serverSocket);
            return 1;
        }

        // Accept the connection from the master
        clientAddressLength = sizeof(clientAddress);
        clientSocket = accept(serverSocket, (struct sockaddr *)&clientAddress, &clientAddressLength);
        if (clientSocket == -1) {
            perror("Failed to accept connection");
            close(serverSocket);
            return 1;
        }

        printf("Slave connected to the master\n");

        // Receive rowsAmount and columnAmount from the master
        int rowsAmount, columnAmount;
        bytesReceived = recv(clientSocket, &rowsAmount, sizeof(int), 0);
        if (bytesReceived == -1) {
            perror("Failed to receive data");
            close(clientSocket);
            close(serverSocket);
            return 1;
        }
        bytesReceived = recv(clientSocket, &columnAmount, sizeof(int), 0);
        if (bytesReceived == -1) {
            perror("Failed to receive data");
            close(clientSocket);
            close(serverSocket);
            return 1;
        }
        
        // printf("rowsAmount: %d, columnAmont: %d,,,, %d\n", rowsAmount, columnAmount, rowsAmount*columnAmount);
        // create a dynamic array with the received rows amount and the column amount
        float* subM = (float *)malloc((rowsAmount*columnAmount) * sizeof(float));
        memset(subM, 0, rowsAmount*columnAmount*sizeof(float));


        // Get the receiving data
        float value;
        for(int row=0; row<rowsAmount; row++){
            for(int col=0; col<columnAmount; col++){
                // Receive data from the master
                bytesReceived = recv(clientSocket, &value, sizeof(float), 0);
                if (bytesReceived == -1) {
                    perror("Failed to receive data");
                    close(clientSocket);
                    close(serverSocket);
                    return 1;
                }

                if(value==-1.0){ // Done receiving the values
                    break;
                }
                subM[row * size + col] = value;


            }

        }

        // PRINT MATRIX
        // for (int i = 0; i < rowsAmount; i++) {
        //         for (int j = 0; j < columnAmount; j++)
        //             printf("%f ", subM[i*size + j]);
        //         printf("\n");
        //     }
        // printf("\n");

        // endingRow = endingRow;
        // startingRow = startingRow;
        // M = subM;
        // size = size;
        // FCCBoundary();
        terrain_inter(subM, columnAmount, 0, rowsAmount);

        printf("DONE INTERPOLATION\n");
        // PRINT MATRIX
        // for (int i = 0; i < rowsAmount; i++) {
        //         for (int j = 0; j < columnAmount; j++)
        //             printf("%.2f ", subM[i*size + j]);
        //         printf("\n");
        //     }
        // printf("\n");
        // pthread_create(&threads[i], NULL, terrain_inter, (void *) &args);

        // while (1)
        // {
            

        //     printf("Received data from the master: %f\n", value);
        // }
        // interpolate here

        // Send data to the slave (acknowledgement)
        const char *message = "ack";
        if (send(clientSocket, message, strlen(message), 0) == -1) {
            perror("Failed to send data");
            close(serverSocket);
            return 1;
        }

        close(clientSocket);
        close(serverSocket);
        free(subM);

    }

    // Create t threads
    // pthread_t threads[t];

    // PRINT MATRIX
    // for (int i = 0; i < size; i++) {
    //         for (int j = 0; j < size; j++)
    //             printf("%f ", M[i*size + j]);
    //         printf("\n");
    //     }
    // printf("\n");

    return 0;

}