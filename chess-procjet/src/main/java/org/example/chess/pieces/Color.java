package org.example.chess.pieces;

public enum Color {

    WHITE("흰색"),
    BLACK("검은색");

    private final String description;

    Color(String description) {
        this.description = description;
    }
}
