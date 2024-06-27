package chess.sorter;

import chess.board.Board;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import java.util.List;

public interface Sorter {
    List<Piece> sortAsc(Board board, Color color);

    List<Piece> sortDesc(Board board, Color color);

}
