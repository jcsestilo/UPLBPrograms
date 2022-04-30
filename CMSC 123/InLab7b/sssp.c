#include <stdlib.h>
#include <stdio.h>
#include "sssp.h"
#include "adj_list.h"

//allocate and initialize REF_TABLE (common to MST and SSP)
REF_TABLE *createTable(int num_vertices) {
  int i;
  REF_TABLE *T = malloc(sizeof(REF_TABLE));
  T->visited = malloc(sizeof(int) * (num_vertices+1));
  T->distance = malloc(sizeof(int) * (num_vertices+1));
  T->parent = malloc(sizeof(int) * (num_vertices+1));
  T->num_vertices=num_vertices;

  for (i=1; i<=num_vertices; i++) {
    T->visited[i] = 0;
    T->distance[i] = 999;
    T->parent[i] = -1;
  }

  return T;
}

//deallocate REF_TABLE (common to MST and SSP)
void destroyTable(REF_TABLE **Tptr) {

  if(Tptr!=NULL && *Tptr!=NULL){
    free((*Tptr)->visited);
    free((*Tptr)->distance);
    free((*Tptr)->parent);
  
    free(*Tptr);
    *Tptr=NULL;
  }
}

//print REF_TABLE (common to MST and SSP)
void printTable(REF_TABLE *T){
  printf("\nvertex visited distance parent\n");
  for(int i = 1; i<=T->num_vertices; i++){
    printf("%6d %7d %8d %5d\n", i, T->visited[i], T->distance[i], T->parent[i]);
  }
}

//return v, such that T->distance[v] is minimum (common to MST and SSP)
int minDistanceVertex(REF_TABLE *T){		
  int i, minDis = 999, u=0;

  for (i = 1; i <= T->num_vertices; ++i){
    if(!T->visited[i] && T->distance[i] < minDis){
      u = i;
      minDis = T->distance[i];
    }
  }
  return u;
}

//**************************************************************************

//MST function: returns the filled-up REF_TABLE (see Graph Problems slides)
REF_TABLE *primMST(GRAPH *G, int source) {
  REF_TABLE *T = createTable(G->num_vertices); //creates the MST Table
  int i, u, alt_distance;
  DATA *temp;

  T->distance[source] = 0; //set source's distance as 0
  for(i=1;i<=G->num_vertices;i++){
    u = minDistanceVertex(T);
    T->visited[u]=1;
    
    temp=G->list[u];
    while(temp != NULL){
	  
      if (!T->visited[temp->v]){
		alt_distance=temp->w; //just replace this line for SSSP
		  if( alt_distance < T->distance[temp->v]){
            T->distance[temp->v] = alt_distance;
            T->parent[temp->v] = u;
		  }
      }
      temp = temp->next;
    }
    //printTable(T);
  }
  return T;
}

//REF_TABLE interpretation for MST
void printMSTCost(REF_TABLE *T){
  int cost=0,i,n=T->num_vertices;
  for(i=1;i<=n;i++){
    if(T->parent[i]!=-1){
      printf("Edge(%i, %i) Cost: %i \n", T->parent[i],i,T->distance[i]);
      cost+=T->distance[i];
    }
  }
  printf("Total MST Cost: %i\n", cost);  

}


//**************************************************************************

//SSSP function: returns the filled-up REF_TABLE (see Graph Problems slides)
REF_TABLE *dijkstraSSSP(GRAPH *G, int source) {
  //Your INLAB Exercise
  //should be very similar to primMST function 
  //except for the computation of the alt_distance variable
  REF_TABLE *T = createTable(G->num_vertices);
  int i, u, alt_distance;
  DATA *temp;

  T->distance[source] = 0;
  for(i=1; i<=G->num_vertices; i++){
    u = minDistanceVertex(T);
    T->visited[u]=1;

    temp = G->list[u];
    while(temp != NULL){


      if(!T->visited[temp->v]){
    alt_distance = (temp->w) + (T->distance[u]);
        if(alt_distance < T->distance[temp->v]){
          T->distance[temp->v] = alt_distance;
          T->parent[temp->v] = u;
        }
      }
      temp = temp->next;
    }
    //printTable(T);
  }
  return T;
}

//REF_TABLE interpretation for SSSP
void printSSSP(REF_TABLE *T, int source){
  //see Graph Problems slides for the details
  // how to interprent the REF_TABLE for SSSP
  //see also expected.txt for the expected results
  int i, j,n=T->num_vertices, counter;

  int temp;
  for(i=2; i<=n; i++){
    printf("SSSP Distance from %d->%d is %d: ", source, i, T->distance[i]);

    int vertices[T->num_vertices];
    temp = i;
    counter = 0;
    // Printing of the vertices
    printf("{%d->", source);
    while(T->parent[temp] != -1){
      //printf("%d", temp);
      vertices[counter] = temp; // first, store the values in vertices array
      counter++; // increment counter, counter will also be used in printing
      temp = T->parent[temp]; // move to the parent of the parent
    }

    for(j=(counter-1); j>=0; j--){ // from vertices[counter] to vertices[0], print the vertices
      if(j==0) printf("%d}", vertices[j]);
      else printf("%d->", vertices[j]);
      
    }

    printf("\n");

  }
}