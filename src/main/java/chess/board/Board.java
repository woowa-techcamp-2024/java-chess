package chess.board;

import static chess.board.Rank.createBlackPawnRank;
import static chess.board.Rank.createBlankRank;
import static chess.board.Rank.createWhitePawnRank;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import chess.pieces.Position;
import chess.sorter.Direction;
import chess.sorter.Sorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {

    public static final int RANK_COUNT = 8;

    public static final int FILE_COUNT = 8;

    private final List<Rank> ranks;

    public Board() {
        this.ranks = new ArrayList<>(Collections.nCopies(RANK_COUNT, null));
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

    public Rank getRank(int rankNum) {
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
                PieceFactory.createWhiteRook(), PieceFactory.createWhiteKnight(), PieceFactory.createWhiteBishop(),
                PieceFactory.createWhiteQueen(), PieceFactory.createWhiteKing(),
                PieceFactory.createWhiteBishop(), PieceFactory.createWhiteKnight(), PieceFactory.createWhiteRook())));
        initializeRank(1, createWhitePawnRank(FILE_COUNT));
        initializeRank(2, createBlankRank(FILE_COUNT));
        initializeRank(3, createBlankRank(FILE_COUNT));
        initializeRank(4, createBlankRank(FILE_COUNT));
        initializeRank(5, createBlankRank(FILE_COUNT));
        initializeRank(6, createBlackPawnRank(FILE_COUNT));
        initializeRank(7, new Rank(FILE_COUNT, Arrays.asList(
                PieceFactory.createBlackRook(), PieceFactory.createBlackKnight(), PieceFactory.createBlackBishop(),
                PieceFactory.createBlackQueen(), PieceFactory.createBlackKing(),
                PieceFactory.createBlackBishop(), PieceFactory.createBlackKnight(), PieceFactory.createBlackRook())));
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

    public void move(Position source, Position target) {
        if (source == target) {
            throw new IllegalArgumentException();
        }
        Piece current = findPiece(source);
        List<Position> path = current.getPath(source, target);
        verifySameTeamOnPath(path, current);
        replacePiece(source, PieceFactory.createBlankPiece());
        replacePiece(target, current);
    }

    public void move(Position source, Piece piece) {
        replacePiece(source, piece);
    }

    private void verifySameTeamOnPath(List<Position> path, Piece current) {
        path.forEach(position -> {
            Piece other = findPiece(position);
            if (current.isSameTeam(other)) {
                throw new IllegalArgumentException();
            }
        });
    }

    private void replacePiece(Position position, Piece piece) {
        Rank rank = getRank(position);
        rank.set(position.getFileNumber(), piece);
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
