package chess.pieces;

public class Pawn implements ChessPiece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';
    private String color;
    private char representation;

    public Pawn() {
        this(WHITE_COLOR,WHITE_REPRESENTATION);
    }
    public Pawn(String color){
        this(color,getRepresentationAccordingToColor(color));
    }

    private Pawn(String color,char representation) {
        this.color = color;
        this.representation = representation;
    }

    private static char getRepresentationAccordingToColor(String color) {
        return switch(color){
            case WHITE_COLOR -> WHITE_REPRESENTATION;
            case BLACK_COLOR -> BLACK_REPRESENTATION;
            default -> throw new IllegalArgumentException("해당 색상의 Pawn을 생성할 수 없습니다.");
        };
    }


    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }
}
