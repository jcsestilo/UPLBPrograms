#ifndef _STACK_H_
#define _STACK_H_

#include "list.h"
// .. contents of the header file

typedef LIST STACK;

STACK * createStack();

void push(STACK*, NODE*); // stack's insert operation

int pop(STACK*); // stack's delete operation
#endif