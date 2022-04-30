/***********************************************************
* This is a simple program that prints “Hello World!”.
*
* @author <your name>
* @created_date 2021-09-17 17:00
*
***********************************************************/

package mypackage;
import java.util.Scanner;
public class HelloWorld {

	public static void main(String[] args) {
		
		int myInt;
		String name;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name:");
		name = sc.next();
		System.out.println("Hello "+ name + "!");
		System.out.println("Enter an integer value:");
		myInt = sc.nextInt();
		// displays the value of myInt

	}

}
