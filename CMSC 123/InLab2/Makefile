CC = gcc
CFLAGS = -O

default: postfix

postfix: build
	./postfix
build: compile
	$(CC) -o postfix postfix.o stack.o list.o
compile: postfix.c stack.h stack.c list.h list.c
	$(CC) -c postfix.c stack.c list.c

clean:
	$(RM) *.o *.gch

