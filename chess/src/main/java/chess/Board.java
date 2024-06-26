package chess;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceFactory;
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
        for (int i = 0; i < BoardArea.ROW.getNum(); i++) {
            ranks.add(new Rank());
        }
    }
    // get
    public long getNumOfPieces() {
        return ranks.stream()
                .mapToLong(Rank::getNumOfPieces)
                .sum();
    }

    public Piece findPiece(String givenPosition) {
        Position position = new Position(givenPosition);
        return ranks.get(position.getRowIdx()).getPiece(position.getColIdx());
    }

    public long countPiece(PieceColor color, PieceType type) {
        return ranks.stream()
                .mapToLong(rank -> rank.countPiece(color, type))
                .sum();
    }

    public List<Piece> getPieces(PieceColor color, Order order) {
        List<Piece> pieces = new ArrayList<>(ranks.stream()
                .flatMap(rank -> rank.getSpecificColorPieces(color).stream())
                .sorted(Comparator.comparingDouble(p -> p.type().getPoint()))
                .toList());
        if (order.equals(Order.DESC)) {
            Collections.reverse(pieces);
        }
        return pieces;
    }


    // set
    public void addPiece(Position position, Piece piece) {
        ranks.get(position.getRowIdx()).setPiece(position.getColIdx(), piece);
    }

    public void movePiece(Position sourcePosition, Position targetPosition){

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
        return IntStream.range(0, BoardArea.COL.getNum())
                .mapToDouble(colIdx -> {
                    int numPawn = (int) IntStream.range(0, BoardArea.ROW.getNum())
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
