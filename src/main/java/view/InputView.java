package view;

import chess.Command;
import java.util.Scanner;

public class InputView {

	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
	}

	public static Command inputCommand() {
		try {
			return new Command(SCANNER.nextLine());
		} catch (IllegalArgumentException ex) {
			OutputView.printString(ex.getMessage() + " please retry");
			return inputCommand();
		}
	}

	public static Command inputPromotion() {
		OutputView.printPromotion();
		try {
			return new Command(SCANNER.nextLine());
		} catch (IllegalArgumentException ex) {
			OutputView.printString(ex.getMessage() + " please retry");
			return inputPromotion();
		}
	}
}
