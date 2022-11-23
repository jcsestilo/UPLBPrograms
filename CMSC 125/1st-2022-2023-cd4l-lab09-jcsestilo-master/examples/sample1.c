/**
  sample1.c
  KBPPelaez
  CMSC 125 2nd Sem AY 15-16
  Revised 2020

  Example of the mutex initialization, locking and unlocking
 */

#include<stdio.h>
#include<pthread.h>
#include<time.h>
#include<stdlib.h>
#include<unistd.h>

//shared resources
int var1 = 1;
//initializing my mutex lock without using the function call
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

//function to be executed
void * doThis(void *args){
  int x,temp;

  x = rand()%5;

  pthread_mutex_lock(&lock);
  //start of critical region
  temp = var1;
  printf("Thread %ld sleeping for %dseconds...\n",pthread_self(),x);
  sleep(x);
  temp = temp + 1;
  var1 = temp;
  //end of critical region
  pthread_mutex_unlock(&lock);

  pthread_exit(NULL);
}

//main function
int main(){
  pthread_t t1,t2,t3;

  srand(time(NULL));
  //i have 3 threads doing the same function
  pthread_create(&t1,NULL,doThis,NULL);
  pthread_create(&t2,NULL,doThis,NULL);
  pthread_create(&t3,NULL,doThis,NULL);

  //waiting for them to finish
  pthread_join(t1,NULL);
  pthread_join(t2,NULL);
  pthread_join(t3,NULL);

  printf("var1 %d\n",var1);

}


/**
  sample1.c
  KBPPelaez
  CMSC 125 2nd Sem AY 15-16

  Example of the mutex initialization, locking and unlocking
 */
