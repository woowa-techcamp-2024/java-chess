package chess.board;

import static chess.board.Rank.createBlackPawnRank;
import static chess.board.Rank.createBlankRank;
import static chess.board.Rank.createWhitePawnRank;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import utils.StringUtils;

public class Board {

    public static final int RANK_COUNT = 8;

    public static final int FILE_COUNT = 8;

    private final List<Rank> ranks;

    private final PointCalculator pointCalculator;

    public Board() {
        this.ranks = new ArrayList<>(Collections.nCopies(RANK_COUNT, null));
        pointCalculator = new PointCalculator(List.of(
                new DefaultPointCalculateStrategy(),
                new SameFilePawnPointCalculateStrategy()
        ));
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

    public int pieceCount(Type type, Color color) {
        return ranks.stream()
                .mapToInt(rank -> rank.count(type, color))
                .sum();
    }

    public void initialize() {
        initializeRank(0, new Rank(FILE_COUNT, Arrays.asList(
                Piece.createWhiteRook(),
                Piece.createWhiteKnight(),
                Piece.createWhiteBishop(),
                Piece.createWhiteQueen(),
                Piece.createWhiteKing(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight(),
                Piece.createWhiteRook()
        )));
        initializeRank(1, createWhitePawnRank(FILE_COUNT));
        initializeRank(2, createBlankRank(FILE_COUNT));
        initializeRank(3, createBlankRank(FILE_COUNT));
        initializeRank(4, createBlankRank(FILE_COUNT));
        initializeRank(5, createBlankRank(FILE_COUNT));
        initializeRank(6, createBlackPawnRank(FILE_COUNT));
        initializeRank(7, new Rank(FILE_COUNT, Arrays.asList(
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
        if (rankNum >= RANK_COUNT) {
            throw new IllegalArgumentException();
        }
        ranks.set(rankNum, rank);
    }


    public void initializeEmpty() {
        for (int rankNum = 0; rankNum < RANK_COUNT; rankNum++) {
            ranks.set(rankNum, createBlankRank(FILE_COUNT));
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

    public void move(Position source, Position target) {
        Rank sourceRank = getRank(source);
        Piece piece = sourceRank.removePiece(source.getFileNumber());

        Rank targetRank = getRank(target);
        targetRank.set(target.getFileNumber(), piece);
    }

    public double calculatePoint(Color color) {
        return pointCalculator.calculate(this, color);
    }

    public List<Piece> sort(Color color, Sorter sorter, Direction direction) {
        List<Piece> sorted = sorter.sort(getPieces(color), direction);
        return Collections.unmodifiableList(sorted);
    }

    public List<Piece> getPieces(Color color) {
        return ranks.stream()
                .map(rank -> rank.getPieces(color))
                .flatMap(List::stream)
                .toList();
    }

}
