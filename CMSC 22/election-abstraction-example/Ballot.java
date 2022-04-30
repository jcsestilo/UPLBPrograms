package politics;

public class Ballot {
	
	Netizen voter;
	WannaBe pres;
	WannaBe vp;
	
	public Ballot(Netizen voter, WannaBe pres, WannaBe vp) {
		this.voter = voter;
		this.pres = pres;
		this.vp = vp;
	}
	
	void displayState() {
		System.out.println("Voter: "  + this.voter.name);
		System.out.println(">> President: " + this.pres.name);
		System.out.println(">> Vice President: " + this.vp.name);
	}
}
