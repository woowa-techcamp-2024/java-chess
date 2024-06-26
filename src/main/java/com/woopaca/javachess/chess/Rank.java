package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public String generateResult() {
        return pieces.stream()
                .map(piece -> piece == null ? "." : String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public int getPiecesCount() {
        return getPieces().size();
    }

    public int getPiecesCount(Color color, Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.getColor() == color && piece.getType() == type)
                .count();
    }

    public Piece findPieceByFile(int fileIndex) {
        return pieces.get(fileIndex);
    }

    public void moveTo(int file, Piece piece) {
        pieces.set(file, piece);
    }

    public double calculatePointWithoutPawns(Color color) {
        return pieces.stream()
                .filter(piece -> piece.getType() != Type.PAWN && piece.getColor() == color)
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    public List<Piece> getPieces() {
        return pieces.stream()
                .filter(piece -> piece.getType() != Type.NO_PIECE)
                .toList();
    }

}
