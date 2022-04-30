#ifndef _ADJ_LIST_H_
#define _ADJ_LIST_H_

typedef struct data_tag { // the DATA contains the neighbor information
  int v;
  struct data_tag *next;
} DATA;

typedef struct adj_list_tag {
  DATA **list; // a 2d array of DATA called list; this is our adjacency list
  int num_vertices; // in order to check if the vertices added are still valid
} GRAPH;


GRAPH *createGraph(int n); //Initialize the graph and its adjacency list
void addEdge(GRAPH *G, int u, int v);   //insert an edge as a pair of vertices u and v
int isAdjacent(GRAPH *G, int u, int v); //returns true if vertices u and v are adjacent
void printGraph(GRAPH*); //prints the Graph with a resemblance of an adjacency list

#endif
