package chess;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void initialize() {
        board.initialize();
    }

    public void move(Position from, Position to) {
        board.move(from, to);
    }

    private boolean canMove(Position from, Position to) {
        if (from.equals(to)) return false;
        Board.Cell fromCell = board.cellAt(from);
        Offset offset = Offset.between(from, to);
        BoardContext context = context(from);
        return !fromCell.isEmpty() && fromCell.getPiece().canMove(offset, context);
    }

    private BoardContext context(Position position) {
        return new BoardContextImpl((offset) -> {
            Position targtPosition = position.add(offset);
            if (!targtPosition.isValid()) return null;
            return board.get(targtPosition);
        });
    }

    public void print() {
        System.out.println(board);
    }
}
