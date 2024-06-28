package org.example.chess.pieces;

import org.example.chess.pieces.global.MoveSeq;

import java.util.List;

public class Queen extends Piece{
    private Queen(Color color) {
        super(color, Type.QUEEN);
    }

    public static Queen of(Color color) {
        return new Queen(color);
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        return List.of(
                MoveSeq.createInfNE(),
                MoveSeq.createInfSW(),
                MoveSeq.createInfSE(),
                MoveSeq.createInfNW(),
                MoveSeq.createInfNorth(),
                MoveSeq.createInfEast(),
                MoveSeq.createInfWest(),
                MoveSeq.createInfSouth()
        );
    }
}
