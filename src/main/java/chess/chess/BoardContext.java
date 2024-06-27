package chess.chess;

import chess.chess.piece.Piece;

public interface BoardContext {

    boolean isEmptyAt(Offset offset);

    boolean isColorAt(Offset offset, Piece.Color color);
}
