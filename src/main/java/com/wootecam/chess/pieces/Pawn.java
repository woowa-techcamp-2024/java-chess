package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {
    private static final List<Direction> BLACK_PAWN_DIRECTIONS = Direction.blackPawnDirection();
    private static final List<Direction> WHITE_PAWN_DIRECTIONS = Direction.whitePawnDirection();
    private static final int PAWN_RANGE = 1;

    public Pawn(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        if (this.color.isBlack()) {
            return findCorrectDirectionInternal(from, to, BLACK_PAWN_DIRECTIONS, PAWN_RANGE);
        }

        return findCorrectDirectionInternal(from, to, WHITE_PAWN_DIRECTIONS, PAWN_RANGE);
    }
}
