package org.example.chess.pieces;

import org.example.chess.pieces.global.MoveSeq;

import java.util.List;

public class Rook extends Piece {
    private Rook(Color color) {
        super(color, Type.ROOK);
    }

    public static Rook of(Color color) {
        return new Rook(color);
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        return List.of(
                MoveSeq.createInfNorth(),
                MoveSeq.createOneSouth(),
                MoveSeq.createInfEast(),
                MoveSeq.createInfWest()
        );
    }
}
