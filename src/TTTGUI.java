import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TTTGUI extends JPanel implements Fields {

	private boolean playerFirst;
	private JFrame frame;
	private TTTButton[] buttons = new TTTButton[9];
	private String[][] grid = new String[3][3];

	public TTTGUI(JFrame frame, boolean playerFirst) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Lato-Light.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Lato-Bold.ttf")));
		} catch (IOException | FontFormatException e) {
		}

		this.frame = frame;

		setLayout(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));

		this.playerFirst = playerFirst;

		for (int i = 0; i < 9; i++) {
			TTTButton button = new TTTButton(i + 1);
			button.setPreferredSize(BUTTON_SIZE);
			formatButton(button);
			button.addActionListener(new ButtonInput());
			buttons[i] = button;
			add(buttons[i]);
		}

		setBoard();

		if (!playerFirst) {
			computerFirstChoice();
		}

		setBackground(Color.white);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	public void setBoard() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				grid[j][i] = " ";
			}
		}
	}

	public void printBoard() {
		System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]);
		System.out.println("-----");
		System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]);
		System.out.println("-----");
		System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]);
		System.out.println(" ");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBars(g);

		printBoard();
	}

	public void drawBars(Graphics g) {
		g.setColor(Color.black);

		// vertical bars
		g.fillRect(PADDING + BUTTON_WIDTH, 0, PADDING, WINDOW_HEIGHT);
		g.fillRect((PADDING + BUTTON_WIDTH) * 2, 0, PADDING, WINDOW_HEIGHT);

		// horizontal bars
		g.fillRect(0, PADDING + BUTTON_WIDTH, WINDOW_WIDTH, PADDING);
		g.fillRect(0, (PADDING + BUTTON_WIDTH) * 2, WINDOW_WIDTH, PADDING);
	}

	public void formatButton(JButton button) {
		button.setForeground(Color.black);
		button.setBackground(Color.white);
		button.setBorder(null);
		button.setFont(Lato_Light);
	}

	public void setTurn(boolean playerTurn) {
		this.playerFirst = playerTurn;
	}

	public void computerFirstChoice() {
		grid[1][1] = "O";
		setComputerChoiceButton(1, 1);
	}

	public void setComputerChoiceButton(int row, int col) {
		int num = row * 3 + col;
		buttons[num].setText("O");
	}

	public void setComputerChoice() {
		if (grid[1][1] == " ") {
			grid[1][1] = "O";
			setComputerChoiceButton(1, 1);
			return;
		}

		// Completing three
		for (int i = 0; i <= 2; i++) {
			if ((grid[i][0] == grid[i][1]) && (grid[i][1] == "O") && (grid[i][2] == " ")) {
				grid[i][2] = "O";
				setComputerChoiceButton(i, 2);
				return;
			} else if ((grid[i][1] == grid[i][2]) && (grid[i][2] == "O") && (grid[i][0] == " ")) {
				grid[i][0] = "O";
				setComputerChoiceButton(i, 0);
				return;
			} else if ((grid[i][0] == grid[i][2]) && (grid[i][2] == "O") && (grid[i][1] == " ")) {
				grid[i][1] = "O";
				setComputerChoiceButton(i, 1);
				return;
			} else if ((grid[0][i] == grid[1][i]) && (grid[1][i] == "O") && (grid[2][i] == " ")) {
				grid[2][i] = "O";
				setComputerChoiceButton(2, i);
				return;
			} else if ((grid[1][i] == grid[2][i]) && (grid[2][i] == "O") && (grid[0][i] == " ")) {
				grid[0][i] = "O";
				setComputerChoiceButton(0, i);
				return;
			} else if ((grid[0][i] == grid[2][i]) && (grid[2][i] == "O") && (grid[1][i] == " ")) {
				grid[1][i] = "O";
				setComputerChoiceButton(1, i);
				return;
			}
		}

		if ((grid[0][0] == grid[1][1]) && (grid[1][1] == "O") && (grid[2][2] == " ")) {
			grid[2][2] = "O";
			setComputerChoiceButton(2, 2);
			return;
		} else if ((grid[1][1] == grid[2][2]) && (grid[2][2] == "O") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			setComputerChoiceButton(0, 0);
			return;
		} else if ((grid[0][0] == grid[2][2]) && (grid[2][2] == "O") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			setComputerChoiceButton(1, 1);
			return;
		} else if ((grid[2][0] == grid[1][1]) && (grid[1][1] == "O") && (grid[0][2] == " ")) {
			grid[0][2] = "O";
			setComputerChoiceButton(0, 2);
			return;
		} else if ((grid[1][1] == grid[0][2]) && (grid[0][2] == "O") && (grid[2][0] == " ")) {
			grid[2][0] = "O";
			setComputerChoiceButton(2, 0);
			return;
		} else if ((grid[2][0] == grid[0][2]) && (grid[0][2] == "O") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			setComputerChoiceButton(1, 1);
			return;
		}

		for (int i = 0; i <= 2; i++) {
			if ((grid[i][0] == grid[i][1]) && (grid[i][1] == "X") && (grid[i][2] == " ")) {
				grid[i][2] = "O";
				setComputerChoiceButton(i, 2);
				return;
			} else if ((grid[i][1] == grid[i][2]) && (grid[i][2] == "X") && (grid[i][0] == " ")) {
				grid[i][0] = "O";
				setComputerChoiceButton(i, 0);
				return;
			} else if ((grid[i][0] == grid[i][2]) && (grid[i][2] == "X") && (grid[i][1] == " ")) {
				grid[i][1] = "O";
				setComputerChoiceButton(i, 1);
				return;
			} else if ((grid[0][i] == grid[1][i]) && (grid[1][i] == "X") && (grid[2][i] == " ")) {
				grid[2][i] = "O";
				setComputerChoiceButton(2, i);
				return;
			} else if ((grid[1][i] == grid[2][i]) && (grid[2][i] == "X") && (grid[0][i] == " ")) {
				grid[0][i] = "O";
				setComputerChoiceButton(0, i);
				return;
			} else if ((grid[0][i] == grid[2][i]) && (grid[2][i] == "X") && (grid[1][i] == " ")) {
				grid[1][i] = "O";
				setComputerChoiceButton(1, i);
				return;
			}
		}

		if ((grid[0][0] == grid[1][1]) && (grid[1][1] == "X") && (grid[2][2] == " ")) {
			grid[2][2] = "O";
			setComputerChoiceButton(2, 2);
			return;
		} else if ((grid[1][1] == grid[2][2]) && (grid[2][2] == "X") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			setComputerChoiceButton(0, 0);
			return;
		} else if ((grid[0][0] == grid[2][2]) && (grid[2][2] == "X") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			setComputerChoiceButton(1, 1);
			return;
		} else if ((grid[2][0] == grid[1][1]) && (grid[1][1] == "X") && (grid[0][2] == " ")) {
			grid[0][2] = "O";
			setComputerChoiceButton(0, 2);
			return;
		} else if ((grid[1][1] == grid[0][2]) && (grid[0][2] == "X") && (grid[2][0] == " ")) {
			grid[2][0] = "O";
			setComputerChoiceButton(2, 0);
			return;
		} else if ((grid[2][0] == grid[0][2]) && (grid[0][2] == "X") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			setComputerChoiceButton(1, 1);
			return;
		}

		if (((grid[0][0] == "X") && (grid[0][1] == " ")) || ((grid[0][2] == "X") && (grid[0][1] == " "))) {
			grid[0][1] = "O";
			setComputerChoiceButton(0, 1);
			return;
		} else if (((grid[2][0] == "X") && (grid[2][1] == " ")) || ((grid[2][2] == "X") && (grid[2][1] == " "))) {
			grid[2][1] = "O";
			setComputerChoiceButton(2, 1);
			return;
		} else if (((grid[2][0] == "X") && (grid[2][1] == " ")) || ((grid[2][2] == "X") && (grid[2][1] == " "))) {
			grid[2][1] = "O";
			setComputerChoiceButton(2, 1);
			return;
		}

		// Opposite corner
		if ((grid[0][0] != " ") && (grid[2][2] == " ")) {
			grid[2][2] = "O";
			setComputerChoiceButton(2, 2);
			return;
		} else if ((grid[2][2] != " ") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			setComputerChoiceButton(0, 0);
			return;
		} else if ((grid[2][0] != " ") && (grid[0][2] == " ")) {
			grid[0][2] = "O";
			setComputerChoiceButton(0, 2);
			return;
		} else if ((grid[0][2] != " ") && (grid[2][0] == " ")) {
			grid[2][0] = "O";
			setComputerChoiceButton(2, 0);
			return;
		}

		if (grid[1][1] == " ") {
			grid[1][1] = "O";
			setComputerChoiceButton(1, 1);
			return;
		} else if ((grid[1][1] != " ") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			setComputerChoiceButton(0, 0);
			return;
		}

		// or else first empty spot
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].equals(" ")) {
					grid[i][j] = "O";
					setComputerChoiceButton(i, j);
					return;
				}
			}
		}
	}

	public int getWinner() {
		// 1 - Player
		// 2 - Computer
		// 3 - Tie
		// 4 - Keep Playing

		// Check by rows
		for (int i = 0; i <= 2; i++) {
			if ((grid[i][0] == "X") && (grid[i][1] == "X") && (grid[i][2] == "X")) {
				return 1;
			} else if ((grid[i][0] == "O") && (grid[i][1] == "O") && (grid[i][2] == "O")) {
				return 2;
			}
		}

		// Check by columns
		for (int i = 0; i <= 2; i++) {
			if ((grid[0][i] == "X") && (grid[1][i] == "X") && (grid[2][i] == "X")) {
				return 1;
			} else if ((grid[0][i] == "O") && (grid[1][i] == "O") && (grid[2][i] == "O")) {
				return 2;
			}
		}

		// Check by diagonals
		if ((grid[0][0] == "X") && (grid[1][1] == "X") && (grid[2][2] == "X")) {
			return 1;
		} else if ((grid[0][0] == "O") && (grid[1][1] == "O") && (grid[2][2] == "O")) {
			return 2;
		} else if ((grid[2][0] == "X") && (grid[1][1] == "X") && (grid[0][2] == "X")) {
			return 1;
		} else if ((grid[2][0] == "O") && (grid[1][1] == "O") && (grid[0][2] == "O")) {
			return 2;
		}

		if (checkFull()) {
			return 3;
		}

		return 4;
	}

	public boolean checkFull() {
		int count = 0;
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (grid[i][j] != " ") {
					count++;
				}
			}
		}

		return count == 9;
	}

	public boolean isTaken(int num) {
		if (num % 3 == 0 && grid[num / 3 - 1][2] == " ") {
			return false;
		} else if (grid[num / 3][num % 3 - 1] == " ") {
			return false;
		}

		return true;
	}

	private class ButtonInput implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TTTButton currButton = (TTTButton) e.getSource();
			int num = currButton.getNum();

			if (currButton.getText().equals("")) {
				currButton.setText("X");
				if (num % 3 == 0) {
					grid[num / 3 - 1][2] = "X";
				} else {
					grid[num / 3][num % 3 - 1] = "X";
				}

				setComputerChoice();
				repaint();
			}

			// 1 - Player
			// 2 - Computer
			// 3 - Tie
			// 4 - Keep Playing

			if (getWinner() != 4) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new WinScreen(getWinner()));
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
			}
		}
	}
}
