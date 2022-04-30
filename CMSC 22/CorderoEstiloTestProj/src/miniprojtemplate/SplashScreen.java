package miniprojtemplate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//reference: https://www.youtube.com/watch?v=vxH7UTgEHhk&t=48s

public class SplashScreen {

	private StackPane root;
	private Scene scene;

	public SplashScreen(){
		this.root = new StackPane();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
	}

	public void setStageComponents(Stage stage){
		ImageView imgView = this.createView();

		this.root.getChildren().add(imgView);

		stage.setTitle("Mini Fruit Ninja");

		stage.setScene(this.scene);
		stage.show();
	}

//	sets the background of the window to splashscreen.png
	private ImageView createView(){
		Image bg = new Image("images/splashscreen.png");
		ImageView view = new ImageView();
		view.setImage(bg);

		return view;
	}
//	public static final int WINDOW_HEIGHT = 500;
//	public static final int WINDOW_WIDTH = 800;
//	private Stage stage;
//	private Group root;
//	private Scene scene;
//	private Canvas canvas;
//	private GraphicsContext gc;
//	private Image splashscreen = new Image("images/splashscreen.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, false);
//
//	public SplashScreen(){
//		this.root = new Group();
//		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.CADETBLUE);
//		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
//		this.gc = canvas.getGraphicsContext2D();
//	}
//
//	public void setStage(Stage stage){
//		this.stage = stage;
//
//		// set stage elements
//		this.gc.drawImage(splashscreen, 0, 0);
//		this.root.getChildren().add(canvas);
//
//		this.stage.setTitle("Mini Fruit Ninja Game");
//		this.stage.setScene(this.scene);
//
//		this.stage.show();
//	}

//	@Override
//	public void start(Stage arg0) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
	//public static void main(String[] args){
//		EventQueue.invokeLater(new Runnable() {
//			public void run(){
//				try{
//					Splash1 frame = new Splash1();
//					frame.setVisible(true);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		});

//		int x;
//
//		SplashScreen frame = new SplashScreen();
//		frame.setVisible(true);
//
//		try{
//			for(x = 0; x <= 100; x++){
////				Splash1.progBar.setValue(x);
//				Thread.sleep(30);
////				Splash1.label.setText(Integer.toString(x) + "%");
//
//				if(x ==100){
//					frame.dispose();
//				}
//			}
//		}catch(InterruptedException e){
//			e.printStackTrace();
//		}
	//}

//	public SplashScreen(){
//		setUndecorated(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(500, 250, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(null);
//		setContentPane(contentPane);
//
//		JLabel label = new JLabel("");
//		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/splashscreen.png"));
//		label.setIcon(icon);
//		label.setBounds(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
////		label.setSize(850, GameStage.WINDOW_HEIGHT);
//		contentPane.add(label);
//
//	}
	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;


}
