#include<stdio.h>
//What does this program do?
void display (int A[10]) {
    int i;
    for(i=0; i<10; i++) {
        printf("%d\n", A[i]);
    }
}

int main() {
    int list[10], i;
    for(i=0; i<10; i++) {
        list[i]=i;
    }

    display(list);

    return 0;
}