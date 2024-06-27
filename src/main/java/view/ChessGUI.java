package view;

import chess.Board;
import chess.ChessGame;
import chess.Ranks;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pieces.Piece;
import pieces.PieceType;

public class ChessGUI extends JFrame {

	public static final String DELIMITER = " ";
	private final ChessGame chessGame;
	private JButton[][] chessSquares;
	private StringBuilder command;
	private int commandCount;

	public ChessGUI() {
		chessGame = new ChessGame(new Board(new Ranks()));
		command = new StringBuilder();
		initializeGUI();
		setupChessboard(chessGame.getPiecesArray());
	}

	public static String inputPromotion() {
		String[] options = PieceType.getPromotionPieceTypes();
		int choice = JOptionPane.showOptionDialog(null, "Choose promotion piece:", "Pawn Promotion",
			JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		return options[choice];
	}

	public void initializeGUI() {
		setTitle("Chess Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLocationRelativeTo(null);

		JPanel boardPanel = new JPanel(new GridLayout(Board.BOARD_SIZE, Board.BOARD_SIZE));
		chessSquares = new JButton[Board.BOARD_SIZE][Board.BOARD_SIZE];

		for (int row = 0; row < Board.BOARD_SIZE; row++) {
			for (int col = 0; col < Board.BOARD_SIZE; col++) {
				JButton button = new JButton();
				button.setOpaque(true);
				button.setPreferredSize(new Dimension(60, 60));
				boardPanel.add(button);
				chessSquares[row][col] = button;

				int finalRow = row;
				int finalCol = col;
				button.addActionListener(e -> handleSquareClick((char) (finalCol + 'a'), (char) (8 - finalRow + '0')));
			}
		}

		getContentPane().add(boardPanel);
	}

	private void setupChessboard(Piece[][] pieces) {
		for (int row = 0; row < pieces.length; row++) {
			for (int col = 0; col < pieces.length; col++) {
				chessSquares[row][col].setText(String.valueOf(pieces[row][col].getRepresentation()));
				chessSquares[row][col].setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
				chessSquares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
				chessSquares[row][col].setBorderPainted(false);
			}
		}
	}

	private void handleSquareClick(char column, char rowReversed) {
		command.append(column).append(rowReversed).append(DELIMITER);
		commandCount++;
		if (commandCount % 2 == 0) {
			move();
		}
		if (chessGame.isGameEnd()) {
			showGameEndAlert();
		}
		setupChessboard(chessGame.getPiecesArray());
	}

	public void showGameEndAlert() {
		JOptionPane.showMessageDialog(ChessGUI.this, printEnd(chessGame.getTurn()));
		System.exit(0);
	}

	private void move() {
		try {
			chessGame.move(command.toString().split(DELIMITER));
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(ChessGUI.this, e.getMessage() + " please retry");
		} finally {
			command = new StringBuilder();
		}
	}

	public String printEnd(int turn) {
		return "game end. " + pieces.Color.fromByTurn(1 - turn).name() + " player win.";
	}
}
