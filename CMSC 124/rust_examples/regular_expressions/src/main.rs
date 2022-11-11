extern crate regex;
use regex::Regex;

fn main() {
    
    let text = "2012-03-14, 2013-01-01 and 2014-07-05";
    
    // ITERATING OVER CAPTURE GROUPS
    let date_regex = Regex::new(r"(\d{4})-(\d{2})-(\d{2})").unwrap();
    for cap in date_regex.captures_iter(text) {
        println!("Month: {} Day: {} Year: {}", cap.at(2).unwrap_or(""), cap.at(3).unwrap_or(""), cap.at(1).unwrap_or(""));
    }

    // NAMING CAPTURE GROUPS
    // let date_regex_with_names = Regex::new(r"(?P<y>\d{4})-(?P<m>\d{2})-(?P<d>\d{2})").unwrap();
    // for cap in date_regex_with_names.captures_iter(text) {
    //     println!("Month: {} Day: {} Year: {}", cap.name("m").unwrap_or(""), cap.name("d").unwrap_or(""), cap.name("y").unwrap_or(""));
    // }
}
