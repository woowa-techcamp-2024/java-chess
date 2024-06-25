package chess;

import static utils.StringUtils.appendNewLine;

import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Board {
    public static final int N = 8;

    public static final List<Integer> BLACK_PAWN_INDICES = List.of(8, 9, 10, 11, 12, 13, 14, 15);

    public static final List<Integer> WHITE_PAWN_INDICES = List.of(48, 49, 50, 51, 52, 53, 54, 55);

    public static final List<Integer> BLACK_KNIGHT_INDICES = List.of(1, 6);

    public static final List<Integer> WHITE_KNIGHT_INDICES = List.of(57, 62);

    public static final List<Integer> BLACK_ROOK_INDICES = List.of(0, 7);

    public static final List<Integer> WHITE_ROOK_INDICES = List.of(56, 63);

    public static final List<Integer> BLACK_BISHOP_INDICES = List.of(2, 5);

    public static final List<Integer> WHITE_BISHOP_INDICES = List.of(58, 61);

    public static final int BLACK_QUEEN_INDEX = 3;

    public static final int BLACK_KING_INDEX = 4;

    public static final int WHITE_QUEEN_INDEX = 59;

    public static final int WHITE_KING_INDEX = 60;


    private List<Piece> board;

    private int pieceCount;

    public Board() {
        this.board = new ArrayList<>();
    }

    public void add(Piece piece) {
        board.add(piece);
    }

    public Piece findPiece(int index) {
        return board.get(index);
    }

    public int size() {
        return board.size();
    }

    public int pieceCount() {
        return pieceCount;
    }

    public void initialize() {
        board = new ArrayList<>(Collections.nCopies(64, null));
        initializePieces(BLACK_PAWN_INDICES, Piece::createBlackPawn);
        initializePieces(WHITE_PAWN_INDICES, Piece::createWhitePawn);
        initializePieces(BLACK_ROOK_INDICES, Piece::createBlackRook);
        initializePieces(WHITE_ROOK_INDICES, Piece::createWhiteRook);
        initializePieces(BLACK_BISHOP_INDICES, Piece::createBlackBishop);
        initializePieces(WHITE_BISHOP_INDICES, Piece::createWhiteBishop);
        initializePieces(BLACK_KNIGHT_INDICES, Piece::createBlackKnight);
        initializePieces(WHITE_KNIGHT_INDICES, Piece::createWhiteKnight);
        initializePiece(BLACK_QUEEN_INDEX, Piece::createBlackQueen);
        initializePiece(WHITE_QUEEN_INDEX, Piece::createWhiteQueen);
        initializePiece(BLACK_KING_INDEX, Piece::createBlackKing);
        initializePiece(WHITE_KING_INDEX, Piece::createWhiteKing);
    }

    private void initializePieces(List<Integer> indices, Supplier<Piece> supplier) {
        indices.forEach(i -> {
            board.set(i, supplier.get());
            pieceCount += 1;
        });
    }

    private void initializePiece(int index, Supplier<Piece> supplier) {
        board.set(index, supplier.get());
        pieceCount += 1;
    }

    public void print() {
        System.out.println(showBoard());
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < N; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < N; col++) {
                Piece piece = board.get(row * N + col);
                line.append(getRepresentation(piece));
            }
            sb.append(appendNewLine(line));
        }
        return sb.toString();
    }

    private char getRepresentation(Piece piece) {
        return piece != null ? piece.getRepresentation() : '.';
    }

    public String show(int startIndexInclusive, int endIndexInclusive) {
        StringBuilder sb = new StringBuilder();
        for (int index = startIndexInclusive; index <= endIndexInclusive; index++) {
            sb.append(getRepresentation(board.get(index)));
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        return show(WHITE_PAWN_INDICES.get(0), WHITE_PAWN_INDICES.get(WHITE_PAWN_INDICES.size() - 1));
    }

    public String getBlackPawnsResult() {
        return show(BLACK_PAWN_INDICES.get(0), BLACK_PAWN_INDICES.get(BLACK_PAWN_INDICES.size() - 1));
    }
}
