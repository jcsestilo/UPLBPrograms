package politics;

public class WannaBe {
	
	String name;
	boolean isRunning;

	Election election;
	int candidateId;
	int position;
	int noOfVotes;
	boolean isElected;
	
	public WannaBe(String name) {
		this.name = name;
		this.isRunning = false;
		this.noOfVotes = 0;
		this.isElected = false;
	}
	
	
	// can only file once
	void fileCOC(Election election, int position) {
		if(!this.isRunning) {
			if(position == Election.PRES) election.addPres(this);
			else if(position == Election.VP) election.addVP(this);
			else System.out.println("\nInvalid position");
		} else System.out.println("\nCan only file one COC.");
	}
	
	void displayState() {
		System.out.println("\nName: " + this.name);
		
		if(this.isRunning) {
			System.out.println("Election: " + this.election.name);
			System.out.println("Candidate ID: " + this.candidateId);
			System.out.println("Position: " + this.position);
			System.out.println("Vote Count: " + this.noOfVotes);
			
			if(this.isElected) {
				System.out.println("ELECTED.");
			}
		}
	}
}





