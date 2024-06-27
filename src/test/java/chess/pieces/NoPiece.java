package chess.pieces;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class NoPiece extends Piece {
    public NoPiece(Piece.Type type, Piece.Color color, Piece.Representation representation) {
        super(type, color, representation);
    }

    @Override
    public List<List<Position>> getPossibleMoves(Position currentPosition) {
        return List.of(List.of());
    }
}
