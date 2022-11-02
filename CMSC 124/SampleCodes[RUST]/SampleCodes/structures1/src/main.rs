//Basic Structure User
struct User {
	username: String,
	email: String,
	sign_in_count: u64,
	active: bool
}

//Function to Create New User and Initialize Some Members
fn create_user(email: String, username: String) -> User {
	User {
		email: email,
		username: username,
		active: true,
		sign_in_count: 1
	}
}

fn main() {
	//Declare New Structure Variable
	let mut User1 = User {
		email: String::from("jmdelacruz@up.edu.ph"),
		username: String::from("jmdelacruz"),
		active: true,
		sign_in_count: 1
	};

	//Update User1's Sign-In Count
	User1.sign_in_count = 2;

	//Create New Structure Variable using Function
	let User2 = create_user("absy@up.edu.ph".to_string(), String::from("absy"));

	//Access Each Member using Dot Operator
	println!("USER 1: {} {} {} {}", User1.email, User1.username, User1.active, User1.sign_in_count);
	println!("USER 2: {} {} {} {}", User2.email, User2.username, User2.active, User2.sign_in_count);
}