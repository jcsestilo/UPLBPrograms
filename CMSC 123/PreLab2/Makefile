CC = gcc
CFLAGS = -O

make queue: queue
make stack: stack

clean:
	$(RM) *.o *.gch

#make commands for queue
queue: build_queue
	./queue < program1.cs
build_queue: compile_queue
	$(CC) -o queue main1.o queue.o list.o
compile_queue: main1.c queue.h queue.c list.h list.c
	$(CC) -c main1.c queue.c list.c
	
#Pre-Lab Exercise: append make commands for stack
stack: build_stack
	./stack < program2.cs
build_stack: compile_stack
	$(CC) -o stack main2.o stack.o list.o 
compile_stack: main2.c stack.h stack.c list.h list.c
	$(CC) -c main2.c stack.c list.c