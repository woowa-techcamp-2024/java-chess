package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.woowatechcamp.utils.StringUtils.appendNewLine;

public class Board {
    private final List<List<Piece>> pieces;

    public Board() {
        pieces = new ArrayList<>();
    }

    public void initialize() {
        pieces.add(Arrays.asList(
                Piece.createBlackRook(), Piece.createBlackKnight(), Piece.createBlackBishop(),
                Piece.createBlackQueen(), Piece.createBlackKing(), Piece.createBlackBishop(),
                Piece.createBlackKnight(), Piece.createBlackRook()
        ));
        pieces.add(Arrays.asList(
                Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(),
                Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(),
                Piece.createBlackPawn(), Piece.createBlackPawn()
        ));

        for (int i = 0; i < 4; i++) {
            pieces.add(Arrays.asList(
                    Piece.createEmpty(), Piece.createEmpty(), Piece.createEmpty(),
                    Piece.createEmpty(), Piece.createEmpty(), Piece.createEmpty(),
                    Piece.createEmpty(), Piece.createEmpty()
            ));
        }

        pieces.add(Arrays.asList(
                Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(),
                Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(),
                Piece.createWhitePawn(), Piece.createWhitePawn()
        ));
        pieces.add(Arrays.asList(
                Piece.createWhiteRook(), Piece.createWhiteKnight(), Piece.createWhiteBishop(),
                Piece.createWhiteQueen(), Piece.createWhiteKing(), Piece.createWhiteBishop(),
                Piece.createWhiteKnight(), Piece.createWhiteRook()
        ));
    }

    private String piecesToString(List<Piece> pieces) {
        StringBuilder result = new StringBuilder();
        pieces.forEach(piece -> result.append(piece.toString()));
        appendNewLine(result);
        return result.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    public int pieceCount() {
        return (int) pieces.stream()
                .flatMap(List::stream)
                .filter(Piece::isNotEmpty)
                .count();
    }

    public String showBoard() {
        StringBuilder result = new StringBuilder();
        pieces.forEach(row -> result.append(piecesToString(row)));
        return result.toString();
    }
}
