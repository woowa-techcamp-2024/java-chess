package lass9436;

import java.util.Scanner;

import lass9436.chess.Board;

public class Main {
	public static void main(String[] args) {
		{
			Scanner scanner = new Scanner(System.in);
			Board board = new Board();
			boolean isStarted = false;
			while (true) {
				String input = scanner.nextLine();
				if (input.equals("start")) {
					board.initialize();
					isStarted = true;
					System.out.println(board.showBoard());
				}
				if (input.equals("exit")) {
					break;
				}
				if (input.startsWith("move")) {
					if (!isStarted) {
						System.out.println("not yet started");
						continue;
					}
					String[] split = input.split(" ");
					String sourcePosition = split[1];
					String targetPosition = split[2];
					try {
						board.move(sourcePosition, targetPosition);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					System.out.println(board.showBoard());
				}
			}
			scanner.close();
		}
	}
}
