package sample;

import java.util.ArrayList;

public class ArrayListMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// constructor
		// ArrayList<E> arrName = new ArrayList<E>();
		// E: object type of elements that will be stored in the array
		// ex. String, Integer, Float, TransportOption, Destination
		
		// ArrayList of String objects
		ArrayList<String> names = new ArrayList<String>();
		
		// ArrayList of Integer objects
		ArrayList<Integer> units = new ArrayList<Integer>();
		
		// we cannot use primitive data type as object types
		// int, float, ...
		
		// methods
		
		// isEmpty()
		if(names.isEmpty()) System.out.println("names empty!");
		if(units.isEmpty()) System.out.println("units empty!");
		
		// add(E element)
		names.add("Kendall");	// 0
		names.add("Coleen");	// 1
		names.add("Edver");		// 2
								// 3...
		
		// get(int index)
		// names.get(2)
		System.out.println(names.get(2));
		System.out.println(names.get(0));
		System.out.println(names.get(1));
		
		// size()
		System.out.println("names size: " + names.size());
		
		// regular for-loop
		// names
		// size
		System.out.println("FOR-LOOP:");
		for(int i=0; i<names.size(); i++) {
			System.out.println(names.get(i));
		}
		
		// for-each loop
		// names
		// Type of elements: String
		// for each String in our ArrayList names
		System.out.println("FOR-EACH-LOOP:");
		for(String name: names) {
			System.out.println(name);
		}
	}

}
