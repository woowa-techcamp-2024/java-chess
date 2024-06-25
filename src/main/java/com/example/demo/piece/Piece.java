package com.example.demo.piece;

public interface Piece extends Comparable<Piece> {

    Color getColor();

    float getPoint();

    Type getType();
}
