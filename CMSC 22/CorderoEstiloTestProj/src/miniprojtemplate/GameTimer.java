package miniprojtemplate;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method.
 */

public class GameTimer extends AnimationTimer{

	private GraphicsContext gc;
	private Scene theScene;
	private Ninja myNinja;
	private ArrayList<Apple> apples;
	private ArrayList<PowerUp> powerups;
	public static final int MAX_NUM_APPLES = 7;
	// Delay variables
	private static final int BOSS_SPAWN_DELAY = 30;
	// Power ups delay
	private static final int ITEMS_SPAWN_DELAY = 10;
	private static final int ITEMS_DESTROY_DELAY = 5;
	// Spawn three apples every three seconds
	private static final int THREE_APPLES_SPAWN_DELAY = 5;
	// Time limit of the game
	private static final int GAME_TIME = 60;

	// Background image
	private Image background = new Image("images/background.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, false);

	// You Lose Image and You Win Image
	private Image youLose = new Image("images/youlose.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, false);
	private Image youWin = new Image("images/youwin.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, false);

	private int scoreCounter = 0;
	// For the timer in spawning the boss orange
	private long startSpawnBoss;
	// For the timer in spawning the powerups (biscuit and vegetable)
	private long startSpawnItems;
	// For the timer in destroying the powerups
	private long endDestroyItems;
	// For the timer in spawning three new apples
	private long startSpawnThreeApples;
	// For the timer of the whole game
	private long endGame;

	// Attribute for boss orange
	private BossOrange orange;

	GameTimer(GraphicsContext gc, Scene theScene){
		this.gc = gc;
		this.theScene = theScene;
		this.myNinja = new Ninja("Going merry",100,100);
		//instantiate the ArrayList of Apple
		this.apples = new ArrayList<Apple>();

		// Instantiate the ArrayList of powerups
		this.powerups = new ArrayList<PowerUp>();

		//call the spawnFishes method
		this.spawnApples();
		//call method to handle mouse click event
		this.handleKeyPressEvent();

		// Create BossOrange object
		// X: window width [rightmost], Y: ((GameStage.WINDOW_HEIGHT)/2) [at the middle of the height; center]
		this.orange = new BossOrange(((GameStage.WINDOW_WIDTH)-50), (((GameStage.WINDOW_HEIGHT)-50)/2));

		this.endGame = this.startSpawnBoss = this.startSpawnItems = this.endDestroyItems = this.startSpawnThreeApples = System.nanoTime();
	}

	@Override
	public void handle(long currentNanoTime) {

		// Get the current nano time and turn it to seconds
		double spawnBossElapsedTime = (currentNanoTime - this.startSpawnBoss) / 1000000000.0;
		double spawnItemsElapsedTime = (currentNanoTime - this.startSpawnItems) / 1000000000.0;
		double destroyItemsElapsedTime = (currentNanoTime - this.endDestroyItems) / 1000000000.0;
		double spawnThreeApplesElapsedTime = (currentNanoTime - this.startSpawnThreeApples) / 1000000000.0;

		double currentSec = (currentNanoTime - this.endGame) / 1000000000.0;

		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);

		// draws the background image
		this.gc.drawImage(background, 0, 0);

		// If the elapsed time is greater than the spawn delay, meaning it is more than 30 seconds
		if(spawnBossElapsedTime > GameTimer.BOSS_SPAWN_DELAY){
			// Time to show the boss orange
			System.out.println("BOSS ORANGE HAS SPAWNED!");
			this.moveOrange();
			this.renderBossOrange();

			// There is no need to reset the this.startSpawnBoss because it will not spawn again
		}

		// Spawn power ups after GameTimer.ITEMS_SPAWN_DELAY(10) seconds
		if(spawnItemsElapsedTime > GameTimer.ITEMS_SPAWN_DELAY){
			this.spawnPowerUps();
			this.startSpawnItems = System.nanoTime();
			// Once the power ups have spawned, start the 5 seconds duration timer
			this.endDestroyItems = System.nanoTime();
		}

		// If 5 seconds duration for the power up has passed
		if(destroyItemsElapsedTime > GameTimer.ITEMS_DESTROY_DELAY){
			// Destroy the power ups
			this.destroyPowerUps();

			//this.startSpawnItems = System.nanoTime();
			this.endDestroyItems = System.nanoTime();
		}

		// Every 5 seconds, spawn new three apples
		if(spawnThreeApplesElapsedTime > GameTimer.THREE_APPLES_SPAWN_DELAY){
			this.spawnThreeApples();

			// Reset the 5 seconds timer for the spawning
			this.startSpawnThreeApples = System.nanoTime();
		}

		// move ninja
		this.myNinja.move();
		// check if ninja collided with any power ups
		this.powerUpCheckCollission();

		this.moveSwords();
		this.moveApples();


		//render the ship
		this.myNinja.render(this.gc);
		this.renderPowerUps();
		this.renderSwords();
		this.renderApples();

		this.timeCount(currentSec); //caller to display the time remaining
		this.scoreCount(); //caller to display the score
		this.ninjaHealth(); // caller to display the health

//		checker to see if the game has been running for a minute or if the ninja is still alive.
//		if the game has reached 1 minute and the ninja is still alive, the screen is prompted to
//		say that the user wins but if the ninja dies before 1 minute is up then the screen says that
//		the user loses.
		if(currentSec > GameTimer.GAME_TIME || !this.myNinja.isAlive()){
			this.stop();
			if(this.myNinja.isAlive()){
				this.EndSequence(1);
			}else{
				this.EndSequence(0);
			}
		}
	}

//	display counter for the time remaining
	private void timeCount(double currentSec){
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		this.gc.setFill(Color.YELLOW);
		this.gc.fillText("Time Remaining:", 270, 30);
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		this.gc.setFill(Color.WHITE);
		if(currentSec > 60){
			currentSec = 60;
		}
		this.gc.fillText(currentSec+"", 430, 30);
	}

//	display counter for the sliced apples. (note that the slicing the orange adds 2 to the score)
	private void scoreCount(){
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		this.gc.setFill(Color.YELLOW);
		this.gc.fillText("Score:", 20, 30);
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(this.getScoreCounter()+"", 90, 30);
	}

//	display counter for the health of the ninja
	private void ninjaHealth(){
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		this.gc.setFill(Color.YELLOW);
		this.gc.fillText("Health:", 130, 30);
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(this.myNinja.getNinjaStrength()+"", 200, 30);
	}

//	displays on the screen whether the user wins or not
	private void EndSequence(int result){
		this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		this.gc.setFill(Color.WHITE);

		if(result == 1){
			this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_WIDTH);
			this.gc.drawImage(youWin, 0, 0);
			this.scoreCount();
			//this.gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 45));
			//this.gc.fillText("Congratulations on winning!", 100, GameStage.WINDOW_HEIGHT/2);
		}else{
			this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
			this.gc.drawImage(youLose, 0, 0);
			this.scoreCount();
			//this.gc.fillText("Better luck next time!", 150, GameStage.WINDOW_HEIGHT/2);
		}
	}

	// Method that will render the boss Orange
	private void renderBossOrange(){
		if(this.orange.isVisible()){
			this.orange.render(this.gc);
		}
	}

	// Method that will render the power ups
	private void renderPowerUps(){
		for(PowerUp p : this.powerups)
			p.render(this.gc);
	}

	//method that will render/draw the fishes to the canvas
	private void renderApples() {
		for (Apple a : this.apples){
			a.render(this.gc);
		}
	}

	//method that will render/draw the bullets to the canvas
	private void renderSwords() {

		ArrayList<Sword> sList = this.myNinja.getSwords();
		for(Sword s:sList){
			s.render(this.gc);
		}
	}

	// method that will destroy the power ups
	private void destroyPowerUps(){
		// Empty all the elements in power up arraylist
		for(int i=0; i<this.powerups.size(); i++){
			// remove(i--) because remove() shifts objects to the left kapag nag remove
			this.powerups.remove(i--);
		}
	}

	// method that will create the power up objects
	private void spawnPowerUps(){
		// For the biscuit
		this.powerups.add(this.createBiscuit());

		// For the vegetable
		this.powerups.add(this.createVegetable());


	}

	// method that will create the biscuit object
	private Biscuit createBiscuit(){
		Random r = new Random();
		// power-ups appear at random locations on the left half of the screen
		int x = r.nextInt(((GameStage.WINDOW_WIDTH)-50)/2);
		int y = r.nextInt((GameStage.WINDOW_HEIGHT)-50);

		return(new Biscuit(x,y));
	}

	// method that will create the vegetable object
	private Vegetable createVegetable(){
		Random r = new Random();
		// power-ups appear at random locations on the left half of the screen
		int x = r.nextInt(((GameStage.WINDOW_WIDTH)-50)/2);
		int y = r.nextInt((GameStage.WINDOW_HEIGHT)-50);

		return(new Vegetable(x,y));
	}

	// method that will spawn three new apples
	private void spawnThreeApples(){
		Random r = new Random();
		for(int i=0;i<3;i++){
			// Create a new apple
			int x = (GameStage.WINDOW_WIDTH)-50;
			int y = r.nextInt((GameStage.WINDOW_HEIGHT)-50);

			Apple apple = new Apple(x,y);
			// add the new apple to the arraylist
			this.apples.add(apple);
		}
	}

	//method that will spawn/instantiate seven fishes at a random x,y location
	private void spawnApples(){
		Random r = new Random();
		for(int i=0;i<GameTimer.MAX_NUM_APPLES;i++){
			int x = (GameStage.WINDOW_WIDTH)-50;
			int y = r.nextInt((GameStage.WINDOW_HEIGHT)-50);

			Apple apple = new Apple(x,y);
			this.apples.add(apple);
		}

	}

	// method that will check if a powerup collided with the ninja
	private void powerUpCheckCollission(){
		for(int i=0; i < this.powerups.size(); i++){
			PowerUp p = this.powerups.get(i);

			// for every power up p, check if collided with ninja
			p.checkCollission(this.myNinja);

			// if collided, remove p from the arraylist
			if(p.collidesWith(this.myNinja)){
				this.powerups.remove(p);
			}
		}
	}

	//method that will move the bullets shot by a ship
	private void moveSwords(){
		//create a local arraylist of Bullets for the bullets 'shot' by the ship
		ArrayList<Sword> sList = this.myNinja.getSwords();
		BossOrange orange = this.orange;
		//Loop through the bullet list and check whether a bullet is still visible.
		for(int i = 0; i < sList.size(); i++){
			Sword s = sList.get(i);

			if(s.getVisible()){
				s.move();

				// Check if bullet collides with boss orange
				if(s.collidesWith(orange) && orange.isVisible()){
					// Everytime the ship’s bullet hits the boss fish, the boss fish’s health is reduced by the ship’s strength.
					sList.remove(s);
					orange.setHealth(-(this.myNinja.getNinjaStrength()));

					// When the Boss Fish’s health reaches 0, the Boss Fish dies and disappears.
					if(orange.getHealth() <= 0){
						System.out.println("Orange has been sliced.");
						orange.setAlive();
						orange.setVisible(false);
						this.setScoreCounter(2); //adds 2 to the score
					}
				}

				// Check if bullet collide with apple
				for(int j = 0; j < this.apples.size(); j++){
					Apple apples = this.apples.get(j);
					if(s.collidesWith(apples)){ //if the sword collides with a apple
						System.out.println("Apple has been sliced.");
						sList.remove(s); //removes the sword from the list
						this.apples.remove(apples); //and removes the apple as well
						this.setScoreCounter(1); //adds 1 to the score
					}
				}
			} else {
				sList.remove(s);
			}
		}
	}

	// method that will move the boss orange
	private void moveOrange(){
		BossOrange orange = this.orange;

		// only do this if orange is visible
		if(orange.getVisible()){
			orange.move();

			if(this.myNinja.collidesWith(orange)){
				System.out.println("Ninja collided with orange!");
				if(this.myNinja.isImmortal()){
					// do nothing if ninja is immortal
					System.out.println("Ninja is immortal! No reduction of health.");
				} else{
					// reduces the ship’s strength by 50 when it hits the ship AND the ship is not immortal.
					this.myNinja.setNinjaStrength(-50);

					// When the ship strength reaches 0 or less than 0, the game is over
					if(this.myNinja.getNinjaStrength() <= 0){
						System.out.println("GAME OVER");
						this.myNinja.die(); //sets the alive attribute of the ninja to false
					}

				}
			}
		}
	}

	//method that will move the apples
	private void moveApples(){
		//Loop through the fishes arraylist
		for(int i = 0; i < this.apples.size(); i++){
			Apple a = this.apples.get(i);
			/*
			 * TODO:  *If a fish is alive, move the fish. Else, remove the fish from the fishes arraylist. DONE
			 */
			if(a.getVisible()){
				a.move();

				if(this.myNinja.collidesWith(a)){
					System.out.println("Ninja collided with an apple.");
					if(this.myNinja.isImmortal()){ // If ninja is immortal, no reduction of health
						System.out.println("Ninja is immortal! No reduction of health.");
						// remove apple
						this.apples.remove(a);
					}else{
						// When an apple hits the ninja AND ninja is not immortal, the ship’s strength is reduced by 30,
						this.myNinja.setNinjaStrength(-30);
						// and the fish dies and disappears.
						this.apples.remove(a);

						// When the ship strength reaches 0 or less than 0, the game is over
						if(this.myNinja.getNinjaStrength() <= 0){
							System.out.println("GAME OVER");
							this.myNinja.die(); //sets the alive attribute of the ninja to false
						}
					}
				}
			} else {
				this.apples.remove(a);
			}
		}
	}


	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyNinja(code);
			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopMyNinja(code);
		            }
		        });
    }

	//method that will move the ship depending on the key pressed
	private void moveMyNinja(KeyCode ke) {
		if(ke==KeyCode.UP) this.myNinja.setDY(-3);

		if(ke==KeyCode.LEFT) this.myNinja.setDX(-3);

		if(ke==KeyCode.DOWN) this.myNinja.setDY(3);

		if(ke==KeyCode.RIGHT) this.myNinja.setDX(3);

		if(ke==KeyCode.SPACE) this.myNinja.shoot();

		System.out.println(ke+" key pressed.");
   	}

	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopMyNinja(KeyCode ke){
		this.myNinja.setDX(0);
		this.myNinja.setDY(0);
	}

	public int getScoreCounter() {
		return scoreCounter;
	}

	public void setScoreCounter(int scoreCounter) {
		this.scoreCounter += scoreCounter;
	}

}
