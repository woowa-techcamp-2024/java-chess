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
        fillPieces(Color.WHITE);
        fillBlank(3, 4, 5, 6);
        fillPieces(Color.BLACK);
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

    private void addPiece(Piece piece, Location location) {
        piece.moveLocation(location);
        board[location.getX() - 1][location.getY()] = piece;
    }

    private void fillPawn(int row, Color color) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            var pawn = switch (color) {
                case WHITE -> Piece.createWhitePawn();
                case BLACK -> Piece.createBlackPawn();
            };
            addPiece(pawn, Location.of(row, (char) ('a' + i)));
        }
    }

    private void fillBlank(int... rows) {
        for (int row : rows) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                board[row - 1][i] = Piece.getBlank();
            }
        }
    }

    private void fillPieces(Color color) {
        switch (color) {
            case WHITE -> {
                addPiece(Piece.createWhiteRook(), Location.of(1, 'a'));
                addPiece(Piece.createWhiteKnight(), Location.of(1, 'b'));
                addPiece(Piece.createWhiteBishop(), Location.of(1, 'c'));
                addPiece(Piece.createWhiteQueen(), Location.of(1, 'd'));
                addPiece(Piece.createWhiteKing(), Location.of(1, 'e'));
                addPiece(Piece.createWhiteBishop(), Location.of(1, 'f'));
                addPiece(Piece.createWhiteKnight(), Location.of(1, 'g'));
                addPiece(Piece.createWhiteRook(), Location.of(1, 'h'));
                fillPawn(2, color);
            }
            case BLACK -> {
                addPiece(Piece.createBlackRook(), Location.of(8, 'a'));
                addPiece(Piece.createBlackKnight(), Location.of(8, 'b'));
                addPiece(Piece.createBlackBishop(), Location.of(8, 'c'));
                addPiece(Piece.createBlackQueen(), Location.of(8, 'd'));
                addPiece(Piece.createBlackKing(), Location.of(8, 'e'));
                addPiece(Piece.createBlackBishop(), Location.of(8, 'f'));
                addPiece(Piece.createBlackKnight(), Location.of(8, 'g'));
                addPiece(Piece.createBlackRook(), Location.of(8, 'h'));
                fillPawn(7, color);
            }
        }
    }

}
