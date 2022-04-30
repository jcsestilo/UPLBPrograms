package mypackage;

import java.util.Scanner;

public class Estilo_Exer02 {

	final static int SIZE = 10;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 1, index;
		String [] words = new String[SIZE];
		String wordToSearch;

		while(choice != 0){
			System.out.println("======MENU======");
			System.out.println("[1] Input words");
			System.out.println("[2] Print all words");
			System.out.println("[3] Search a word");
			System.out.println("[4] Sort by length");
			System.out.println("[0] Exit");
			System.out.println("================");
			System.out.print("Enter choice: ");

			try{
				choice = Integer.parseInt(scanner.nextLine());

				switch(choice){
				case 1: //Input words
					Estilo_Exer02.inputWords(words);
					break;
				case 2: //Print all words
					Estilo_Exer02.printWords(words);
					break;
				case 3: //Search a word
					System.out.print("Enter string to search: ");
					wordToSearch = scanner.nextLine();

					index = Estilo_Exer02.searchWord(wordToSearch, words);

					if (index == -1){
						System.out.println("Word not found!");
					} else {
						System.out.println("Word: "+words[index]+"\nIndex: "+index);
					}
					break;
				case 4: //Sort by length
					Estilo_Exer02.sortWords(words);
					System.out.println("Words sorted.");
					break;
				case 0: //Exit
					System.out.println("Bye!");
					break;
				default:
					System.out.println("Invalid input! Try again.");
					break;
				}
			}catch(Exception e){
				scanner.next();
				System.out.println(e);
				continue;
			}

		}
	}

	public static void inputWords(String [] words){
		for(int i=0;i<SIZE;i++){
			System.out.print("Enter string "+(i+1)+": ");
			try{
				words[i] = scanner.nextLine();


			} catch(Exception e){
				System.out.println("Invalid input! Try again.");

				// decrement so that in the next iteration, it will go back to previous value
				i=i-1;
				continue;
			}
		}
	}

	public static void printWords(String [] words){
		if (words[0]==null){
			System.out.println("Please input words first.");
			return;
		}
		for(int i=0;i<SIZE;i++){
			System.out.println("String "+(i+1)+": "+words[i]);
		}
	}

	public static int searchWord(String wordToSearch, String [] words){
		String wordToSearch_lower = wordToSearch.toLowerCase();

		for(int i=0;i<SIZE;i++){
			if(wordToSearch_lower.equals(words[i].toLowerCase())){
				return i;
			}
		}
		return -1;
	}

	public static void sortWords(String [] words){
		String strtemp;
		for(int i=0;i<SIZE;i++){
			for(int j=0;j<SIZE;j++){
				
				if(j==9){ continue; }

				if(words[j].length()>words[j+1].length()){
					//swap
					strtemp = words[j];
					words[j] = words[j+1];
					words[j+1] = strtemp;
					//System.out.println("Swapped "+words[j]+" & "+words[j+1]);
				}
			}
		}
	}
	
	
}
