package chess;

import chess.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int pawnNum = 8;
    private static final int whitePawnY = 2;
    private static final int blackPawnY = 7;

    private final Map<Position, Pawn> board = new HashMap<>();

    public void initialize() {
        for (int i = 1; i <= pawnNum; i++) {
            board.put(new Position(whitePawnY, i), new Pawn(PieceColor.WHITE));
            board.put(new Position(blackPawnY, i), new Pawn(PieceColor.BLACK));
        }
    }

    public void add(final Position position, final Pawn pawn) {
        this.board.put(position, pawn);
    }

    public int size() {
        return this.board.size();
    }

    public Pawn findPawn(final Position position) {
        return this.board.get(position);
    }

    public String getPieceResult(final PieceColor color, final Type type) {
        long count = board.values().stream()
                .filter(pawn -> pawn.getColor().equals(color))
                .filter(pawn -> pawn.getType().equals(type))
                .count();

        char representation = PieceRepresentation.getPieceRepresentation(color, type);

        return String.join("", Collections.nCopies((int) count, String.valueOf(representation)));
    }
}
