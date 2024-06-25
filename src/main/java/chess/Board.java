package chess;

import chess.pieces.Colors;
import chess.pieces.Piece;

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
        whitePieces.add(Piece.createWhiteRook());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteQueen());
        whitePieces.add(Piece.createWhiteKing());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteRook());

        for (int i=0; i<8; i++) {
            Piece white = Piece.createWhitePawn();
            Piece black = Piece.createBlackPawn();
            whitePawns.add(white);
            blackPawns.add(black);
        }

        blackPieces.add(Piece.createBlackRook());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackQueen());
        blackPieces.add(Piece.createBlackKing());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackRook());
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
                sb.append(p.getRepresentation().getSymbol());
            }
        } else {
            for (Piece p : blackPieces) {
                sb.append(p.getRepresentation().getSymbol());
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
                sb.append(p.getRepresentation().getSymbol());
            }
        } else {
            for (Piece p : blackPawns) {
                sb.append(p.getRepresentation().getSymbol());
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
