/***********************************************************
* This is a simple program that prints gets 5 integer inputs from user
* and displays minimum, maximum, and average of the integers. With input
* validation
*
* @author Jan Coleen S. Estilo
* @created_date 2021-09-17 19:00
*
***********************************************************/

package mypackage;

import java.util.Scanner;

public class Estilo_Exer1 {

	public static void main(String[] args) {
		int inputs[] = {1,2,3,4,5}, counter=0, choice=1;
		Scanner scanner = new Scanner(System.in);
		// Part 1: Fill up the array of integer inputs.
		System.out.println("Please enter 5 numbers.\n");

		while (counter<5){
			System.out.print("Enter a number: ");

			try{
				inputs[counter] = scanner.nextInt(); // Getting the input
				counter++;
			} catch (Exception e){
				scanner.next(); // Will catch the newline when error occurs
				System.out.println("Oops! That's not an integer :'(");
			}
		} // end of while loop

		// printing
		System.out.println("\nInputs are: ");
		for(int i=0;i<5;i++){
			System.out.print(inputs[i]+" ");
		}

		// Part 2: Create and implement the “Menu”.
		while(choice != 0){
			System.out.println("\n----------Menu----------");
			System.out.println("[1] Get Minimum Value");
			System.out.println("[2] Get Maximum Value");
			System.out.println("[3] Get Average");
			System.out.println("[0] Exit");
			System.out.print("Choice:");

			try{
				choice = scanner.nextInt(); // Getting the input

				switch(choice){
					case 1: // Get Minimum Value
						int minValue = inputs[0]; // First element of array will be minValue
						for(int j=1;j<5;j++){
							if(minValue>inputs[j]) // If next element is less than minValue
								minValue=inputs[j]; // Change value of minValue to that element
							else
								continue;
						}
						System.out.println("Minimum value is "+minValue);
						break;

					case 2: // Get Maximum Value
						int maxValue = inputs[0]; //// First element of array will be maxValue
						for(int k=1;k<5;k++){
							if (maxValue<inputs[k]) // If next element is > than maxValue
								maxValue=inputs[k]; // Change value of minValue to that element
							else
								continue;
						}
						System.out.println("Maximum value is "+maxValue);
						break;

					case 3: // Get Average
						float average;
						average = (inputs[0]+inputs[1]+inputs[2]+inputs[3]+inputs[4])/5; // Sum / 5
						System.out.println("Average is "+average);
						break;

					case 0: // Exit
						System.out.println("Bye Bye!");
						continue;

					default: // Integer input out of range
						System.out.println("Input out of range.");
						break;
				}
			} catch (Exception e){
				scanner.next();
				System.out.println("Invalid input! Try again.");
			}
		}

	}

}
