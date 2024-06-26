package woowa.camp.pieces;

public enum Color {

    PAWN_WHITE("white", "p"),
    PAWN_BLACK("black", "P"),

    ROOK_WHITE("white", "r"),
    ROOK_BLACK("black", "R"),

    KNIGHT_WHITE("white", "n"),
    KNIGHT_BLACK("black", "N"),

    BISHOP_WHITE("white", "b"),
    BISHOP_BLACK("black", "B"),

    QUEEN_WHITE("white", "q"),
    QUEEN_BLACK("black", "Q"),

    KING_WHITE("white", "k"),
    KING_BLACK("black", "K");

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
