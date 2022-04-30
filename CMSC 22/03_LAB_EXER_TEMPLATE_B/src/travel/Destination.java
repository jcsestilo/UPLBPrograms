package travel;

public class Destination {

	// attributes
	int id;
	String destinationName;
	String description;
	String location;

	// Array of transport options
	TransportOption [] transportOptions;
	int tOptionsCounter = 0;
	static int destinationCount =0;

	final static int MAX_OPTIONS = 4;
		// For the three values of location
	final static String LUZON = "Luzon";
	final static String VISAYAS = "Visayas";
	final static String MINDANAO = "Mindanao";

	// constructors
	public Destination(int id, String destinationName, String description, String location){
		this.id = id;
		this.destinationName = destinationName;
		this.description = description;
		this.location = location;
		
		this.transportOptions = new TransportOption[MAX_OPTIONS];
		
		destinationCount++;
	}
	

	// methods
	public void addOption(TransportOption option){
		this.transportOptions[this.tOptionsCounter]=option;
		this.tOptionsCounter++;
		
	}

	void viewState(){
		System.out.println("\n=== DESTINATION INFO ===");
		System.out.println("Destination ID: " + this.id);
		System.out.println("Name: " + this.destinationName);
		System.out.println("Description: " + this.description);
		System.out.println("Location: " + this.location);

		// Printing of all the transport options of the destination
		System.out.println("Transport options: ");
		// If there are no activities
		if (this.tOptionsCounter==0){ System.out.println("\tNo entry yet."); }
		else {
			for(int i=0;i<tOptionsCounter;i++){
				TransportOption tOption = transportOptions[i];
				System.out.println("\t"+(i+1)+". " + tOption.name + " | Class " + tOption.classOption + " (Cost: " + tOption.cost + ")");
			}
		}
	}

}
