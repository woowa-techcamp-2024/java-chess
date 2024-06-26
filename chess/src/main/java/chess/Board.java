package chess;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import util.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private final List<Rank> ranks;

    public Board() {
        ranks = new ArrayList<>();
        for (int y = BoardArea.Y.getMax(); y > 0; y--) {
            ranks.add(new Rank(y));
        }
    }
    // get
    public long getNumOfPieces() {
        return ranks.stream()
                .mapToLong(Rank::getNumOfPieces)
                .sum();
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getY()).getPiece(position.getX());
    }

    public long countPiece(PieceColor color, PieceType type) {
        return ranks.stream()
                .mapToLong(rank -> rank.countPiece(color, type))
                .sum();
    }

    public List<Piece> getPieces(PieceColor color, Order order) {
        List<Piece> pieces = new ArrayList<>(ranks.stream()
                .flatMap(rank -> rank.getSpecificColorPieces(color).stream())
                .sorted(Comparator.comparingDouble(p -> p.getType().getPoint()))
                .toList());
        if (order.equals(Order.DESC)) {
            Collections.reverse(pieces);
        }
        return pieces;
    }


    // set
    public void setPiece(Position position, Piece piece) {
        ranks.get(position.getY()).setPiece(position.getX(), piece);
    }

    // util
    public double calculatePoint(PieceColor color) {
        double point = 0;
        point += calculatePawnPoint(color);
        point += ranks.stream()
                .mapToDouble(rank -> rank.calculatePoint(color))
                .sum();
        return point;
    }

    private double calculatePawnPoint(PieceColor color) {
        return IntStream.range(0, BoardArea.X.getMax())
                .mapToDouble(colIdx -> {
                    int numPawn = (int) IntStream.range(0, BoardArea.Y.getMax())
                            .filter(rowIdx -> ranks.get(rowIdx).isPawn(color, colIdx))
                            .count();

                    if (numPawn == 1) {
                        return PieceType.PAWN.getPoint();
                    } else if (numPawn >= 2) {
                        return numPawn * PieceType.PAWN.getPoint();
                    }
                    return 0.0;
                })
                .sum();
    }


    // print
    public String show() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : ranks) {
            sb.append(rank.toString()).append("\n");
        }
        return sb.toString().strip();
    }
}
