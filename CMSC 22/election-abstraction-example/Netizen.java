package politics;

public class Netizen {
	
	String name;
	boolean isRegistered;

	Election election;
	Ballot ballot;
	int voterId;
	boolean hasVoted;
	
	public Netizen(String name) {
		this.name = name;
		this.isRegistered = false;
		this.hasVoted = false;
	}
	
	// not registered
	void register(Election election) {
		if(!this.isRegistered)	election.addVoter(this);
		else System.out.println("\nCan only register once.");
	}
	
	// registered and has not yet voted
	void castVotes(WannaBe pres, WannaBe vp) {
		if(this.isRegistered && !this.hasVoted) {
			this.ballot = new Ballot(this, pres, vp);
			this.election.addBallot(this.ballot);
		}else System.out.println("\nVoting failed.");
	}
	
	void displayState() {
		System.out.println("\nName: " + this.name);
		
		// if a voter, print voter details
		if(this.isRegistered) {
			System.out.println("Election: " + this.election.name);
			System.out.println("Voter ID: " + this.voterId);
			// if already voted, print ballot details
			if(this.hasVoted) {
				this.ballot.displayState();
			}
		}
	}
}







