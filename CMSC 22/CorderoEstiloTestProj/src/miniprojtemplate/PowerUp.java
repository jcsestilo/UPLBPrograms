package miniprojtemplate;

// Create a PowerUp class to make to arraylist in GameTimer
public abstract class PowerUp extends Sprite{
	PowerUp(int x, int y){
		super(x, y);
	}

	abstract void checkCollission(Ninja ninja);
}
