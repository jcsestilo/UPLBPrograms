CC = gcc
CFLAGS = -O

default: run

run: build
	./run < program.cs
build: compile
	$(CC) -o run main.o sort.o
compile: main.c sort.h sort.c
	$(CC) -c main.c sort.c

#silent mode
silent: build2
	./silent < program2.cs
build2: compile2
	$(CC) -o silent main2.o sort.o
compile2: main2.c sort.h sort.c
	$(CC) -c main2.c sort.c

clean:
	$(RM) *.o *.gch
