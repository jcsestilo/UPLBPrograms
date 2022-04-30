package travel;

public class TransportOption {

	// attributes
	int id;
	String locationName;
	String name;
	int classOption;
	int maxCapacity;
	float cost;
	Destination desti;
	
	
	// static variable
	final static int MAX_GROUPS = 50;
	// Groups array
	TravelBuddiesGroup[] groups;
	int groupsCounter=0;

	
	// constructors
	public TransportOption(int id, String name, int classOption, float cost, Destination location){
		this.id = id;
		this.locationName = location.destinationName;
		this.name = name;
		this.classOption = classOption;
		this.cost = cost;
		this.desti = location;
		
		this.groups = new TravelBuddiesGroup[MAX_GROUPS];
		
		switch(classOption){
		case 1: // If class 1
			this.maxCapacity=2;
			break;
		case 2: // If class 2
			this.maxCapacity=4;
			break;
		case 3: // If class 3
			this.maxCapacity=8;
			break;
		case 4: // If class 4
			this.maxCapacity=15;
			break;
		}

		// Add the option to the Destination object
		location.addOption(this);
	}

	// methods
	void viewState(){

		System.out.println("\n=== TRANSPORT OPTION ===");
		System.out.println("Transport Option Id: " + this.id);
		System.out.println("Location: " + this.locationName);
		System.out.println("Name: " + this.name);
		System.out.println("Cost: " + this.cost);

		// Printing the capacity
		System.out.print("Capacity: ");
		switch(this.classOption){
			case 1:
				System.out.println("max 2 pax");
				break;
			case 2:
				System.out.println("3 to 4 pax");
				break;
			case 3:
				System.out.println("5 to 8 pax");
				break;
			default:
				System.out.println("9 to 15 pax");
				break;
		}

		// For the printing of the groups
		System.out.println("Groups: ");
		if (this.groupsCounter==0){ // If there are no groups yet
			System.out.println("\t No entry yet.");
		} else {
			for(int i=0;i<this.groupsCounter;i++){
				TravelBuddiesGroup g = groups[i]; // Make another object to access the number of members
				System.out.println("\t"+(i+1)+". " + g.groupName + " - " + g.numOfMembers + " (members count)");
			}
		}

	}

	void book(TravelBuddiesGroup group, int packageCount){
		for(int i=0; i<packageCount; i++){
			this.groups[this.groupsCounter] = group;
			this.groupsCounter++;
		}
	}

	int getPackageCount(int memberCount){
		int result = memberCount / this.maxCapacity;
		int remainder = memberCount % this.maxCapacity;
		if (remainder>0){
			result++;
		}
		return result;
	}
}
