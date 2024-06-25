package chess;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void initialize() {
        board.initialize();
    }

    public void print() {
        System.out.println(board);
    }
}
