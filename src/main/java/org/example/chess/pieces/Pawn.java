package org.example.chess.pieces;

import org.example.chess.pieces.global.Direction;
import org.example.chess.pieces.global.Move;
import org.example.chess.pieces.global.MoveSeq;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private boolean isFirstMove;

    private Pawn(Color color) {
        super(color, Type.PAWN);
        isFirstMove = true;
    }

    public static Pawn of(Color color) {
        return new Pawn(color);
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        Direction defaultDir = this.getColor() == Color.WHITE ? Direction.NORTH : Direction.SOUTH;
        List<MoveSeq> moveSeqs = new ArrayList<>(List.of(MoveSeq.of(
                List.of(Move.of(defaultDir, false))
        )));

        if (isFirstMove) {
            isFirstMove = false;
            moveSeqs.add(MoveSeq.of(List.of(Move.of(defaultDir, false), Move.of(defaultDir, false))));
        }

        return moveSeqs;
    }
}
