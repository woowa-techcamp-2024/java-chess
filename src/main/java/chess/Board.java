package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private List<ChessPiece> pieces;
    private List<Pawn> whitePawns;
    private List<Pawn> blackPawns;
    public Board(){
        pieces = new ArrayList<>();
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();
    }

    public int size(){
        return pieces.size();
    }

    public void add(ChessPiece piece){
        pieces.add(piece);
    }

    public ChessPiece findPawn(int index){
        return pieces.get(index);
    }

    public void initialize(){
        for(int w=0;w<WIDTH;w++){
            Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
            Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);
            whitePawns.add(whitePawn);
            blackPawns.add(blackPawn);
            pieces.add(whitePawn);
            pieces.add(blackPawn);
        }
    }

    public String getWhitePawnsResult(){
        return whitePawns.stream()
                .map(Pawn::getRepresentation)
                .map(String::valueOf)
                .reduce((x,y)->x+y)
                .get();
    }

    public String getBlackPawnsResult(){
        return blackPawns.stream()
                .map(Pawn::getRepresentation)
                .map(String::valueOf)
                .reduce((x,y)->x+y)
                .get();
    }

}
