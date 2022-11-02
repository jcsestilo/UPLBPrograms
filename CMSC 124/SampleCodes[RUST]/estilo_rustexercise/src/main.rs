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

// function that creates a new customer
fn create_customer(customer_name: String) -> Customer {
    let customer_orders:Vec<String> = Vec::new();
    let customer_total_cost:f64 = 0.0;

    Customer {
        name: customer_name,
        orders: customer_orders,
        total_cost: customer_total_cost
    }
}

// function that prints all the menu items
fn print_menu_items(menu: &Vec<MenuItem>) {
    if menu.len() == 0{
        println!("There are no menu items available!");

    } else {
        println!("-------- MENU ITEMS AVAILABLE --------");
        // iterate over the items and print them
        for item in menu {
            println!("Item ID: {}", item.item_id);
            println!("Item Name: {}", item.item_name);
            println!("Food Establishment: {}", item.food_establishment);
            println!("Item Price: {}", item.item_price);
            println!("Item Stock: {}\n", item.item_stock);
        }
    }
}

// function that prints the information about the customers
fn print_customers(customers: &Vec<Customer>) {
    if customers.len() == 0 {
        println!("There are no customers available!");
    } else {
        for person in customers {
            println!("\nCustomer Name: {}", person.name);

            println!("Menu Items Ordered:");
            for order in &person.orders {
                println!("\t- {}", order);
            }

            println!("Total Cost: {}", person.total_cost);
        }
    }
}

fn main() {

    let mut items:Vec<MenuItem> = Vec::new(); // vector of the menu items
    let mut item_ids:Vec<u32> = Vec::new(); // vector of the item ids inputted by the user, for checking of duplicates
    let mut customers:Vec<Customer> = Vec::new(); // vector of the customers

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
                println!("Menu ID already exists!");
                continue; // continue to the next loop, don't ask for other info
            }

            println!("Enter item name: ");
            io::stdin().read_line(&mut item_name_in).expect("Error!");
            let item_name_in:String = item_name_in.trim().to_string();

            println!("Enter food establishment: ");
            io::stdin().read_line(&mut food_est).expect("Error!");
            let food_est:String = food_est.trim().to_string();

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

            if items.len() == 0 {
                println!("There are no menu items available!");
                continue;
            }

            // The program will ask for the name of the customer.
            let mut customer_name = String::new();
            println!("Enter customer name: ");
            io::stdin().read_line(&mut customer_name).expect("Error!");
            let customer_name = customer_name.trim().to_string();

            let mut customer_index:usize = 0;
            let mut customer_found:bool = false;
            
            // finding the index where the customer is
            for person in &customers {
                if person.name == customer_name {
                    customer_found = true;
                    break;
                }
                customer_index = &customer_index + 1;
            }
            // If the customer is not in the system, add them first
            if customer_found == false {
                customers.push(create_customer(customer_name));
            }

            // the program will show the menu items available, and will ask for the menu id to be ordered
            if items.len() == 0 { // if no available items...
                println!("No available menu items!");
                continue;
            } else {
                println!("-------- MENU ITEMS AVAILABLE --------");
                for item in &items {
                    println!("[{}] {} ({}) - {}", item.item_id, item.item_name, item.food_establishment, item.item_price);
                }
            }

            // get the user input item ID
            let mut id_to_order = String::new();
            println!("Enter item id:");
            io::stdin().read_line(&mut id_to_order).expect("Error!");
            let id_to_order:u32 = id_to_order.trim().parse().expect("Error!");

            // check the items vector for that particular item
            let mut item_found:bool = false;
            for item in &mut items {
                // if it is found, we process the information
                if item.item_id == id_to_order {
                    item_found = true;

                    // check if the stock is 0
                    if item.item_stock == 0 {
                        println!("Out of stock!");
                        break;
                    }

                    // ItemID_Item Name_Food Establishment
                    let mut order_string = String::from((item.item_id).to_string());
                    order_string.push_str("_");
                    order_string.push_str(&item.item_name);
                    order_string.push_str("_");
                    order_string.push_str(&item.food_establishment);

                    // push our made string to the orders vector of the customer
                    println!("Successfully ordered menu item {}", &order_string);
                    customers[customer_index].orders.push(order_string);

                    // increment the total_cost of the customer to the price of the item
                    customers[customer_index].total_cost = &customers[customer_index].total_cost + item.item_price;

                    // update the item stock
                    item.item_stock = item.item_stock - 1;

                }
            }

            if item_found == false {
                println!("Menu ID not found!");
            }
            

        } else if choice == 3 {
            println!("\nEDIT A MENU ITEM");

            // user cannot edit if there are no menu items available
            if items.len() == 0{
                println!("No menu items available!");
                continue;
            }

            // print the menu items
            print_menu_items(&items);
            
            // get the user input ID
            let mut choice = String::new();
            println!("Enter item id:");
            io::stdin().read_line(&mut choice).expect("Error!");
            let choice:u32 = choice.trim().parse().expect("Error!");

            let mut item_found = false;
            // find the choice
            for item in &mut items {
                if item.item_id == choice {
                    item_found = true;

                    // enter the new price and stock
                    let mut new_price = String::new();
                    let mut new_stock = String::new();
                    println!("Enter new item price:");
                    io::stdin().read_line(&mut new_price).expect("Error!");
                    println!("Enter new item stock:");
                    io::stdin().read_line(&mut new_stock).expect("Error!");
                    let new_price:f64 = new_price.trim().parse().expect("Error!");
                    let new_stock:u32 = new_stock.trim().parse().expect("Error!");

                    // set the new value of item price and item stock
                    item.item_price = new_price;
                    item.item_stock = new_stock;

                    // move onto the next loop
                    continue;
                }
            }

            if item_found == false {
                println!("Item ID not found!");
            }
            

        } else if choice == 4 {
            println!("\nDELETE A MENU ITEM");

            // if no items in the items vector...
            if items.len() == 0 {
                println!("No menu items available!");
                continue;
            }

            // print the menu items
            print_menu_items(&items);

            let mut index:usize = 0; // for our index checker once the item was found
            let mut del_success = false;

            // get user input for item ID
            let mut id_todel = String::new();
            println!("Enter item ID to delete:");
            io::stdin().read_line(&mut id_todel).expect("Error!");
            let id_todel:u32 = id_todel.trim().parse().expect("Error!");

            // find the id inputted by user
            for item in &items {
                if item.item_id == id_todel {
                    items.remove(index); // pop from items vector in position of index
                    del_success = true; 
                    println!("Successfully deleted item!");
                    break;
                }

                index = &index + 1; // increment the index
            }

            if del_success == false {
                println!("Item not found!");
            }
            
        } else if choice == 5 {
            println!("\nVIEW ALL MENU ITEMS");
            print_menu_items(&items);
        } else if choice == 6 {
            println!("\nVIEW ALL CUSTOMERS");
            print_customers(&customers);
        } else if choice == 7 {
            // when the user exits
            break;
        } else {
            println!("Invalid Input!");
        }
    }

    println!("\nBye!");

}

