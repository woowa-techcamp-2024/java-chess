package chess;

import pieces.Piece;
import pieces.PieceFactory;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int NUM_COL = 8;
    private final String BLANK = ".";
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private final List<Piece> whitePawns;
    private final List<Piece> blackPawns;
    private int numOfPieces = 0;
    private final PieceFactory pieceFactory;

    public Board() {
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        this.whitePawns = new ArrayList<>();
        this.blackPawns = new ArrayList<>();
        this.pieceFactory = new PieceFactory();
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public void initialize() {
        whitePieces.add(pieceFactory.createWhiteRook());
        whitePieces.add(pieceFactory.createWhiteKnight());
        whitePieces.add(pieceFactory.createWhiteBishop());
        whitePieces.add(pieceFactory.createWhiteQueen());
        whitePieces.add(pieceFactory.createWhiteKing());
        whitePieces.add(pieceFactory.createWhiteBishop());
        whitePieces.add(pieceFactory.createWhiteKnight());
        whitePieces.add(pieceFactory.createWhiteRook());

        blackPieces.add(pieceFactory.createBlackRook());
        blackPieces.add(pieceFactory.createBlackKnight());
        blackPieces.add(pieceFactory.createBlackBishop());
        blackPieces.add(pieceFactory.createBlackQueen());
        blackPieces.add(pieceFactory.createBlackKing());
        blackPieces.add(pieceFactory.createBlackBishop());
        blackPieces.add(pieceFactory.createBlackKnight());
        blackPieces.add(pieceFactory.createBlackRook());

        for (int i = 0; i < NUM_COL; i++) {
            whitePawns.add(pieceFactory.createWhitePawn());
            blackPawns.add(pieceFactory.createBlackPawn());
        }
        numOfPieces = 32;
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(getRepresentation(blackPieces)).append("\n")
                .append(getBlackPawnsResult()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getBlankLine()).append("\n")
                .append(getWhitePawnsResult()).append("\n")
                .append(getRepresentation(whitePieces));
        return sb.toString();
    }

    private String getBlankLine() {
        return BLANK.repeat(NUM_COL);
    }

    public String getWhitePawnsResult() {
        return getRepresentation(whitePawns);
    }

    public String getBlackPawnsResult() {
        return getRepresentation(blackPawns);
    }

    private String getRepresentation(List<Piece> pieces) {
        StringBuilder sb = new StringBuilder();
        for(Piece piece : pieces)
        {
            sb.append(piece.represent().getUnicode());
        }
        return sb.toString();
    }
}
