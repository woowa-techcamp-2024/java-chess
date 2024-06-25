package chess;

import static chess.Board.BOARD_SIZE;

import java.util.ArrayList;
import java.util.List;
import pieces.Piece;

public class Rank {

    private final List<Piece> ranks = new ArrayList<>();

    public Rank(int row) {
        if (row == 1) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                ranks.add(Piece.createBlackPawn());
            }
        } else if (row == 6) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                ranks.add(Piece.createWhitePawn());
            }
        } else if (row == 0) {
            ranks.add(Piece.createBlackRook());
            ranks.add(Piece.createBlackKnight());
            ranks.add(Piece.createBlackBishop());
            ranks.add(Piece.createBlackQueen());
            ranks.add(Piece.createBlackKing());
            ranks.add(Piece.createBlackBishop());
            ranks.add(Piece.createBlackKnight());
            ranks.add(Piece.createBlackRook());
        } else if (row == 7) {
            ranks.add(Piece.createWhiteRook());
            ranks.add(Piece.createWhiteKnight());
            ranks.add(Piece.createWhiteBishop());
            ranks.add(Piece.createWhiteQueen());
            ranks.add(Piece.createWhiteKing());
            ranks.add(Piece.createWhiteBishop());
            ranks.add(Piece.createWhiteKnight());
            ranks.add(Piece.createWhiteRook());
        } else {
            for (int i = 0; i < BOARD_SIZE; i++) {
                ranks.add(Piece.createBlank());
            }
        }
    }

    public int getPieceCount() {
        int result = 0;
        for (Piece rank : ranks) {
            if (!rank.isBlank()) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece rank : ranks) {
            stringBuilder.append(rank.getRepresentation());
        }
        return stringBuilder.toString();
    }
}
