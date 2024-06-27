package com.wootecam.chess.pieces;

import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> ROOK_DIRECTIONS = Direction.rookDirections();

    public Rook(PieceType pieceType, Color color) {
        super(pieceType, color);
    }
}
