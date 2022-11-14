// Author: Jan Coleen S. Estilo
// Section: T-2L
// Rust Exercise 2

use std::io;
use std::error::Error;
use std::fs::File;
use std::io::prelude::*;
use std::path::Path;
extern crate regex;
use regex::Regex;

fn main() {
    println!("[1] Show all numbers");
    println!("[2] Show all keywords");
    println!("[3] Show all strings");
    println!("[4] Show all non-keyword identifiers");

    let mut choice = String::new(); 

    // getting the input
    println!("\nEnter choice: ");
    io::stdin().read_line(&mut choice).expect("Error!");

    // parse the input to the specified integer type
    let choice:isize = choice.trim().parse().expect("Invalid input");

    // open the file
    let path = Path::new("files/input.arnoldc");
    let display = path.display();

    let mut file = match File::open(&path){
        Err(why) => panic!("Couldn't open {}: {}", display, why.to_string()),
        Ok(file) => file,
    };

    // put the contents of the file to the string s
    let mut s = String::new();
    match file.read_to_string(&mut s){
        Err(why) => panic!("couldn't read {}: {}",display,why.to_string()),
        Ok(_) => println!(""),
    }

    let mut output = String::new();
    let mut counter:isize = 0;

    if choice == 1 {
        // Show all numbers
        let re = Regex::new(r"([^a-zA-Z]-?\d{1,})").unwrap();

        for cap in re.captures_iter(&s) {
            counter = counter + 1;
            // println!("Detected integer: {}", cap.at(1).unwrap_or(""));
            output.push_str("Detected integer: ");
            output.push_str(cap.at(1).unwrap_or(""));
            output.push_str("\n");
        }
        // println!("Count: {}", counter);
        output.push_str("Count: ");
        output.push_str(&counter.to_string());
        output.push_str("\n");

    } else if choice == 2 {
        // Show all keywords

        let re = Regex::new("([^a-z\\d\"-][A-Z\x27]*( [A-Z\x27]*)* *)").unwrap();
        // [^\\Wa-z0-9\x22][A-Z]*(\\s[A-Z|']*)*\\s*)
        // let re = Regex::new(r"([^\x22](\b[A-Z][A-Z0-9])*)").unwrap();
        // let re = Regex::new(r"([^\x22])").unwrap();
        // let re = Regex::new(r"([A-Z]+(\\s[A-Z|']*)*\\s*)").unwrap();

        for cap in re.captures_iter(&s) {
            counter = counter + 1;
            // println!("Detected integer: {}", cap.at(1).unwrap_or(""));
            output.push_str("Detected keyword: ");
            output.push_str(cap.at(1).unwrap_or(""));
            output.push_str("\n");
        }
        // println!("Count: {}", counter);
        output.push_str("Count: ");
        output.push_str(&counter.to_string());
        output.push_str("\n");

    } else if choice == 3 {
        // Show all strings
        let re = Regex::new("(\"[^\"]+\")").unwrap();


        for cap in re.captures_iter(&s) {
            counter = counter + 1;
            // println!("Detected integer: {}", cap.at(1).unwrap_or(""));
            output.push_str("Detected string literal: ");
            let mut string_ltrl = cap.at(1).unwrap_or("").to_string();
            string_ltrl.pop(); // remove the last character
            string_ltrl.remove(0); // remove the first character
            output.push_str(&string_ltrl);
            output.push_str("\n");
        }
        // println!("Count: {}", counter);
        output.push_str("Count: ");
        output.push_str(&counter.to_string());
        output.push_str("\n");

    } else if choice == 4 {
        // Show all non-keyword identifiers

        let re = Regex::new(r"([^A-Z\d][a-z]+.*)").unwrap();

        for cap in re.captures_iter(&s) {
            counter = counter + 1;
            // println!("Detected integer: {}", cap.at(1).unwrap_or(""));
            output.push_str("Detected identifier: ");
            output.push_str(cap.at(1).unwrap_or(""));
            output.push_str("\n");
        }
        // println!("Count: {}", counter);
        output.push_str("Count: ");
        output.push_str(&counter.to_string());
        output.push_str("\n");

    } else {
        println!("Invalid input!");
    }

    // write to output file
    let path = Path::new("files/output.txt");
    let display = path.display();
    let mut file = match File::create(&path){
        Err(why) => panic!("couldn't create {}: {}",display,why.to_string()),
        Ok(file) => file,
    };

    match file.write_all(output.as_bytes()){
        Err(why) => panic!("couldn't write {} to {}", why.description(), display),
        Ok(_) => println!("Successfully wrote to {}", display),
    };
}
