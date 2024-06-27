package chess.board;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final List<Rank> ranks;
    // TODO 이거 왜 있지,,
    private int pieceCount;

    private static final int BOARD_WIDTH = 8;
    private static final int RANK_HEIGHT = 8;
    private static final int INITIAL_PIECE_COUNT = 32;
    private static final int BLANK_ROW_START = 2;
    private static final int BLANK_ROW_END = 6;

    // TODO 이건 차라리 Enum으로 하는 게 나을 듯,,,
    public static final Comparator<Piece> SORT_ASCENDING = Comparator.comparingDouble(Piece::getPoint);
    public static final Comparator<Piece> SORT_DESCENDING = (o1, o2) -> Double.compare(o2.getPoint(), o1.getPoint());


    public List<Rank> getRanks() {
        return ranks;
    }
    /*
    * 테스트에서만 사용하는 초기화 메서드
    * */
    protected void initializeEmpty() {
        ranks.clear();
        initializeEmtpyRows(0, RANK_HEIGHT);
        pieceCount = 0;
    }

    public int getPieceCount(Piece.Color color, Piece.Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.getPieceCount(color, type))
                .sum();
    }

    public int getTotalPieceCount() {
        return pieceCount;
    }

    public double caculcatePoint(Piece.Color color) {
        return ranks.stream()
                .mapToDouble(rank -> rank.calculateRankPoint(color))
                .sum();
    }

    public List<Piece> sortPiecesByPoint(Piece.Color color, Comparator<Piece> comparator) {
        return ranks.stream().map(rank -> rank.getAllPieces(color))
                .flatMap(List::stream)
                .sorted(comparator)
                .toList();
    }

    public void move(String sourceCoordinateStr, String targetCoordinateStr) {
        Coordinate sourceCoordinate = Coordinate.of(sourceCoordinateStr);
        Coordinate targetCoordinate = Coordinate.of(targetCoordinateStr);

        Piece sourcePiece = findPiece(sourceCoordinate);
        Piece targetPiece = findPiece(targetCoordinate);
        move(targetCoordinate, sourcePiece);
        move(sourceCoordinate, targetPiece);
    }

    public Board() {
        ranks = new ArrayList<>();
        initialize();
    }

    protected Piece findPiece(Coordinate coordinate) {
        return ranks.get(coordinate.getRankIndex())
                .getPieceByIndex(coordinate.getWidthIndex());
    }

    protected void move(Coordinate coordinate, Piece piece) {
        Rank targetRank = ranks.get(coordinate.getRankIndex());
        targetRank.setPiece(coordinate.getWidthIndex(), piece);
    }

    private Coordinate convertCoordinate(String coordinateStr) {
        return Coordinate.of(coordinateStr);
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
}
