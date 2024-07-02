package org.example.chess.pieces;

import org.example.chess.pieces.global.Direction;
import org.example.chess.pieces.global.MoveSeq;

import java.util.List;

public interface Movable {
    List<MoveSeq> getMoveSeqs();
}
