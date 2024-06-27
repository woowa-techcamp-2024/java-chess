package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class Queen extends Piece {
    private static final List<Direction> QUEEN_DIRECTIONS = Direction.queenDirections();
    private static final int QUEEN_RANGE = 16;

    public Queen(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return findCorrectDirectionInternal(from, to, QUEEN_DIRECTIONS, QUEEN_RANGE);
    }
}
