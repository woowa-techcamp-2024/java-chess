package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Color;
import com.woowatechcamp.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import static com.woowatechcamp.utils.StringUtils.appendNewLine;

public class Board {
    private static final char EMPTY_REPRESENTATION = '.';
    private final List<Pawn> whitePawns;
    private final List<Pawn> blackPawns;

    public Board() {
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(Color.WHITE));
            blackPawns.add(new Pawn(Color.BLACK));
        }
    }

    public void add(Pawn pawn) {
        if (pawn.getColor() == Color.WHITE) {
            whitePawns.add(pawn);
            return;
        }
        blackPawns.add(pawn);
    }

    public int size() {
        return whitePawns.size() + blackPawns.size();
    }

    public String getWhitePawnsResult() {
        return piecesToString(whitePawns);
    }

    public String getBlackPawnsResult() {
        return piecesToString(blackPawns);
    }

    private String piecesToString(List<Pawn> pieces) {
        StringBuilder result = new StringBuilder();
        pieces.forEach(pawn -> result.append(pawn.toString()));
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
