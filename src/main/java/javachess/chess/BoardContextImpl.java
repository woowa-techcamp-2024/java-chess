package javachess.chess;

import javachess.chess.piece.Piece;

import java.util.function.Function;

public class BoardContextImpl implements BoardContext {

    private final Function<Offset, Piece> pieceGetter;

    public BoardContextImpl(Function<Offset, Piece> pieceGetter) {
        this.pieceGetter = pieceGetter;
    }

    @Override
    public boolean isEmptyAt(Offset offset) {
        return pieceGetter.apply(offset) == null;
    }

    @Override
    public boolean isColorAt(Offset offset, Piece.Color color) {
        Piece piece = pieceGetter.apply(offset);
        return piece != null && piece.isColor(color);
    }
}
