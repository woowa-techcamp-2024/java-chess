package utils;

import chess.Rank;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representation;

import java.util.List;

public final class ScoreCalculator {
    private ScoreCalculator() {}

    /**
     * 체스판에 기물들의 점수를 계산합니다
     * @param ranks 체스판 변수
     * @param color 색상 (흰색, 검은색)
     * @return 점수
     */
    public static double calc(List<Rank> ranks, Color color) {
        double sum = 0.0;
        int[] filePawnCount = new int[8];

        // 기물 점수 계산
        for (Rank rank : ranks) {
            for (int i = 0; i < 8; i++) {
                Piece piece = rank.get(i);

                if (piece.getColor() != color) {
                    continue;
                }
                if (piece.getType() == Representation.Type.PAWN) {
                    filePawnCount[i] += 1;
                    continue;
                }
                sum += pointOfPiece(piece);
            }
        }

        // pawn 점수 계산
        for (int i = 0; i < 8; i++) {
            if (filePawnCount[i] == 1) {
                sum += 1.0;
                continue;
            }
            sum += 0.5 * filePawnCount[i];
        }
        return sum;
    }

    private static double pointOfPiece(Piece piece) {
        switch (piece.getType()) {
            case PAWN:
                return 1.0;
            case KNIGHT:
                return 2.5;
            case BISHOP:
                return 3.0;
            case ROOK:
                return 5.0;
            case QUEEN:
                return 9.0;
            case KING:
                return 0.0;
            default:
                return 0.0;
        }
    }
}
