package org.example.chess;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;

public class Board {

    private class OneColumn {

        // 자신의 col과 row길이를 생성시 받는다.
        private final char colIndex;
        private final int rowLen;
        private final List<Piece> pieces;

        public OneColumn(char colIndex, int rowLen) {
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

    private final List<OneColumn> oneColumns;

    private final int BOARD_SIZE = 8;
    //전연변수 처리?
    private final char startChar = 'a';

    public Board() {
        oneColumns = createSizeList(BOARD_SIZE);
    }

    public void move(Position position, Piece piece) {
        oneColumns.get(position.getColIdx()).modifyPiece(piece, position.getRow());
    }

    private List<OneColumn> createSizeList(int boardSize) {
        List<OneColumn> ret = new ArrayList<>();
        for (char i = startChar; i < startChar + boardSize; i++) {
            ret.add(new OneColumn(i, boardSize));
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

        for (OneColumn oneColumn : oneColumns) {
            oneColumn.modifyPiece(Piece.createWhitePawn(), whitePawnRow);
        }

        oneColumns.get(0).modifyPiece(Piece.createWhiteRook(), whitePieceRow);
        oneColumns.get(1).modifyPiece(Piece.createWhiteKnight(), whitePieceRow);
        oneColumns.get(2).modifyPiece(Piece.createWhiteBishop(), whitePieceRow);
        oneColumns.get(3).modifyPiece(Piece.createWhiteQueen(), whitePieceRow);
        oneColumns.get(4).modifyPiece(Piece.createWhiteKing(), whitePieceRow);
        oneColumns.get(5).modifyPiece(Piece.createWhiteBishop(), whitePieceRow);
        oneColumns.get(6).modifyPiece(Piece.createWhiteKnight(), whitePieceRow);
        oneColumns.get(7).modifyPiece(Piece.createWhiteRook(), whitePieceRow);
    }

    private void placeBlackPiece() {
        final int blackPieceRow = 8; // Assuming the black pieces are placed at row 7
        final int blackPawnRow = 7;

        // Place black pawns
        oneColumns.forEach(
            oneColumn -> oneColumn.modifyPiece(Piece.createBlackPawn(), blackPawnRow));

        // Place other black pieces
        oneColumns.get(0).modifyPiece(Piece.createBlackRook(), blackPieceRow);
        oneColumns.get(1).modifyPiece(Piece.createBlackKnight(), blackPieceRow);
        oneColumns.get(2).modifyPiece(Piece.createBlackBishop(), blackPieceRow);
        oneColumns.get(3).modifyPiece(Piece.createBlackQueen(), blackPieceRow);
        oneColumns.get(4).modifyPiece(Piece.createBlackKing(), blackPieceRow);
        oneColumns.get(5).modifyPiece(Piece.createBlackBishop(), blackPieceRow);
        oneColumns.get(6).modifyPiece(Piece.createBlackKnight(), blackPieceRow);
        oneColumns.get(7).modifyPiece(Piece.createBlackRook(), blackPieceRow);
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
        for (OneColumn oneColumn : oneColumns) {
            sb.append(oneColumn.getPiece(row).getRepresentation());
        }
        return sb.toString();
    }

    public int nonEmptyPiece() {
        return oneColumns.stream()
            .mapToInt(oneColumn -> (int) oneColumn.pieces.stream()
                .filter(piece -> piece.isBlack() || piece.isWhite())
                .count())
            .sum();
    }

    public int countByQuery(Color color, Type type) {
        return oneColumns.stream()
            .mapToInt(oneColumn -> (int) oneColumn.pieces.stream()
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(type))
                .count())
            .sum();
    }

    public Piece findPiece(Position position) {

        return oneColumns.get(position.getColIdx()).getPiece(position.getRow());
    }

    public double caculcatePoint(Color color) {
        return oneColumns.stream()
            .mapToDouble(oneColumn -> oneColumn.pieces.stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum())
            .sum();
    }
}
