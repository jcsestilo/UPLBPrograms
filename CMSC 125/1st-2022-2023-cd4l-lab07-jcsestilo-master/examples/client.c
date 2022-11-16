/*
	client.c
	KBPPelaez
	CMSC125 2nd Semester 2015-2016
*/

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>	//for new types declared
#include<sys/ipc.h>		//for interprocess communication
#include<sys/shm.h>		//for shared memory
#define MY_KEY 125

int main(){
	key_t key = MY_KEY;
	int segment_id;		//segment ID of the shared-memory
	int *shared_memory;	//pointer to the shared memory
						//in this case, we want a shared memory of integers
	int i;

	//STEP 1:
	//Create a shared memory segment using shmget

	segment_id = shmget(key, 10*sizeof(int), 0666);
	//ARGUMENTS   	1st: is the key of this shared memory
				//	2nd: is the size of the shared memory
   				//	3rd: is the mode of the memory (in Octal)
				//  	where 0666 means the owner of the file, the group it belongs to,
				//		and the other users can read and write.

	//check for errors
	if(segment_id < 0){
		printf("shmget error\n");
		exit(1);
	}

	//STEP 2:
	//Attach the shared memory to this process
	shared_memory = shmat(segment_id, NULL, 0);
	//ARGUMENTS		1st: is the segment_id of the shared memory
				//	2nd: is the address of the shared memory
				//	3rd: is the shared memory permissions
				//		possible values: 0 -> for read and write, and 1 -> for read-only

	//check for errors
	if(shared_memory == (int *) -1){
		printf("shmat error\n");
		exit(1);
	}

	for(i=0; i<10; i+=1){
		printf("%3d\n", shared_memory[i]);
	}

	shmdt(shared_memory);



}

/*
	client.c
	KBPPelaez
	CMSC125 2nd Semester 2015-2016
*/
