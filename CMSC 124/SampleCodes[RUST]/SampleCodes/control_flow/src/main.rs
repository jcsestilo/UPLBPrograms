fn main() {
	//Conditional Statements using If-Else Statements
	let grade = 55;
	let lec = 6;
	let lab = 4;

	if (lec > 7) || (lab > 3) {
		println!("Grade is 5.0");
	} else if grade >= 55 {
		println!("You passed CMSC 124");
	} else {
		println!("Grade is 5.0");
	}

	//Conditional Statements using Let Statement
	let condition = true;
	let num = if condition { 5 } else { 6 };

	println!("Number: {}", num);

	//Iterative Statements using Loop Keyword
	print!("\nLOOP: ");
	let mut i = 0;
	loop {
		i += 1;
		print!("{} ", i);
		if i == 10 {
			break;
		}
	}

	//Iterative Statements using While Keyword
	print!("\nWHILE: ");
	let mut i = 0;
	while i != 10 {
		i+=1;
		print!("{} ", i);
	}

	//Iterative Statements using For Keyword
	print!("\n\nFOR: \n\tNORMAL LOOP: ");
	for i in 1..11 { //start to end-1
		print!("{} ", i);
	}

	print!("\n\tVECTORS: ");
	//For Loop for Printing Values of Vectors
	let v1 = vec![1, 2, 3, 4, 5];
	for i in &v1 {
		print!("{} ", i);
	}

	//For Loop for Changing Mutable Vector
	let mut v2 = vec![1, 2, 3, 4, 5];
	for i in &mut v2 {
		*i = *i + 10;
	}

	println!("\n\tUPDATED VECTORS: {:?}\n", v2);
}
