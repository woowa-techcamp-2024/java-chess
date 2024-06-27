package org.example.chess.pieces;

import java.util.List;
import org.example.chess.pieces.enums.Direction;

public class Blank extends Piece{

    public Blank() {
        super(Color.NONCOLOR, Type.NO_TYPE);
    }

    @Override
    protected List<Direction> getDirections() {
        return List.of();
    }
}
