package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private final List<Rank> ranks;

    public Board() {
        ranks = new ArrayList<>();
    }

    public void initialize() {
        ranks.add(new Rank(
                Piece.createBlackRook(), Piece.createBlackKnight(), Piece.createBlackBishop(),
                Piece.createBlackQueen(), Piece.createBlackKing(), Piece.createBlackBishop(),
                Piece.createBlackKnight(), Piece.createBlackRook()
        ));
        ranks.add(new Rank(
                Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(),
                Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(),
                Piece.createBlackPawn(), Piece.createBlackPawn()
        ));

        for (int i = 0; i < 4; i++) {
            ranks.add(Rank.createBlankRank());
        }

        ranks.add(new Rank(
                Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(),
                Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(),
                Piece.createWhitePawn(), Piece.createWhitePawn()
        ));
        ranks.add(new Rank(
                Piece.createWhiteRook(), Piece.createWhiteKnight(), Piece.createWhiteBishop(),
                Piece.createWhiteQueen(), Piece.createWhiteKing(), Piece.createWhiteBishop(),
                Piece.createWhiteKnight(), Piece.createWhiteRook()
        ));
    }

    public void initializeEmpty() {
        for (int i = 0; i < 8; i++) {
            ranks.add(Rank.createBlankRank());
        }
    }

    public void print() {
        System.out.println(showBoard());
    }

    public int pieceCount() {
        return ranks.stream()
                .map(Rank::getPieceCount)
                .reduce(0, Integer::sum);
    }

    public int pieceCount(Piece.Type type, Piece.Color color) {
        return ranks.stream()
                .map(rank -> rank.getPieceCount(type, color))
                .reduce(0, Integer::sum);
    }

    public String showBoard() {
        return ranks.stream()
                .map(Rank::toString)
                .reduce("", String::concat);
    }

    public void move(String position, Piece piece) {
        validatePosition(position);
        int xPos = getXPosition(position);
        int yPos = getYPosition(position);
        ranks.get(yPos)
                .setPiece(xPos, piece);
    }

    private void validatePosition(String position) {
        if (position.charAt(0) < 'a' || position.charAt(0) > 'h') {
            throw new IllegalArgumentException("Invalid XPosition: " + position);
        }
        if (position.charAt(1) < '1' || position.charAt(1) > '8') {
            throw new IllegalArgumentException("Invalid YPosition: " + position);
        }
    }

    private int getXPosition(String position) {
        return position.charAt(0) - 'a';
    }

    private int getYPosition(String position) {
        return 7 - (position.charAt(1) - '1');
    }

    public Object findPiece(String position) {
        validatePosition(position);
        int xPos = getXPosition(position);
        int yPos = getYPosition(position);
        return ranks.get(yPos)
                .getPiece(xPos);
    }

    public double calculatePoint(Piece.Color color) {
        return ranks.stream()
                .mapToDouble(rank -> rank.calculateScore(color))
                .sum() - calculatePawnPenalty(color);
    }

    private double calculatePawnPenalty(Piece.Color color) {
        return IntStream.range(0, 8)
                .map(xPos -> pawnCount(color, xPos))
                .filter(count -> count > 1)
                .sum();
    }

    private int pawnCount(Piece.Color color, int xPos) {
        return (int) ranks.stream()
                .filter(rank -> rank.isSameColorPawn(color, xPos))
                .count();
    }
}
