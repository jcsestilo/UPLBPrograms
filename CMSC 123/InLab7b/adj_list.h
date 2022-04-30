#ifndef _ADJ_LIST_H_
#define _ADJ_LIST_H_

typedef struct data_tag { // the DATA contains the neighbor information
  int v;
  int w; //weight
  struct data_tag *next;
}DATA;

typedef struct adj_list_tag {
  DATA **list; // a 2d array of DATA called list; this is our adjacency list
  int num_vertices; // in order to check if the vertices added are still valid
}GRAPH;


GRAPH *createGraph(int);
void addEdge(GRAPH*, int, int, int);
int isAdjacent(GRAPH*, int, int);
void printGraph(GRAPH*);

#endif
