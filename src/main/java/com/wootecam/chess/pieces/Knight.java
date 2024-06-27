package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {
    private static final List<Direction> KNIGHT_DIRECTIONS = Direction.knightDirection();

    public Knight(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return findCorrectDirectionInternal(from, to, KNIGHT_DIRECTIONS);
    }
}
