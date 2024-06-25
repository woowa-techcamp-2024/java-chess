package chess;

import static chess.Board.BOARD_SIZE;
import static pieces.Piece.PieceType.BLANK;

import java.util.ArrayList;
import java.util.List;
import pieces.Piece;
import pieces.Piece.Color;
import pieces.Piece.PieceType;

public class Rank {

    private final List<Piece> ranks = new ArrayList<>();

    public Rank(int row) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.add(Position.getPieceByDefaultPosition(row, ranks.size()));
        }
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.set(i, Piece.createPiece(Color.BLANK, BLANK));
        }
    }

    public int totalPieceCount() {
        int result = 0;
        for (Piece rank : ranks) {
            if (!rank.isBlank()) {
                result++;
            }
        }
        return result;
    }

    public int getPieceCountByPieceType(PieceType pieceType) {
        return (int) ranks.stream()
            .filter(rank -> rank.getPieceType() == pieceType)
            .count();
    }

    public List<Piece> getRanks() {
        return ranks;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ranks.forEach(rank -> stringBuilder.append(rank.getRepresentation()));
        return stringBuilder.toString();
    }
}
