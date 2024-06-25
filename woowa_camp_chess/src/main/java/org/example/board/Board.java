package org.example.board;

import org.example.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    static List<Pawn> pawnList = new ArrayList<>();

    public void add(Pawn pawn) {
        pawnList.add(pawn);
    }


    public int size() {
        return pawnList.size();
    }

    public Pawn findPawn(int i) {
        return pawnList.get(i);
    }
}
