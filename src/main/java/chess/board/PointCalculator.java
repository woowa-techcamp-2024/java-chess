package chess.board;

import chess.pieces.Piece;

import java.util.List;

public class PointCalculator {

    public double calculatePoint(Board board, Piece.Color color) {
        List<Rank> ranks = board.getRanks();
        final double pawnPoint = isHalfPointOfPawn(ranks, color) ? 0.5 : 1;

        return ranks.stream()
                .mapToDouble(rank -> calculateRankPoint(rank, color, pawnPoint))
                .sum();
    }

    private double calculateRankPoint(Rank rank, Piece.Color color, double pawnPoint) {
        double point = 0;
        for (Piece piece : rank.getAllPieces(color)) {
            if (piece.isSameType(Piece.Type.PAWN)) {
                point += pawnPoint;
            } else {
                point += piece.getPoint();
            }
        }
        return point;
    }

    private boolean isHalfPointOfPawn(List<Rank> ranks, Piece.Color color) {
        for (int rankIndex = 0; rankIndex < ranks.size() - 1; rankIndex++) {
            Rank firstRank = ranks.get(rankIndex);
            Rank secondRank = ranks.get(rankIndex + 1);

            for (int widthIndex = 0; widthIndex < firstRank.getPieces().size(); widthIndex++) {
                Piece firstPiece = firstRank.getPieceByIndex(widthIndex);
                Piece secondPiece = secondRank.getPieceByIndex(widthIndex);

                if (firstPiece.isSameColorAndType(color, Piece.Type.PAWN)
                        && secondPiece.isSameColorAndType(color, Piece.Type.PAWN)) {
                    return true;
                }
            }
        }
        return false;
    }
}
