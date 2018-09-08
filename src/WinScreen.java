import javax.swing.*;
import java.awt.*;

public class WinScreen extends JPanel implements Fields {

	private String message;
	private JLabel winner;

	public WinScreen(int win) {
		// 1 - Player
		// 2 - Computer
		// 3 - Tie
		
		if (win == 1)
			message = "You win.";
		else if (win == 2)
			message = "You lost.";
		else if (win == 3)
			message = "Tie.";
		
		winner = new JLabel(message);
		winner.setFont(titleFont);
		
		add(winner);
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}

}
