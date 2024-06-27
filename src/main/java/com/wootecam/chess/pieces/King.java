package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> KING_DIRECTIONS = Direction.kingDirection();

    public King(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        if (board.isAllyPieceAt(to, this)) {
            return false;
        }

        int xOffset = to.x - from.x;
        int yOffset = to.y - from.y;

        return Direction.findByDegree(xOffset, yOffset)
                .filter(KING_DIRECTIONS::contains)
                .isPresent();
    }
}