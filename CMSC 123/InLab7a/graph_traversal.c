#include <stdio.h>
#include <stdlib.h>
#include "adj_list.h"
#include "graph_traversal.h"
#include "queue.h"
#include "stack.h"

void dfsR(GRAPH *G, int source, int *visited) { // [1] recursive DFS (see Graphs slides)
  int i;

  visited[source] = 1; // set source as visisted
  printf("%d ", source); // print source vertex

  for (i=1; i<=G->num_vertices; i++) {
    if ( isAdjacent(G, source, i) && !visited[i] ) { // for each adjacent vertex that is not visited
      dfsR(G, i, visited); //perform DFS; recursive call on same graph, adjacent vertex, and same visited array
    }
  }

}

void bfs(GRAPH *G, int source) { //[2] non-recursive BFS (see Graphs slides)
  int i;
  QUEUE *S = createQueue(); //BFS uses a queue
  NODE *node = createNode(source);	//creates source node to be enqueued first
  int *visited = malloc(sizeof(int) * G->num_vertices+1);	//array to store information if a 
															//	vertex is visited or not
  for (i=1; i<=G->num_vertices; i++) {	//set all vertices to be not visited at first
    visited[i] = 0;
  }

  enqueue(S, node);	//enqueues the source node 

  while(!isEmpty(S)){	//loop if queue is not empty
    int u = dequeue(S);	//dequeue 
	
	if (visited[u]) continue;   //skip if u is already visited
	
	visited[u] = 1;			    //set u as visited
    printf("%d ", u);	        //and print u

    DATA *node = (G->list[u]);	//set node to be the head of the list of neighbors of the u (u-th index)
    while(node){	
      int v = node->v;			    //the vertex adjacent to u
      NODE *nodev = createNode(v);	//creates the vertex node for enqueueing 
      if(visited[v] == 0){		    //if the v is not visited,
        enqueue(S,nodev);	        //then enqeueue v
      }
      node = node->next;	        //traverses the list
    }

  }

}

void dfs(GRAPH *G, int source) { //[2] non-recursive DFS (see Graphs slides)
   //Your INLAB Exercise
   //should be very similar to the bfs function
   //except that this function uses a Stack ADT instead of Queue ADT
   int i;
   STACK *S = createStack();
   NODE *node = createNode(source);
   int *visited = malloc(sizeof(int) * G->num_vertices+1);

   for(i = 1; i <= G->num_vertices; i++){
     visited[i] = 0;
   }

   push(S, node);

   while(!isEmpty(S)){
     int u = pop(S);

     if(visited[u]) continue;

     visited[u] = 1;
      printf("%d ", u);

      DATA *node = (G->list[u]);
      while(node){
        int v = node->v;
        NODE *nodev = createNode(v);
        if(visited[v] == 0){
          enqueue(S, nodev);
        }
        node = node->next;
      }
   }
}

/* [1]
  take note of the parameters for DFS

  why do we need to add int *visited as params?
    this is to properly keep track the visited vertices during recursive calls

  if we instantiate a new visited array every time a recursive call happens
    we lose all the information we had before

  you may also think of the visited array as an accumulator
*/

/* [2] BFS uses a queue, DFS uses a stack (see Graphs slides)
*/
