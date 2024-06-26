package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.utils.StringUtils.NEWLINE;

public class Board {

    private final List<Rank> ranks;
    private int pieceCount;

    private static final int BOARD_WIDTH = 8;
    private static final int RANK_HEIGHT = 8;
    private static final int INITIAL_PIECE_COUNT = 32;
    private static final int BLANK_ROW_START = 2;
    private static final int BLANK_ROW_END = 6;
    private static final int WIDTH_INDEX = 0;
    private static final int RANK_INDEX = 1;

    /*
    * 테스트에서만 사용하는 초기화 메서드
    * */
    protected void initializeEmpty() {
        ranks.clear();
        initializeEmtpyRows(0, RANK_HEIGHT);
        pieceCount = 0;
    }

    private void initialize() {
        pieceCount = INITIAL_PIECE_COUNT;
        initializeBlackFirstRow();
        initializeBlackPawns();
        initializeEmtpyRows(BLANK_ROW_START, BLANK_ROW_END);
        initializeWhitePawns();
        initializeWhiteFirstRow();
        validateRankHeight();
    }

    private void validateRankHeight() {
        if(ranks.size() != RANK_HEIGHT) {
            throw new IllegalArgumentException("한 보드는 8개의 랭크를 가집니다.");
        }
    }

    private void initializeEmtpyRows(int start, int end) {
        IntStream.range(start, end).forEach(i ->
                ranks.add(Rank.initializeRank(IntStream.range(0, BOARD_WIDTH)
                        .mapToObj(c -> Piece.createBlank())
                        .collect(Collectors.toCollection(ArrayList::new)))));
    }

    private void initializeBlackFirstRow() {
        List<Piece> firstRow = List.of(
                Piece.createBlackRook(),
                Piece.createBlackKnight(),
                Piece.createBlackBishop(),
                Piece.createBlackQueen(),
                Piece.createBlackKing(),
                Piece.createBlackBishop(),
                Piece.createBlackKnight(),
                Piece.createBlackRook());

        ranks.add(Rank.initializeRank(firstRow));
    }

    private void initializeWhiteFirstRow() {
        List<Piece> lastRow = List.of(
                Piece.createWhiteRook(),
                Piece.createWhiteKnight(),
                Piece.createWhiteBishop(),
                Piece.createWhiteQueen(),
                Piece.createWhiteKing(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight(),
                Piece.createWhiteRook());

        ranks.add(Rank.initializeRank(lastRow));
    }

    private String print() {
        return ranks.stream()
                .map(Rank::printRank)
                .collect(Collectors.joining(NEWLINE));
    }

    public int getPieceCount(Piece.Color color, Piece.Type type) {
        return ranks.stream().mapToInt(rank -> rank.getPieceCount(color, type)).sum();
    }

    public int getTotalPieceCount() {
        return pieceCount;
    }

    public String showBoard() {
        return print();
    }

    public void move(String position, Piece piece) {
        validateCoordinate(position);
        int widthIndex = convertWidthIndex(position.charAt(WIDTH_INDEX));
        int rankIndex = convertRankIndex(position.charAt(RANK_INDEX));

        Rank targetRank = ranks.get(rankIndex);
        targetRank.setPiece(widthIndex, piece);
    }

    public double caculcatePoint(Piece.Color color) {
        return ranks.stream()
                .mapToDouble(rank -> rank.calculateRankPoint(color))
                .sum();
    }

    protected Board() {
        ranks = new ArrayList<>();
        initialize();
    }

    private void initializeWhitePawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Piece.createWhitePawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.ranks.add(Rank.initializeRank(initializedPieces));
    }

    private void initializeBlackPawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Piece.createBlackPawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.ranks.add(Rank.initializeRank(initializedPieces));
    }


    public Piece findPiece(String coordinate) {
        validateCoordinate(coordinate);
        int index = convertWidthIndex(coordinate.charAt(WIDTH_INDEX));
        int rank = convertRankIndex(coordinate.charAt(RANK_INDEX));

        return ranks.get(rank).getPieceByIndex(index);
    }

    private void validateCoordinate(String coordinate) {
        if(coordinate == null || coordinate.isBlank()) {
            throw new IllegalArgumentException("좌표를 입력해주세요.");
        }
        if(coordinate.length() != 2) {
            throw new IllegalArgumentException("좌표는 2글자여야 합니다.");
        }
    }

    private int convertRankIndex(char rank) {
        if(rank >= 'a') {
            throw new IllegalArgumentException("좌표는 알파벳과 숫자의 순서로 이루어져야 합니다.");
        }
        int convertedRank = rank - '1';

        if(convertedRank < 0 || convertedRank >= RANK_HEIGHT) {
            throw new IllegalArgumentException("범위를 넘어선 좌표입니다.");
        }
        return RANK_HEIGHT - convertedRank - 1;
    }

    private int convertWidthIndex(char width) {
        if(width < 'a') {
            throw new IllegalArgumentException("좌표는 알파벳과 숫자의 순서로 이루어져야 합니다.");
        }

        int convertedWidth = width - 'a';

        if(convertedWidth < 0 || convertedWidth >= BOARD_WIDTH) {
            throw new IllegalArgumentException("범위를 넘어선 좌표입니다.");
        }
        return convertedWidth;
    }


}
