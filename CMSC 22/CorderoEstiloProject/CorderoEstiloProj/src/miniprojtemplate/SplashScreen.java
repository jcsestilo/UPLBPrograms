package miniprojtemplate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//reference: https://www.youtube.com/watch?v=vxH7UTgEHhk&t=48s

public class SplashScreen extends JFrame {

	private JPanel contentPane;

	public SplashScreen(){
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel label = new JLabel("");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/splashscreen.png"));
		label.setIcon(icon);
		label.setBounds(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
//		label.setSize(850, GameStage.WINDOW_HEIGHT);
		contentPane.add(label);

	}
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
