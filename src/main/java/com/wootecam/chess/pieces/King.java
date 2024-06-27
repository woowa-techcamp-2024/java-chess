package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class King extends Piece {
    private static final List<Direction> KING_DIRECTIONS = Direction.kingDirections();

    public King(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        int xOffset = to.x - from.x;
        int yOffset = to.y - from.y;

        Optional<Direction> directionOpt = Direction.findByDegree(xOffset, yOffset);
        if (directionOpt.isEmpty()) {
            return Optional.empty();
        }
        Direction direction = directionOpt.get();

        return KING_DIRECTIONS.stream()
                .filter(d -> d == direction)
                .findFirst();
    }
}