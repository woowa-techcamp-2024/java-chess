package chess;

import chess.pieces.Blank;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import chess.pieces.enums.Type;
import chess.pieces.values.Location;
import utils.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int[] ROWS = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final char[] COLS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    private final Map<Location, Piece> board;

    public Board() {
        board = new HashMap<>();
        for (int row : ROWS) {
            for (char col : COLS) {
                board.put(Location.of(row, col), Piece.getBlank());
            }
        }
    }

    public void initialize() {
        fillPieces(1, Color.WHITE);
        fillPawns(2, Color.WHITE);
        fillBlanks(3, 4, 5, 6);
        fillPawns(7, Color.BLACK);
        fillPieces(8, Color.BLACK);
    }

    public void addPiece(Location location, Piece piece) {
        board.put(location, piece);
    }

    public int countPiecesByTypeAndColor(Type type, Color color) {
        return (int) board.values().stream()
                .filter(type::isInstance)
                .filter(piece -> piece.getColor().equals(color))
                .count();
    }

    public double calculateScoreByColor(Color color) {
        var score = 0.0;
        score += board.values().stream()
                .filter(piece -> !(piece instanceof Blank) && !(piece instanceof Pawn))
                .filter(piece -> piece.getColor().equals(color))
                .mapToDouble(Piece::getScore).sum();
        score += calculatePawnScore(color);
        return score;
    }

    public List<Piece> getPiecesByColor(Color color) {
        return getPiecesByColor(color, false);
    }

    public List<Piece> getPiecesByColor(Color color, boolean reverse) {
        return board.values().stream()
                .filter(piece -> !(piece instanceof Blank))
                .filter(piece -> piece.getColor().equals(color))
                .sorted(reverse ? Comparator.comparingDouble(Piece::getScore) : Comparator.comparingDouble(Piece::getScore).reversed())
                .toList();
    }

    public Piece getPiece(String locationStr) {
        var location = Location.from(locationStr);
        return board.get(location);
    }

    public int size() {
        return (int) board.values().stream().filter(piece -> !(piece instanceof Blank)).count();
    }

    public String printRow(int row) {
        var sb = new StringBuilder();
        for (char col : COLS) {
            sb.append(board.get(Location.of(row, col)));
        }
        return StringUtils.appendNewLine(sb.toString());
    }

    public String print() {
        var sb = new StringBuilder();
        for (int i = ROWS.length - 1; i >= 0; i--) {
            sb.append(printRow(ROWS[i]));
        }
        return sb.toString();
    }

    private void fillPawns(int row, Color color) {
        for (char col : COLS) {
            board.put(Location.of(row, col), Piece.createPawn(color));
        }
    }

    private void fillBlanks(int... rows) {
        for (int row : rows) {
            for (char col : COLS) {
                board.put(Location.of(row, col), Piece.getBlank());
            }
        }
    }

    private void fillPieces(int row, Color color) {
        addPiece(Location.of(row, 'a'), Piece.createRook(color));
        addPiece(Location.of(row, 'b'), Piece.createKnight(color));
        addPiece(Location.of(row, 'c'), Piece.createBishop(color));
        addPiece(Location.of(row, 'd'), Piece.createQueen(color));
        addPiece(Location.of(row, 'e'), Piece.createKing(color));
        addPiece(Location.of(row, 'f'), Piece.createBishop(color));
        addPiece(Location.of(row, 'g'), Piece.createKnight(color));
        addPiece(Location.of(row, 'h'), Piece.createRook(color));
    }

    private double calculatePawnScore(Color color) {
        var result = 0.0;
        for (char col : COLS) {
            var temp = 0.0;
            for (int row : ROWS) {
                var piece = board.get(Location.of(row, col));
                if (piece instanceof Pawn && piece.getColor().equals(color)) {
                    temp += piece.getScore();
                }
            }
            result += temp == 1 ? 1.0 : temp / 2;
        }
        return result;
    }

}
