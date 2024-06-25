package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.King;
import com.example.demo.piece.Pawn;
import com.example.demo.piece.Piece;

public class Board {

    Piece[][] pieceLocation = new Piece[8][8];

    /**
     * <p>
     * 보드는 생성시에 정해진 규칙에 따라서 말을 배치한 초기상태를 가져야한다.
     * </p>
     * <img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Immortal_game_animation.gif" />
     */
    public Board() {
        initPawn();
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