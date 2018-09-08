import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class CoinFlip extends JPanel implements Fields {

	private int coinFlip;
	private JButton one, two;
	private JFrame frame;
	private JLabel title, blank;
	private Border blackBorder;

	public CoinFlip(JFrame frame) {
		setLayout(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));

		this.frame = frame;
		
		title = new JLabel("Coin Flip");
		title.setFont(titleFont);
		
		blank = new JLabel("");
		blank.setPreferredSize(new Dimension(30, 0));

		one = new JButton("H");
		two = new JButton("T");
		
		formatButton(one);
		formatButton(two);

		one.addActionListener(new ButtonInput());
		two.addActionListener(new ButtonInput());

		coinFlip = (int) (Math.random() * 2) + 1;

		add(title);
		add(one);
		add(blank);
		add(two);

		setBackground(Color.white);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	public void formatButton(JButton button) {
		button.setForeground(Color.black);
		button.setBackground(Color.white);
		button.setBorder(null);
		button.setFont(Lato_Light);
	}

	private class ButtonInput implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean playerFirst = false;

			if (e.getSource() == one) {
				if (1 == coinFlip)
					playerFirst = true;
			} else if (e.getSource() == two) {
				if (2 == coinFlip)
					playerFirst = true;
			}

			frame.getContentPane().removeAll();
			frame.getContentPane().add(new TTTGUI(frame, playerFirst));
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
		}
	}

}