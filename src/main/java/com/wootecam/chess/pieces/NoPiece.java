package com.wootecam.chess.pieces;

import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.Optional;

public class NoPiece extends Piece {
    public static final NoPiece BLANK = new NoPiece(PieceType.NO_PIECE, Color.NO_COLOR);

    public NoPiece(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public Optional<Direction> findCorrectDirection(Position from, Position to) {
        return Optional.empty();
    }
}
