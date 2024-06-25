package org.example.chess;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;

public class Board {

    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();
    private final int BOARD_SIZE = 8;

    public void initialize() {
        addPawn();
    }

    private void addPawn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            addBlackPawn();
            addWhitePawn();
        }
    }

    private void addWhitePawn() {
        whitePieces.add(Piece.createWhitePawn());
    }

    private void addBlackPawn() {
        blackPieces.add(Piece.createBlackPawn());
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : whitePieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : blackPieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }


    public String print() {
        StringBuilder sb = new StringBuilder();
        String defaultLine = ".".repeat(BOARD_SIZE);
        String defaultLineWithNewLine = appendNewLine(defaultLine);

        sb.append(defaultLineWithNewLine);
        sb.append(appendNewLine(getWhitePawnsResult()));

        sb.append(defaultLineWithNewLine);
        sb.append(defaultLineWithNewLine);
        sb.append(defaultLineWithNewLine);
        sb.append(defaultLineWithNewLine);

        sb.append(appendNewLine(getBlackPawnsResult()));
        sb.append(defaultLineWithNewLine);

        return sb.toString();
    }

    public int size() {
        return whitePieces.size();
    }

    public Piece findPawn(int id) {
        // index를 넘어가는 경우 에러를 발생시키는 코드 추가하기
        return whitePieces.get(id);
    }
}
