package chess;

public class Game {

    private ChessBoard board;

    public Game() {
        this.board = new ChessBoard();
    }

    public void initialize() {
        board.initialize();
    }

    public void print() {
        System.out.println(board);
    }
}
