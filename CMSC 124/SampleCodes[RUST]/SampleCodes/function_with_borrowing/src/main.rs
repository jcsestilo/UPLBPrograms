use std::io; //imports the standard input output library

//Function that Adds Data to Vector
fn add_data(data: &mut Vec<i8>) {
	let mut x = String::new(); //Asks for new input
	println!("Enter data: ");
	io::stdin().read_line(&mut x).expect("Error");

	let x:i8 = x.trim().parse().unwrap(); //Converts x to i8

	data.push(x); //Adds data to Vector
}

//Function that Prints the Data of the Vectors
fn print_data(data: &Vec<i8>) {
	for i in data { //Iterates over the elements of the vector
		print!("{} ", i);
	}
}

fn main() {
	//Creates new vector with type i8
	let mut data:Vec<i8> = Vec::new();

	//Adds five inputs to the vector
	add_data(&mut data);
	add_data(&mut data);
	add_data(&mut data);
	add_data(&mut data);
	add_data(&mut data);

	//Prints the output
	print!("\nThe data are: ");
	print_data(&data);
	println!("");
}