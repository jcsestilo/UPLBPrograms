// Author: Jan Coleen S. Estilo
// Section: T-2L
// RUST Exercise

use std::io; // library for input (standard input output)

struct MenuItem {
    item_id: u32,
    item_name: String,
    food_establishment: String,
    item_price: f64,
    item_stock: u32
}

struct Customer {
    name: String,
    orders: Vec<String>,
    total_cost:f64
}

// function that creates a new menu item
fn create_menu_item(id: u32, name: String, establishment: String, price: f64, stock: u32) -> MenuItem {
    MenuItem {
        item_id: id,
        item_name: name,
        food_establishment: establishment,
        item_price: price,
        item_stock: stock
    }
}

// function that prints all the menu items
fn print_menu_items(menu: &Vec<MenuItem>) {
    println!("Here");

    if(menu.len() == 0){
        println!("There are no menu items available!");

    } else {
        println!("-------- MENU ITEMS AVAILABLE --------");
        for item in menu {
            println!("[{}] - {} ({}) - {}", item.item_id, item.item_name, item.food_establishment, item.item_price_in);
        }
    }

    
}

fn main() {

    let mut items:Vec<MenuItem> = Vec::new(); // array of the menu items
    let mut item_ids:Vec<u32> = Vec::new(); // array of the item ids inputted by the user, for checking of duplicates

    loop {

        let mut choice = String::new(); 

        // printing of the menu
        println!("\n==================== MENU ====================");
        println!("[1] Add a Menu Item");
        println!("[2] Order a Menu Item");
        println!("[3] Edit a Menu Item");
        println!("[4] Delete a Menu Item");
        println!("[5] View All Menu Items");
        println!("[6] View All Customers");
        println!("[7] Exit");

        // getting the input
        println!("\nEnter choice: ");
        io::stdin().read_line(&mut choice).expect("Error!");

        // parse the input to the specified integer type
        let choice:isize = choice.trim().parse().expect("Invalid input");

        // input validation and checking
        if choice == 1 {
            println!("\nADD A MENU ITEM");

            // The program will ask for the details of the menu item (item id, item name, food establishment,
            // item price, item stock). Duplicate item ids are NOT ALLOWED.
            let mut item_id_in = String::new();
            let mut item_name_in = String::new();
            let mut food_est = String::new();
            let mut item_price_in = String::new();
            let mut item_stock_in = String::new();

            println!("Enter item id: ");
            io::stdin().read_line(&mut item_id_in).expect("Error!");
            let item_id_in:u32 = item_id_in.trim().parse().expect("Error!");
            
            // check if there is a duplicate
            let mut id_has_duplicate:bool = false;
            for item_id in &item_ids {
                if item_id == &item_id_in {
                    id_has_duplicate = true;
                    break;
                }
            }

            // if the has duplicate variable is true
            if id_has_duplicate == true {
                println!("Duplicate Item ID!");
                continue; // continue to the next loop, don't ask for other info
            }

            println!("Enter item name: ");
            io::stdin().read_line(&mut item_name_in).expect("Error!");
            println!("Enter food establishment: ");
            io::stdin().read_line(&mut food_est).expect("Error!");

            println!("Enter item price: ");
            io::stdin().read_line(&mut item_price_in).expect("Error!");
            let item_price_in:f64 = item_price_in.trim().parse().expect("Error!");

            println!("Enter item stock: ");
            io::stdin().read_line(&mut item_stock_in).expect("Error!");
            let item_stock_in:u32 = item_stock_in.trim().parse().expect("Error!");

            // call the create menu item function and create the menu item
            items.push(create_menu_item(item_id_in, item_name_in, food_est, item_price_in, item_stock_in));
            item_ids.push(item_id_in);
            println!("Adding of menu item successful!");

        } else if choice == 2 {
            println!("\nORDER A MENU ITEM");
        } else if choice == 3 {
            println!("\nEDIT A MENU ITEM");
        } else if choice == 4 {
            println!("\nDELETE A MENU ITEM");
        } else if choice == 5 {
            println!("\nVIEW ALL MENU ITEMS");
            
            if(items.len() == 0){
                println!("There are no menu items available!");

            } else {
                println!("-------- MENU ITEMS AVAILABLE --------");
                for item in &items {
                    println!("[{}] - {} ({}) - {}", item.item_id, item.item_name, item.food_establishment, item.item_price_in);
                }
            }

        } else if choice == 6 {
            println!("\nVIEW ALL CUSTOMERS");
        } else if choice == 7 {
            // when the user exits
            break;
        } else {
            println!("Invalid Input!");
        }
    }

    println!("\nBye!");

}

