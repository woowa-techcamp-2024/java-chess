package com.woopaca.javachess.chess;

import com.woopaca.javachess.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.woopaca.javachess.chess.utils.StringUtils.appendNewLine;

public class Board {

    public static final int BOARD_SIZE = 8;
    public static final int WHITE_PAWNS_ROW = 6;
    public static final int BLACK_PAWNS_ROW = 1;

    private final List<Piece>[] pawns = new ArrayList[BOARD_SIZE];

    {
        for (int i = 0; i < BOARD_SIZE; i++) {
            pawns[i] = new ArrayList<>(BOARD_SIZE);
            for (int j = 0; j < BOARD_SIZE; j++) {
                pawns[i].add(null);
            }
        }
    }

    public void add(Piece piece) {
        // FIXME 요구사항에 맞게 수정하기
        pawns[0].add(piece);
    }

    public int size() {
        // FIXME 요구사항에 맞게 수정하기
        int size = 0;
        for (List<Piece> piece : pawns) {
            size += piece.size();
        }
        return size;
    }

    public Piece findPawn(int index) {
        // FIXME 요구사항에 맞게 수정하기
        return null;
    }

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            addPawn(Piece.WHITE_COLOR, WHITE_PAWNS_ROW);
            addPawn(Piece.BLACK_COLOR, BLACK_PAWNS_ROW);
        }
    }

    private void addPawn(String color, int row) {
        /*Piece piece = new Piece(color);
        if (pawns[row].size() >= BOARD_SIZE) {
            pawns[row].remove(0);
        }
        pawns[row].add(piece);*/
    }

    public String getWhitePawnsResult() {
        List<Piece> whitePieces = pawns[WHITE_PAWNS_ROW];
        return generatePawnsResult(whitePieces);
    }

    public String getBlackPawnsResult() {
        List<Piece> blackPieces = pawns[BLACK_PAWNS_ROW];
        return generatePawnsResult(blackPieces);
    }

    public String print() {
        StringBuilder boardResult = new StringBuilder();
        for (List<Piece> row : pawns) {
            String result = generatePawnsResult(row);
            boardResult.append(appendNewLine(result));
        }
        return boardResult.toString();
    }

    private String generatePawnsResult(List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> piece == null ? "." : String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

}
