package javachess.chess;

import javachess.chess.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private State state;
    private Board board;
    private Piece.Color player;

    public Game() {
        this.state = State.EMPTY;
        this.board = new Board();
    }

    public void initialize() {
        board.initialize();
        player = Piece.Color.WHITE;
        state = State.PLAYING;
    }

    public State getState() {
        return state;
    }

    public void move(Position from, Position to) {
        validateMove(from, to);
        board.move(from, to);
        if (isCheck(player)) {
            state = State.win(player.opposite());
        } else {
            player = player.opposite();
        }
    }

    public List<Position> getValidMoves(Position from) {
        List<Position> validMoves = new ArrayList<>();
        for (int r = 0; r < Board.LENGTH; r++) {
            for (int c = 0; c < Board.LENGTH; c++) {
                try {
                    Position to = Position.of(r, c);
                    validateMove(from, to);
                    validMoves.add(to);
                } catch (Exception e) {
                }
            }
        }
        return validMoves;
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

    private boolean canMove(Position from, Position to) {
        if (from.equals(to)) return false;
        Board.Cell fromCell = board.cellAt(from);
        if (fromCell.isEmpty()) return false;

        Piece piece = fromCell.getPiece();

        Offset offset = Offset.between(from, to);
        BoardContext context = context(from);
        if (!piece.canMove(offset, context)) return false;
        return true;
    }

    private BoardContext context(Position position) {
        return new BoardContextImpl((offset) -> {
            Position targtPosition = position.add(offset);
            if (!targtPosition.isValid()) return null;
            return board.get(targtPosition);
        });
    }

    public String getPiece(Position position) {
        Piece piece = board.get(position);
        return piece == null ? "" : piece.toString();
    }

    public Piece.Color getPlayer() {
        return player;
    }

    public boolean isCheck(Piece.Color kingColor) {
        Board.Cell kingCell = board.findKing(kingColor);
        if (kingCell.isEmpty()) return false;
        List<Board.Cell> opponentCells = board.findAll(kingColor.opposite());
        System.out.println(opponentCells);
        for (Board.Cell cell : opponentCells) {
            if (canMove(cell.getPosition(), kingCell.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public Position getKingPosition(Piece.Color kingColor) {
        Board.Cell kingCell = board.findKing(kingColor);
        return kingCell == null ? null : kingCell.getPosition();
    }

    public void print() {
        int rank = 8;
        for (String line : board.toString().split(StringUtils.NEWLINE)) {
            System.out.printf("%d %s%n", rank--, line);
        }
        System.out.println("  abcdefgh");
        System.out.println("Turn: " + player);
    }

    public enum State {
        EMPTY,
        PLAYING,
        BLACK_WIN,
        WHITE_WIN,
        DRAW;

        static State win(Piece.Color color) {
            return color == Piece.Color.BLACK ? BLACK_WIN : WHITE_WIN;
        }
    }

}
