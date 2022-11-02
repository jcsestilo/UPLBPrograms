fn main() {
	//BOOLEAN:
	let x:bool = true;
	let y = false;

	//CHARACTER:
	let letter = 'a';
	let symbol = '%';
	let omega = '\u{03A9}';

	//NUMERIC TYPES:
	let x = 12;	//immutable, i32 (default)
	let y = 5.8; //immutable, f64 (default)
	let z:u8 = 255; //immutable, u8

	// let a:i8 = 255; -> INVALID, out of range for i8

	//Checks allowed max and min values for a numeric type
	println!("SCALAR DATA TYPE: \n{}", std::i8::MAX);
	println!("{}\n", std::i8::MIN);

	let mut b:i32 = 64; //mutable, i32
	b = 478;

	//////////////////////////////////////////////////////////////////

	//TUPLES:
	let tup = ('a', 32, true);
	println!("TUPLES: \nSECOND ELEM: {}", tup.1);
	println!("TUPLE: {:?}", tup);

	//Tuple Destructuring
	let (x, y, z) = tup;
	println!("{} {} {}\n", x, y, z);

	//ARRAYS:
	let arr1 = [1, 2, 3, 4];
	// arr1[0] = 3; -> INVALID, arr1 not mutable

	//Replaces array value, mutable
	let mut arr2 = [2, 3, 4];
	arr2[0] = 15;
	println!("ARRAY:\n{:?}", arr2);

	//Initialize value of arrays to g
	let a = ['g';20];
	println!("SIZE: {}", a.len()); //Gets size of array
	println!("{:?}\n", a);

	//SLICES:
	let arr1 = [1, 2, 3, 4, 5]; 
	let all = &arr1[..];
 	let some = &arr1[1..4]; //from start to end-1

	println!("SLICES:\n{:?}", all);
	println!("{:?}\n", some);

	//////////////////////////////////////////////////////////////////

	//VECTORS:

	//Creates immutable vectors
	let v:Vec<i32> = Vec::new();
	let v = vec![1, 2, 3];
	
	//Creates mutable vector
	let mut v1 = Vec::new();

	//Adds data to vector
	v1.push(5);
	v1.push(4);
	v1.push(3);

	println!("VECTOR:\n{:?}", v1);
	println!("{}", v1[1]);
	println!("{}", &v1[0]);
	println!("{:?}", &v1[0..2]);

	println!("{:?}", v1.get(0));
	// println!("{}", v1[4]); -> INDEX OUT OF BOUNDS

	//Removes last element from vector
	v1.pop();
	println!("{:?}\n", v1);

	//STRINGS:

	//Creates new strings
	let mut s1 = String::from("hello");
	let mut s2 = "hello".to_string();
	let mut s3 = String::new();

	//Appends to string
	s3 = "str".to_string();
	s3.push_str("ing");
	s3.push('s');

	println!("STRING:\n{}", s3);
}
