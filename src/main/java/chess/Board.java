package chess;

import chess.pieces.Colors;
import chess.pieces.Piece;
import chess.pieces.Representations;

import java.util.ArrayList;
import java.util.List;

import static utils.StringUtils.*;

public class Board {
    List<Piece> whitePieces = new ArrayList<>();
    List<Piece> whitePawns = new ArrayList<>();
    List<Piece> blackPieces = new ArrayList<>();
    List<Piece> blackPawns = new ArrayList<>();

    public void add(Piece piece) {
        if (piece.getColor() == Colors.WHITE) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }

    public int pawnCount() {
        return whitePieces.size() + blackPieces.size();
    }

    public int pieceCount() {
        return pawnCount() + whitePieces.size() + blackPieces.size();
    }

    public Piece findWhitePawn(int index) {
        return whitePieces.get(index);
    }

    public Piece findBlackPawn(int index) {
        return blackPieces.get(index);
    }

    public void initialize() {
        whitePieces.add(Piece.create(Representations.Type.ROOK, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.KNIGHT, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.BISHOP, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.QUEEN, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.KING, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.BISHOP, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.KNIGHT, Colors.WHITE));
        whitePieces.add(Piece.create(Representations.Type.ROOK, Colors.WHITE));

        for (int i=0; i<8; i++) {
            Piece white = Piece.create(Representations.Type.PAWN, Colors.WHITE);
            Piece black = Piece.create(Representations.Type.PAWN, Colors.BLACK);
            whitePawns.add(white);
            blackPawns.add(black);
        }

        blackPieces.add(Piece.create(Representations.Type.ROOK, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.KNIGHT, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.BISHOP, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.QUEEN, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.KING, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.BISHOP, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.KNIGHT, Colors.BLACK));
        blackPieces.add(Piece.create(Representations.Type.ROOK, Colors.BLACK));
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        sb.append(appendNewLine(getBlackPiecesResult()));
        sb.append(appendNewLine(getBlackPawnsResult()));
        sb.append(getBlank());
        sb.append(getBlank());
        sb.append(getBlank());
        sb.append(getBlank());
        sb.append(appendNewLine(getWhitePawnsResult()));
        sb.append(appendNewLine(getWhitePiecesResult()));

        return sb.toString();
    }

    private String getBlank() {
        return appendNewLine( "........");
    }

    private String getPiecesResult(Colors color) {
        StringBuilder sb = new StringBuilder();
        if (color == Colors.WHITE) {
            for (Piece p : whitePieces) {
                sb.append(p.getSymbol());
            }
        } else {
            for (Piece p : blackPieces) {
                sb.append(p.getSymbol());
            }
        }
        return sb.toString();
    }

    public String getWhitePiecesResult() {
        return getPiecesResult(Colors.WHITE);
    }

    public String getBlackPiecesResult() {
        return getPiecesResult(Colors.BLACK);
    }

    private String getPawnsResult(Colors color) {
        StringBuilder sb = new StringBuilder();

        if (color == Colors.WHITE) {
            for (Piece p : whitePawns) {
                sb.append(p.getSymbol());
            }
        } else {
            for (Piece p : blackPawns) {
                sb.append(p.getSymbol());
            }
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Colors.WHITE);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Colors.BLACK);
    }
}
