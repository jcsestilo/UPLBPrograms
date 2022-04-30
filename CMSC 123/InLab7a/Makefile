CC = gcc
CFLAGS = -O

default: run

run: build
	./run < test1.in
build: compile
	$(CC) -o run main.o graph_traversal.o adj_list.o stack.o queue.o list.o
compile: main.c graph_traversal.h graph_traversal.c adj_list.h adj_list.c stack.h stack.c queue.h queue.c list.h list.c
	$(CC) -c main.c
	$(CC) -c graph_traversal.c adj_list.c stack.h stack.c queue.c list.c
clean:
	$(RM) *.o *.gch
