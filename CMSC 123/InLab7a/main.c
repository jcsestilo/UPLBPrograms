#include <stdio.h>
#include <stdlib.h>
#include "adj_list.h"
#include "graph_traversal.h"


int main() {
  int num_vertices, num_edges;
  int u, v;
  int i;

  scanf("%d %d", &num_vertices, &num_edges);
  GRAPH *G = createGraph(num_vertices);

  for (i=0; i<num_edges; i++) {
    scanf("%d %d", &u, &v);
    addEdge(G, u, v);
    addEdge(G, v, u);
  }
  
  printGraph(G);

  int *visited = malloc(sizeof(int) * num_vertices+1);
  for (i=0; i<num_vertices+1; i++) {
    visited[i] = 0;
  }
  printf("DFSr: ");
  dfsR(G, 1, visited);
  printf("\n");
  
  printf(" DFS: ");
  dfs(G, 1);  //Your InLab Exercise
  printf("\n");

  printf(" BFS: ");
  bfs(G, 1);
  printf("\n");
  


  return 0;
}
