package org.example.chess;

import org.example.chess.pieces.Pawn;
import org.example.chess.pieces.global.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Pawn> pawns;

    public Board() {
        pawns = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public void add(Pawn pawn, Position position) {
        pawns.add(pawn);
        pawn.setPosition(position);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int idx) {
        return pawns.get(idx);
    }

    public String getWhitePawnsRepresentation() {
        return pawns.stream().filter(Pawn::isWhite).map(Pawn::getRepresentation).collect(Collectors.joining());
    }

    public String getBlackPawnsRespresentation() {
        return pawns.stream().filter(Pawn::isBlack).map(Pawn::getRepresentation).collect(Collectors.joining());
    }

    public void initialize() {
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 0));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 1));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 2));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 3));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 4));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 5));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 6));
        this.add(new Pawn(Pawn.Color.BLACK), new Position(1, 7));

        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 0));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 1));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 2));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 3));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 4));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 5));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 6));
        this.add(new Pawn(Pawn.Color.WHITE), new Position(6, 7));

        this.print();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();

        String[][] map = new String[8][8];

        for (String[] row: map) {
            Arrays.fill(row, ".");
        }

        pawns.forEach(pawn -> {
            Position pos = pawn.getPosition();
            map[pos.getRow()][pos.getCol()] = pawn.getRepresentation();
        });

        for (String[] row: map) {
            sb.append(String.join("", row)).append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }
}
