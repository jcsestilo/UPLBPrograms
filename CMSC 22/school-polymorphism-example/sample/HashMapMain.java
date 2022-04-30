package sample;

import java.util.HashMap;

public class HashMapMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// constructor
		// HashMap<K, V> hmName = new HashMap<K, V>();
		// K: object type of key
		// V: object type of value
		// primitive data types cannot be used
		
		// key->value
		// 1->"Kendall"
		// 13->"Miyah"
		HashMap<Integer, String> names = new HashMap<Integer, String>();
		
//		HashMap<Integer, ArrayList<String>>
		
//		HashMap<String, String>
		
		// methods
		
		// isEmpty()
		if(names.isEmpty()) System.out.println("empty!");
		
		// put(K key, V value)
		// 1->"Kendall"
		// 13->"Miyah"
		names.put(1, "Kendall");
		names.put(13, "Miyah");
		names.put(2, "Betel");
		
		names.put(1, "Jeane");	
		names.put(12, "Jim");
		
		// size()
		System.out.println(names.size());
		
		// get(Object key)
		// names.get(2)
		System.out.println(names.get(2));
		System.out.println(names.get(12));
		
		// containsKey(Object key)
		if(names.containsKey(13)) System.out.println(names.get(13));
		
		// keySet() - returns set of keys
		System.out.println(names.keySet());
		
		// for-each	
		// 1  -> "Kendall"
		// 13 -> "Miyah"
		// names
		// key: Integer
		System.out.println("\nFOR EACH LOOP:");
		for(Integer key: names.keySet()) {
			System.out.println(key + " -> " + names.get(key));
		}
				
	}

}
