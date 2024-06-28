package org.example.chess.pieces;

import org.example.chess.pieces.global.Direction;
import org.example.chess.pieces.global.Move;
import org.example.chess.pieces.global.MoveSeq;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    private Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    public static Knight of(Color color) {
        return new Knight(color);
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        return new ArrayList<>(List.of(
                MoveSeq.of(List.of(Move.of(Direction.NORTH, true), Move.of(Direction.NORTHEAST, false))),
                MoveSeq.of(List.of(Move.of(Direction.NORTH, true), Move.of(Direction.NORTHWEST, false))),
                MoveSeq.of(List.of(Move.of(Direction.SOUTH, true), Move.of(Direction.SOUTHEAST, false))),
                MoveSeq.of(List.of(Move.of(Direction.SOUTH, true), Move.of(Direction.SOUTHWEST, false))),
                MoveSeq.of(List.of(Move.of(Direction.EAST, true), Move.of(Direction.NORTHEAST, false))),
                MoveSeq.of(List.of(Move.of(Direction.EAST, true), Move.of(Direction.SOUTHEAST, false))),
                MoveSeq.of(List.of(Move.of(Direction.WEST, true), Move.of(Direction.NORTHWEST, false))),
                MoveSeq.of(List.of(Move.of(Direction.WEST, true), Move.of(Direction.SOUTHWEST, false)))
        )) {
        };
    }
}
