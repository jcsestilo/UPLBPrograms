/**
  sample2a.c
  CMSC 125 1st Sem AY 2020-2021

  Example of mutex with condition variables
  on a simple producer/consumer program
*/

/*
    pthread_cond_t - data type for condition variables

    pthread_cond_wait(pthread_cond_t *cond, pthread_mutex_t *mutex)
      unlocks the mutex and waits for the cond to be signalled by another thread

    pthread_cond_signal(pthread_cond_t *cond)
      signals the cond; waking it up and allowing it to continue its execution
*/

#include<stdio.h>
#include<unistd.h>
#include<pthread.h>


pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t full = PTHREAD_COND_INITIALIZER;
int resource = 0;

void *consume(void *args) {
  pthread_mutex_lock(&lock);

  // start of critical region
  printf("C: checking resources...\n");
  while (resource < 100)
    pthread_cond_wait(&full, &lock);
    // releases lock; enters sleep/waiting state while waiting for 'full' signal
    // waits until resource is full

  resource -= 100;
  printf("C: resources consumed.\n");
  // end of critical region

  pthread_mutex_unlock(&lock);
  pthread_exit(NULL);
}

void *produce(void *args) {
  pthread_mutex_lock(&lock); // acquires lock

  // start of critical region
  printf("P: producing resources...\n");
  sleep(2);
  resource = 100;
  printf("P: resources replenished.\n");
  // end of critical region

  pthread_cond_signal(&full); // wakes up any thread waiting for 'full'
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
