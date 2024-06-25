package com.example.demo.context;

import com.example.demo.piece.*;

public class Board {

    Piece[][] pieceLocation = new Piece[8][8];

    /**
     * <p>
     * 보드는 생성시에 정해진 규칙에 따라서 말을 배치한 초기상태를 가져야한다.
     * </p>
     * <img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Immortal_game_animation.gif" />
     */
    public static Board createBoard() {
        Board board = new Board();

        board.initPawn();
        board.initKing();
        board.initQueen();
        board.initBishop();
        board.initKnight();
        board.initRook();

        return board;
    }

    //--------------init board start----------------
    private void initPawn(){
        for (File file : File.values()) {
            setPiece(Rank.TWO, file, new Pawn(Color.WHITE));
            setPiece(Rank.SEVEN, file, new Pawn(Color.BLACK));
        }
    }

    private void initKing(){
        setPiece(Rank.ONE, File.D, new King(Color.WHITE));
        setPiece(Rank.EIGHT, File.D, new King(Color.BLACK));
    }

    private void initQueen(){
        setPiece(Rank.ONE, File.E, new Queen(Color.WHITE));
        setPiece(Rank.EIGHT, File.E, new Queen(Color.BLACK));
    }

    private void initBishop(){
        setPiece(Rank.ONE, File.C, new Bishop(Color.WHITE));
        setPiece(Rank.ONE, File.F, new Bishop(Color.WHITE));
        setPiece(Rank.EIGHT, File.C, new Bishop(Color.BLACK));
        setPiece(Rank.EIGHT, File.F, new Bishop(Color.BLACK));
    }

    private void initKnight(){
        setPiece(Rank.ONE, File.B, new Knight(Color.WHITE));
        setPiece(Rank.ONE, File.G, new Knight(Color.WHITE));
        setPiece(Rank.EIGHT, File.B, new Knight(Color.BLACK));
        setPiece(Rank.EIGHT, File.G, new Knight(Color.BLACK));
    }

    private void initRook() {
        setPiece(Rank.ONE, File.A, new Rook(Color.WHITE));
        setPiece(Rank.ONE, File.H, new Rook(Color.WHITE));
        setPiece(Rank.EIGHT, File.A, new Rook(Color.BLACK));
        setPiece(Rank.EIGHT, File.H, new Rook(Color.BLACK));
    }
    //--------------init board end  ----------------

    public void setPiece(Rank row, File column, Piece piece) {
        pieceLocation[row.index()][column.index()] = piece;
    }

    public Piece getPiece(Rank row, File column) {
        return pieceLocation[row.index()][column.index()];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Piece[] pieces : pieceLocation) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    sb.append(piece);
                } else {
                    sb.append(".");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}