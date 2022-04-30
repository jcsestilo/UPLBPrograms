#include "BST.h"
#include <stdio.h>
#include <stdlib.h>
// include other headers u need

// a recursive subroutine to display the BST in tree mode
void showTreeHelper(BST_NODE* node, int tabs){
	if(node==NULL) return; // node is null, do nothing

	showTreeHelper(node->right, tabs + 1);
	for(int i=0; i<tabs; i++) 
		printf("\t");
	
	printf("%d(p%d)\n", node->key, node->parent==NULL?0:node->parent->key);
	//printf("%d\n", node->key); //use this instead to show nodes only
	
	showTreeHelper(node->left, tabs + 1);
}

void showTree(BST* B){
	printf("\n\nstart----------\n\n");
	showTreeHelper(B->root, 0);
	printf("\n------------end\n\n");
}

// insert your function definitions below
/*
** function: createBSTNode
** requirements:
    an integer indicating the key of the node
	L, R, or P are initialized as `NULL`
** results:
    creates a BST node with fields initialized
    returns a pointer of this instance
*/
BST_NODE* createBSTNode(int key){
	BST_NODE *new_node = (BST_NODE *)malloc(sizeof(BST_NODE));
	new_node->left=new_node->right=new_node->parent=NULL;
	new_node->key=key;
	return(new_node);
}


/*
** function: createBST
** requirements:
    NONE
** results:
    creates an empty BST with root field initialized to NULL
*/
BST* createBST(){
	BST *new_bst = (BST *)malloc(sizeof(BST));
	new_bst->root=NULL;
	return(new_bst);
}

/*
** function: isEmpty
** requirements:
    a non-null BST pointer
** results:
    returns 1 if BST is empty;
    otherwise, return 0
*/
int isEmpty(BST* B){
	return(B->root==NULL);
}

void insertR(BST_NODE **rootptr,BST_NODE *new){
  BST_NODE *temp=*rootptr; //temp=root in main

  if(*rootptr==NULL) 
	*rootptr=new;
  else{
	new->parent=*rootptr;
	if(new->key < (*rootptr)->key)
		insertR(&((*rootptr)->left),new);
	else insertR(&((*rootptr)->right),new);
  }
}

/*
** function: insert
** requirements:
    a non-null BST pointer
    a non-null BST_NODE pointer
** results:
    inserts the given `node` to the BST `B`
*/
void insert(BST* B, BST_NODE* node){
	insertR(&(B->root), node);
	//NOTE: this is just a place holder answer
	//      you have still have to implement 
	//      the non-recursive version of the insert function in Exer04
} 

/*
** function: search
** requirements:
    a non-null BST pointer
    a non-empty BST
    an integer `key`
** results:
    finds `key` from BST `B` and returns its node pointer if found, 
        otherwise, return `NULL`
*/
BST_NODE* search(BST* B, int key){
	BST_NODE *root=B->root;

	while(root!=NULL && root->key!=key){
		if(key < root->key) {
			root=root->left;
		}
		else{
			root=root->right;
		}
	}//while(1)
	return root;
}

/*
** function: clearNodeR
** requirements:
    a non-null BST pointer
** results:
    removes all the nodes of the BST.
*/
void clearNodeR(BST_NODE** node_ptr){
	BST_NODE *node=*node_ptr;
	if(node==NULL) return; // node is null, do nothing

	clearNodeR(&(node->left));
	clearNodeR(&(node->right));
	free(node);
	*node_ptr=NULL;
}

/*
** function: clear
** requirements:
    a non-null BST pointer
** results:
    removes all the nodes of the BST.
*/
void clear(BST* B){
	//recursively remove all the node
	clearNodeR(&(B->root)); 
	//NOTE: In the BST deletion exercise you have to 
	//      have to replace this with a reapeated 
	//      delete function call
	
}

/*
** function: delete
** requirements:
    a non-null BST pointer
    a non-empty BST
    an integer `key`
** results:
    finds `key` and removes the key from BST `B`
	see the lecture slides and the notes below
	make sure to draw your solution first before 
	   typing anything at this point
*/


void delete(BST* B, int key){
//posting this in advance as the In-Lab MAIN Exercise
//calls the descendantSuccessor when deleting `key` 
//whose node has two children

/* 
sharing the notes from last sem...

This is just the guide how to implement delete function "easily" i.e. the way two of my students have implemented it in just barely 30 lines of codes. This is not to limit your creativity, but just to guide you. If you think you can do better, then why not...

Again, here's the guide for the delete function:

Step1. Let todel be a BST_NODE* that will hold or point to the node to be deleted
i..e. 
todel = search(B, key); 
if (todel == NULL) return;  //this is to rule-out the case where todel is NULL

Step2. //TWO CHILDREN CASE

if (todel has TWO CHILDREN) {
   2.a. replace todel->key with the key of its descendant successor
   2.b. todel should point to its descendant successor
}
NOTE: 
This makes todel point to a node that has either only one child or no children at all i.e. proceed to Step3.
Just use a loop or the minimum function to find the descendant successor. DO NOT, implement the successor fully at this point. You can do that later when you have implemented deletion correctly. The reason is that the successor function is also a complicated function by itself because it should look for either the descendant or the ancestor type of successor, we only need the descendant type for the delete function.
 

Step3. //HAS ONE CHILD OR NO CHILDREN CASE

NOTE: At this point todel has the following properties:
todel is not NULL, this due to Step1
todel is pointing to a node with one child or no children at all, this due to either that todel does so right from the start OR that it has to gone through Step2

3.a. You still have to set a child pointer to point to the only child or set to NULL if todel has no children

3.b. Replace the node to be deleted (held by todel) with its only child or NULL child
Aside from the two cases for the child pointer that either it is NULL or it points to the only child, you also need to consider the following cases:
- whether todel is also pointing to the root node or NOT
- todel is either a left or right child of its parent

3.c. free(todel);
 
HINT: DRAW!!!

*/
	
	
}


/*
** function: minimum
** requirements:
    a non-null BST pointer
    a non-empty BST
** results:
    finds the minimum `key` from BST `B` and returns its node pointer if found, 
        otherwise, return `NULL`
*/
BST_NODE* minimum(BST_NODE *node){
	//called by descendantSuccessor
	BST_NODE*root=node;
	while(root!=NULL && root->left!=NULL)
			root=root->left;
	return(root);
}


/*
** function: descendantSuccessor
** requirements:
    a non-null BST pointer
    a non-empty BST
	an integer `key`
	
** results:
    finds the descendant type of successor of the given `key` from BST `B` and returns its node pointer if found, otherwise, return `NULL`
*/
BST_NODE* descendantSuccessor(BST_NODE *node){
	BST_NODE*temp=node;
	
	if(temp==NULL || temp->right==NULL) return temp;
	
	//calls the minimum function
	return minimum(temp->right);
	
}