package woowa.camp.pieces;

public enum Color {

    PAWN_WHITE("white", "p"),
    PAWN_BLACK("black", "P");

    private final String name;
    private final String representation;

    Color(String name, String representation) {
        this.name = name;
        this.representation = representation;
    }

    public String getName() {
        return name;
    }

    public String getRepresentation() {
        return representation;
    }
}
