package org.example.chess.pieces;

import org.example.chess.pieces.global.Direction;
import org.example.chess.pieces.global.Move;
import org.example.chess.pieces.global.MoveSeq;

import java.util.List;

public class King extends Piece {
    private King(Color color) {
        super(color, Type.KING);
    }

    public static King of(Color color) {
        return new King(color);
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        return List.of(
                MoveSeq.createOneNorth(),
                MoveSeq.createOneEast(),
                MoveSeq.createOneWest(),
                MoveSeq.createOneSouth()
        );
    }
}
