package org.example.chess.pieces;

import org.example.chess.pieces.global.Direction;
import org.example.chess.pieces.global.Move;
import org.example.chess.pieces.global.MoveSeq;
import org.example.chess.pieces.global.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Bishop extends Piece{
    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    public static Bishop of(Color color) {
        return new Bishop(color);
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        return List.of(
                MoveSeq.createInfNE(),
                MoveSeq.createInfNW(),
                MoveSeq.createInfSE(),
                MoveSeq.createInfSW()
        );
    }
}
