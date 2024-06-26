package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private final List<Rank> ranks;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    public Board() {
        ranks = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
    }

    public void initialize() {
        ranks.add(new Rank(
                Piece.createBlackRook(new Position("a8")), Piece.createBlackKnight(new Position("b8")), Piece.createBlackBishop(new Position("c8")),
                Piece.createBlackQueen(new Position("d8")), Piece.createBlackKing(new Position("e8")), Piece.createBlackBishop(new Position("f8")),
                Piece.createBlackKnight(new Position("g8")), Piece.createBlackRook(new Position("h8"))
        ));
        ranks.add(new Rank(
                Piece.createBlackPawn(new Position("a7")), Piece.createBlackPawn(new Position("b7")), Piece.createBlackPawn(new Position("c7")),
                Piece.createBlackPawn(new Position("d7")), Piece.createBlackPawn(new Position("e7")), Piece.createBlackPawn(new Position("f7")),
                Piece.createBlackPawn(new Position("g7")), Piece.createBlackPawn(new Position("h7"))
        ));

        for (int yPos = 6; yPos >= 3; yPos--) {
            ranks.add(Rank.createBlankRank(yPos));
        }

        ranks.add(new Rank(
                Piece.createWhitePawn(new Position("a2")), Piece.createWhitePawn(new Position("b2")), Piece.createWhitePawn(new Position("c2")),
                Piece.createWhitePawn(new Position("d2")), Piece.createWhitePawn(new Position("e2")), Piece.createWhitePawn(new Position("f2")),
                Piece.createWhitePawn(new Position("g2")), Piece.createWhitePawn(new Position("h2"))
        ));
        ranks.add(new Rank(
                Piece.createWhiteRook(new Position("a1")), Piece.createWhiteKnight(new Position("b1")), Piece.createWhiteBishop(new Position("c1")),
                Piece.createWhiteQueen(new Position("d1")), Piece.createWhiteKing(new Position("e1")), Piece.createWhiteBishop(new Position("f1")),
                Piece.createWhiteKnight(new Position("g1")), Piece.createWhiteRook(new Position("h1"))
        ));

        ranks.stream()
                .map(Rank::getPieces)
                .flatMap(List::stream)
                .forEach(this::addPiece);
    }

    public void initializeEmpty() {
        for (int yPos = 8; yPos >= 1; yPos--) {
            ranks.add(Rank.createBlankRank(yPos));
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

    public void move(Piece piece) {
        ranks.get(piece.getPosition().getYPos())
                .setPiece(piece);
        addPiece(piece);
    }

    public Object findPiece(Position position) {
        return ranks.get(position.getYPos())
                .getPiece(position.getXPos());
    }

    public double calculatePoint(Piece.Color color) {
        return ranks.stream()
                .mapToDouble(rank -> rank.calculatePoint(color))
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

    private void addPiece(Piece piece) {
        if (piece.isWhite()) {
            addWhitePiece(piece);
        } else if (piece.isBlack()) {
            addWBlackPiece(piece);
        }
    }

    private void addWhitePiece(Piece piece) {
        whitePieces.add(piece);
        whitePieces.sort((o1, o2) -> Double.compare(o1.getType().getPoint(), o2.getType().getPoint()));
    }

    private void addWBlackPiece(Piece piece) {
        blackPieces.add(piece);
        blackPieces.sort((o1, o2) -> Double.compare(o1.getType().getPoint(), o2.getType().getPoint()));
    }
}
