import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, 
				{ '-', '+', '-', '+', '-' }, 
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, 
				{ ' ', '|', ' ', '|', ' ' } };

		printGameBoard(gameBoard);

		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9):");
			int playerPosition = scan.nextInt();

			while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPositions)) {
				System.out.println("Position taken! Enter a correct Position");
				playerPosition = scan.nextInt();
			}

			PlacePiece(gameBoard, playerPosition, "player");

			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			Random rand = new Random();

			int cpuPosition = rand.nextInt(9) + 1;
			while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
				cpuPosition = rand.nextInt(9) + 1;
			}

			PlacePiece(gameBoard, cpuPosition, "cpu");

			printGameBoard(gameBoard);

			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}

		}
	}

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void PlacePiece(char[][] gameBoard, int pos, String user) {

		char symbol = ' ';

		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = '0';
			cpuPositions.add(pos);
		}
		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			break;
		}
	}

	public static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);

		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);

		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			} else if (cpuPositions.contains(l)) {
				return "CPU wins!";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Draw!";
			}
		}

		return "";
	}
}
