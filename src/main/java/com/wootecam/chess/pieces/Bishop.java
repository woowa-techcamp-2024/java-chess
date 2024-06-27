package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {
    private static final List<Direction> BISHOP_DIRECTIONS = Direction.bishopDirections();
    private static final int BISHOP_RANGE = 8;

    public Bishop(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return findCorrectDirectionInternal(from, to, BISHOP_DIRECTIONS, BISHOP_RANGE);
    }
}
