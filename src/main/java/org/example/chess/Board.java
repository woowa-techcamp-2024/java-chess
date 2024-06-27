package org.example.chess;

import static org.example.pieces.PieceFactory.*;
import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;
import org.example.pieces.PieceFactory;

public class Board {

    private final List<Column> columns;

    private final int BOARD_SIZE = 8;
    //전연변수 처리?
    private final char startChar = 'a';

    public Board() {
        columns = createSizeList(BOARD_SIZE);
    }

    public void move(Position start, Position end) {
        if(start.equals(end)) {
            throw new IllegalArgumentException("같은 지점으로 이동할 수 없습니다.");
        }

        Piece startPiece = findPiece(start);

        setPiece(end, startPiece);
        setPiece(start, createNoColorPiece());
    }

    public void setPiece(Position position, Piece piece) {
        columns.get(position.getColIdx()).setPiece(piece, position.getRow());
    }

    private List<Column> createSizeList(int boardSize) {
        List<Column> ret = new ArrayList<>();
        for (char i = startChar; i < startChar + boardSize; i++) {
            ret.add(new Column(i, boardSize));
        }
        return ret;
    }

    //게임 설정
    void initialize() {
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
            column.setPiece(createWhitePawn(), whitePawnRow);
        }

        columns.get(0).setPiece(createWhiteRook(), whitePieceRow);
        columns.get(1).setPiece(createWhiteKnight(), whitePieceRow);
        columns.get(2).setPiece(createWhiteBishop(), whitePieceRow);
        columns.get(3).setPiece(createWhiteQueen(), whitePieceRow);
        columns.get(4).setPiece(createWhiteKing(), whitePieceRow);
        columns.get(5).setPiece(createWhiteBishop(), whitePieceRow);
        columns.get(6).setPiece(createWhiteKnight(), whitePieceRow);
        columns.get(7).setPiece(createWhiteRook(), whitePieceRow);
    }

    private void placeBlackPiece() {
        final int blackPieceRow = 8; // Assuming the black pieces are placed at row 7
        final int blackPawnRow = 7;

        // Place black pawns
        columns.forEach(
            column -> column.setPiece(createBlackPawn(), blackPawnRow));

        // Place other black pieces
        columns.get(0).setPiece(createBlackRook(), blackPieceRow);
        columns.get(1).setPiece(createBlackKnight(), blackPieceRow);
        columns.get(2).setPiece(createBlackBishop(), blackPieceRow);
        columns.get(3).setPiece(createBlackQueen(), blackPieceRow);
        columns.get(4).setPiece(createBlackKing(), blackPieceRow);
        columns.get(5).setPiece(createBlackBishop(), blackPieceRow);
        columns.get(6).setPiece(createBlackKnight(), blackPieceRow);
        columns.get(7).setPiece(createBlackRook(), blackPieceRow);
    }
    // 게임 설정

    public int nonEmptyPiece() {
        return columns.stream()
            .mapToInt(column -> (int) column.getPieces().stream()
                .filter(piece -> piece.isBlack() || piece.isWhite())
                .count())
            .sum();
    }

    public int countByQuery(Color color, Type type) {
        return columns.stream()
            .mapToInt(column -> (int) column.getPieces().stream()
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(type))
                .count())
            .sum();
    }

    public Piece findPiece(Position position) {
        return columns.get(position.getColIdx()).getPiece(position.getRow());
    }

    public Piece findPiece(String position) {
        return findPiece(new Position(position));
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Piece> sortByPoint(Color color) {
        return columns.stream()
            .flatMap(column -> column.getPieces().stream())
            .filter(piece -> piece.isSameColor(color))
            .sorted((p1, p2) -> Double.compare(p2.getPoint(), p1.getPoint()))
            .toList();
    }
}
