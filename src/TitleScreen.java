import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleScreen extends JPanel implements Fields {

	private JLabel title;
	private JLabel instructions;
	private JFrame frame;

	public TitleScreen(JFrame frame) {
		this.frame = frame;

		title = new JLabel("Tic Tac Toe");
		instructions = new JLabel("Click anywhere to continue.");

		title.setFont(startFont);
		instructions.setFont(instructionsFont);

		add(title);
		add(instructions);

		addMouseListener(new MouseInput());

		setBackground(Color.white);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	private class MouseInput implements MouseListener {

		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new CoinFlip(frame));
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
		}

		public void mouseReleased(MouseEvent e) {

		}

	}

}
