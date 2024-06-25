package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> whitePawns = new ArrayList<>();
    private final List<Pawn> blackPawns = new ArrayList<>();

    public void add(Pawn pawn) {
        if (pawn.getColor() == Pawn.Color.WHITE) {
            whitePawns.add(pawn);
        } else {
            blackPawns.add(pawn);
        }
    }

    public int size() {
        return whitePawns.size() + blackPawns.size();
    }

    public Pawn findPawn(int i, Pawn.Color color) {
        if (color == Pawn.Color.WHITE) {
            return whitePawns.get(i);
        } else {
            return blackPawns.get(i);
        }
    }

    public void initialize() {
        whitePawns.clear();
        blackPawns.clear();
        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(Pawn.Color.WHITE));
            blackPawns.add(new Pawn(Pawn.Color.BLACK));
        }
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePawns);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPawns);
    }

    private String getPawnsResult(List<Pawn> pawns) {
        StringBuilder builder = new StringBuilder();
        for (Pawn pawn : pawns) {
            builder.append(pawn.getRepresentation());
        }
        return builder.toString();
    }
}
