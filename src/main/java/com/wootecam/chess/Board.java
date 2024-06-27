package com.wootecam.chess;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Rank;
import com.wootecam.chess.pieces.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    public static final char START_COLUMN_SYMBOL = 'a';
    public static final char END_COLUMN_SYMBOL = 'h';
    public static final char START_ROW_SYMBOL = '1';
    public static final char END_ROW_SYMBOL = '8';

    private final List<Rank> ranks;

    public Board(final List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int countBoardPieces() {
        return ranks.stream()
                .mapToInt(Rank::countPieces)
                .sum();
    }

    public int countSpecificBoardPieces(final Color color, final Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.countSpecificPieces(color, type))
                .sum();
    }

    public Piece findPiece(final Position position) {
        Rank rowRank = ranks.get(position.row());

        return rowRank.findPieceByColumn(position.column());
    }

    public void updatePiece(final Position position, final Piece piece) {
        Rank rank = ranks.get(position.row());
        ranks.set(position.row(), rank.placePiece(position.column(), piece));
    }

    public List<Piece> findDescOrderedPieces(final Color color) {
        return ranks.stream()
                .flatMap(rank -> findSpecificColorPiece(rank, color))
                .sorted(Comparator.comparingDouble((Piece piece) -> piece.getType().getPoint()).reversed())
                .toList();
    }

    private Stream<Piece> findSpecificColorPiece(final Rank rank, final Color color) {
        return IntStream.range(0, Rank.PIECE_COUNT)
                .mapToObj(rank::findPieceByColumn)
                .filter(piece -> piece.getColor() == color);
    }

    public List<Rank> getRanks() {
        return Collections.unmodifiableList(ranks);
    }
}
