package chess;

import pieces.Piece;
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
        for(int i=0; i < NUM_ROW; i++){
            ranks.add(new Rank());
        }
        this.pieceFactory = new PieceFactory();
    }

    public int getNumOfPieces() {
        int num = 0;
        for(Rank rank : ranks){
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

    private void initBlackPieces()
    {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceFactory.createBlackPiece(PieceType.ROOK));
        pieces.add(pieceFactory.createBlackPiece(PieceType.KNIGHT));
        pieces.add(pieceFactory.createBlackPiece(PieceType.BISHOP));
        pieces.add(pieceFactory.createBlackPiece(PieceType.QUEEN));
        pieces.add(pieceFactory.createBlackPiece(PieceType.KING));
        pieces.add(pieceFactory.createBlackPiece(PieceType.BISHOP));
        pieces.add(pieceFactory.createBlackPiece(PieceType.KNIGHT));
        pieces.add(pieceFactory.createBlackPiece(PieceType.ROOK));
        ranks.set(0, new Rank(pieces));
    }

    private void initBlackPawns()
    {
        List<Piece> pieces = new ArrayList<>();
        for(int i=0; i < NUM_COL; i++){
            pieces.add(pieceFactory.createBlackPiece(PieceType.PAWN));
        }
        ranks.set(1, new Rank(pieces));
    }

    private void initWhitePawns()
    {
        List<Piece> pieces = new ArrayList<>();
        for(int i=0; i < NUM_COL; i++){
            pieces.add(pieceFactory.createWhitePiece(PieceType.PAWN));
        }
        ranks.set(6, new Rank(pieces));
    }

    private void initWhitePieces(){
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceFactory.createWhitePiece(PieceType.ROOK));
        pieces.add(pieceFactory.createWhitePiece(PieceType.KNIGHT));
        pieces.add(pieceFactory.createWhitePiece(PieceType.BISHOP));
        pieces.add(pieceFactory.createWhitePiece(PieceType.QUEEN));
        pieces.add(pieceFactory.createWhitePiece(PieceType.KING));
        pieces.add(pieceFactory.createWhitePiece(PieceType.BISHOP));
        pieces.add(pieceFactory.createWhitePiece(PieceType.KNIGHT));
        pieces.add(pieceFactory.createWhitePiece(PieceType.ROOK));
        ranks.set(7, new Rank(pieces));
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for(Rank rank : ranks){
            sb.append(rank.toString()).append("\n");
        }
        return sb.toString().strip();
    }
}
