package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<PieceOnBoard> pieces;

    public Board() {
        pieces = new ArrayList<>();
    }

    public void add(Piece piece) {
        pieces.add(new PieceOnBoard(piece, new Position(0, 0)));
    }

    public void addWithPos(Piece piece, Position position) {
        pieces.add(new PieceOnBoard(piece, position));
    }

    public int pieceCount() {
        return pieces.size();
    }

    public Piece findPawn(int idx) {
        return pieces.get(idx).piece;
    }

    public String getWhitePawnsRepresentation() {
        return pieces.stream().filter(pieceOnBoard -> pieceOnBoard.piece.isWhite())
                .filter(pieceOnBoard -> pieceOnBoard.piece.getName() == Piece.Name.PAWN)
                .map(pieceOnBoard ->pieceOnBoard.piece.getRepresentation()).collect(Collectors.joining());
    }

    public String getBlackPawnsRespresentation() {
        return pieces.stream().filter(pieceOnBoard -> pieceOnBoard.piece.isBlack())
                .filter(pieceOnBoard -> pieceOnBoard.piece.getName() == Piece.Name.PAWN)
                .map(pieceOnBoard -> pieceOnBoard.piece.getRepresentation()).collect(Collectors.joining());
    }

    public void initialize() {
        this.addWithPos(Piece.createBlackRook(), new Position(0, 0));
        this.addWithPos(Piece.createBlackKnight(), new Position(0, 1));
        this.addWithPos(Piece.createBlackBishop(), new Position(0, 2));
        this.addWithPos(Piece.createBlackQueen(), new Position(0, 3));
        this.addWithPos(Piece.createBlackKing(), new Position(0, 4));
        this.addWithPos(Piece.createBlackBishop(), new Position(0, 5));
        this.addWithPos(Piece.createBlackKnight(), new Position(0, 6));
        this.addWithPos(Piece.createBlackRook(), new Position(0, 7));

        this.addWithPos(Piece.createBlackPawn(), new Position(1, 0));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 1));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 2));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 3));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 4));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 5));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 6));
        this.addWithPos(Piece.createBlackPawn(), new Position(1, 7));

        this.addWithPos(Piece.createWhitePawn(), new Position(6, 0));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 1));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 2));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 3));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 4));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 5));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 6));
        this.addWithPos(Piece.createWhitePawn(), new Position(6, 7));

        this.addWithPos(Piece.createWhiteRook(), new Position(7, 0));
        this.addWithPos(Piece.createWhiteKnight(), new Position(7, 1));
        this.addWithPos(Piece.createWhiteBishop(), new Position(7, 2));
        this.addWithPos(Piece.createWhiteQueen(), new Position(7, 3));
        this.addWithPos(Piece.createWhiteKing(), new Position(7, 4));
        this.addWithPos(Piece.createWhiteBishop(), new Position(7, 5));
        this.addWithPos(Piece.createWhiteKnight(), new Position(7, 6));
        this.addWithPos(Piece.createWhiteRook(), new Position(7, 7));

        this.showBoard();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        String[][] map = new String[8][8];

        for (String[] row: map) {
            Arrays.fill(row, ".");
        }

        pieces.forEach(pieceOnBoard -> {
            Position pos = pieceOnBoard.getPosition();
            map[pos.getRow()][pos.getCol()] = pieceOnBoard.piece.getRepresentation();
        });

        for (String[] row: map) {
            sb.append(String.join("", row)).append(System.lineSeparator());
        }

        System.out.println(sb);

        return sb.toString();
    }

    private class PieceOnBoard {
        private Piece piece;
        private Position position;

        public Position getPosition() {
            return position;
        }

        public PieceOnBoard(Piece piece, Position position){
            this.piece = piece;
            this.position = position;
        }
    }
}
