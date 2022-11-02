use std::io;

fn get_input(s: &mut String){
    let mut string = String::new();
    println!("Enter string: ");
    io::stdin().read_line(&mut string).expect("error");
    *s = string;
}

fn main() {
    let mut name = String::new();
    get_input(&mut name);

    println!("NAME: {}", name);
}