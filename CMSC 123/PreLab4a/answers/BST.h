#ifndef _BST_H_
#define _BST_H_

typedef struct bst_node{
 
    struct bst_node* left;  // left pointer
    struct bst_node* right; // right pointer
    struct bst_node* parent; // parent pointer
    int key; // key of this node (used to compare)

    //int height; // for AVL later path length from this node to the deepest leaf
}BST_NODE;

typedef struct bst{
    // the root of the tree
    BST_NODE* root;
}BST;

/*
** function: createBSTNode
** requirements:
    an integer indicating the key of the node
	L, R, or P are initialized as `NULL`
** results:
    creates a BST node with fields initialized
    returns a pointer of this instance
*/
BST_NODE* createBSTNode(int key);


/*
** function: createBST
** requirements:
    NONE
** results:
    creates an empty BST with root field initialized to NULL
*/
BST* createBST();

/*
** function: isEmpty
** requirements:
    a non-null BST pointer
** results:
    returns 1 if BST is empty;
    otherwise, return 0
*/
int isEmpty(BST* B);


/*
** function: insert
** requirements:
    a non-null BST pointer
    a non-null BST_NODE pointer
** results:
    inserts the given `node` to the BST `B`
*/
void insert(BST* B, BST_NODE* node); 

//Non-recursive insert function is the InLab Exercises
//Only a recursive version is provided
void insertR(BST_NODE** rootptr, BST_NODE* node); 


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
BST_NODE* search(BST* B, int key);

/*
** function: showTree
** requirements:
    a non-null BST pointer
** results:
    displays elements of the BST in tree mode.
*/
void showTree(BST* B);

/*
** function: clearNodeR
** requirements:
    a non-null BST pointer
** results:
    removes all the nodes of the BST.
*/
void clearNodeR(BST_NODE** node_ptr); //called by clear

/*
** function: clear
** requirements:
    a non-null BST pointer
** results:
    removes all the nodes of the BST.
*/
void clear(BST* B); //calls clearNodeR
#endif