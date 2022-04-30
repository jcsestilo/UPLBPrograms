/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example (with GUI): Kart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
	protected Image img;
	protected double xPos, yPos;
	protected double width;
	protected double height;

    public Sprite(double xPos, double yPos, Image image){
		this.xPos = xPos;
		this.yPos = yPos;
		this.loadImage(image);
	}

	private void setSize(){
		this.width = this.img.getWidth();
        this.height = this.img.getHeight();
	}

	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	public void render(GraphicsContext gc){
        gc.drawImage( this.img, this.xPos, this.yPos );
    }

	public Image getImage(){
		return this.img;
	}

	public double getXPos(){
		return this.xPos;
	}

	public double getYPos(){
		return this.yPos;
	}

	public void setXPos(int xPos){
		this.xPos = xPos;
	}

//	public void setYPos(int yPos){
//		this.yPos = yPos;
//	}
}
