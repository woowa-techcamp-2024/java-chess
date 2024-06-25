package chess;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceUnicode;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int NUM_COL = 8;
    private final String BLANK = ".";
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private int size = 0;

    public Board() {
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
    }

    public void add(Piece piece) {
        if (piece.color().equals(PieceColor.WHITE)) {
            whitePieces.add(piece);
        } else if (piece.color().equals(PieceColor.BLACK)) {
            blackPieces.add(piece);
        }
        size += 1;
    }

    public int getSize() {
        return size;
    }


    public void initialize() {
        for (int i = 0; i < NUM_COL; i++) {
            whitePieces.add(new Piece(PieceColor.WHITE));
            blackPieces.add(new Piece(PieceColor.BLACK));
        }
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(getBlankLine()).append("\n")
                .append(getBlackPawnsResult()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getWhitePawnsResult()).append("\n")
                .append(getBlankLine()).append("\n");
        return sb.toString();
    }

    private String getBlankLine() {
        return BLANK.repeat(NUM_COL);
    }

    public String getWhitePawnsResult() {
        return getRepresentation(whitePieces);
    }

    public String getBlackPawnsResult() {
        return getRepresentation(blackPieces);
    }

    private String getRepresentation(List<Piece> pieces) {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieces) {
            if (piece.color().equals(PieceColor.BLACK)) {
                sb.append(PieceUnicode.BLACK_PAWN.getUnicode());
            } else if (piece.color().equals(PieceColor.WHITE)) {
                sb.append(PieceUnicode.WHITE_PAWN.getUnicode());
            }
        }
        return sb.toString();
    }
}
