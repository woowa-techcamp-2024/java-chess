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

    public void play(final String source, final String target) {
        board.move(source, target);

        board.print();
    }

    public void end() {

    }

    //TODO: implement isCheckmate
    public boolean isCheckmate() {
        return false;
    }
}
