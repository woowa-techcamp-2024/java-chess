package chess;

import chess.pieces.Blank;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import chess.pieces.values.Location;
import utils.StringUtils;

import java.util.Arrays;

public class Board {

    private static final int BOARD_SIZE = 8;

    private final Piece[][] board;

    public Board() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        for (int i = 1; i <= board.length; i++) {
            fillBlank(i);
        }
    }

    public void initialize() {
        fillBlank(1);
        fillPawn(2, Color.WHITE);
        fillBlank(3);
        fillBlank(4);
        fillBlank(5);
        fillBlank(6);
        fillPawn(7, Color.BLACK);
        fillBlank(8);
    }

    public void addPiece(Piece piece, Location location) {
        board[location.getX() - 1][location.getY()] = piece;
        piece.moveLocation(location);
    }

    public Piece getPiece(Location location) {
        return board[location.getX() - 1][location.getY()];
    }

    public int size() {
        var result = 0;
        for (Piece[] pieces : board) {
            result += (int) Arrays.stream(pieces).filter(piece -> !(piece instanceof Blank)).count();
        }
        return result;
    }

    public String printRow(int row) {
        var sb = new StringBuilder();
        for (Piece piece : board[row - 1]) {
            sb.append(piece);
        }
        return StringUtils.appendNewLine(sb.toString());
    }

    public String print() {
        var sb = new StringBuilder();
        for (int i = board.length; i > 0; i--) {
            sb.append(printRow(i));
        }
        return sb.toString();
    }

    private void fillPawn(int row, Color color) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            var pawn = switch (color) {
                case WHITE -> Piece.createWhitePawn();
                case BLACK -> Piece.createBlackPawn();
            };
            board[row - 1][i] = pawn;
            pawn.moveLocation(Location.of(row, (char) ('a' + i)));
        }
    }

    private void fillBlank(int row) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[row - 1][i] = new Blank();
        }
    }

}
