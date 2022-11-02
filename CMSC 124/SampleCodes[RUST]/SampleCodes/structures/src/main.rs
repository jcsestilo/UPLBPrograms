//Basic Format of Structures
struct Color1 {
	red: i32,
	green: i32, 
	blue: i32
}

//Tuple Structures
struct Color2(i32, i32, i32);

fn main() {
	//Initialization of Basic Structure
	let cerulean = Color1 {
		red: 42, 
		green: 82,
		blue: 190
	};

	//Access via Dot Operator and Member
	println!("RGB: {} {} {}", cerulean.red, cerulean.green, cerulean.blue);

	//Initialization of Tuple Structures
	let maroon = Color2(180, 0, 0);

	//Access via Dot Operator and Index
	println!("RGB: {} {} {}", maroon.0, maroon.1, maroon.2);
}