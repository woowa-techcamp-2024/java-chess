package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceFactory;

public class Board {

    private final int BOARD_SIZE = 8;
    private final int MAX_PIECES = 32;

    private final List<Piece> pieces = new ArrayList<>();
    private final char[][] board;
    private final Point[][] points;
    private final Map<Point, Piece> pieceMap = new HashMap<>();

    public void add(Piece piece) {
        if (pieces.size() > MAX_PIECES) {
            throw new IllegalArgumentException("한 보드판에는 최대 32개의 말이 존재할 수 있습니다.");
        }
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public void initialize() {
        // 보드판에 흰색 폰 8개, 검은색 폰 8개를 놓도록 초기화하는 메서드
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
                points[i][j] = new Point(i, j);
            }
        }
        addPieceToBoard();
    }

    private void addPieceToBoard() {
        initBlackPiece();
        initWhitePiece();
    }

    private void initBlackPiece() {
        // 폰 8개, 루크2개, 나이트 2개, 비숍2개, 킹1개, 퀸 1개
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece blackPawn = PieceFactory.createBlackPawn();
            Point point = points[1][i];
            pieceMap.put(point, blackPawn);
            board[1][i] = blackPawn.getRepresentation().charAt(0);
        }

        List<Piece> blackPiecesExceptPawn = new ArrayList<>();
        blackPiecesExceptPawn.add(PieceFactory.createBlackRook());
        blackPiecesExceptPawn.add(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.add(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.add(PieceFactory.createBlackQueen());
        blackPiecesExceptPawn.add(PieceFactory.createBlackKing());
        blackPiecesExceptPawn.add(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.add(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.add(PieceFactory.createBlackRook());

        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = blackPiecesExceptPawn.get(i);
            Point point = points[0][i];
            pieceMap.put(point, piece);
            board[0][i] = piece.getRepresentation().charAt(0);
        }
    }

    private void initWhitePiece() {
        // 폰 8개, 루크2개, 나이트 2개, 비숍2개, 킹1개, 퀸 1개
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece blackPawn = PieceFactory.createWhitePawn();
            Point point = points[6][i];
            pieceMap.put(point, blackPawn);
            board[6][i] = blackPawn.getRepresentation().charAt(0);
        }

        List<Piece> whitePieceExceptPawn = new ArrayList<>();
        whitePieceExceptPawn.add(PieceFactory.createWhiteRook());
        whitePieceExceptPawn.add(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.add(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.add(PieceFactory.createWhiteQueen());
        whitePieceExceptPawn.add(PieceFactory.createWhiteKing());
        whitePieceExceptPawn.add(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.add(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.add(PieceFactory.createWhiteRook());

        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = whitePieceExceptPawn.get(i);
            Point point = points[7][i];
            pieceMap.put(point, piece);
            board[7][i] = piece.getRepresentation().charAt(0);
        }
    }

    public void print() {
        System.out.println(showBoard());
    }

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.points = new Point[BOARD_SIZE][BOARD_SIZE];
    }

    public int pieceCount() {
        return (int) pieceMap.values().size();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            sb.append(appendNewLine(String.valueOf(row)));
        }
        return sb.toString();
    }
}
