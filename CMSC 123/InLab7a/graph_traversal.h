#ifndef _GRAPH_TRAVERSAL_H_
#define _GRAPH_TRAVERSAL_H_
#include "adj_list.h" // replace with representation of your choice

void dfsR(GRAPH *G, int source, int *visited); //recursive DFS
void bfs(GRAPH *G, int source); //non-recursive BFS
void dfs(GRAPH *G, int source); //non-recursive DFS

#endif
