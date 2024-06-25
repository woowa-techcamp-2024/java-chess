package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

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
}
