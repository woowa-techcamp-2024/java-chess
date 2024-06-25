package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Pawn> pawns;
    Character[][] boardMap;

    public Board() {
        this.pawns = new ArrayList<>();
    }


    public void initialize() {
        boardMap = new Character[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},

        };
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int pos) {
        return pawns.get(pos);
    }

    private String getLineAt(int pos) {
        StringBuilder res = new StringBuilder();
        for (Character p : boardMap[pos]) {
            res.append(p);
        }
        return res.toString();
    }

    public String getWhitePawnsResult() {
        return getLineAt(6);
    }

    public String getBlackPawnsResult() {
        return getLineAt(1);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (Character[] row : boardMap) {
            for (Character c : row) {
                sb.append(c);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
