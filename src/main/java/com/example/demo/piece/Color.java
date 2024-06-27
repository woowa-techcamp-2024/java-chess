package com.example.demo.piece;

public enum Color {
    WHITE, BLACK
    ;

    public Color opposite(){
        return switch (this) {
            case WHITE -> BLACK;
            case BLACK -> WHITE;
        };
    }
}
