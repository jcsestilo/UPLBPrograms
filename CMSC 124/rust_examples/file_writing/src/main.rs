
use std::error::Error;
use std::fs::File;
use std::io::prelude::*;
use std::path::Path;

fn main() {
    let path = Path::new("files/out.txt");
    let display = path.display();
    
    // create a new file
    let mut file = match File::create(&path) {
        Err(why) => panic!("couldn't create {}: {}", display, why.description()),
        Ok(file) => file,
    };
    
    // string to write to the file
    let mut s = "We love Rust";
    
    // writes on the file
    match file.write_all(s.as_bytes()) {
        Err(why) => panic!("couldn't write {} to {}", why.description(), display),
        Ok(_) => print!("successfully wrote to {}\n", display),
    };
    
    // file variable goes out of scope
    // file automatically gets closed
}   