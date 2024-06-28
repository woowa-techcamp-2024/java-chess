package pe.goblin.chess.domain.piece.factory;

import pe.goblin.chess.domain.piece.*;
import pe.goblin.chess.domain.piece.vo.Color;

public class PieceFactory {

    public static Piece of(char ch) {
        return switch (ch) {
            case 'p' -> new Pawn(Color.WHITE);
            case 'P' -> new Pawn(Color.BLACK);
            case 'r' -> new Rook(Color.WHITE);
            case 'R' -> new Rook(Color.BLACK);
            case 'n' -> new Knight(Color.WHITE);
            case 'N' -> new Knight(Color.BLACK);
            case 'b' -> new Bishop(Color.WHITE);
            case 'B' -> new Bishop(Color.BLACK);
            case 'q' -> new Queen(Color.WHITE);
            case 'Q' -> new Queen(Color.BLACK);
            case 'k' -> new King(Color.WHITE);
            case 'K' -> new King(Color.BLACK);
            case '.' -> new Blank();
            default -> throw new IllegalArgumentException("not supported character");
        };
    }

    public static Piece createBlank() {
        return new Blank();
    }
}
