#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct song_tag {
    char title[20];
    char artist[20];
    char album[20];
    struct song_tag *nextSong;
} song;

typedef struct playlist_tag {
    char name[50];
    int songCount;
    song *songHead;
    struct playlist_tag *next;
} playlist;

void load(playlist *head, int *count){
    playlist *new = (playlist *) malloc (sizeof(playlist));
    song *newSong = (song *) malloc (sizeof(song));

    // Opening the file
    FILE *fp = fopen("playlists.txt", "r");

    if(fp == NULL){ // If the file does not exist, will create a new file
        printf("\nLoad file does not exist. New file will be created when you exit.\n");
        return;
    }
    else{

        // Playlist Count
        fscanf(fp, "%d\n", count);

        // If count=0
        if((*count)==0) return;

        for(int i = 0; i < (*count); i++){
            // Playlist details
            fscanf(fp, " %[^\n]s", new->name); // Playlist name
            fscanf(fp, "%d\n", &new->songCount); // Playlist song count
            new->next = NULL;

            // Song details
            for(int j=0; j < new->songCount; j++){
                fscanf(fp, " %[^\n]s", newSong->title);
                fscanf(fp, " %[^\n]s", newSong->artist);
                fscanf(fp, " %[^\n]s", newSong->album);
                newSong->nextSong = NULL;

                // Adding to tail yung song node
                if(new->songHead == NULL){
                    newSong->nextSong = new->songHead;
                    new->songHead = newSong;
                } else {
                    song *tempSong = new->songHead;
                    while(tempSong->nextSong != NULL){
                        tempSong = tempSong->nextSong;
                    }

                    tempSong->nextSong = newSong;
                }
            }

            // Adding to tail yung playlist node
            if(head==NULL){ // Iaadd sa head kapag wala pang laman
                new->next = head;
                head = new;
            } else { 
                playlist *temp = head;
                // Move nang move hangga't hindi napupunta sa huli
                while(temp->next != NULL){
                    temp = temp->next;
                }
                temp->next=new;
            }
        }

        printf("Loaded file.\n");
        fclose(fp);

    }

}

void addPlaylist(playlist **head){
    // Making "new" pointer
    playlist *new = (playlist *) malloc (sizeof(playlist));
    new->next = NULL;

    // Getting input from the user
    printf("Enter PLAYLIST NAME: ");
    scanf(" %[^\n]s", new->name);
    getchar();

    // Removing the \n character from fgets
    new->name[strcspn(new->name, "\n")] = '\0';

    // If walang laman si head OR may laman pero mas nauuna si new kesa kay head alphabetically
    // In short, add kay HEAD or first element
    if((*head) == NULL || ((*head) != NULL && new->name > (*head)->name)){
        new->next = (*head); // Pointer nung new is magpopoint sa pinopoint ni head
        (*head) = new; // Head will point kay new, so new is first element na
    } else {
        // Si temp ay hindi nasa huli at yung name nung susunod kay temp is "smaller" than user input name
        playlist *temp = (*head);
        while (temp->next != NULL) {
            // Checking if there is a same playlist name as the user entered
            if(strcmp(temp->name, new->name) == 0) {
                printf("Playlist name already exists!\n");
                return;
            }
        }
        
        while(temp->next != NULL && temp->next->name > new->name){
            temp = temp->next; // Temp will usod
        }

        // Kapag nasa right place na, isisingit si NEW between kung saan nakaturo si temp at sa susunod
        new->next = temp->next;
        temp->next = new;

    }
    printf("Playlist %s succesfully added!\n", new->name);
}

void addSongToPlaylist(playlist *head){
    int checker=0, i=0;
    char userInput[50];

    // Printing of the playlists
    printf("PLAYLIST/S AVAILABLE:\n");
    for(playlist *temp1 = head; temp1 != NULL; temp1 = temp1->next){
        printf("\t[%d] %s\n", i, temp1->name);
        i++;
    }
    
    // Getting user input
    printf("Enter playlist name: ");
    scanf(" %[^\n]s", userInput);
    getchar();

    for(playlist *temp = head; temp != NULL; temp=temp->next){
        if(strcmp(userInput, temp->name) == 0){
            song *newSong = (song *) malloc (sizeof(song));
            
            // Getting song details
            printf("Enter song title: ");
            scanf(" %[^\n]s", newSong->title);
            getchar();
            printf("Enter song artist: ");
            scanf(" %[^\n]s", newSong->artist);
            getchar();
            printf("Enter song album: ");
            scanf(" %[^\n]s", newSong->album);
            getchar();

            // Put at head
            if(temp->songHead == NULL || (temp->songHead != NULL && newSong->title > temp->songHead->title)){
                newSong->nextSong = temp->songHead;
                temp->songHead = newSong;
            } else {
                song *tempSong = temp->songHead;
                
                while(tempSong->nextSong != NULL && tempSong->nextSong->title > newSong->title){
                    tempSong = tempSong->nextSong;
                }

                newSong->nextSong = tempSong->nextSong;
                tempSong->nextSong = newSong;
            }

            temp->songCount++;
            checker = 1;
            printf("Song successfully added.\n");
            break;
        }
    }

    if(checker == 0) printf("Invalid playlist!\n");
}

void removeSongFromPlaylist(playlist *head){
    int i=0, checker=0;
    char userInputPlaylist[50], userInputTitle[20];

    // Printing of the playlists
    printf("PLAYLIST/S AVAILABLE:\n");
    for(playlist *temp = head; temp != NULL; temp = temp->next){
        printf("\t[%d] %s\n", i, temp->name);
        i++;
    }
    
    // Getting user input for playlist name
    printf("Enter playlist name: ");
    scanf(" %[^\n]s", userInputPlaylist);
    getchar();

    // Checking if userInputPlaylist is valid
    for(playlist *temp1 = head; temp1 != NULL; temp1=temp1->next){
        
        // If may kaparehas
        if(strcmp(temp1->name, userInputPlaylist) == 0){
            // If there are no songs in the playlist
            if(temp1->songCount == 0) {
                printf("No songs here!\n");
                return;
            }
            
            else {
                int l=0; // Para sa index
                // Printing of lahat ng songs sa playlist
                printf("SONG/S AVAILABLE IN *%s*:\n", temp1->name);
                for(song *tempSong = temp1->songHead; tempSong != NULL; tempSong=tempSong->nextSong){  
                    printf("[%d] %s by %s\n", l, tempSong->title, tempSong->artist);
                    l++;
                }

                // Getting input from the user
                printf("Enter SONG TITLE: ");
                scanf(" %[^\n]s", userInputTitle);
                
                // Declaring of the pointer for idedelete
                song *toDel = temp1->songHead;

                while(toDel != NULL){
                    // If may kaparehas, break the loop na
                    if (strcmp(toDel->title, userInputTitle) == 0) break;
                    // Move sa other node
                    toDel = toDel->nextSong;
                }

                if(toDel == NULL) printf("There are no songs with this title.\n");
                else {
                    // If the song to delete is the first element
                    if(toDel == temp1->songHead){
                        // songHead will move one node to the next
                        temp1->songHead = temp1->songHead->nextSong;
                        // toDel will be deleted
                        free(toDel);
                    } else{
                        song *tempSong = temp1->songHead;
                        // Maglalakad si tempSong hanggat di niya nakukuha yung node before doon sa toDel
                        while(tempSong->nextSong != toDel){
                            tempSong = tempSong->nextSong;
                        }
                        // Yung nextSong before toDel ay magpopoint doon sa node after toDel
                        // If toDel is nasa tail, magiging NULL lang si nextSong
                        tempSong->nextSong = toDel->nextSong;
                        // toDel will be deleted
                        free(toDel);
                    }
                }

                // Checker will be updated and hindi na masasatisfy condition sa baba
                checker = 1;
                printf("Song successfully removed.\n");
                temp1->songCount--;
                break;
            }

        }
    }
    if (checker == 0) printf("Invalid input.\n");
}

void viewPlaylist(playlist *head){
    int i=0, checker=0, k;
    char userInput[50];

    // Checker if there are playlists
    if(head == NULL){
        printf("Please add a playlist first.\n");
        return;
    }

    // Printing of available playlists
    printf("PLAYLIST/S AVAILABLE:\n");
    for(playlist *temp = head; temp != NULL; temp=temp->next){
        printf("\t[%d] %s\n", i, temp->name);
        i++;
    }
    // Getting user input
    printf("Enter PLAYLIST NAME: ");
    scanf(" %[^\n]s", userInput);
    getchar();

    for (playlist *temp1 = head; temp1 != NULL; temp1 = temp1->next){
        // Kapag may kaparehas
        if(strcmp(temp1->name, userInput) == 0){
            // Printing of playlist name tsaka song count nung playlist na yun
            printf("PLAYLIST NAME: %s\n", temp1->name);
            printf("SONG COUNT: %d\n\n", temp1->songCount);

            // Printing of song deets
            if(temp1->songCount > 0){
                k = 0;
                // Tumatakbo si tempSong from songHead, tapos print print
                for(song *tempSong = temp1->songHead; tempSong != NULL; tempSong=tempSong->nextSong){
                    printf("SONG TITLE %d: %s\n", k+1, tempSong->title);
                    printf("SONG ARTIST %d: %s\n", k+1, tempSong->artist);
                    printf("SONG ALBUM %d: %s\n", k+1, tempSong->album);
                    k++;
                }
            }
            // Magbabago value ni checker tapos di na niya masasatisfy yung condition sa baba
            checker=1;
        }
    }

    // If walang kaparehas
    if (checker == 0) printf("Invalid playlist input!\n");

}

void viewAllData(playlist *head){
    int k;

    // Gagawa ng temp na pointer, tapos first ipopoint kay head. Tapos maglalakad si temp sa buong ano
    // Habang naglalakad, print yung PLAYLIST tsaka SONG COUNT only
    for(playlist *temp=head; temp != NULL; temp=temp->next){
        printf("PLAYLIST: %s\n", temp->name);
        printf("SONG COUNT: %d\n\n", temp->songCount);

        if(temp->songCount > 0){
            k = 0;
            // Printing of the song deets of each song
            for(song *tempSong=temp->songHead; tempSong != NULL; tempSong=tempSong->nextSong){
                printf("SONG TITLE %d: %s\n", k+1, tempSong->title);
                printf("SONG ARTIST %d: %s\n", k+1, tempSong->artist);
                printf("SONG ALBUM %d: %s\n", k+1, tempSong->album);
                k++;
            }
        }
    }
}

void save(playlist *head, int count){
    // Making or writing the file
    FILE *fp = fopen("playlists.txt", "w");

    // Start of the printing
    fprintf(fp, "%d\n", count);
    // If no data
    if (count==0){
        printf("There is no data to be saved.\n");
        fclose(fp);
        return;
    }

    for(playlist *temp = head; temp != NULL; temp=temp->next){
        fprintf(fp, "%s\n", temp->name);
        fprintf(fp, "%d\n", temp->songCount);

        // For the song details
        for(song *tempSong = temp->songHead; tempSong != NULL; tempSong=tempSong->nextSong){
            fprintf(fp, "%s\n", tempSong->title);
            fprintf(fp, "%s\n", tempSong->artist);
            fprintf(fp, "%s\n", tempSong->album);
        }
    }

    printf("Saved data successfuly. :) GOOD BYE CMSC 21\n");
    fclose(fp);
    return;
}

int main(){
    playlist *head = NULL; // head pointer
    int choice, count=0;

    // Load file before anything else
    load(head,&count);

    while (1){
        // Printing of the menu
        printf("\n======= MENU =======\n");
        printf("[1] Add Playlist\n");
        printf("[2] Add Song to Playlist\n");
        printf("[3] Remove Song from Playlist\n");
        printf("[4] View a Playlist\n");
        printf("[5] View All Data\n");
        printf("[6] Exit\n\n");
        printf("Enter choice: ");
        scanf("%d", &choice);
        getchar();

        switch (choice) {
            case 1: // Add Playlist
                addPlaylist(&head);
                count++;
                break;
            case 2: // Add Song to Playlist
                if (head == NULL) printf("There are no playlists available.\n");
                else addSongToPlaylist(head);
                break;
            case 3: // Remove Song from Playlist
                if (head == NULL) printf("There are no playlists available.\n");
                else removeSongFromPlaylist(head);
                break;
            case 4: // View a Playlist
                if(count==0) printf("There are no playlists available.\n");
                else viewPlaylist(head);
                break;
            case 5: // View All Data
                if (head == NULL) printf("There are no playlists available.\n");
                else viewAllData(head);
                break;
            case 6: // Exit
                save(head, count);
                return 0;
            default: // Error message
                printf("Invalid input. Please try again.\n\n");
                break;
            }
        printf("-----------------------------\n"); // Ending line
    }
}