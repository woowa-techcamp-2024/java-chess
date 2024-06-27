package chess.sorter;

import chess.board.Board;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PointSorter implements Sorter {

    @Override
    public List<Piece> sortAsc(Board board, Color color) {
        return board.getPieces(color).stream()
                .sorted(Comparator.comparingDouble(Piece::getDefaultPoint))
                .collect(Collectors.toList());
    }

    @Override
    public List<Piece> sortDesc(Board board, Color color) {
        return board.getPieces(color).stream()
                .sorted(Comparator.comparingDouble(Piece::getDefaultPoint).reversed())
                .collect(Collectors.toList());
    }
}
