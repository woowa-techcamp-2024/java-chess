package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Position;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Board {
    private List<Rank> pieces;

    public Board() {
        List<Rank> board = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            board.add(new Rank());
        }
        pieces = board;
    }

    public void move(Piece piece, Position position) {
        Rank rank = pieces.get(position.getRow());
        rank.placePiece(position.getCol(), piece);
    }

    public int pieceCount() {
        return pieces.stream().filter().size();
    }

    public Piece findPawn(Position position) {
        return pieces.get(position.getRow()).pieceRow.get(position.getCol());
    }

    public String getWhitePawnsRepresentation() {
        return pieces.stream()
                .flatMap(rank -> rank.pieceRow.stream())
                .filter(piece -> piece.getName() == Piece.Type.PAWN)
                .filter(piece -> piece.getColor() == Piece.Color.WHITE)
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(joining());
    }

    public String getBlackPawnsRepresentation() {
        return pieces.stream()
                .flatMap(rank -> rank.pieceRow.stream())
                .filter(piece -> piece.getName() == Piece.Type.PAWN)
                .filter(piece -> piece.getColor() == Piece.Color.BLACK)
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(joining());
    }

    public void initialize() {
        for(int i = 0; i < 8; i++) {
            this.move(Piece.createBlackPawn(), new Position(1, i));
            this.move(Piece.createWhitePawn(), new Position(6, i));
        }

        this.move(Piece.createBlackRook(), new Position(0, 0));
        this.move(Piece.createBlackKnight(), new Position(0, 1));
        this.move(Piece.createBlackBishop(), new Position(0, 2));
        this.move(Piece.createBlackQueen(), new Position(0, 3));
        this.move(Piece.createBlackKing(), new Position(0, 4));
        this.move(Piece.createBlackBishop(), new Position(0, 5));
        this.move(Piece.createBlackKnight(), new Position(0, 6));
        this.move(Piece.createBlackRook(), new Position(0, 7));

        this.move(Piece.createWhiteRook(), new Position(7, 0));
        this.move(Piece.createWhiteKnight(), new Position(7, 1));
        this.move(Piece.createWhiteBishop(), new Position(7, 2));
        this.move(Piece.createWhiteQueen(), new Position(7, 3));
        this.move(Piece.createWhiteKing(), new Position(7, 4));
        this.move(Piece.createWhiteBishop(), new Position(7, 5));
        this.move(Piece.createWhiteKnight(), new Position(7, 6));
        this.move(Piece.createWhiteRook(), new Position(7, 7));

        this.showBoard();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        this.pieces.forEach(rank -> {
            sb.append(rank.pieceRow.stream()
                    .map(Piece::getRepresentation)
                    .map(String::valueOf)
                    .collect(joining())
            ).append(System.lineSeparator());
        });

        System.out.println(sb);

        return sb.toString();
    }

    private static class Rank {
        private List<Piece> pieceRow;

        public Rank() {
            List<Piece> pieceRow = new ArrayList<Piece>();
            for(int i = 0; i < 8; i++) {
                pieceRow.add(Piece.createBlank());
            }
            this.pieceRow = pieceRow;
        }

        public void placePiece(int idx, Piece piece) {
            this.pieceRow.set(idx, piece);
        }

        public long countPieces() {
            return this.pieceRow.stream().filter(Piece::isExist).count();
        }
    }
}
