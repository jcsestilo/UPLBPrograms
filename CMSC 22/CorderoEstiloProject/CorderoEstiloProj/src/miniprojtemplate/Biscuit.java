package miniprojtemplate;

import javafx.scene.image.Image;

// Similar to Apples and Boss Orange, but it doesn't move
public class Biscuit extends PowerUp{
	// Attributes
	public final static int BISCUIT_WIDTH=50;
	public final static Image BISCUIT_IMAGE = new Image("images/biscuit.png", Biscuit.BISCUIT_WIDTH, Biscuit.BISCUIT_WIDTH, false, false);
	public final static int BISCUIT_POWERS = 50;

	private boolean alive;


	// Constructor
	Biscuit(int x, int y){
		super(x,y);

		this.alive = true;
		this.loadImage(Biscuit.BISCUIT_IMAGE);
	}
	// Methods
	@Override
	public void checkCollission(Ninja ninja){
		if(this.collidesWith(ninja)){
			System.out.println(ninja.getName() + " has collected a biscuit.");
			this.vanish();
			// When collected, upgrade ninja health
			System.out.println("Old Ninja Strength: " + ninja.getNinjaStrength());
			this.upgradeNinjaHealth(ninja);
			System.out.println("New Ninja Strength: " + ninja.getNinjaStrength());

		}
	}

	// Collection of pearl adds 50 strength to ship
	void upgradeNinjaHealth(Ninja ninja){
		ninja.setNinjaStrength(Biscuit.BISCUIT_POWERS);
	}

	void vanish(){
		this.setVisible(false);
	}

	// Getter
	public boolean isAlive(){
		return this.alive;
	}

	// Setter
	public void setAlive(){
		this.alive = false;
	}
}
