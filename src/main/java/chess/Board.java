package chess;

import chess.pieces.Blank;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import chess.pieces.enums.Type;
import chess.pieces.values.Location;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.StringUtils;

public class Board {

    private static final int[] ROWS = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final char[] COLS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    private final Map<Location, Piece> table;

    public Board() {
        table = new HashMap<>();
        addBlanksInRows(ROWS);
    }

    public void initialize() {
        addDefaultPieces(8, Color.BLACK);
        addDefaultPawns(7, Color.BLACK);
        addBlanksInRows(3, 4, 5, 6);
        addDefaultPawns(2, Color.WHITE);
        addDefaultPieces(1, Color.WHITE);
    }

    public void addPiece(Location location, Piece piece) {
        table.put(location, piece);
    }

    public Piece getPiece(String locationStr) {
        var location = Location.from(locationStr);
        return table.get(location);
    }

    public List<Piece> getPiecesByColor(Color color) {
        return getPiecesByColor(color, false);
    }

    public List<Piece> getPiecesByColor(Color color, boolean reverse) {
        return table.values().stream()
            .filter(piece -> piece.verifySameColor(color))
            .sorted(reverse ? Comparator.comparingDouble(Piece::getScore)
                : Comparator.comparingDouble(Piece::getScore).reversed())
            .toList();
    }

    public long size() {
        return table.values().stream().filter(piece -> !(piece instanceof Blank)).count();
    }

    private void addDefaultPieces(int row, Color color) {
        final Type[] types = new Type[]{Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING,
            Type.BISHOP, Type.KNIGHT, Type.ROOK};
        for (int i = 0; i < types.length; i++) {
            addPiece(Location.of(row, COLS[i]), Piece.generatePiece(types[i], color));
        }
    }

    private void addDefaultPawns(int row, Color color) {
        for (var col : COLS) {
            addPiece(Location.of(row, col), Piece.generatePiece(Type.PAWN, color));
        }
    }

    private void addBlank(Location location) {
        table.put(location, Piece.getBlank());
    }

    private void addBlanksInRow(int row) {
        for (char col : COLS) {
            addBlank(Location.of(row, col));
        }
    }

    private void addBlanksInRows(int... rows) {
        for (var row : rows) {
            addBlanksInRow(row);
        }
    }

    public int countPiecesByTypeAndColor(Type type, Color color) {
        return (int) table.values().stream()
            .filter(type::isInstance)
            .filter(piece -> piece.verifySameColor(color))
            .count();
    }

    public double calculateScoreByColor(Color color) {
        var score = 0.0;
        score += table.values().stream()
            .filter(piece -> !(Type.PAWN.isInstance(piece)) && !(piece.isBlank()))
            .filter(piece -> piece.verifySameColor(color))
            .mapToDouble(Piece::getScore).sum();
        score += calculatePawnScore(color);
        return score;
    }

    // TODO: 보드에서 처리할 책임이 있을까요?
    private double calculatePawnScore(Color color) {
        var result = 0.0;
        for (char col : COLS) {
            var temp = 0.0;
            for (int row : ROWS) {
                var piece = table.get(Location.of(row, col));
                if (piece instanceof Pawn && piece.getColor().equals(color)) {
                    temp += piece.getScore();
                }
            }
            result += temp == 1 ? 1.0 : temp / 2;
        }
        return result;
    }

    // TODO: Board에서 처리해야할 책임이 존재하는가?
    public String printRow(int row) {
        var sb = new StringBuilder();
        for (char col : COLS) {
            sb.append(table.get(Location.of(row, col)));
        }
        return StringUtils.appendNewLine(sb.toString());
    }

    // TODO: Board에서 처리해야할 책임이 존재하는가?
    public String print() {
        var sb = new StringBuilder();
        for (int i = ROWS.length - 1; i >= 0; i--) {
            sb.append(printRow(ROWS[i]));
        }
        return sb.toString();
    }

}
