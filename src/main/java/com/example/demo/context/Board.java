package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Pawn;

import java.util.Arrays;

public class Board {

    Pawn[][] pieceLocation = new Pawn[8][8];

    /**
     * <p>
     * 보드는 생성시에 정해진 규칙에 따라서 말을 배치한 초기상태를 가져야한다.
     * </p>
     * <img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Immortal_game_animation.gif" />
     */
    public Board() {
        // init pawn
        for (File file : File.values()) {
            setPiece(Rank.TWO, file, new Pawn(Color.BLACK));
            setPiece(Rank.SEVEN, file, new Pawn(Color.WHITE));
        }
    }

    public void setPiece(Rank row, File column, Pawn pawn) {
        pieceLocation[row.index()][column.index()] = pawn;
    }

    public Pawn getPiece(Rank row, File column) {
        return pieceLocation[row.index()][column.index()];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Pawn[] pieces : pieceLocation) {
            for (Pawn piece : pieces) {
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