#include <stdio.h>
#include <stdlib.h>
#include "adj_list.h"

GRAPH *createGraph(int num_vertices) {
  int i;
  GRAPH *G = (GRAPH *) malloc(sizeof(GRAPH)); // allocate memory for GRAPH G

  G->num_vertices = num_vertices; // initialize number of vertices
  G->list = (DATA **) malloc(sizeof(DATA *) * (G->num_vertices + 1)); // initialize a dynamic 2d array of DATA for G->list that corresponds to the num of vertices

  // initialize each element of G->list to point to NULL
  for (i=1; i<=G->num_vertices; i++) {
    G->list[i] = NULL;
  }

  return G; // do not forget to return the newly created graph!
}


void addEdge(GRAPH *G, int u, int v, int w) {
  //check if u and v are valid vertices based on number of vertices
  if (u <= G->num_vertices && v <= G->num_vertices) { 
    DATA **hptr = &(G->list[u]); // get a reference to list of neighbors of u

    //initialize new DATA; update the value of v as well
    DATA *new = (DATA *)malloc(sizeof(DATA));
    new->v = v;
    new->w = w;

    // RECALL: insert at head in linked list
    new->next = *hptr;
    *hptr=new;

  }
}


int isAdjacent(GRAPH *G, int u, int v) {
  if (u <= G->num_vertices && v <= G->num_vertices) { //check if u and v are valid vertices based on number of vertices
    DATA *temp = G->list[u]; // create a temp that points to the 
							 // u-th index of the adjacency list;
    
	// the list at the u-th index are the neighbors of vertex u

    // while u has neighbors
    while(temp!=NULL) {
      if (temp->v == v) return 1; // if neighbor == v, return 1
      temp = temp->next; // else, check other neighbors of u in the list
    }
  }
  return 0;
  // do not forget to return 0; always check the function return type
}

void printGraph(GRAPH *G) {
  int i;
  DATA *temp;

  for (i=1; i<=G->num_vertices; i++) {
    printf("%2d -> {", i);
    temp = G->list[i];

    while(temp!=NULL) {
      printf("(%2d,%2d)", temp->v, temp->w);
      temp = temp->next;
      if (temp!=NULL) printf(", ");
      else break;
    }
    printf("}\n");
  }

}
