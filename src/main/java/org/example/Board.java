package org.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> pawns = new ArrayList<>();


    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int id) {
        // index를 넘어가는 경우 에러를 발생시키는 코드 추가하기
        return pawns.get(id);
    }
}
