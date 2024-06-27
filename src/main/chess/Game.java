package chess;

import chess.piece.Piece;

public class Game {

    private Board board;
    private Piece.Color player;

    public Game() {
        this.board = new Board();
    }

    public void initialize() {
        board.initialize();
        player = Piece.Color.WHITE;
    }

    public void move(Position from, Position to) {
        validateMove(from, to);
        board.move(from, to);
        player = player.opposite();
    }

    private void validateMove(Position from, Position to) {
        if (from.equals(to)) throw new IllegalArgumentException("Cannot move to same position");

        Board.Cell fromCell = board.cellAt(from);
        if (fromCell.isEmpty()) throw new IllegalArgumentException("No piece to move");

        Piece piece = fromCell.getPiece();
        if (!piece.isColor(player)) throw new IllegalArgumentException("Cannot move other player's piece");

        Offset offset = Offset.between(from, to);
        BoardContext context = context(from);
        if (!piece.canMove(offset, context)) throw new IllegalArgumentException("Invalid move");
    }

    private BoardContext context(Position position) {
        return new BoardContextImpl((offset) -> {
            Position targtPosition = position.add(offset);
            if (!targtPosition.isValid()) return null;
            return board.get(targtPosition);
        });
    }

    public void print() {
        int rank = 8;
        for (String line : board.toString().split(StringUtils.NEWLINE)) {
            System.out.printf("%d %s%n", rank--, line);
        }
        System.out.println("  abcdefgh");
        System.out.println("Turn: " + player);
    }

}
