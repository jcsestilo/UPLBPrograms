CC = gcc
CFLAGS = -O

default: run

run: build
	./run < program1.cs
	./run < program2.cs
	./run < program3.cs
build: compile
	$(CC) -o run main.o closed_hashtable.o
compile: main.c closed_hashtable.c closed_hashtable.h
	$(CC) -c main.c
	$(CC) -c closed_hashtable.c
clean:
	$(RM) *.o *.gch
