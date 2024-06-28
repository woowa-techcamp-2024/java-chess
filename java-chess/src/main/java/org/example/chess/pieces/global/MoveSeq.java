package org.example.chess.pieces.global;

import java.util.ArrayList;
import java.util.List;

public class MoveSeq {
    private List<Move> moves;

    private MoveSeq(List<Move> moves) {
        this.moves = moves;
    }

    public static MoveSeq of(List<Move> moves) {
        return new MoveSeq(moves);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void add(Move move) {
        moves.add(move);
    }

    public static MoveSeq createOneNorth() {
        return MoveSeq.of(List.of(Move.of(Direction.NORTH, false)));
    }

    public static MoveSeq createOneSouth() {
        return MoveSeq.of(List.of(Move.of(Direction.SOUTH, false)));
    }

    public static MoveSeq createOneEast() {
        return MoveSeq.of(List.of(Move.of(Direction.EAST, false)));
    }

    public static MoveSeq createOneWest() {
        return MoveSeq.of(List.of(Move.of(Direction.WEST, false)));
    }

    public static MoveSeq createOneNW() {
        return MoveSeq.of(List.of(Move.of(Direction.NORTHWEST, false)));
    }

    public static MoveSeq createOneNE() {
        return MoveSeq.of(List.of(Move.of(Direction.NORTHEAST, false)));
    }

    public static MoveSeq createOneSW() {
        return MoveSeq.of(List.of(Move.of(Direction.SOUTHWEST, false)));
    }

    public static MoveSeq createOneSE() {
        return MoveSeq.of(List.of(Move.of(Direction.SOUTHEAST, false)));
    }

    public static MoveSeq createInfNE() {
        MoveSeq moveSeqNE = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqNE.add(Move.of(Direction.NORTHEAST, false));
        }
        return moveSeqNE;
    }

    public static MoveSeq createInfNorth() {
        MoveSeq moveSeqNorth = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqNorth.add(Move.of(Direction.NORTH, false));
        }
        return moveSeqNorth;
    }

    public static MoveSeq createInfSouth() {
        MoveSeq moveSeqSouth = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqSouth.add(Move.of(Direction.SOUTH, false));
        }
        return moveSeqSouth;
    }

    public static MoveSeq createInfWest() {
        MoveSeq moveSeqWest = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqWest.add(Move.of(Direction.WEST, false));
        }
        return moveSeqWest;
    }

    public static MoveSeq createInfEast() {
        MoveSeq moveSeqEast = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqEast.add(Move.of(Direction.EAST, false));
        }
        return moveSeqEast;
    }

    public static MoveSeq createInfNW() {
        MoveSeq moveSeqNW = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqNW.add(Move.of(Direction.NORTHWEST, false));
        }
        return moveSeqNW;
    }

    public static MoveSeq createInfSE() {
        MoveSeq moveSeqSE = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqSE.add(Move.of(Direction.SOUTHEAST, false));
        }
        return moveSeqSE;
    }

    public static MoveSeq createInfSW() {
        MoveSeq moveSeqSW = MoveSeq.of(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            moveSeqSW.add(Move.of(Direction.SOUTHWEST, false));
        }
        return moveSeqSW;
    }
}