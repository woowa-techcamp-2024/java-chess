package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Color;
import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.Type;

import java.util.ArrayList;
import java.util.List;
import static com.woowatechcamp.utils.StringUtils.appendNewLine;

public class Board {
    private static final char EMPTY_REPRESENTATION = '.';
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    public Board() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            whitePieces.add(Piece.createWhitePawn());
            blackPieces.add(Piece.createBlackPawn());
        }
    }

    public void add(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            whitePieces.add(piece);
            return;
        }
        blackPieces.add(piece);
    }

    public int size() {
        return whitePieces.size() + blackPieces.size();
    }

    public String getWhitePawnsResult() {
        return piecesToString(whitePieces);
    }

    public String getBlackPawnsResult() {
        return piecesToString(blackPieces);
    }

    private String piecesToString(List<Piece> pieces) {
        StringBuilder result = new StringBuilder();
        pieces.forEach(piece -> result.append(piece.toString()));
        return result.toString();
    }

    public void print() {
        StringBuilder result = new StringBuilder();
        addEmptyRowRepresentation(result);
        result.append(getBlackPawnsResult());
        appendNewLine(result);
        for (int i = 0; i < 4; i++) {
            addEmptyRowRepresentation(result);
        }
        result.append(getWhitePawnsResult());
        appendNewLine(result);
        addEmptyRowRepresentation(result);
        System.out.println(result);
    }

    private void addEmptyRowRepresentation(StringBuilder result) {
        for (int i = 0; i < 8; i++) {
            result.append(EMPTY_REPRESENTATION);
        }
        appendNewLine(result);
    }
}
