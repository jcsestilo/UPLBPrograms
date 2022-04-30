package politics;

public class Election {
	
	// max
	static final int MAX_VOTERS = 5;
	static final int MAX_PRES = 3;
	static final int MAX_VP = 3;
	
	// positions
	static final int PRES = 0;
	static final int VP = 1;
	
	// election status
	static final int PRE_ELECTION = 0;
	static final int ELECTION_PERIOD = 1;
	static final int END_OF_ELECTION = 2;
	
	String name;
	int electionStatus;

	Netizen[] voters;
	WannaBe[] presidents;
	WannaBe[] vps;
	Ballot[] ballotBox;

	int votersCnt;
	int presCnt;
	int vpCnt;
	int ballotCnt;
	
	public Election(String name) {
		this.name = name;
		this.electionStatus = Election.PRE_ELECTION;
		
		this.voters = new Netizen[Election.MAX_VOTERS];
		this.presidents = new WannaBe[Election.MAX_PRES];
		this.vps = new WannaBe[Election.MAX_VP];
		this.ballotBox = new Ballot[Election.MAX_VOTERS];
		
		this.votersCnt = 0;
		this.presCnt = 0;
		this.vpCnt = 0;
		this.ballotCnt = 0;
	}
	
	// can only add voters during pre-election
	// should not exceed max voters
	void addVoter(Netizen voter) {
		if(this.electionStatus == Election.PRE_ELECTION && this.votersCnt<Election.MAX_VOTERS) {
			voter.isRegistered = true;
			voter.election = this;
			voter.voterId = this.votersCnt;
			this.voters[this.votersCnt++] = voter;
			
			System.out.println("\nSuccessfully registered a voter.");
			voter.displayState();
		} else System.out.println("\nRegistration failed.");
	}
	
	// can only add during pre-election
	// should not reach max pres
	void addPres(WannaBe pres) {
		if(this.electionStatus == Election.PRE_ELECTION && this.presCnt<Election.MAX_PRES) {
			pres.isRunning = true;
			pres.election = this;
			pres.candidateId = this.presCnt;
			pres.position = Election.PRES;
			
			this.presidents[this.presCnt++] = pres;
			System.out.println("\nSuccessfully filed a COC.");
			pres.displayState();
		} else System.out.println("\nFiling of COC failed");
	}
	
	// can only add during pre-election
	// should not reach max vp
	void addVP(WannaBe vp) {
		if(this.electionStatus == Election.PRE_ELECTION && this.vpCnt<Election.MAX_VP) {
			vp.isRunning = true;
			vp.election = this;
			vp.candidateId = this.vpCnt;
			vp.position = Election.VP;
			
			this.vps[this.vpCnt++] = vp;
			System.out.println("\nSuccessfully filed a COC.");
			vp.displayState();
		} else System.out.println("\nFiling of COC failed");
	}
	
	void displayVoters() {
		System.out.println("\nVOTERS-------------------------------");
		if(this.votersCnt>0) {
			for(int i=0; i<this.votersCnt; i++)
				this.voters[i].displayState();
		} else System.out.println("\nNo registered voters yet.");
	}
	
	void displayPresidents() {
		System.out.println("\nPRESIDENTS---------------------------");
		if(this.presCnt>0) {
			for(int i=0; i<this.presCnt; i++)
				this.presidents[i].displayState();
		} else System.out.println("\nNo presidential candidates yet.");
	}
	
	void displayVPs() {
		System.out.println("\nVICE PRESIDENTS----------------------");
		if(this.vpCnt>0) {
//			for(int i=0; i<this.vpCnt; i++)
//				this.vps[i].displayState();
			
			// WannaBe[] vps
			for(WannaBe wb: vps) {
				wb.displayState();
			}
			
		} else System.out.println("\nNo vice presidential candidates yet.");
	}
	
	void startElectionPeriod() {
		this.electionStatus = Election.ELECTION_PERIOD;
	}
	
	// can only add a ballot during election period
	void addBallot(Ballot ballot) {
		if(this.electionStatus == Election.ELECTION_PERIOD) {
			this.ballotBox[this.ballotCnt++] = ballot;
			ballot.voter.hasVoted = true;
			
			System.out.println("\nSuccessfully casted a vote.");
		} else System.out.println("\nVoting failed.");
	}
	
	void endElectionPeriod() {
		this.electionStatus = Election.END_OF_ELECTION;
		this.countVotes();
		this.displayElectionResult();
	}
	
	void countVotes() {
		System.out.println("\nCounting votes...");
		// using normal for loop
//		for(int i=0; i<this.ballotCnt; i++) {
//			this.ballotBox[i].pres.noOfVotes++;
//			this.ballotBox[i].vp.noOfVotes++;
//		}
		// using for-each
		// Ballot[] ballotBox
		for(Ballot b: ballotBox) {
			b.pres.noOfVotes++;
			b.vp.noOfVotes++;
		}
		
		this.displayPresidents();
		this.displayVPs();
		System.out.println("\nSuccessfully counted votes.");
	}
	
	WannaBe electPres() {
		WannaBe pres = this.presidents[0];
		
		for (int i=0; i<this.presCnt; i++) {
			if(pres.noOfVotes<this.presidents[i].noOfVotes) 
				pres = this.presidents[i];
		}
		
		return pres;
	}
	
	WannaBe electVP() {
		WannaBe vp = this.vps[0];
		
		for (int i=0; i<this.vpCnt; i++) {
			if(vp.noOfVotes<this.vps[i].noOfVotes) 
				vp = this.vps[i];
		}
		
		return vp;
	}
	
	
	void displayElectionResult() {
		WannaBe winningPres = this.electPres();
		WannaBe winningVP = this.electVP();
		
		System.out.println("\nELECTION RESULT---------------------");
		System.out.println("President: " + winningPres.name);
		System.out.println("Vice President: " + winningVP.name);
		
	}
	
	void displayState() {
		System.out.println("\nElection Name: " + this.name);
		switch(this.electionStatus) {
			case Election.PRE_ELECTION: System.out.println("Election Status: Registration Period"); break;
			case Election.ELECTION_PERIOD: System.out.println("Election Status: Election Period"); break;
			case Election.END_OF_ELECTION: System.out.println("Election Status: End of Election"); break;
		}
		this.displayVoters();
		this.displayPresidents();
		this.displayVPs();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
