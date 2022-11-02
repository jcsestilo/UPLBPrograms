fn main() {
	//VARIABLE DECLARATIONS: 
	let x = 5; //immutable
	let mut y = 10; //mutable

	println!("x: {},  y: {}", x, y);

	// x = 10; -> INVALID, x is not mutable
	y = 15;

	println!("x: {},  y: {}", x, y);

	//SHADOWING:
	let x = 5;
	println!("x: {}", x);

	let x = x + 5;
	println!("x: {}", x);

	let x = "five";
	println!("x: {}", x);
}
