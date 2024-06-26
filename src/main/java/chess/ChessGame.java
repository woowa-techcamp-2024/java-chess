package chess;

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

        board.saveByPosition(piece, destinationPosition);
        board.saveByPosition(Piece.createBlank(sourcePosition), sourcePosition);
    }
}
