package chess.board.calculator;

import chess.board.Rank;
import chess.pieces.type.Color;
import chess.pieces.Piece;
import chess.pieces.type.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ScoreCalculator {
    private final List<Rank> ranks;

    public ScoreCalculator(List<Rank> ranks) {
        this.ranks = ranks;
    }

    /**
     * 체스판에 기물들의 점수를 계산합니다
     * @param color 색상 (흰색, 검은색)
     * @return 점수
     */
    public double calc(Color color) {
        double sum = 0.0;
        int[] filePawnCount = new int[8];

        // 기물 점수 계산
        for (Rank rank : ranks) {
            for (int i = 0; i < 8; i++) {
                Piece piece = rank.get(i);

                if (piece.getColor() != color) {
                    continue;
                }

                if (piece.getType() == Type.PAWN) {
                    filePawnCount[i] += 1;
                    continue;
                }

                sum += piece.getType().getPoint();
            }
        }

        return sum + calculatePawnScores(filePawnCount);
    }

    private static double calculatePawnScores(final int[] filePawnCount) {
        return Arrays.stream(filePawnCount)
            .mapToDouble(pawnCount -> pawnCount == 1 ? 1 : pawnCount * 0.5)
            .sum();
    }

    /**
     * 체스판의 기물들을 점수 기준으로 정렬합니다
     * @param color 색상 (흰색, 검은색)
     * @param orderBy 정렬기준 (OrderBy.ASC, OrderBy.DESC)
     * @return 정렬된 기물 리스트
     */
    public List<Piece> sort(Color color, OrderBy orderBy) {
        return ranks.stream()
            .flatMap(rank -> IntStream.range(0, 8)
                .mapToObj(rank::get)
                .filter(piece -> piece.getColor() == color))
            .sorted(orderBy.getComparator())
            .toList();
    }
}
