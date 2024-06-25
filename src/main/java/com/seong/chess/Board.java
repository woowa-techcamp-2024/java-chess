package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;

import com.seong.chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BLACK_PIECE_LINE = 0;
    private static final int BLACK_PAWN_LINE = 1;
    private static final int WHITE_PAWN_LINE = 6;
    private static final int WHITE_PIECE_LINE = 7;
    private static final int BOARD_LENGTH = 8;

    private final List<Column> columns = new ArrayList<>();

    public void initialize() {
        columns.clear();
        initializeBlackPieces();
        initializeBlank();
        initializeWhitePieces();
    }

    private void initializeBlackPieces() {
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 0), Piece.createBlackRook()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 1), Piece.createBlackKnight()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 2), Piece.createBlackBishop()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 3), Piece.createBlackQueen()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 4), Piece.createBlackKing()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 5), Piece.createBlackBishop()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 6), Piece.createBlackKnight()));
        columns.add(new Column(new Position(BLACK_PIECE_LINE, 7), Piece.createBlackRook()));
        for (int i = 0; i < BOARD_LENGTH; i++) {
            columns.add(new Column(new Position(BLACK_PAWN_LINE, i), Piece.createBlackPawn()));
        }
    }

    private void initializeBlank() {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                columns.add(new Column(new Position(i, j)));
            }
        }
    }

    private void initializeWhitePieces() {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            columns.add(new Column(new Position(WHITE_PAWN_LINE, i), Piece.createWhitePawn()));
        }
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 0), Piece.createWhiteRook()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 1), Piece.createWhiteKnight()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 2), Piece.createWhiteBishop()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 3), Piece.createWhiteQueen()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 4), Piece.createWhiteKing()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 5), Piece.createWhiteBishop()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 6), Piece.createWhiteKnight()));
        columns.add(new Column(new Position(WHITE_PIECE_LINE, 7), Piece.createWhiteRook()));
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            StringBuilder inner = new StringBuilder();
            for (int j = 0; j < BOARD_LENGTH; j++) {
                inner.append(columns.get(i * BOARD_LENGTH + j).getRepresentation());
            }
            sb.append(appendNewLine(inner.toString()));
        }
        return sb.toString();
    }

    public int pieceCount() {
        return (int) columns.stream()
                .filter(Column::hasPiece)
                .count();
    }
}
