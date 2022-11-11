use std::error::Error;
use std::fs::File;
use std::io::prelude::*;
use std::path::Path;

fn main() {
    let path = Path::new("files/in.txt");
    let display = path.display();
    let mut file = match File::open(&path) {
        // Description method of the error retured by open (if it fails)
        // This clause will be used if an error occurs
        Err(why) => panic!("couldn't open {}: {}", display, why.description()),
        
        // If there are no problems in opening the file (Ok), then it is opened and is accessible using the variable file
        Ok(file) => file,
    };
    
    let mut s = String::new();
    match file.read_to_string(&mut s) {
        Err(why) => panic!("couldn't read {}: {}", display, why.description()),
        Ok(_) => print!("{} contains: \n{}", display, s),
        // s will print the whole content of the file
    }
    
    // file variable goes out of scope
    // file automatically gets closed
}