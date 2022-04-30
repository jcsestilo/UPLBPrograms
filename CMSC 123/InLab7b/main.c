#include <stdio.h>
#include <stdlib.h>
#include "sssp.h"
#include "adj_list.h"

int main(){
  int num_vertices, num_edges;
  int u, v, w, source;
  int i, q, p;
  int vertex;
  GRAPH *G;
  REF_TABLE *T;

  scanf("%d %d %d\n", &num_vertices, &num_edges, &source);
  G = createGraph(num_vertices);

  for (i=0; i<num_edges; i++) {
    scanf("%d %d %d", &u, &v, &w);
    addEdge(G, u, v, w);
  }



  printGraph(G);

  //MST  
  T = primMST(G, source);
  printTable(T);
  printMSTCost(T);

  destroyTable(&T);//de-allocate T
   
  //SSP: YOUR INLAB Exercise
  T = dijkstraSSSP(G, source);
  printTable(T);
  printSSSP(T, source);



  return(0);
}
