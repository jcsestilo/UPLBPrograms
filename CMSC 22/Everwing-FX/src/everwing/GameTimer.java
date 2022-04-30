/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Everwing Example
 * Problem Domain: Everwing is a game where the goal is for the guardian to kill monsters by shooting them and collect coins.  
 * When a guardian is created, it is given a name and initially a score of 0. The guardian can move to the left or to the right. 
 * Monsters appear by waves and die when health reaches 0. 
 * When a monster dies, it becomes a coin or item that the guardian can collect. There is the red and the green type monster 
 * with 10 and 50 health respectively.
 * When the guardian hits a monster, the guardian dies.
 * Different items can be collected by the guardian:
 * Item 		Effect
 * Coin 		Adds +1 score and 1 coin
 * Purple Gem 	Adds +10 score and 10 coins
 * Clover 		Increase the player's bullet level by 5 for the 5 seconds
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 * @date 2018-05-10 
 *************************************************************************************************************************/
package everwing;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Random;

class GameTimer extends AnimationTimer {
	private long startSpawn;
	private long startShoot;
	private GraphicsContext gc;
	private Guardian guardian;
	private Scene scene;
	private ArrayList<Monster> monsters;
	private ArrayList<Item> collectibles;
	private static boolean goLeft;
	private static boolean goRight;
	
	private Image background = new Image( "images/background.jpg" );
	private final static int MIN_MONSTERS = 3;
	private final static int MAX_MONSTERS = 5;
	private final static int MONSTER_TYPES = 2;
	private final static int WIDTH_PER_MONSTER = 80;
	private final static double SHOOT_DELAY = 0.08;
	private final static double SPAWN_DELAY = 1.5;
    
    GameTimer(Scene scene, GraphicsContext gc) {
    	this.gc = gc;
    	this.scene = scene;    	
    	this.guardian = new Guardian("Alice");
    	this.monsters = new ArrayList<Monster>();
    	this.collectibles = new ArrayList<Item>();
    	this.startSpawn = this.startShoot = System.nanoTime();
    	this.prepareActionHandlers();
    }
    
    @Override
	public void handle(long currentNanoTime)
    {
		double spawnElapsedTime = (currentNanoTime - this.startSpawn) / 1000000000.0;
		double shootElapsedTime = (currentNanoTime - this.startShoot) / 1000000000.0;
		
		this.moveGuardian();

		// clear the canvas
        this.gc.clearRect(0, 0, Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT);

        // background image clears canvas
        this.gc.drawImage( background, 0, 0 );

        // draw guardian
        this.guardian.render(this.gc);
        
        // shoot
        if(shootElapsedTime > GameTimer.SHOOT_DELAY) {
        	this.guardian.shoot();
        	this.startShoot = System.nanoTime();
        }
        
        // spawn monsters
        if(spawnElapsedTime > GameTimer.SPAWN_DELAY) {
        	this.spawnMonsters();
        	this.startSpawn = System.nanoTime();
        }
        
        this.moveMonsters();
        this.moveBullets();
        this.moveCollectibles();
        
        // draw
        for (Monster monster : this.monsters )
        	monster.render( this.gc );
        
        for (Bullet b : this.guardian.getBullets())
        	b.render( this.gc );
        
        for (Item c : this.collectibles)
        	c.render( this.gc );
        
        this.drawScore();
        
        if(!this.guardian.isAlive()) {
        	this.stop();
        	this.drawGameOver();
        }
    }
	
    /*
     * Catches the left and right key presses for the guardian's movement
     * */
	private void prepareActionHandlers() {
    	this.scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                if(code.equals("LEFT")) {
                	GameTimer.goLeft = true;
                }else if(code.equals("RIGHT")) {
                	GameTimer.goRight = true;
                }
                
            }
        });

    	this.scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                if(code.equals("LEFT")) {
                	GameTimer.goLeft = false;
                }else if(code.equals("RIGHT")) {
                	GameTimer.goRight = false;
                }
            }
        });
    }
	
	/*
     * Gets called in handle() to move the guardian based on the goLeft and goRight flags
     * */
	private void moveGuardian() {
		if (GameTimer.goLeft)
            this.guardian.setDX(-15);
		else if (GameTimer.goRight)
        	this.guardian.setDX(15);
        else 
        	this.guardian.setDX(0);
        
        this.guardian.move();
        
	}
	
	private void drawScore(){
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.YELLOW);
		this.gc.fillText("Score:", 20, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(guardian.getScore()+"", 90, 30);
	}
	
	private void drawGameOver(){
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("GAME OVER!", 20, Game.WINDOW_HEIGHT/2);
	}
	
	/*
     * Moves the coins, gems, and clovers and checks if they collide with the guardian  in checkCollision()
     * If they are outside the screen, they get removed from the ArrayList
     * */
	private void moveCollectibles(){
		for(int i = 0; i < this.collectibles.size(); i++){
			Item c = this.collectibles.get(i);
			if(c.isVisible()){
				c.move();
				c.checkCollision(this.guardian);
			}
			else collectibles.remove(i);
		}
	}
	
	/*
     * Moves the bullets 
     * If they are outside the screen, they get removed from the ArrayList
     * */
	private void moveBullets(){
		ArrayList<Bullet> bList = this.guardian.getBullets();
		for(int i = 0; i < bList.size(); i++){
			Bullet b = bList.get(i);
			if(b.isVisible())
				b.move();
			else bList.remove(i);
		}
	}
	
	/*
     * Moves the monsters and checks if they collide with the guardian/guardian's bullets in checkCollision()
     * If they are outside the screen, they get removed from the ArrayList
     * */
	private void moveMonsters() {
		for(int i = 0; i < this.monsters.size(); i++){
			Monster m = this.monsters.get(i);
			if(m.isVisible()){
				m.move();
				m.checkCollision(this.guardian);
			}
			else this.monsters.remove(i);
		}
	}
	
	/*
     * Spawn 3-5 monsters at random types and columns
     * */
	private void spawnMonsters(){
		int xPos, yPos = -60, type;
		Random r = new Random();
		
		int monsterCount = r.nextInt(GameTimer.MAX_MONSTERS-GameTimer.MIN_MONSTERS+1)+GameTimer.MIN_MONSTERS;
		for(int i=0;i<monsterCount;i++){
			
			type = r.nextInt(GameTimer.MONSTER_TYPES);

			xPos = i*GameTimer.WIDTH_PER_MONSTER;
			this.monsters.add(new Monster(type, xPos, yPos, this.collectibles));
		}
	}
}
