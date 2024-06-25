package org.example.chess.board;

import org.example.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Pawn.createBlackPawn;

public class Board {

    static final int size = 8;
    static final int EMPTY_INDEX = -1;
    static int[][] board = new int[size][size];
    static List<Pawn> pawnList = new ArrayList<>();
    static List<Pawn> whitePawnList = new ArrayList<>();
    static List<Pawn> blackPawnList = new ArrayList<>();

    public void add(Pawn pawn) {
        pawnList.add(pawn);
    }

    public int size() {
        return pawnList.size();
    }

    public Pawn findPawn(int i) {
        return pawnList.get(i);
    }

    public void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = EMPTY_INDEX;
            }    
        }
        
        for (int i = 0; i < size; i++) {
            Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
            whitePawnList.add(whitePawn);
            pawnList.add(whitePawn);
            // Todo : 임의로 pawnList 의 Index 를 가지게 하였다
            board[1][i] = pawnList.size() - 1;

            Pawn blackPawn = createBlackPawn();
            blackPawnList.add(blackPawn);
            pawnList.add(blackPawn);
            // Todo : ''
            board[1][i] = pawnList.size() - 1;
        }
    }
    
    public String print() {
        StringBuilder sb = new StringBuilder();

        for (int[] row : board) {
            for (int now : row) {
                Pawn pawn = pawnList.get(now);
                sb.append(pawn.getRepresentation()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public String getWhitePawnsResult() {
        return getPawnResult(Pawn.WHITE_REPRESENTATION, whitePawnList);
    }

    public String getBlackPawnsResult() {
        return getPawnResult(Pawn.BLACK_REPRESENTATION, blackPawnList);
    }

    private static String getPawnResult(char representation, List<Pawn> pawnList) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(representation).repeat(pawnList.size()));
        return sb.toString();
    }
}
