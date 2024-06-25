package org.example.chess.board;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Color;
import org.example.chess.pieces.Pawn;

public class Board {

    private final int MAX_PAWNS = 32;
    private final List<Pawn> pawns = new ArrayList<>();
    private final List<Pawn> blackPawnsResult = new ArrayList<>();
    private final List<Pawn> whitePawnsResult = new ArrayList<>();

    public void add(Pawn pawn) {
        if (pawns.size() > MAX_PAWNS) {
            throw new IllegalArgumentException("한 보드판에는 최대 32개의 말이 존재할 수 있습니다.");
        }
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int i) {
        if (i < 0 || i >= pawns.size()) {
            throw new IllegalArgumentException("존재하지 않는 말입니다.");
        }
        return pawns.get(i);
    }

    public void initialize() {
        // 보드판에 흰색 폰 8개, 검은색 폰 8개를 놓도록 초기화하는 메서드
        for (int i = 0; i < 8; i++) {
            blackPawnsResult.add(new Pawn(Color.BLACK, Pawn.BLACK_REPRESENTATION));
            whitePawnsResult.add(new Pawn(Color.WHITE, Pawn.WHITE_REPRESENTATION));
        }

    }

    public void print() {
        // 현재 포드판의 상태를 출력해주는 메서드.
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Pawn pawn : blackPawnsResult) {
            sb.append(pawn.getRepresentation());
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Pawn pawn : whitePawnsResult) {
            sb.append(pawn.getRepresentation());
        }
        return sb.toString();
    }
}
