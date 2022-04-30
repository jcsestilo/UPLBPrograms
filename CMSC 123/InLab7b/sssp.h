#ifndef _SSSP_H_
#define _SSSP_H_
//#include "dont forget to include graph representation"
#include "adj_list.h"

typedef struct ref_table_tag {
  int *visited;
  int *distance;
  int *parent;
  int num_vertices;
} REF_TABLE;


//REF_TABLE functions common to MST and SSSP
REF_TABLE * createTable(int num_vertices); //allocate and initialize REF_TABLE
void destroyTable(REF_TABLE **Tptr); //deallocate the REF_TABLE
void printTable(REF_TABLE *T);       //print the REF_TABLE
int minDistanceVertex(REF_TABLE *T); //return v, such that T->distance[v] is minimum

//MST
REF_TABLE * primMST(GRAPH *, int source);
void printMSTCost(REF_TABLE *T);


//SSSP: Your InLab Exercises
REF_TABLE * dijkstraSSSP(GRAPH *, int source);
void printSSSP(REF_TABLE *T, int source);

#endif
