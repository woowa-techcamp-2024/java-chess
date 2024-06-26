package view;

import java.util.Scanner;

import chess.Command;

public class InputView {

	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
	}

	public static Command inputCommand() {
		return new Command(SCANNER.nextLine());
	}
}
