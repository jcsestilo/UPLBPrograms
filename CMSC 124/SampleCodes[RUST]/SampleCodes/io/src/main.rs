use std::io; //library for standard input output

fn main() {
	//Different Cases for Printing Data
    println!("Jane Doe");
    println!("Jane {}", "Doe");
    println!("{:?}", ["Jane", "Doe"]);

    //Getting String Inputs
    let mut name = String::new();
    println!("\nEnter name: ");
    io::stdin().read_line(&mut name).expect("Error");
    println!("My name is {}", name);

    //Getting Inputs and Converting them to Other Type
    let mut num = String::new();
    println!("Enter age: ");
    io::stdin().read_line(&mut num);
    let num:isize = num.trim().parse().expect("Error");
    println!("My age is {}", num);
}
