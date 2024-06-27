package org.example.chess.pieces.global;

import java.util.List;

public class MoveSeq {
    private List<Move> moves;

    private MoveSeq(List<Move> moves) {
        this.moves = moves;
    }

    public static MoveSeq of(List<Move> moves) {
        return new MoveSeq(moves);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void add(Move move) {
        moves.add(move);
    }
}