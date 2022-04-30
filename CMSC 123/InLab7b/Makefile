CC = gcc
CFLAGS = -O

default: run

run: build
	./run < test2.in
build: compile
	$(CC) -o run main.o sssp.o adj_list.o
compile: main.c sssp.h sssp.c adj_list.h adj_list.c
	$(CC) -c main.c
	$(CC) -c sssp.c adj_list.c
clean:
	$(RM) *.o *.gch
