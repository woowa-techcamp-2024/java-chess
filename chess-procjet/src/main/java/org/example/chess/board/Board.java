package org.example.chess.board;

import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Color;
import org.example.chess.pieces.Piece;

public class Board {

    private final int BOARD_SIZE = 8;
    private final int MAX_PAWNS = 32;
    private final int BLACK_INIT_ROW = 1;
    private final int WHITE_INIT_ROW = 6;

    private final List<Piece> pieces = new ArrayList<>();
    private final List<Piece> blackPawnsResult = new ArrayList<>();
    private final List<Piece> whitePawnsResult = new ArrayList<>();
    private final char[][] board;

    public void add(Piece piece) {
        if (pieces.size() > MAX_PAWNS) {
            throw new IllegalArgumentException("한 보드판에는 최대 32개의 말이 존재할 수 있습니다.");
        }
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(int i) {
        if (i < 0 || i >= pieces.size()) {
            throw new IllegalArgumentException("존재하지 않는 말입니다.");
        }
        return pieces.get(i);
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
            Piece blackPiece = new Piece(Color.BLACK, Piece.BLACK_REPRESENTATION);
            blackPawnsResult.add(blackPiece);
            board[BLACK_INIT_ROW][i] = blackPiece.getRepresentation();
        }
    }

    private void initWhitePawnsResult() {
        for (int i = 0; i < 8; i++) {
            Piece whitePiece = new Piece(Color.WHITE, Piece.WHITE_REPRESENTATION);
            whitePawnsResult.add(whitePiece);
            board[WHITE_INIT_ROW][i] = whitePiece.getRepresentation();
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
        for (Piece piece : blackPawnsResult) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : whitePawnsResult) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
    }
}
