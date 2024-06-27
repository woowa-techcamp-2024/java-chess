package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class King extends Piece {
    private static final List<Direction> KING_DIRECTIONS = Direction.kingDirections();
    private static final int KING_RANGE = 1;

    public King(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return findCorrectDirectionInternal(from, to, KING_DIRECTIONS, KING_RANGE);
    }
}