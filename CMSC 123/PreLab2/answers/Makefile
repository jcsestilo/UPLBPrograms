CC = gcc
CFLAGS = -O

default: queue

clean:
	$(RM) *.o *.gch

#make commands for queue
queue: build_queue
	./queue < program.cs
build_queue: compile_queue
	$(CC) -o queue main_queue.o queue.o list.o
compile_queue: main_queue.c queue.h queue.c list.h list.c
	$(CC) -c main_queue.c queue.c list.c
	
#Pre-Lab Exercise: append make commands for stack
stack: build_stack
	./stack < program.cs
build_stack: compile_stack
	$(CC) -o stack main_stack.o stack.o list.o
compile_stack: main_stack.c stack.h stack.c list.h list.c
	$(CC) -c main_stack.c stack.c list.c