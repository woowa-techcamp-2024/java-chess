package chess;

import static chess.Board.BOARD_SIZE;
import static chess.CommandType.END;
import static chess.CommandType.START;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public class ChessManager {

    private final ChessView chessView;
    private Board board;

    public ChessManager(ChessView chessView) {
        this.chessView = chessView;
    }

    public void go() {
        CommandType commandType;
        do {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            commandType = Arrays.stream(CommandType.values())
                .filter(type -> command.startsWith(type.getValue().toLowerCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
            execute(commandType, command);
        } while (commandType != END);
    }

    public void execute(CommandType commandType, String command) {
        if (commandType == START) {
            start();
            return;
        }
        if (commandType == END) {
            return;
        }
        move(command.split(" "));
    }

    public void start() {
        List<Rank> ranks = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            List<Piece> pieces = createPieces(row);
            ranks.add(new Rank(pieces));
        }
        board = new Board(ranks);
        chessView.printChessBoard(board);
    }

    private List<Piece> createPieces(int row) {
        List<Piece> pieces = new ArrayList<>();
        for (int column = 0; column < BOARD_SIZE; column++) {
            pieces.add(Piece.of(Color.from(row), PieceType.from(row, column), Position.of(row, column)));
        }
        return pieces;
    }

    public void move(String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }

        String sourcePosition = commands[1];
        String destinationPosition = commands[2];
        board.move(sourcePosition, destinationPosition);
        chessView.printChessBoard(board);
    }
}
