package org.example.chess.pieces;

import org.example.chess.pieces.global.MoveSeq;

import java.util.List;

public class NoPiece extends Piece{
    private NoPiece() {
        super(Color.NOCOLOR, Type.NO_PIECE);
    }

    public static NoPiece of() {
        return new NoPiece();
    }

    @Override
    public List<MoveSeq> getMoveSeqs() {
        return null;
    }
}
