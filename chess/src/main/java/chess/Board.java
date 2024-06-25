package chess;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceFactory;
import pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int NUM_ROW = 8;
    private final int NUM_COL = 8;

    private final String BLANK = ".";
    private final List<Rank> ranks;
    private final PieceFactory pieceFactory;

    public Board() {
        ranks = new ArrayList<>();
        for (int i = 0; i < NUM_ROW; i++) {
            ranks.add(new Rank());
        }
        this.pieceFactory = new PieceFactory();
    }

    public int getNumOfPieces() {
        int num = 0;
        for (Rank rank : ranks) {
            num += rank.getNumOfPieces();
        }
        return num;
    }

    public void initialize() {
        initBlackPieces();
        initBlackPawns();
        initWhitePawns();
        initWhitePieces();
    }

    private void initBlackPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.ROOK));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.QUEEN));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.KING));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.ROOK));
        ranks.set(0, new Rank(pieces));
    }

    private void initBlackPawns() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < NUM_COL; i++) {
            pieces.add(pieceFactory.createPiece(PieceColor.BLACK, PieceType.PAWN));
        }
        ranks.set(1, new Rank(pieces));
    }

    private void initWhitePawns() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < NUM_COL; i++) {
            pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.PAWN));
        }
        ranks.set(6, new Rank(pieces));
    }

    private void initWhitePieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.ROOK));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.QUEEN));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.KING));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(PieceColor.WHITE, PieceType.ROOK));
        ranks.set(7, new Rank(pieces));
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : ranks) {
            sb.append(rank.toString()).append("\n");
        }
        return sb.toString().strip();
    }

    public long countColorPiece(PieceColor color, PieceType type){
        return ranks.stream()
                .mapToLong(rank -> rank.countColorPiece(color, type))
                .sum();
    }

    public Piece findPiece(String givenPosition)
    {
        Position position = new Position(givenPosition);
        return ranks.get(position.getRowIdx()).getPiece(position.getColIdx());
    }

    public void move(String givenPosition, Piece piece)
    {
        Position position = new Position(givenPosition);
        ranks.get(position.getRowIdx()).setPiece(position.getColIdx(), piece);
    }

    public double calculatePoint(PieceColor color)
    {
        double point = 0;
        point += calculatePawnPoint(color);
        for(int rowIdx = 0; rowIdx < NUM_ROW; rowIdx++)
        {
            point += ranks.get(rowIdx).calculatePoint(color);
        }
        return point;
    }

    private double calculatePawnPoint(PieceColor color) {
        double point = 0.0;
        for(int colIdx = 0; colIdx < NUM_COL; colIdx++)
        {
            int numPawn = 0;
            for(int rowIdx = 0; rowIdx < NUM_ROW; rowIdx++)
            {
                if(ranks.get(rowIdx).isPawn(color, colIdx)){
                    numPawn += 1;
                }
            }
            if(numPawn == 1){
                point += PieceType.PAWN.getPoint();
            }
            else if(numPawn >= 2){
                point += numPawn*PieceType.PAWN.getPoint()/2;
            }
        }
        return point;
    }
}
