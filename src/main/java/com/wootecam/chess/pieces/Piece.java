package com.wootecam.chess.pieces;

import static com.wootecam.chess.common.ChessConstraint.isValidIndex;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceRepresentation;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Piece {
    public static final Piece BLANK = new Piece(PieceType.NO_PIECE, Color.NO_COLOR);

    private final PieceType type;
    private final Color color;
    private final PieceRepresentation representation;

    protected Piece(PieceType pieceType, Color color) {
        this.type = pieceType;
        this.color = color;
        this.representation = PieceRepresentation.findByTypeAndColor(pieceType, color);
    }

    public static Piece createWhitePawn() {
        return createWhite(PieceType.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(PieceType.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(PieceType.ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(PieceType.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(PieceType.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(PieceType.KING);
    }

    public static Piece createBlackPawn() {
        return createBlack(PieceType.PAWN);
    }

    public static Piece createBlackKnight() {
        return createBlack(PieceType.KNIGHT);
    }

    public static Piece createBlackRook() {
        return createBlack(PieceType.ROOK);
    }

    public static Piece createBlackBishop() {
        return createBlack(PieceType.BISHOP);
    }

    public static Piece createBlackQueen() {
        return createBlack(PieceType.QUEEN);
    }

    public static Piece createBlackKing() {
        return createBlack(PieceType.KING);
    }

    private static Piece createWhite(PieceType type) {
        return createPiece(type, Color.WHITE);
    }

    private static Piece createBlack(PieceType type) {
        return createPiece(type, Color.BLACK);
    }

    private static Piece createPiece(PieceType type, Color color) {
        return switch (type) {
            case PAWN -> new Pawn(type, color);
            case KNIGHT -> new Knight(type, color);
            case ROOK -> new Rook(type, color);
            case BISHOP -> new Bishop(type, color);
            case QUEEN -> new Queen(type, color);
            case KING -> new King(type, color);
            default -> BLANK;
        };
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isPiece() {
        return type != PieceType.NO_PIECE;
    }

    public boolean isAlly(Piece piece) {
        return isColor(piece.getColor());
    }

    public boolean isType(PieceType type) {
        return this.type == type;
    }

    public boolean isPawn() {
        return isType(PieceType.PAWN);
    }

    public boolean isKnight() {
        return isType(PieceType.KNIGHT);
    }

    public boolean hasTypeAndColor(PieceType type, Color color) {
        return this.type == type && this.color == color;
    }

    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return Optional.empty();
    }

    protected Optional<Direction> findCorrectDirectionInternal(Position from, Position to, List<Direction> directions) {
        for (Direction d : directions) {
            int nx = from.x;
            int ny = from.y;
            while (true) {
                nx += d.xDegree;
                ny += d.yDegree;
                if (!isValidIndex(nx, ny)) {
                    break;
                }
                if (nx == to.x && ny == to.y) {
                    return Optional.of(d);
                }
            }
        }

        return Optional.empty();
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public PieceRepresentation getRepresentation() {
        return representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }

    @Override
    public String toString() {
        return color.name() + " " + type.name();
    }
}
