package org.example.chess.board;

import org.example.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Pawn.createBlackPawn;

public class Board {

    static final int size = 8;
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
            Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
            whitePawnList.add(whitePawn);
            pawnList.add(whitePawn);

            Pawn blackPawn = createBlackPawn();
            blackPawnList.add(blackPawn);
            pawnList.add(blackPawn);
        }
    }


    public String getWhitePawnsResult() {
        return null;
    }

    public String getBlackPawnsResult() {
        return null;
    }
}
