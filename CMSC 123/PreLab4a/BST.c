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
	//Pre-Lab Exercise: implement a non-recursive BST insert function
	BST_NODE *node = (BST_NODE*) malloc(sizeof(BST_NODE));
	node->left=NULL;
	node->right=NULL;
	node->parent=NULL;
	node->key=key;
	return node;
	
}


/*
** function: createBST
** requirements:
    NONE
** results:
    creates an empty BST with root field initialized to NULL
*/
BST* createBST(){
	//Pre-Lab Exercise: implement a non-recursive BST insert function
	BST *B = (BST*) malloc(sizeof(BST));
	B->root = NULL;
	return B;
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
	//Pre-Lab Exercise: implement a non-recursive BST insert function
	
	return(B->root==NULL);
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
	
	//Pre-Lab MAIN Exercise: implement a non-recursive BST insert function
	
	//NOTE: the provided answer is a recursive version of insert
	//      it is just a place holder answer
	//      you have still have to implement 
	//      the non-recursive version of the insert function

	BST_NODE *p=B->root;
	BST_NODE *previous;

	if(p==NULL){
		B->root=node;
		return;
	}

	while(p!=NULL){
		previous=p;
		if(node->key < p->key) {
			p=p->left;
		}
		else{
			p=p->right;
		}
	}//while(1)
	if(node->key < previous->key){
		previous->left = node;
		node->parent = previous;
	} else {
		previous->right = node;
		node->parent = previous;
	}

	
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
	//Pre-Lab Exercise: implement a non-recursive BST insert function
	BST_NODE *root=B->root;

	while(root!=NULL && root->key!=key){
		if(key < root->key) {
			root=root->left;
		}
		else{
			root=root->right;
		}
	}//while(1)
	return (root);
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