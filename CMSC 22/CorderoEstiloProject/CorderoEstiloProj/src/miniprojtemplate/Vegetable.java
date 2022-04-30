package miniprojtemplate;

import javafx.scene.image.Image;

public class Vegetable extends PowerUp{
	// Attributes
	public final static int VEGETABLE_WIDTH=50;
	public final static Image VEGETABLE_IMAGE = new Image("images/cabbage.png", Vegetable.VEGETABLE_WIDTH, Vegetable.VEGETABLE_WIDTH, false, false);

	private boolean alive;

	// Constructors
	Vegetable(int x, int y){
		super(x,y);

		this.alive=true;
		this.loadImage(Vegetable.VEGETABLE_IMAGE);
	}

	// Methods

	@Override
	public void checkCollission(Ninja ninja){
		if(this.collidesWith(ninja)){
			System.out.println(ninja.getName() + " has collected a vegetable!");
			this.vanish();
			// When collected, ninja becomes immortal
			ninja.setImmortal(true);
			Thread ninjaThread = ninja.getNinjaAsThread();
			ninjaThread.start();
		}
	}

	void vanish(){
		this.setVisible(false);
	}

	// Getter
	boolean isAlive(){
		return this.alive;
	}

	// Setter
	void setAlive(){
		this.alive = false;
	}
}
