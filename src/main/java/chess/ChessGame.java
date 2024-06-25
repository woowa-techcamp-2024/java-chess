package chess;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void start() {
        board.initialize();

        board.print();
    }

    public void play() {

    }

    public void end() {

    }
}
