package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Direction;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Position;
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

    public void verifyMove(final Piece piece, final Position startPosition, final Position targetPosition) {
        Direction direction = piece.findDirection(startPosition, targetPosition)
                .orElseThrow(() -> new IllegalArgumentException("현재 기물에 대한 알맞은 방향을 찾을 수 없습니다."));

        Piece targetPiece = findPiece(targetPosition);

        if (piece.isTypeOf(Type.PAWN)) {
            verifyPawnMove(startPosition, targetPosition, targetPiece, direction);
        }
    }

    private void verifyPawnMove(final Position startPosition,
                                final Position targetPosition,
                                final Piece targetPiece,
                                final Direction direction) {
        // 공격하려는데 빈칸인 경우
        if (direction.isPawnAttackDirection() && targetPiece.isBlank()) {
            throw new IllegalArgumentException("폰은 빈칸을 공격할 수 없습니다.");
        }
        // 2칸 움직이려는데 기물 있는 경우
        if (direction.isPawnMoveDirection() && startPosition.calculateDistanceWith(targetPosition) == 2
                && isBlockWithOtherPiece(startPosition, direction,
                startPosition.calculateDistanceWith(targetPosition))) {
            throw new IllegalArgumentException("폰이 2칸 이동할때 방문하는 칸에 기물이 있으면 안됩니다.");
        }
        // 1칸 움직이려는데 기물 있는 경우
        if (direction.isPawnMoveDirection() && isBlockWithOtherPiece(startPosition, direction,
                startPosition.calculateDistanceWith(targetPosition))) {
            throw new IllegalArgumentException("폰이 1칸 이동할때 도착지점에 기물이 있으면 안됩니다.");
        }
    }

    private boolean isBlockWithOtherPiece(final Position startPosition, final Direction direction,
                                          final int moveCount) {
        return !IntStream.range(0, moveCount)
                .mapToObj(i -> startPosition.addPosition(direction.getRow(), direction.getColumn()))
                .map(this::findPiece)
                .allMatch(Piece::isBlank);
    }
}
