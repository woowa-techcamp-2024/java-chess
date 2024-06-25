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
    private final int NUM_ROW = 8;
    private final int NUM_COL = 8;

    private final String BLANK = ".";
    private final List<Rank> ranks;
    private final PieceFactory pieceFactory;

    public Board() {
        ranks = new ArrayList<>();
        for (int i = 0; i < NUM_ROW; i++) {
            ranks.add(new Rank());
        }
        this.pieceFactory = new PieceFactory();
    }

    public long getNumOfPieces() {
        return ranks.stream()
                .mapToLong(Rank::getNumOfPieces)
                .sum();
    }

    public void initialize() {
        initPieces(PieceColor.BLACK, 0);
        initPawns(PieceColor.BLACK, 1);
        initPawns(PieceColor.WHITE, 6);
        initPieces(PieceColor.WHITE, 7);
    }

    private void initPieces(PieceColor color, int rankIndex) {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceFactory.createPiece(color, PieceType.ROOK));
        pieces.add(pieceFactory.createPiece(color, PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(color, PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(color, PieceType.QUEEN));
        pieces.add(pieceFactory.createPiece(color, PieceType.KING));
        pieces.add(pieceFactory.createPiece(color, PieceType.BISHOP));
        pieces.add(pieceFactory.createPiece(color, PieceType.KNIGHT));
        pieces.add(pieceFactory.createPiece(color, PieceType.ROOK));
        ranks.set(rankIndex, new Rank(pieces));
    }

    private void initPawns(PieceColor color, int rankIndex) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < NUM_COL; i++) {
            pieces.add(pieceFactory.createPiece(color, PieceType.PAWN));
        }
        ranks.set(rankIndex, new Rank(pieces));
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : ranks) {
            sb.append(rank.toString()).append("\n");
        }
        return sb.toString().strip();
    }

    public long countPiece(PieceColor color, PieceType type) {
        return ranks.stream()
                .mapToLong(rank -> rank.countPiece(color, type))
                .sum();
    }

    public Piece findPiece(String givenPosition) {
        Position position = new Position(givenPosition);
        return ranks.get(position.getRowIdx()).getPiece(position.getColIdx());
    }

    public void move(String givenPosition, Piece piece) {
        Position position = new Position(givenPosition);
        ranks.get(position.getRowIdx()).setPiece(position.getColIdx(), piece);
    }

    public double calculatePoint(PieceColor color) {
        double point = 0;
        point += calculatePawnPoint(color);
        point += ranks.stream()
                .mapToDouble(rank -> rank.calculatePoint(color))
                .sum();
        return point;
    }

    private double calculatePawnPoint(PieceColor color) {
        return IntStream.range(0, NUM_COL)
                .mapToDouble(colIdx -> {
                    int numPawn = (int) IntStream.range(0, NUM_ROW)
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
}
