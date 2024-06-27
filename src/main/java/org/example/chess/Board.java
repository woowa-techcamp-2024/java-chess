package org.example.chess;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;

public class Board {

    private class Column {

        // 자신의 col과 row길이를 생성시 받는다.
        private final char colIndex;
        private final int rowLen;
        private final List<Piece> pieces;

        public Column(char colIndex, int rowLen) {
            this.colIndex = colIndex;
            this.rowLen = rowLen;
            this.pieces = createSizeList(rowLen);
        }

        private List<Piece> createSizeList(int boardSize) {
            List<Piece> ret = new ArrayList<>();
            for (int i = 0; i < boardSize; i++) {
                ret.add(Piece.createNoColorPiece());
            }
            return ret;
        }

        public void modifyPiece(Piece piece, int rowNumber) {
            pieces.set(rowNumber - 1, piece);
        }

        public Piece getPiece(int index) {
            // 1 을 빼줘야한다.
            return pieces.get(index - 1);
        }
    }

    private final List<Column> columns;

    private final int BOARD_SIZE = 8;
    //전연변수 처리?
    private final char startChar = 'a';

    public Board() {
        columns = createSizeList(BOARD_SIZE);
    }

    public void move(Position position, Piece piece) {
        columns.get(position.getColIdx()).modifyPiece(piece, position.getRow());
    }

    private List<Column> createSizeList(int boardSize) {
        List<Column> ret = new ArrayList<>();
        for (char i = startChar; i < startChar + boardSize; i++) {
            ret.add(new Column(i, boardSize));
        }
        return ret;
    }

    public void initialize() {
        initColoredPiece();
    }

    private void initColoredPiece() {
        placeBlackPiece();
        placeWhitePiece();
    }

    private void placeWhitePiece() {
        final int whitePieceRow = 1;
        final int whitePawnRow = 2;

        for (Column column : columns) {
            column.modifyPiece(Piece.createWhitePawn(), whitePawnRow);
        }

        columns.get(0).modifyPiece(Piece.createWhiteRook(), whitePieceRow);
        columns.get(1).modifyPiece(Piece.createWhiteKnight(), whitePieceRow);
        columns.get(2).modifyPiece(Piece.createWhiteBishop(), whitePieceRow);
        columns.get(3).modifyPiece(Piece.createWhiteQueen(), whitePieceRow);
        columns.get(4).modifyPiece(Piece.createWhiteKing(), whitePieceRow);
        columns.get(5).modifyPiece(Piece.createWhiteBishop(), whitePieceRow);
        columns.get(6).modifyPiece(Piece.createWhiteKnight(), whitePieceRow);
        columns.get(7).modifyPiece(Piece.createWhiteRook(), whitePieceRow);
    }

    private void placeBlackPiece() {
        final int blackPieceRow = 8; // Assuming the black pieces are placed at row 7
        final int blackPawnRow = 7;

        // Place black pawns
        columns.forEach(
            column -> column.modifyPiece(Piece.createBlackPawn(), blackPawnRow));

        // Place other black pieces
        columns.get(0).modifyPiece(Piece.createBlackRook(), blackPieceRow);
        columns.get(1).modifyPiece(Piece.createBlackKnight(), blackPieceRow);
        columns.get(2).modifyPiece(Piece.createBlackBishop(), blackPieceRow);
        columns.get(3).modifyPiece(Piece.createBlackQueen(), blackPieceRow);
        columns.get(4).modifyPiece(Piece.createBlackKing(), blackPieceRow);
        columns.get(5).modifyPiece(Piece.createBlackBishop(), blackPieceRow);
        columns.get(6).modifyPiece(Piece.createBlackKnight(), blackPieceRow);
        columns.get(7).modifyPiece(Piece.createBlackRook(), blackPieceRow);
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        // row를 구하면 각 col마다 해당 row의 piece를 가져와서 출력한다.
        for (int i = BOARD_SIZE; i > 0; i--) {
            sb.append(appendNewLine(getRow(i)));
        }
        return sb.toString();
    }

    private String getRow(int row) {
        StringBuilder sb = new StringBuilder();
        for (Column column : columns) {
            sb.append(column.getPiece(row).getRepresentation());
        }
        return sb.toString();
    }

    public int nonEmptyPiece() {
        return columns.stream()
            .mapToInt(column -> (int) column.pieces.stream()
                .filter(piece -> piece.isBlack() || piece.isWhite())
                .count())
            .sum();
    }

    public int countByQuery(Color color, Type type) {
        return columns.stream()
            .mapToInt(column -> (int) column.pieces.stream()
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(type))
                .count())
            .sum();
    }

    public Piece findPiece(Position position) {
        return columns.get(position.getColIdx()).getPiece(position.getRow());
    }

    public double calculatePoint(Color color) {
        return columns.stream()
            .mapToDouble(column -> calculateColumnPoints(column, color))
            .sum();
    }


    private double calculateColumnPoints(Column column, Color color) {
        double pawnPoints = calculatePawnPoints(column, color);
        double otherPoints = calculateOtherPoints(column, color);
        return pawnPoints + otherPoints;
    }


    private double calculatePawnPoints(Column column, Color color) {
        long pawnCount = column.pieces.stream()
            .filter(piece -> piece.isSameColor(color) && piece.isPawn())
            .count();

        return pawnCount <= 1 ? pawnCount : pawnCount * 0.5;
    }

    private double calculateOtherPoints(Column column, Color color) {
        return column.pieces.stream()
            .filter(piece -> piece.isSameColor(color) && !piece.isPawn())
            .mapToDouble(Piece::getPoint)
            .sum();
    }

    public List<Piece> sortByPoint(Color color) {
        return columns.stream()
            .flatMap(column -> column.pieces.stream())
            .filter(piece -> piece.isSameColor(color))
            .sorted((p1, p2) -> Double.compare(p2.getPoint(), p1.getPoint()))
            .toList();
    }
}
