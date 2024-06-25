package chess;

import static utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import pieces.Piece;
import pieces.Piece.Color;
import pieces.Piece.PieceType;

public class Board {

    public static final int BOARD_SIZE = 8;
    private final List<Rank> boards = new ArrayList<>();

    public Board() {
        initialize();
    }

    public void initialize() {
        boards.clear();
        IntStream.range(0, BOARD_SIZE)
            .forEach(i -> boards.add(new Rank(i)));
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boards.get(i).initializeEmpty();
        }
    }

    public void print() {
        System.out.println(this);
    }

    public int size() {
        return boards.size();
    }

    public int totalPieceCount() {
        return boards.stream()
            .map(Rank::totalPieceCount)
            .reduce(0, Integer::sum);
    }

    public int getPieceCountByPieceType(PieceType pieceType) {
        return boards.stream()
            .map(rank -> rank.getPieceCountByPieceType(pieceType))
            .reduce(0, Integer::sum);
    }

    public Piece getPieceByPosition(String pos) {
        Position position = Position.of(pos);
        return boards.get(position.getRow()).getRanks().get(position.getColumn());
    }

    public void move(String pos, Piece piece) {
        Position position = Position.of(pos);
        boards.get(position.getRow()).getRanks().set(position.getColumn(), piece);
    }

    public double calculatePoint(Color color) {
        double result = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece piece = boards.get(i).getRanks().get(j);
                if (piece.getColor() == color) {
                    result += piece.getPieceType().getDefaultPoint();
                }
            }
        }
        for (int j = 0; j < BOARD_SIZE; j++) {
            int pawnCount = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                Piece piece = boards.get(i).getRanks().get(j);
                if (piece.isPawn()) {
                    pawnCount++;
                }
            }
            if (pawnCount > 1) {
                result -= pawnCount * 0.5;
            }
        }
        return result;
    }

    public List<Piece> sortPieces(Color color, Comparator<Piece> comparator) {
        List<Piece> pieces = new ArrayList<>(boards.stream()
            .map(rank -> rank.getRanks().stream()
                .filter(piece -> piece.getColor() == color)
                .toList())
            .flatMap(List::stream)
            .toList());
        pieces.sort(comparator);
        return pieces;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : boards) {
            stringBuilder.append(appendNewLine(rank.toString()));
        }
        return stringBuilder.toString();
    }
}
