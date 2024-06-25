package chess.pieces;

public class Pawn {

    private final Color color;

    private final char representation;

    public enum Color {

        WHITE('p'),
        BLACK('P');

        Color(char representation) {
            this.representation = representation;
        }

        private final char representation;

        public char getRepresentation() { return representation; }
    }

    public Pawn(Color color) {
        this.color = color;
        this.representation = color.getRepresentation();
    }

    public Pawn() {
        this.color = Color.WHITE;
        this.representation = this.color.getRepresentation();
    }

    public Color getColor() {
        return color;
    }

    public boolean verifyPawn(final Color color) {
        return color.equals(this.color);
    }

    public char getRepresentation() {
        return representation;
    }

}
