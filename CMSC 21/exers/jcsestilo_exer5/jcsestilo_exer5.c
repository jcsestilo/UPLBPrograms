
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Structure for the song details
struct songDetails { 
    char title[50], artist[50], album[50];
};

// Structure for the playlist
struct pList {
    char name [50];
    int songCount;
    struct songDetails songs[10]; // Array of structures with size 10
};

// Load function
void load(struct pList *pL, int *count){

    // Opening the file
    FILE *fp = fopen("playlists.txt", "r");

    if (fp == NULL) { // If the file does not exist, create a new file
        printf("\nLoad File does not exist. New file will be saved once you exit.\n\n");
    }
    else {

        // Playlist Count
        fscanf(fp, "%d\n", count);
        
        for(int i = 0; i < (*count); i++) {
            
            // Playlist details
            fgets(pL[i].name, 50, fp);
            fscanf(fp, "%d\n", &pL[i].songCount);
            
            // Song Details
            for (int j = 0; j < pL[i].songCount; j++) { 
                fgets(pL[i].songs[j].title, 50, fp); // Getting the title
                fgets(pL[i].songs[j].artist, 50, fp); // Getting the artist
                fgets(pL[i].songs[j].album, 50, fp); // Getting the album

                // Removing the '\n' from fgets
                pL[i].songs[j].title[strcspn(pL[i].songs[j].title, "\n")] = '\0';
                pL[i].songs[j].artist[strcspn(pL[i].songs[j].artist, "\n")] = '\0';
                pL[i].songs[j].album[strcspn(pL[i].songs[j].album, "\n")] = '\0';
            }

            // Closing the file
            fclose(fp);
        }

        printf("Successfully loaded data!\n");
    }
    
}

void save(struct pList *pL, int count){
    // Making/Writing the file
    FILE *fp = fopen("playlists.txt", "w");

    // Start of the printing
    fprintf(fp, "%d\n", count);
    
    // Printing the name and song count of each playlist
    for (int i = 0; i < count; i++) {
        fprintf(fp, "%s\n", pL[i].name);
        fprintf(fp, "%d\n", pL[i].songCount);
        // For printing the title, artist, and album of each song
        for (int j = 0; j < pL[i].songCount; j++)
        {   
            // Printing Song Details
            fprintf(fp, "%s\n", pL[i].songs[j].title);
            fprintf(fp, "%s\n", pL[i].songs[j].artist);
            fprintf(fp, "%s\n", pL[i].songs[j].album);
        }
        
    }
    // Closing the file
    fclose(fp);
}

int addPlaylist(struct pList *pL, int i){

    // Getting input from the user
    printf("Enter playlist name: ");
    fgets(pL[i].name, 50, stdin);

    // Removing the \n character from fgets
    pL[i].name[strcspn(pL[i].name, "\n")] = '\0';

    // // Checking if playlist name already exists
    for(int j = 0; j < i; j++) {
        if ( (strcmp(pL[j].name, pL[i].name)) == 0){
            printf("Playlist name already exists!\n");
            return 0; // Will return 0, and no incrementing will happen
        }
    }

    // Initialization for songCount
    pL[i].songCount=0;

    // Verification that playlist has been added
    printf("\nPlaylist has been added!\n\n");
    return 1; // Will return 1, and incrementing will happen

}

void addSong(struct pList *pL, int count){
    int pChoice;

    if(count==0){
        printf("Please add a playlist first.\n");
        return;
    }
    // Printing of all available playlists
    printf("THE PLAYLISTS AVAILABLE ARE:\n");
    for (int i = 0; i < count; i++){
        printf("\t[%d] %s\n", i, pL[i].name);
    }

    // Getting input which playlist to add a song
    printf("\nEnter playlist number: ");
    scanf("%d", &pChoice);
    while( getchar() != '\n' ){ /* flush to end of input line */ }

    // Valid input, and songs are less than 10
    if((pChoice >= 0 && pChoice <= count-1) && pL[pChoice].songCount < 10){
        printf("\nEditing Playlist: %s\n", pL[pChoice].name);

        // Getting title
        printf("Enter song title: ");
        fgets(pL[pChoice].songs[pL[pChoice].songCount].title, 50, stdin);
        pL[pChoice].songs[pL[pChoice].songCount].title[strcspn(pL[pChoice].songs[pL[pChoice].songCount].title, "\n")] = '\0';

        // Getting artist
        printf("Enter song artist: ");
        fgets(pL[pChoice].songs[pL[pChoice].songCount].artist, 50, stdin);
        pL[pChoice].songs[pL[pChoice].songCount].artist[strcspn(pL[pChoice].songs[pL[pChoice].songCount].artist, "\n")] = '\0';

        // Getting album
        printf("Enter song album: ");
        fgets(pL[pChoice].songs[pL[pChoice].songCount].album, 50, stdin);
        pL[pChoice].songs[pL[pChoice].songCount].album[strcspn(pL[pChoice].songs[pL[pChoice].songCount].album, "\n")] = '\0';

        // Verification for successfully adding song
        printf("Song successfully added to playlist %s!\n", pL[pChoice].name);
        pL[pChoice].songCount++; // Song Count will be incremented

        // to delete; for checking
        printf("Songs in this playlist:\n");
        for (int i = 0; i < pL[pChoice].songCount; i++) {
            printf("[%d] %s\n", i, pL[pChoice].songs[i].title);
        }
    } 

    // If the input is valid, but there are already 10 songs...
    else if((pChoice >= 0 && pChoice <= count) && pL[pChoice].songCount >= 10) printf("Max number of songs reached!");
    // Invalid input
    else printf("Invalid playlist!\n");

}

void removeSong(struct pList *pL, int count){
    int pChoice, toDelete;
    if(count==0){
        printf("Please add a playlist first.\n");
        return;
    }
    // Printing of all available playlists
    printf("THE PLAYLISTS AVAILABLE ARE:\n");
    for (int i = 0; i < count; i++){
        printf("\t[%d] %s\n", i, pL[i].name);
    }

    printf("Enter playlist number: ");
    scanf("%d", &pChoice);
    while( getchar() != '\n' ){ /* flush to end of input line */ }

    // For valid input
    if(pChoice >= 0 && pChoice <= count-1) {

        // If there are no songs in the playlist
        if (pL[pChoice].songCount == 0) printf("There are no songs in this playlist!\n");
        
        // If there are songs in the playlist
        else {
            // Printing of available songs
            printf("THE SONGS AVAILABLE ARE:\n");
            for (int i = 0; i < pL[pChoice].songCount; i++){
                printf("\t[%d] %s by %s\n", i, pL[pChoice].songs[i].title, pL[pChoice].songs[i].artist);
            }

            // Getting input what song to delete
            printf("\nEnter song number to delete: ");
            scanf("%d", &toDelete);
            while( getchar() != '\n' ){ /* flush to end of input line */ }

            // If user enters song na wala sa index
            if((toDelete < 0) || (toDelete > pL[pChoice].songCount - 1)) printf("Song out of bounds.\n");
            else {
                // Updating the locations with next elements
                for(int c = toDelete; c < pL[pChoice].songCount - 1; c++){
                    pL[pChoice].songs[c] = pL[pChoice].songs[c+1];
                }

                // Verification
                printf("Song succesfully deleted. Here is the updated playlist:\n");
                pL[pChoice].songCount--; // Song count decrements
                if (pL[pChoice].songCount == 0) printf("No songs here!\n"); // if wala nang natirang songs
                else {
                    for(int i=0; i< pL[pChoice].songCount; i++){ // Printing of the updated songs
                        printf("\t[%d] %s by %s\n", i, pL[pChoice].songs[i].title, pL[pChoice].songs[i].artist);
                    }
                }
            }
        }
    }
    // Invalid input
    else printf("Invalid input.\n");
}

void viewPlaylist(struct pList *pL, int count){
    int pChoice;

    if(count==0){
        printf("Please add a playlist first.\n");
        return;
    }
    else{
        // Printing of all available playlists
        printf("THE PLAYLISTS AVAILABLE ARE:\n");
        for (int i = 0; i < count; i++){
            printf("\t[%d] %s\n", i, pL[i].name);
        }

        // Getting input which playlist to view
        printf("\nEnter playlist number: ");
        scanf("%d", &pChoice);
        while( getchar() != '\n' ){ /* flush to end of input line */ }

        // Checker if playlist number is valid
        if(pChoice < 0 || pChoice > count-1){
            printf("Invalid input!\n");
            return;
        }
        
        // Viewing playlist
        printf("PLAYLIST: %s\n", pL[pChoice].name); // Playlist name
        printf("SONG COUNT: %d\n", pL[pChoice].songCount); // How many songs?
        
        for(int i = 0; i < pL[pChoice].songCount; i++){ //Song details
            printf("\nSONG TITLE: %s\n", pL[pChoice].songs[i].title);
            printf("SONG ARTIST: %s\n", pL[pChoice].songs[i].artist);
            printf("SONG ALBUM: %s\n", pL[pChoice].songs[i].album);
        }
    }
}

void viewAllData(struct pList *pL, int count){
    // If there are no playlists
    if(count==0){
        printf("Please add a playlist first.\n");
        return;
    }
    for (int i = 0; i < count; i++) {
        // Playlist deets
        printf("\nPLAYLIST: %s\n", pL[i].name); // Printing the playlist name
        printf("SONG COUNT: %d\n", pL[i].songCount); // Printing the song count

        // Printing of the song deets of each song
        for(int k=0; k < pL[i].songCount; k++){
            printf("SONG TITLE %d: %s\n", k+1, pL[i].songs[k].title);
            printf("SONG ARTIST %d: %s\n", k+1, pL[i].songs[k].artist);
            printf("SONG ALBUM %d: %s\n", k+1, pL[i].songs[k].album);
        }
    }
}

int main() {
    struct pList playlist[10];
    int count=0, choice;
    
    // Loading the file before anything else
    load(playlist, &count);

    while(1) {
        // Printing of menu
        printf("====== MENU ======\n");
        printf("[1] Add Playlist\n");
        printf("[2] Add Song to Playlist\n");
        printf("[3] Remove Song from Playlist\n");
        printf("[4] View a Playlist\n");
        printf("[5] View All Data\n");
        printf("[6] Exit\n\n");
        printf("Enter choice: ");
        scanf("%d", &choice);
        while( getchar() != '\n' ){ /* flush to end of input line */ } // getchar() gets the '\n' from scanf

        switch (choice) {
        case 1: // Add Playlist
            // When there are max number of playlists
            if(count == 10) printf("There are a maximum number of playists!\n");
            // Incrementing will depend on what will be returned
            else count = count + addPlaylist(playlist, count);
            break;
        case 2: // Add Song to Playlist
            addSong(playlist, count);
            break;
        case 3: // Remove Song from Playlist
            removeSong(playlist, count);
            break;
        case 4: // View a Playlist
            viewPlaylist(playlist, count);
            break;
        case 5: // View All Data
            viewAllData(playlist, count);
            break;
        case 6: // Exit
            save(playlist, count);
            printf("Data has been saved.\n");
            return 0;
        default: // Error message
            printf("Invalid input. Please try again.\n\n");
            break;
        }
        
        printf("-----------------------------\n"); // Ending line
    }

}