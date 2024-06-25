package chess;

import pieces.Pawn;
import pieces.PawnColor;
import pieces.PieceUnicode;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int NUM_COL = 8;
    private final String BLANK = ".";
    private final List<Pawn> whitePawns;
    private final List<Pawn> blackPawns;
    private int size = 0;

    public Board() {
        this.whitePawns = new ArrayList<>();
        this.blackPawns = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        if(pawn.getColor().equals(PawnColor.WHITE))
        {
            whitePawns.add(pawn);
        }
        else if(pawn.getColor().equals(PawnColor.BLACK))
        {
            blackPawns.add(pawn);
        }
        size += 1;
    }

    public int getSize() {
        return size;
    }


    public void initialize() {
        for(int i=0; i < NUM_COL; i++){
            whitePawns.add(new Pawn(PawnColor.WHITE));
            blackPawns.add(new Pawn(PawnColor.BLACK));
        }
    }

    public String getWhitePawnsResult() {
        return getRepresentation(whitePawns);
    }

    public String getBlackPawnsResult() {
        return getRepresentation(blackPawns);
    }


    private String getRepresentation(List<Pawn> pawns){
        StringBuilder sb = new StringBuilder();
        for(Pawn pawn : pawns)
        {
            if(pawn.getColor().equals(PawnColor.BLACK))
            {
                sb.append(PieceUnicode.BLACK_PAWN.getUnicode());
            }

            else if(pawn.getColor().equals(PawnColor.WHITE))
            {
                sb.append(PieceUnicode.WHITE_PAWN.getUnicode());
            }
        }
        return sb.toString();
    }
}
