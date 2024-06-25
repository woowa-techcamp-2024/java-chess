package org.example.chess.board;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Color;
import org.example.chess.pieces.Pawn;
import org.example.utils.StringUtils;

public class Board {

    private final int BOARD_SIZE = 8;
    private final int MAX_PAWNS = 32;
    private final int BLACK_INIT_ROW = 1;
    private final int WHITE_INIT_ROW = 6;

    private final List<Pawn> pawns = new ArrayList<>();
    private final List<Pawn> blackPawnsResult = new ArrayList<>();
    private final List<Pawn> whitePawnsResult = new ArrayList<>();
    private final char[][] board;

    public void add(Pawn pawn) {
        if (pawns.size() > MAX_PAWNS) {
            throw new IllegalArgumentException("한 보드판에는 최대 32개의 말이 존재할 수 있습니다.");
        }
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int i) {
        if (i < 0 || i >= pawns.size()) {
            throw new IllegalArgumentException("존재하지 않는 말입니다.");
        }
        return pawns.get(i);
    }

    public void initialize() {
        // 보드판에 흰색 폰 8개, 검은색 폰 8개를 놓도록 초기화하는 메서드
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }
        addPawnsToBoard();
    }

    private void addPawnsToBoard() {
        initBlackPawnsResult();
        initWhitePawnsResult();
    }

    private void initBlackPawnsResult() {
        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn(Color.BLACK, Pawn.BLACK_REPRESENTATION);
            blackPawnsResult.add(blackPawn);
            board[BLACK_INIT_ROW][i] = blackPawn.getRepresentation();
        }
    }

    private void initWhitePawnsResult() {
        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = new Pawn(Color.WHITE, Pawn.WHITE_REPRESENTATION);
            whitePawnsResult.add(whitePawn);
            board[WHITE_INIT_ROW][i] = whitePawn.getRepresentation();
        }
    }

    public void print() {
        // 현재 보드판의 상태를 출력해주는 메서드.
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            sb.append(row);
            appendNewLine(sb);
        }
        System.out.println(sb.toString());
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Pawn pawn : blackPawnsResult) {
            sb.append(pawn.getRepresentation());
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Pawn pawn : whitePawnsResult) {
            sb.append(pawn.getRepresentation());
        }
        return sb.toString();
    }

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
    }
}
