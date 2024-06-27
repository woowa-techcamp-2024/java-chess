package chess.board;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final List<Rank> ranks;

    private static final int BOARD_WIDTH = 8;
    private static final int RANK_HEIGHT = 8;
    private static final int BLANK_ROW_START = 2;
    private static final int BLANK_ROW_END = 6;


    public List<Rank> getRanks() {
        return ranks;
    }
    /*
    * 테스트에서만 사용하는 초기화 메서드
    * */
    protected void initializeEmpty() {
        ranks.clear();
        initializeEmtpyRows(0, RANK_HEIGHT);
    }

    public int getPieceCount(Piece.Color color, Piece.Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.getPieceCount(color, type))
                .sum();
    }

    public List<Piece> sortPiecesByPoint(Piece.Color color, SORT sort) {
        return ranks.stream().map(rank -> rank.getAllPieces(color))
                .flatMap(List::stream)
                .sorted(sort.getComparator())
                .toList();
    }

    public void movePiece(Coordinate from, Coordinate to) {
        Piece sourcePiece = findPiece(from);

        movePiece(to, sourcePiece);
        movePiece(from, Blank.createBlank());
    }

    public Board() {
        ranks = new ArrayList<>();
        initialize();
    }

    public Piece findPiece(Coordinate coordinate) {
        return ranks.get(coordinate.getRankIndex())
                .getPieceByIndex(coordinate.getWidthIndex());
    }

    protected void movePiece(Coordinate coordinate, Piece piece) {
        Rank targetRank = ranks.get(coordinate.getRankIndex());
        targetRank.setPiece(coordinate.getWidthIndex(), piece);
    }

    private void initializeWhitePawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Pawn.createWhitePawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.ranks.add(Rank.initializeRank(initializedPieces));
    }

    private void initializeBlackPawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Pawn.createBlackPawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.ranks.add(Rank.initializeRank(initializedPieces));
    }

    private void initialize() {
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
        IntStream.range(start, end).forEach(i -> {
                    ArrayList<Blank> collect = IntStream.range(0, BOARD_WIDTH)
                            .mapToObj(c -> Blank.createBlank())
                            .collect(Collectors.toCollection(ArrayList::new));

                    ranks.add(Rank.initializeRank(collect));
                });
    }

    private void initializeBlackFirstRow() {
        List<Piece> firstRow = List.of(
                Rook.createBlackRook(),
                Knight.createBlackKnight(),
                Bishop.createBlackBishop(),
                Queen.createBlackQueen(),
                King.createBlackKing(),
                Bishop.createBlackBishop(),
                Knight.createBlackKnight(),
                Rook.createBlackRook());

        ranks.add(Rank.initializeRank(firstRow));
    }

    private void initializeWhiteFirstRow() {
        List<Piece> lastRow = List.of(
                Rook.createWhiteRook(),
                Knight.createWhiteKnight(),
                Bishop.createWhiteBishop(),
                Queen.createWhiteQueen(),
                King.createWhiteKing(),
                Bishop.createWhiteBishop(),
                Knight.createWhiteKnight(),
                Rook.createWhiteRook());

        ranks.add(Rank.initializeRank(lastRow));
    }

    public enum SORT {
        ASCENDING(Comparator.comparingDouble(Piece::getDefaultPoint)),
        DESCENDING((o1, o2) -> Double.compare(o2.getDefaultPoint(), o1.getDefaultPoint()));

        private final Comparator<Piece> comparator;

        SORT(Comparator<Piece> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Piece> getComparator() {
            return comparator;
        }
    }
}
