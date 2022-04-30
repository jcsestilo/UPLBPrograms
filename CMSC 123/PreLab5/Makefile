CC = gcc
CFLAGS = -O

default: run

run: build
	./run < program.cs
build: compile
	$(CC) -o run main.o heap.o
compile: main.c heap.c heap.h
	$(CC) -c main.c
	$(CC) -c heap.c
clean:
	$(RM) *.o *.gch
