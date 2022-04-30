package app;

import marketplace.*;
import customer.*;

/* DO NOT MODIFY THIS FILE */
class Main {
	public static void main(String[] args){
		Marketplace marketplace = new Marketplace("RideHome Car Marketplace");
		
		Customer polo = new Customer("Polo Benavent","Calamba, Laguna","09998765431",1600000);
		Customer lu = new Customer("Lu Montesinos","San Pedro, Laguna","09998765432",1700000);
		Customer rebeka = new RebateCustomer("Rebeka Parilla","Los Banos, Laguna","09998765433",1800000);
		Customer omar = new RebateCustomer("Omar Shanaa","Bay, Laguna","09998765434",900000);
		Customer samuel = new Customer("Samuel Dominguez","San Pablo, Laguna","09998765435",90000);
	
		Car[] Cars = {
			new Car("Mitsubishi", "2009 Montero Sport", 520000, Car.COND_GOOD, Car.CLASS_SUV),
			new Car("Toyota", "2011 Fortuner", 620000, Car.COND_GOOD, Car.CLASS_SUV),
			new Car("Chevrolet", "2019 Trailblazer", 980000, Car.COND_MINT, Car.CLASS_SUV),
			new Car("Hyundai", "2014 Tucson", 570000, Car.COND_GOOD, Car.CLASS_SUV),
			new Car("Nissan", "2019 Terra", 1440000, Car.COND_LIKE_NEW, Car.CLASS_SUV),
			new Car("Nissan", "2017 Juke", 630000, Car.COND_GOOD, Car.CLASS_CROSSOVER),
			new Car("Subaru", "2014 Forester", 450000, Car.COND_GOOD, Car.CLASS_CROSSOVER),
			new Car("Ford", "2015 Ecosport", 590000, Car.COND_MINT, Car.CLASS_CROSSOVER),
			new Car("Hyundai", "2020 Kona", 700000, Car.COND_LIKE_NEW, Car.CLASS_CROSSOVER),
			new Car("Nissan", "2016 Juke", 610000, Car.COND_GOOD, Car.CLASS_CROSSOVER),
			new Car("Mitsubishi", "1996 Lancer", 68000, Car.COND_GOOD, Car.CLASS_SEDAN),
			new Car("Honda", "2000 Accord", 90000, Car.COND_GOOD, Car.CLASS_SEDAN),
			new Car("Toyota", "20202 Vios", 650000, Car.COND_MINT, Car.CLASS_SEDAN),
			new Car("Subaru", "2013 Legacy", 838000, Car.COND_LIKE_NEW, Car.CLASS_SEDAN),
			new Car("Chevrolet", "2017 Sail", 458000, Car.COND_GOOD, Car.CLASS_SEDAN),
		};

		for (int i=0; i<Cars.length; i++) {
			marketplace.add(Cars[i]);
		}

		polo.buy(Cars[0], marketplace);
		polo.buy(Cars[5], marketplace);
		polo.buy(Cars[11], marketplace);
		polo.viewState();
		marketplace.viewProducts();

		rebeka.buy(Cars[1], marketplace);
		rebeka.buy(Cars[6], marketplace);
		rebeka.buy(Cars[12], marketplace);
		rebeka.viewState();
		marketplace.viewProducts();

		lu.buy(Cars[2], marketplace);
		lu.buy(Cars[7], marketplace);
		lu.buy(Cars[13], marketplace);
		lu.viewState();
		marketplace.viewProducts();

		omar.buy(Cars[0], marketplace);
		omar.viewState();

		samuel.buy(Cars[4], marketplace);
		samuel.viewState();

		polo.buy(Cars[8], marketplace);
		polo.viewState();

		omar.buy(Cars[14], marketplace);
		omar.viewState();

		marketplace.viewInfo();
	}
}
