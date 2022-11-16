/*
	lab08_estilo_B
	Jan Coleen Estilo
	Lab06 - Shared Memory
*/

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>	//for new types declared
#include<sys/ipc.h>		//for interprocess communication
#include<sys/shm.h>		//for shared memory
#define MY_KEY 125