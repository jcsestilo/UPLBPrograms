fn main() {
    let mut num = 10;
    let num2 = &mut num;
    *num = 22;
    println!("Number: {}", num2);
}
