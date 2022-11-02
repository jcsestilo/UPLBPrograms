//Function for Printing Sum of Two Numbers
fn print_sum(x:i32, y:i32) {
	println!("{} + {} = {}", x, y, x + y);
}

//Function that Returns the Sum of Two Numbers
fn return_sum(x:i32, y:i32) -> i32 {
	x+y //No semi-colon for the return statement!
}

fn main() {
	print_sum(21, 23);
	let x:i32 = return_sum(29, 2);
	println!("{}", x);
}
