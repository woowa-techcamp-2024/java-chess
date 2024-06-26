package chess.calculator;

import chess.Rank;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representation;

import java.util.ArrayList;
import java.util.List;

// TODO 이름 뭐라하지..?
public final class ScoreUtils {
    private ScoreUtils() {}

    /**
     * 체스판에 기물들의 점수를 계산합니다
     * @param ranks 체스판
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

    /**
     * 체스판의 기물들을 점수 기준으로 정렬합니다
     * @param ranks 체스판
     * @param color 색상 (흰색, 검은색)
     * @return 정렬된 기물 리스트
     */
    public static List<Piece> sort(List<Rank> ranks, Color color, OrderBy orderBy) {
        List<Piece> list = new ArrayList<>();
        for (Rank rank : ranks) {
            for (int i = 0; i < 8; i++) {
                Piece piece = rank.get(i);
                if (piece.getColor() != color) {
                    continue;
                }
                list.add(piece);
            }
        }

        // 정렬
        list.sort((p1, p2) -> {
            if (p1.getType() == p2.getType()) return 0;
            if (OrderBy.ASC == orderBy) {
                return pointOfPiece(p1) < pointOfPiece(p2) ? -1 : 1;
            }
            return pointOfPiece(p1) < pointOfPiece(p2) ? 1 : -1;
        });
        return list;
    }

    public static List<Piece> sort(List<Rank> ranks, Color color) {
        return sort(ranks, color, OrderBy.DESC);
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
