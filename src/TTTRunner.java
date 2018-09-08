import java.awt.*;
import javax.swing.*;

public class TTTRunner implements Fields {
	private JFrame frame;

	public TTTRunner() {
		frame = new JFrame("Tic Tac Toe");
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		frame.getContentPane().add(new TitleScreen(frame));
	}

	public void display() {
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		TTTRunner game = new TTTRunner();
		game.display();
	}

}
