package woowa.camp.pieces;

public enum Color {

    PAWN_WHITE("white", 'p'),
    PAWN_BLACK("black", 'P');

    private final String name;
    private final char representation;

    Color(String name, char representation) {
        this.name = name;
        this.representation = representation;
    }

    public String getName() {
        return name;
    }

    public char getRepresentation() {
        return representation;
    }
}
