package chess;

import chess.constant.Color;
import chess.constant.Type;
import chess.pieces.*;

import java.util.List;
import java.util.Objects;

import static chess.Board.BOARD_SIZE;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public int pieceCount() {
        List<Rank> ranks = board.findAll();

        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            count += ranks.get(i).getCount();
        }
        return count;
    }

    public void start() {
        board.initialize();
        initializeWhitePiece();
        initializeBlackPiece();
    }

    private void initializeWhitePiece() {
        board.saveByPosition(Piece.createWhiteRook(null), new Position("a1"));
        board.saveByPosition(Piece.createWhiteKnight(null), new Position("b1"));
        board.saveByPosition(Piece.createWhiteBishop(null), new Position("c1"));
        board.saveByPosition(Piece.createWhiteQueen(null), new Position("d1"));
        board.saveByPosition(Piece.createWhiteKing(null), new Position("e1"));
        board.saveByPosition(Piece.createWhiteBishop(null), new Position("f1"));
        board.saveByPosition(Piece.createWhiteKnight(null), new Position("g1"));
        board.saveByPosition(Piece.createWhiteRook(null), new Position("h1"));

        for (int i = 0; i < BOARD_SIZE; i++) {
            board.saveByPosition(Piece.createWhitePawn(null), new Position(i, 6));
        }
    }

    private void initializeBlackPiece() {
        board.saveByPosition(Piece.createBlackRook(null), new Position("a8"));
        board.saveByPosition(Piece.createBlackKnight(null), new Position("b8"));
        board.saveByPosition(Piece.createBlackBishop(null), new Position("c8"));
        board.saveByPosition(Piece.createBlackQueen(null), new Position("d8"));
        board.saveByPosition(Piece.createBlackKing(null), new Position("e8"));
        board.saveByPosition(Piece.createBlackBishop(null), new Position("f8"));
        board.saveByPosition(Piece.createBlackKnight(null), new Position("g8"));
        board.saveByPosition(Piece.createBlackRook(null), new Position("h8"));

        for (int i = 0; i < BOARD_SIZE; i++) {
            board.saveByPosition(Piece.createBlackPawn(null), new Position(i, 1));
        }
    }

    public int countPieces(final Color color, final Type type) {
        List<Piece> pieces = board.findByColor(color);
        return (int) pieces.stream().filter(piece -> Objects.equals(type, piece.getType())).count();
    }

    private double calculatePawns(int[] pawns) {
        double point = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (pawns[i] == 1) point += Type.PAWN.getDefaultPoint();
            else point += (Type.PAWN.getDefaultPoint() / 2) * pawns[i];
        }
        return point;
    }

    public double calculatePoint(final Color color) {
        double point = 0;
        int[] pawns = new int[BOARD_SIZE];
        List<Piece> pieces = board.findByColor(color);

        for (Piece piece : pieces) {
            if (!Objects.equals(piece.getType(), Type.PAWN)) point += piece.getType().getDefaultPoint();
            else pawns[piece.getPosition().getX()] += 1;
        }
        point += calculatePawns(pawns);
        return point;
    }

    public void move(final String position, final Piece piece) {
        Position piecePosition = new Position(position);
        board.saveByPosition(piece, piecePosition);
    }

    public void move(final String source, final String destination) {
        Position sourcePosition = new Position(source);
        Position destinationPosition = new Position(destination);

        Piece piece = board.findByPosition(sourcePosition);
        if (destinationPosition.isOutOfIndex()) return;
        if (isColorSame(piece.getColor(), destinationPosition)) return;
        if (!isMoveAvailable(piece, destinationPosition)) return;

        board.saveByPosition(piece, destinationPosition);
        board.saveByPosition(Piece.createBlank(sourcePosition), sourcePosition);
    }

    private boolean isColorSame(final Color color, final Position position) {
        Piece piece = board.findByPosition(position);
        return Objects.equals(color, piece.getColor());
    }

    private boolean isMoveAvailable(final Piece piece, final Position position) {
        if (Objects.equals(piece.getType(), Type.KING)) {
            return isKingMoveAvailable(piece, position);
        }
        if (Objects.equals(piece.getType(), Type.QUEEN)) {
            return isQueenMoveAvailable(piece, position);
        }
        return false;
    }

    private boolean isKingMoveAvailable(final Piece piece, final Position position) {
        Position sourcePosition = piece.getPosition();
        final int[] y_grad = {-1, -1, -1, 0, 0, 1, 1, 1};
        final int[] x_grad = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int y = sourcePosition.getY() + y_grad[i];
            int x = sourcePosition.getX() + x_grad[i];
            if (position.getX() == x && position.getY() == y) return true;
        }
        return false;
    }

    private boolean isQueenMoveAvailable(final Piece piece, final Position position) {
        Position sourcePosition = piece.getPosition();
        final int[] y_grad = {-1, -1, -1, 0, 0, 1, 1, 1};
        final int[] x_grad = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            Position nextPosition = new Position(sourcePosition.getX() + x_grad[i], sourcePosition.getY() + y_grad[i]);
            boolean moveAvailable = moveVirtualQueen(nextPosition, position, y_grad[i], x_grad[i]);
            if (moveAvailable) return true;
        }
        return false;
    }

    private boolean moveVirtualQueen(final Position source, final Position destination, final int y_grad, final int x_grad) {
        if (source.isOutOfIndex()) return false;
        if (source.getX() == destination.getX() && source.getY() == destination.getY()) return true;

        Piece piece = board.findByPosition(source);
        if (!Objects.equals(piece.getType(), Type.NO_PIECE)) return false;

        Position position = new Position(source.getX() + x_grad, source.getY() + y_grad);
        return moveVirtualQueen(position, destination, y_grad, x_grad);
    }
}
