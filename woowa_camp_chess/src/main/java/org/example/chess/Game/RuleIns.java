package org.example.chess.Game;

import org.example.chess.board.Board;

public class RuleIns extends Rule {

    RuleIns(Board board) {
        super(board);
    }

    @Override
    double calculatePoint() {
        //todo : 규칙에 맞는 포인트 계산
        return 0;
    }

    @Override
    void move(String src, String dest) {
        //todo : 조건에 따라 이동을 못하게 해야 한다.
        board.move(src,dest);
    }
}
