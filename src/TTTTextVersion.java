import java.util.Random;
import java.util.Scanner;

public class TTTTextVersion {
	private String[][] grid = new String[3][3];

	public TTTTextVersion() {

	}

	public void run() {
		Scanner input = new Scanner(System.in);
		Random generator = new Random();
		System.out.println("1 - Heads");
		System.out.println("2 - Tails");
		int choice = input.nextInt();
		int coinFlip = generator.nextInt(2) + 1;
		TTTTextVersion game = new TTTTextVersion();
		game.setBoard();
		boolean userTurn;
		boolean compFirstTurn = false;
		if (choice == coinFlip) {
			userTurn = true;
			System.out.println("You go first.");
		} else {
			userTurn = false;
			compFirstTurn = true;
			System.out.println("Computer goes first.");
		}

		while (game.getWinner() == 4) {
			if (userTurn) {
				System.out.println("Type the position.");
				System.out.println("First type the row number then the column number.");
				int p1 = input.nextInt();
				int p2 = input.nextInt();
				if (game.checkBounds(p1, p2)) {
					if (game.checkTaken(p1, p2)) {
						game.setPlayerChoice(p1, p2);
						System.out.println("Your turn: ");
						System.out.println(" ");
						game.printBoard();
						userTurn = false;
					}
				}
			} else {
				if (compFirstTurn) {
					game.computerFirstChoice();
					System.out.println("Computer's turn: ");
					System.out.println(" ");
					game.printBoard();
					userTurn = true;
					compFirstTurn = false;
				} else {
					game.setComputerChoice();
					System.out.println("Computer's turn: ");
					System.out.println(" ");
					game.printBoard();
					userTurn = true;
				}
			}
		}
		System.out.println(game.displayWinner());
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

	public boolean checkBounds(int p1, int p2) {
		if ((p1 >= 1) && (p1 <= 3) && (p2 >= 1) && (p2 <= 3)) {
			return true;
		}
		System.out.println("Out of bounds.");
		return false;
	}

	public boolean checkTaken(int p1, int p2) {
		if (grid[p1 - 1][p2 - 1] == " ") {
			return true;
		}
		System.out.println("Spot taken.");
		return false;
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

	public void setPlayerChoice(int p1, int p2) {
		grid[p1 - 1][p2 - 1] = "X";

	}

	public void computerFirstChoice() {
		grid[1][1] = "O";
	}

	public void setComputerChoice() {
		if (grid[1][1] == " ") {
			grid[1][1] = "O";
			return;
		}

		// Completing three
		for (int i = 0; i <= 2; i++) {
			if ((grid[i][0] == grid[i][1]) && (grid[i][1] == "O") && (grid[i][2] == " ")) {
				grid[i][2] = "O";
				return;
			} else if ((grid[i][1] == grid[i][2]) && (grid[i][2] == "O") && (grid[i][0] == " ")) {
				grid[i][0] = "O";
				return;
			} else if ((grid[i][0] == grid[i][2]) && (grid[i][2] == "O") && (grid[i][1] == " ")) {
				grid[i][1] = "O";
				return;
			} else if ((grid[0][i] == grid[1][i]) && (grid[1][i] == "O") && (grid[2][i] == " ")) {
				grid[2][i] = "O";
				return;
			} else if ((grid[1][i] == grid[2][i]) && (grid[2][i] == "O") && (grid[0][i] == " ")) {
				grid[0][i] = "O";
				return;
			} else if ((grid[0][i] == grid[2][i]) && (grid[2][i] == "O") && (grid[1][i] == " ")) {
				grid[1][i] = "O";
				return;
			}
		}

		if ((grid[0][0] == grid[1][1]) && (grid[1][1] == "O") && (grid[2][2] == " ")) {
			grid[2][2] = "O";
			return;
		} else if ((grid[1][1] == grid[2][2]) && (grid[2][2] == "O") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			return;
		} else if ((grid[0][0] == grid[2][2]) && (grid[2][2] == "O") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			return;
		} else if ((grid[2][0] == grid[1][1]) && (grid[1][1] == "O") && (grid[0][2] == " ")) {
			grid[0][2] = "O";
			return;
		} else if ((grid[1][1] == grid[0][2]) && (grid[0][2] == "O") && (grid[2][0] == " ")) {
			grid[2][0] = "O";
			return;
		} else if ((grid[2][0] == grid[0][2]) && (grid[0][2] == "O") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			return;
		}

		for (int i = 0; i <= 2; i++) {
			if ((grid[i][0] == grid[i][1]) && (grid[i][1] == "X") && (grid[i][2] == " ")) {
				grid[i][2] = "O";
				return;
			} else if ((grid[i][1] == grid[i][2]) && (grid[i][2] == "X") && (grid[i][0] == " ")) {
				grid[i][0] = "O";
				return;
			} else if ((grid[i][0] == grid[i][2]) && (grid[i][2] == "X") && (grid[i][1] == " ")) {
				grid[i][1] = "O";
				return;
			} else if ((grid[0][i] == grid[1][i]) && (grid[1][i] == "X") && (grid[2][i] == " ")) {
				grid[2][i] = "O";
				return;
			} else if ((grid[1][i] == grid[2][i]) && (grid[2][i] == "X") && (grid[0][i] == " ")) {
				grid[0][i] = "O";
				return;
			} else if ((grid[0][i] == grid[2][i]) && (grid[2][i] == "X") && (grid[1][i] == " ")) {
				grid[1][i] = "O";
				return;
			}
		}

		if ((grid[0][0] == grid[1][1]) && (grid[1][1] == "X") && (grid[2][2] == " ")) {
			grid[2][2] = "O";
			return;
		} else if ((grid[1][1] == grid[2][2]) && (grid[2][2] == "X") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			return;
		} else if ((grid[0][0] == grid[2][2]) && (grid[2][2] == "X") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			return;
		} else if ((grid[2][0] == grid[1][1]) && (grid[1][1] == "X") && (grid[0][2] == " ")) {
			grid[0][2] = "O";
			return;
		} else if ((grid[1][1] == grid[0][2]) && (grid[0][2] == "X") && (grid[2][0] == " ")) {
			grid[2][0] = "O";
			return;
		} else if ((grid[2][0] == grid[0][2]) && (grid[0][2] == "X") && (grid[1][1] == " ")) {
			grid[1][1] = "O";
			return;
		}

		if (((grid[0][0] == "X") && (grid[0][1] == " ")) || ((grid[0][2] == "X") && (grid[0][1] == " "))) {
			grid[0][1] = "O";
			return;
		} else if (((grid[2][0] == "X") && (grid[2][1] == " ")) || ((grid[2][2] == "X") && (grid[2][1] == " "))) {
			grid[2][1] = "O";
			return;
		} else if (((grid[2][0] == "X") && (grid[2][1] == " ")) || ((grid[2][2] == "X") && (grid[2][1] == " "))) {
			grid[2][1] = "O";
			return;
		}

		// Opposite corner
		if ((grid[0][0] != " ") && (grid[2][2] == " ")) {
			grid[2][2] = "O";
			return;
		} else if ((grid[2][2] != " ") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			return;
		} else if ((grid[2][0] != " ") && (grid[0][2] == " ")) {
			grid[0][2] = "O";
			return;
		} else if ((grid[0][2] != " ") && (grid[2][0] == " ")) {
			grid[2][0] = "O";
			return;
		}

		if (grid[1][1] == " ") {
			grid[1][1] = "O";
			return;
		} else if ((grid[1][1] != " ") && (grid[0][0] == " ")) {
			grid[0][0] = "O";
			return;
		}

		// or else first empty spot
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].equals(" ")) {
					grid[i][j] = "O";
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

	public String displayWinner() {
		if (getWinner() == 1) {
			return "You win.";
		} else if (getWinner() == 2) {
			return "The computer wins.";
		} else {
			return "Tie.";
		}
	}

}