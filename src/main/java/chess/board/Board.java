package chess.board;

import static chess.board.Rank.getBlankRank;

import chess.calculator.DefaultPointCalculateStrategy;
import chess.calculator.PointCalculator;
import chess.calculator.SameFilePawnPointCalculateStrategy;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.Position;
import chess.sorter.Direction;
import chess.sorter.Sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.StringUtils;

public class Board {

    public static final int RANK_COUNT = 8;

    public static final int FILE_COUNT = 8;

    private final List<Rank> ranks;

    private int pieceCount;

    private final PointCalculator pointCalculator;

    private final List<Piece> blackPieces;

    private final List<Piece> whitePieces;

    public Board() {
        this.ranks = new ArrayList<>(Collections.nCopies(RANK_COUNT, null));
        pointCalculator = new PointCalculator(List.of(
                new DefaultPointCalculateStrategy(),
                new SameFilePawnPointCalculateStrategy()
        ));
        blackPieces = new ArrayList<>();
        whitePieces = new ArrayList<>();
    }


    public Piece findPiece(Position position) {
        return getRank(position).findPiece(position.getFileNumber());
    }

    public List<Rank> getRanks() {
        return Collections.unmodifiableList(ranks);
    }

    private Rank getRank(Position position) {
        return ranks.get(position.getRankNumber());
    }

    private Rank getRank(int rankNum) {
        if (rankNum < 0 || rankNum >= RANK_COUNT) {
            throw new IllegalArgumentException();
        }
        return ranks.get(rankNum);
    }

    public int size() {
        return RANK_COUNT * FILE_COUNT;
    }

    public int pieceCount() {
        return pieceCount;
    }

    public int pieceCount(Type type, Color color) {
        return ranks.stream()
                .mapToInt(rank -> rank.count(type, color))
                .sum();
    }

    public void initialize() {
        initializeRank(0, new Rank(FILE_COUNT, List.of(
                Piece.createWhiteRook(),
                Piece.createWhiteKnight(),
                Piece.createWhiteBishop(),
                Piece.createWhiteQueen(),
                Piece.createWhiteKing(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight(),
                Piece.createWhiteRook()
        )));
        initializeRank(1, new Rank(FILE_COUNT, List.of(
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn()
        )));
        initializeRank(2, getBlankRank(FILE_COUNT));
        initializeRank(3, getBlankRank(FILE_COUNT));
        initializeRank(4, getBlankRank(FILE_COUNT));
        initializeRank(5, getBlankRank(FILE_COUNT));
        initializeRank(6, new Rank(FILE_COUNT, List.of(
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn()
        )));
        initializeRank(7, new Rank(FILE_COUNT, List.of(
                Piece.createBlackRook(),
                Piece.createBlackKnight(),
                Piece.createBlackBishop(),
                Piece.createBlackQueen(),
                Piece.createBlackKing(),
                Piece.createBlackBishop(),
                Piece.createBlackKnight(),
                Piece.createBlackRook()
        )));
    }


    private void initializeRank(int rankNum, Rank rank) {
        rank.getPieces()
                .forEach(piece -> {
                    if (!piece.isBlank()) {
                        pieceCount += 1;
                        addPieces(piece);
                    }
                });
        ranks.set(rankNum, rank);
    }

    private void addPieces(Piece piece) {
        if (piece.isBlack()) {
            blackPieces.add(piece);
        } else if (piece.isWhite()) {
            whitePieces.add(piece);
        }
    }


    public void initializeEmpty() {
        for (int rankNum = 0; rankNum < RANK_COUNT; rankNum++) {
            ranks.set(rankNum, getBlankRank(FILE_COUNT));
        }
    }

    public void print() {
        System.out.println(showBoard());
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int rankNum = RANK_COUNT - 1; rankNum >= 0; rankNum--) {
            StringBuilder rank = StringUtils.appendNewLine(getRank(rankNum).show());
            sb.append(rank);
        }
        return sb.toString();
    }

    public void move(Position position, Piece piece) {
        Rank rank = getRank(position);
        rank.set(position.getFileNumber(), piece);
    }

    public double calculatePoint(Color color) {
        return pointCalculator.calculate(this, color);
    }

    public List<Piece> sort(Color color, Sorter sorter, Direction direction) {
        List<Piece> sorted;
        if (direction.equals(Direction.ASC)) {
            sorted = sorter.sortAsc(this, color);
        } else {
            sorted = sorter.sortDesc(this, color);
        }
        return Collections.unmodifiableList(sorted);
    }

    public List<Piece> getPieces(Color color) {
        List<Piece> pieces = color.equals(Color.WHITE) ? whitePieces : blackPieces;
        return Collections.unmodifiableList(pieces);
    }

}
