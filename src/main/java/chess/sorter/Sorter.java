package chess.sorter;

import chess.pieces.Piece;
import java.util.List;

public interface Sorter {
    List<Piece> sort(List<Piece> pieces, Direction direction);
}
