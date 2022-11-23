/**
  sample3.c
  KBPPelaez
  CMSC 125 2nd Sem AY 15-16
  Revised 2017

  Example of SEMAPHORE initialization, waiting, and posting
 */

#include<stdio.h>
#include<pthread.h>
#include<time.h>
#include<stdlib.h>
#include<unistd.h>
#include<semaphore.h>

//shared resources
int N = 0;
// declaration of semaphore
sem_t sem;

//function to be executed
void * doThis(void *args){

  //waiting
  sem_wait(&sem);
  printf("Thread %ld started. Sleeping for 3seconds...\n",pthread_self());
  sleep(3);
  printf("Thread %ld stopped.\n", pthread_self());
  //posting (or releasing)
  sem_post(&sem);

}

//main function
int main(){
  pthread_t *t1;
  int i;

  //semaphore initialization
  //initial value -> 3
  //at most 3 threads at a time
  sem_init(&sem, 0, 3);

  printf("Enter number of threads: ");
  scanf("%d", &N);

  //creates an array of thread
  t1 = (pthread_t *) malloc(sizeof(pthread_t)*N);

  //creates the threads
  for(i=0;i<N;i+=1){
    pthread_create(t1+i,NULL,doThis,NULL);
  }

  //waiting for them to finish
  for(i=0;i<N;i+=1){
    pthread_join(t1[i],NULL);
  }

}


/**
  sample3.c
  KBPPelaez
  CMSC 125 2nd Sem AY 15-16

  Example of SEMAPHORE initialization, waiting, and posting
 */
