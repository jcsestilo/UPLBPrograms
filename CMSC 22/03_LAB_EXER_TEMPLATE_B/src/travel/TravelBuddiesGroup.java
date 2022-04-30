package travel;

public class TravelBuddiesGroup {

	// attributes
	int id;
	String groupName;
	String destinationName;
	int numOfMembers = 1; // When a group is created, there will always be one member.
	float budget;
	float budgetRemaining;
	String activities[]; // Array of all the activities
	Destination desti;


	// For the transport bookings
	final static int MAX_BOOKINGS = 20;
	TransportOption [] tOption = new TransportOption[TravelBuddiesGroup.MAX_BOOKINGS];
	int tOptionsCounter=0;


	// constructors
	public TravelBuddiesGroup(int id, String groupName, int numOfMembers, float budget, Destination destination){
		this.id = id;
		this.groupName = groupName;
		this.numOfMembers = numOfMembers;
		this.budget = budget;
		this.destinationName = destination.destinationName;
		this.desti = destination;
	}

	// methods
	void addTransportOption(int classIndex){
		if(this.tOptionsCounter >= MAX_BOOKINGS){ 
			System.out.println("You can't book anymore!");
			return; }
		
		System.out.println("\n============================\n");
		System.out.println(this.groupName + ": chose transport option no. '" + classIndex + "' for destination " + this.destinationName);

		if(classIndex>=1 && classIndex<=4){
			TransportOption tOption = this.desti.transportOptions[classIndex-1];

			System.out.println(this.groupName+": Adding transport option: "+ tOption.name);

			// Compute for no. of packages to avail: 5(members count) divided by 2 (maximum capacity/pax)
			System.out.println("Computer for no. of packages to avail: "+ this.numOfMembers+" (members count) divided by "+ tOption.maxCapacity);

			// call getPackageCount
			int packs = tOption.getPackageCount(this.numOfMembers);

			System.out.println("No. of packages: " + packs);

			// get the total cost
			float totalCost = tOption.cost * (float)packs;
			System.out.println("Total Cost: " + totalCost);
			
			// Kaya ba ng budget or no?
			if(totalCost>this.budget){ // If the budget is too expensive
				System.out.println("Di na kaya ng budget. Sad lyf.\n");
			} else {
				System.out.println("Yes! Kaya pa ng budget.");
				
				// Subtract the total cost from the budget
				this.budgetRemaining = budget - totalCost;
				
				// Add the options to the group
				for(int i=0; i<packs; i++){
					this.tOption[this.tOptionsCounter] = tOption;
					this.tOptionsCounter++;
				}
				
				// Successful printing
				System.out.println("Successfully added '" + tOption.name + "' '" + packs + "' times.\n");
				
				// Booking to TransportOption: book()
				tOption.book(this, packs);
			}
			// view state
			viewState();
		}
		// If the option number is invalid
		else{ System.out.println("Cannot add transport option. Invalid option number.\n"); }
	}

	void viewState(){
		System.out.println("\n=== TRAVEL BUDDIES ===");
		System.out.println("Group ID: " + this.id);
		System.out.println("Group Name: " + this.groupName);
		System.out.println("Destination: " + this.destinationName);
		System.out.println("No. of members: " + this.numOfMembers);
		System.out.println("Budget: " + this.budget);
		System.out.println("Amount remaining: " + this.budgetRemaining);

		// Printing of all the activities of the group
		System.out.println("Activities: ");
		// If there are no activities
		if (this.tOptionsCounter==0){ 
			System.out.println("\tNo entry yet.\n");
			}
		else {
			for(int i=0;i<this.tOptionsCounter;i++){
				TransportOption transOption = tOption[i];
				System.out.println("\t"+(i+1)+". " + transOption.name + " - P" + transOption.cost);
			}
		}

		}
}
