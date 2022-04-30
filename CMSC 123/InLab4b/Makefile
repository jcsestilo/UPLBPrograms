CC = gcc
CFLAGS = -0

default: run

run: build
	./run < program.cs
build: compile
	$(CC) -o run main.o BST.o
compile: main.c BST.h BST.c
	$(CC) -c main.c BST.c
clean:
	$(RM) *.o *.gch


