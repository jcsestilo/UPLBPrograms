CC = gcc
CFLAGS = -O

default: run

run: build
	./run < test1.in
build: compile
	$(CC) -o run main.o adj_list.o
compile: main.c adj_list.h adj_list.c
	$(CC) -c main.c
	$(CC) -c adj_list.c
clean:
	$(RM) *.o *.gch
