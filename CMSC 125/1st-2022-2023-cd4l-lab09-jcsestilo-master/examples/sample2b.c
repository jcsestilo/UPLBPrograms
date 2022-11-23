/**
  sample2b.c
  CMSC 125 1st Sem AY 2020-2021

  Example of mutex with waiting
  on a simple producer/consumer program
*/

#include<stdio.h>
#include<unistd.h>
#include<pthread.h>


pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
int resource = 0;

void *consume(void *args) {
  pthread_mutex_lock(&lock);

  // start of critical region
  printf("C: checking resources...\n");
  while (resource < 100); // waits until resource is full
  // this will infinitely loop since consumer still has the mutex;
  // producer can never produce resources

  resource -= 100;
  printf("C: resources consumed.\n");
  // end of critical region

  pthread_mutex_unlock(&lock);
  pthread_exit(NULL);
}

void *produce(void *args) {
  pthread_mutex_lock(&lock);

  // start of critical region
  printf("P: producing resources...\n");
  sleep(2);
  resource = 100;
  printf("P: resources restored. %d resources left.\n", resource);
  // end of critical region

  pthread_mutex_unlock(&lock);
  pthread_exit(NULL);
}


int main() {
  pthread_t producer, consumer;

  pthread_create(&consumer,NULL,consume,NULL);
  pthread_create(&producer,NULL,produce,NULL);

  pthread_join(consumer, NULL);
  pthread_join(producer, NULL);

  return 0;
}
