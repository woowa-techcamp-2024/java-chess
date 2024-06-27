package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.List;
import java.util.Optional;

public class Rook extends Piece {
    private static final List<Direction> ROOK_DIRECTIONS = Direction.rookDirections();

    public Rook(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return findCorrectDirectionInternal(from, to, ROOK_DIRECTIONS);
    }
}
