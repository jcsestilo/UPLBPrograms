// Submitted by: Jan Coleen S. Estilo
// Date: November 29, 2022
// Description: Exercise 09: Synchronization
// References:
// https://www.javatpoint.com/os-dining-philosophers-problem#:~:text=The%20dining%20philosopher's%20problem%20is,for%20each%20of%20the%20philosophers.

#include <stdio.h>
#include <stdlib.h>
#include <time.h>		// time()
#include <math.h>		// srand() and ran()
#include <pthread.h>	// PTHREADS
#include <semaphore.h>	// SEMAPHORES
#include <unistd.h>
#define N 5

// This method will be performed by each philosopher
void * Philosophize(void * id);

// Chopsticks: Shared resources
sem_t chopstick[N];
// IF YOU DO NOT WANT TO USE SEMAPHORES, YOU CAN USE MUTEXES

int main()
{
	int i;

	// Create 5 PHILOSOPHER
	pthread_t philo[N];
  	int *ids = (int *) malloc(sizeof(int) * N);

	// Initialize Semaphores
	for(i=0; i<N; i++){
		sem_init(&chopstick[i], 0, 1);
	}

	// CREATE PHILOSOPHER threads
	for(i=0; i<N; i++){
    	ids[i] = i;
		pthread_create(&philo[i], NULL, Philosophize, (void *) &ids[i]);
	}

	// JOIN PHILOSOPHER threads
	for(i=0; i<N; i++){
		pthread_join(philo[i], NULL);
	}

	return 0;
}

void * Philosophize(void * id){
	int ID = *((int *) id);
	// printf("%d\n", ID);
	int leftChopstick, rightChopstick, sleeptime; // determine what the id of the semaphore is
	time_t t;
	int sem_value;

	// initializes random number generator of how many seconds
	sleeptime = rand() % 10;
	
	while(1){

		// 1. THINK for a while
		printf("Philosopher %d is thinking...\n", ID);
		sleep(sleeptime);

		// 2. PICK-UP left chopstick
		leftChopstick = ID;
		rightChopstick = (ID + 1) % 5;
		
		sem_wait(&chopstick[leftChopstick]);
		printf("Philosopher %d has picked up chopstick %d, the left chopstick...\n", ID, leftChopstick);

		// 3. PICK-UP right chopstick
		// check if the right chopstick is available
		sem_getvalue(&chopstick[rightChopstick], &sem_value);
		if(sem_value <= 0){
			// if the right chopstick is not available, bitawan ni philosopher yung left chopstick tapos loop again
			printf("Right chopstick of Philosopher %d is not available, letting go of left chopstick...\n", ID);
			sem_post(&chopstick[leftChopstick]);
			continue;
		} else {
			sem_wait(&chopstick[rightChopstick]);
			printf("Philosopher %d has picked up chopstick %d, the right chopstick...\n", ID, rightChopstick);
		}

		// 4. EAT for a while
		sleep(sleeptime);
		printf("Philosopher %d is eating...\n", ID);

		// 5. RELEASE left chopstick
		sem_post(&chopstick[leftChopstick]);
		printf("Philosopher %d has released chopstick %d, the left chopstick...\n", ID, leftChopstick);

		// 6. RELEASE right choptick
		sem_post(&chopstick[rightChopstick]);
		printf("Philosopher %d has released chopstick %d, the right chopstick...\n", ID, rightChopstick);

	}

}
