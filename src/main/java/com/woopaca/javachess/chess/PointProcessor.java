package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.Type;

import java.util.Comparator;
import java.util.List;

public class PointProcessor {

    private final Board board;

    public PointProcessor(Board board) {
        this.board = board;
    }

    public double calculatePoint(Color color) {
        double pointWithoutPawns = board.getRanks()
                .stream()
                .mapToDouble(rank -> rank.calculatePointWithoutPawns(color))
                .sum();
        double pawnsPoint = calculatePawnsPoint(color);
        return pointWithoutPawns + pawnsPoint;
    }

    private double calculatePawnsPoint(Color color) {
        double pawnsPoint = 0;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            int fileIndex = i;
            long pawnsCount = board.getRanks()
                    .stream()
                    .filter(rank -> {
                        Piece piece = rank.findPieceByFile(fileIndex);
                        return piece.getType() == Type.PAWN && piece.getColor() == color;
                    })
                    .count();
            pawnsPoint += pawnsCount * (pawnsCount > 1 ? 0.5 : Type.PAWN.getPoint());
        }
        return pawnsPoint;
    }

    public List<Piece> sortPiecesByPoint(Color color) {
        Comparator<Piece> comparator = Comparator.comparing(Piece::getPoint);
        return sortPieces(color, comparator);
    }

    public List<Piece> sortPiecesByPointDescending(Color color) {
        Comparator<Piece> comparator = Comparator.comparing(Piece::getPoint).reversed();
        return sortPieces(color, comparator);
    }

    private List<Piece> sortPieces(Color color, Comparator<Piece> comparator) {
        return board.getRanks()
                .stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor() == color)
                .sorted(comparator)
                .toList();
    }

}
