package travel;

/*************************************************************************************************************
 *
 * Topic: Abstraction (Laboratory exercise)
 *
 * (refer to pdf file for the problem domain)
 *
 * @author Jan Coleen S. Estilo of Y-5L
 * @date 2021-10-02 17:00
 * (c) Institute of Computer Science, UPLB
 *
 */


public class Main {


	public static void main(String[] args) {

		/*
		 * Create 2 sample destination
		 */

		Destination destination1 = new Destination(++Destination.destinationCount, "Siargao", "Surfing Capital of the South", Destination.MINDANAO);
		destination1.viewState();

		Destination destination2 = new Destination(++Destination.destinationCount, "La Union", "Surfing Capital of the North", Destination.LUZON);
		destination2.viewState();

		/*
		 * Create 8 sample transport options.  Transport option is specific to a destination.  Upon creation, specify destination.
		 *
		 */


		TransportOption option1 = new TransportOption(1, "Off-road tiny", 1, 3500f, destination1);
		option1.viewState();

		TransportOption option2 = new TransportOption(2, "Off-road small", 2, 4500f, destination1);
		option2.viewState();

		TransportOption option3 = new TransportOption(3, "Off-road group", 3, 5500f, destination1);
		option3.viewState();

		TransportOption option4 = new TransportOption(4, "Coaster", 4, 6500f, destination1);
		option4.viewState();

		TransportOption option5 = new TransportOption(5, "City driving tiny", 1, 3500f, destination2);
		option5.viewState();

		TransportOption option6 = new TransportOption(6, "City driving small", 2, 4500f, destination2);
		option6.viewState();

		TransportOption option7 = new TransportOption(7, "Off-road group", 3, 5500f, destination2);
		option7.viewState();

		TransportOption option8 = new TransportOption(8, "Coaster", 4, 6500f, destination2);
		option8.viewState();

		/*
		 * At this point, the following are the destinations and corresponding transport options
		 * (note the option order or sequence number)
		 *
		 * Destination: Siargao
		 * options list:
		 * 1."Off-road tiny" 	- Type: Class 1 (max 2 pax)
		 * 2."Off-road small" 	- Type: Class 2 (3-4 pax)
		 * 3."Off-road group" 	- Type: Class 3 (5-8 pax)
		 * 4."Coaster" 			- Type: Class 4 (9-15 pax)
		 *
		 *
		 * Destination: La Union
		 * options list:
		 * 1."City driving tiny" 	- Type: Class 1 (max 2 pax)
		 * 2."City driving small" 	- Type: Class 2 (3-4 pax)
		 * 3."Off-road group" 	- Type: Class 3 (5-8 pax)
		 * 4."Coaster" 			- Type: Class 4 (9-15 pax)
		 *
		 * -- end of list --
		 */


		/*
		 * Create 4 sample travel buddies groups. Upon creation, specify the destination.
		 */

		TravelBuddiesGroup group1 = new TravelBuddiesGroup(1, "Team Uhaw", 5, 20000f, destination1);
		group1.viewState();

		TravelBuddiesGroup group2 = new TravelBuddiesGroup(2, "Team Gutom", 2, 10000f, destination1);
		group2.viewState();

		TravelBuddiesGroup group3 = new TravelBuddiesGroup(3, "Team Puyat", 6, 80000f, destination1);
		group3.viewState();

		TravelBuddiesGroup group4 = new TravelBuddiesGroup(4, "Team GG", 7, 7000f, destination2);
		group4.viewState();



		System.out.println("\n----------------------------------------------------------------");
		System.out.println("Start booking process...");
		System.out.println("----------------------------------------------------------------\n");


		/*
		 * - Group 1 tries to book for "Off-road tiny", no. 1 in the options list for Siargao
		 * - Compute for no. of packages to avail: 5(members count) divided by 2 (maximum capacity/pax)
		 * - No. of packages: 3
		 * - Total cost: 10500.0 < remaining money (budget)
		 * - Transport bookings should succeed.
		 * - Transport option will be booked 3 times
		 */
		group1.addTransportOption(1);

		/*
		 * Try for other cases.
		 * Same process as above.
		 */

		group1.addTransportOption(3);
		group4.addTransportOption(5);
		group4.addTransportOption(1);
		group4.addTransportOption(3);




		System.out.println("\n----------------------------------------------------------------");
		System.out.println("                           SUMMARY");
		System.out.println("----------------------------------------------------------------\n");



		group1.viewState();
		group2.viewState();
		group3.viewState();
		group4.viewState();
		destination1.viewState();
		destination2.viewState();
		option1.viewState();
		option2.viewState();
		option3.viewState();
		option4.viewState();
		option5.viewState();
		option6.viewState();
		option7.viewState();
		option8.viewState();


		/*
		 * You may add more test cases after this comment.
		 * Wala nang iba pang kailangan baguhin sa itaas.
		 * Kung mayroon man, class-level attributes:  the ID of groups can be assigned programmatically using a class-level attribute in TravelBuddiesGroup.
		 * Right now, if you noticed, the ids are hard-coded. Similarly, transport options can also have their ids programmatically determined using
		 * class-level attribute id counter.
		 */



	}


}
