package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.PieceFactory;
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
                PieceFactory.createBlackRook(new Position("a8")), PieceFactory.createBlackKnight(new Position("b8")), PieceFactory.createBlackBishop(new Position("c8")),
                PieceFactory.createBlackQueen(new Position("d8")), PieceFactory.createBlackKing(new Position("e8")), PieceFactory.createBlackBishop(new Position("f8")),
                PieceFactory.createBlackKnight(new Position("g8")), PieceFactory.createBlackRook(new Position("h8"))
        ));
        ranks.add(new Rank(
                PieceFactory.createBlackPawn(new Position("a7")), PieceFactory.createBlackPawn(new Position("b7")), PieceFactory.createBlackPawn(new Position("c7")),
                PieceFactory.createBlackPawn(new Position("d7")), PieceFactory.createBlackPawn(new Position("e7")), PieceFactory.createBlackPawn(new Position("f7")),
                PieceFactory.createBlackPawn(new Position("g7")), PieceFactory.createBlackPawn(new Position("h7"))
        ));

        for (int yPos = 6; yPos >= 3; yPos--) {
            ranks.add(Rank.createBlankRank(yPos));
        }

        ranks.add(new Rank(
                PieceFactory.createWhitePawn(new Position("a2")), PieceFactory.createWhitePawn(new Position("b2")), PieceFactory.createWhitePawn(new Position("c2")),
                PieceFactory.createWhitePawn(new Position("d2")), PieceFactory.createWhitePawn(new Position("e2")), PieceFactory.createWhitePawn(new Position("f2")),
                PieceFactory.createWhitePawn(new Position("g2")), PieceFactory.createWhitePawn(new Position("h2"))
        ));
        ranks.add(new Rank(
                PieceFactory.createWhiteRook(new Position("a1")), PieceFactory.createWhiteKnight(new Position("b1")), PieceFactory.createWhiteBishop(new Position("c1")),
                PieceFactory.createWhiteQueen(new Position("d1")), PieceFactory.createWhiteKing(new Position("e1")), PieceFactory.createWhiteBishop(new Position("f1")),
                PieceFactory.createWhiteKnight(new Position("g1")), PieceFactory.createWhiteRook(new Position("h1"))
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

    public void move(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        sourcePiece.move(target, this);
        ranks.get(target.getYPos())
                .setPiece(sourcePiece);
        ranks.get(source.getYPos())
                .setPiece(PieceFactory.createBlank(source));
    }

    public boolean isOccupiedBySameColor(Position piecePosition, Piece.Color color) {
        return findPiece(piecePosition).getColor() == color;
    }

    public boolean isOccupied(Position position) {
        return findPiece(position).isNotBlank();
    }

    public Piece findPiece(Position position) {
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

    public boolean isPathClear(Position from, Position to) {
        int deltaX = Integer.compare(to.getXPos() - from.getXPos(), 0);
        int deltaY = Integer.compare(to.getYPos() - from.getYPos(), 0);

        if (deltaX == 0 && deltaY == 0) {
            return true;
        }

        int currentX = from.getXPos() + deltaX;
        int currentY = from.getYPos() + deltaY;

        while (currentX != to.getXPos() || currentY != to.getYPos()) {
            if (ranks.get(currentY).getPiece(currentX).isNotBlank()) {
                return false;
            }
            currentX += deltaX;
            currentY += deltaY;

            // 목적지 직전에 도달하면 루프를 종료합니다.
            if (currentX == to.getXPos() && currentY == to.getYPos()) {
                break;
            }
        }
        return true;
    }

    public boolean isKingAlive(Piece.Color color) {
        return ranks.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .anyMatch(piece -> piece.getType() == Piece.Type.KING && piece.getColor() == color);
    }
}
