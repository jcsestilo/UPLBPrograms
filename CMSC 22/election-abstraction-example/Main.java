package politics;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Election oe2021 = new Election("Election 2021");
		oe2021.displayState();
		
		Netizen ntzn1 = new Netizen("Kendall");
		Netizen ntzn2 = new Netizen("Miyah");
		Netizen ntzn3 = new Netizen("Mylah");
		Netizen ntzn4 = new Netizen("Beili");
		Netizen ntzn5 = new Netizen("Gelo");
		Netizen ntzn6 = new Netizen("Jade");
		
		WannaBe wb1 = new WannaBe("Bobbie");
		WannaBe wb2 = new WannaBe("Alex");
		WannaBe wb3 = new WannaBe("Gabbie");
		WannaBe wb4 = new WannaBe("Teddie");
		WannaBe wb5 = new WannaBe("Monica");
		WannaBe wb6 = new WannaBe("Phoebe");
		WannaBe wb7 = new WannaBe("Rachel");
		WannaBe wb8 = new WannaBe("Janice");
		
		oe2021.displayState();
		ntzn1.register(oe2021);
		ntzn2.register(oe2021);
		ntzn3.register(oe2021);
		ntzn4.register(oe2021);
		ntzn5.register(oe2021);
		// max voters reached
		ntzn6.register(oe2021);
		
		oe2021.displayState();
		
		wb1.fileCOC(oe2021, Election.PRES);
		wb2.fileCOC(oe2021, Election.PRES);
		wb3.fileCOC(oe2021, Election.PRES);
		// max pres reached
		wb4.fileCOC(oe2021, Election.PRES);
		
		wb1.fileCOC(oe2021, Election.VP);
		wb5.fileCOC(oe2021, Election.VP);
		wb6.fileCOC(oe2021, Election.VP);
		wb7.fileCOC(oe2021, Election.VP);
		// max vps reached
		wb8.fileCOC(oe2021, Election.VP);
		
		oe2021.displayState();
		
		// cannot vote yet 
		ntzn1.castVotes(wb1, wb7);
		
		// starts election period, registered netizens can now vote
		oe2021.startElectionPeriod();
		
		// all netizens voted
		ntzn1.castVotes(wb1, wb5);
		ntzn2.castVotes(wb2, wb6);
		ntzn3.castVotes(wb1, wb5);
		ntzn4.castVotes(wb1, wb7);
		ntzn5.castVotes(wb3, wb5);
		
		oe2021.endElectionPeriod();
		
	}
}




