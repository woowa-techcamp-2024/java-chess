package chess;

import chess.pieces.Colors;
import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

import static utils.StringUtils.*;

public class Board {
    List<Pawn> whitePawns = new ArrayList<>();
    List<Pawn> blackPawns = new ArrayList<>();

    public void add(Pawn pawn) {
        if (pawn.getColor() == Colors.WHITE) {
            whitePawns.add(pawn);
        } else {
            blackPawns.add(pawn);
        }
    }

    public int size() {
        return whitePawns.size() + blackPawns.size();
    }

    public Pawn findWhitePawn(int index) {
        return whitePawns.get(index);
    }

    public Pawn findBlackPawn(int index) {
        return blackPawns.get(index);
    }

    public void initialize() {
        for (int i=0; i<8; i++) {
            Pawn white = new Pawn(Colors.WHITE);
            Pawn black = new Pawn(Colors.BLACK);
            whitePawns.add(white);
            blackPawns.add(black);
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        sb.append(getBlank());
        sb.append(appendNewLine(getBlackPawnsResult()));
        sb.append(getBlank());
        sb.append(getBlank());
        sb.append(getBlank());
        sb.append(getBlank());
        sb.append(appendNewLine(getWhitePawnsResult()));
        sb.append(getBlank());

        return sb.toString();
    }

    private String getBlank() {
        return appendNewLine( "........");
    }

    private String getPawnsResult(Colors color) {
        StringBuilder sb = new StringBuilder();

        if (color == Colors.WHITE) {
            for (Pawn p : whitePawns) {
                sb.append(p.getRepresentation().getSymbol());
            }
        } else {
            for (Pawn p : blackPawns) {
                sb.append(p.getRepresentation().getSymbol());
            }
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Colors.WHITE);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Colors.BLACK);
    }
}
