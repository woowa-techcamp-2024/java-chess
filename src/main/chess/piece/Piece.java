package chess.piece;

public abstract class Piece {

    private final Color color;

    protected Piece() {
        this(Color.WHITE);
    }

    protected Piece(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    @Override
    public final String toString() {
        return isWhite() ? whiteRepresentation() : blackRepresentation();
    }

    protected abstract String whiteRepresentation();

    protected abstract String blackRepresentation();

    public static Pawn createWhitePawn() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn createBlackPawn() {
        return new Pawn(Color.BLACK);
    }

    public static Knight createWhiteKnight() {
        return new Knight(Color.WHITE);
    }

    public static Knight createBlackKnight() {
        return new Knight(Color.BLACK);
    }

    public static Bishop createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Bishop createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    public static Rook createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Rook createBlackRook() {
        return new Rook(Color.BLACK);
    }

    public static Queen createWhiteQueen() {
        return new Queen(Color.WHITE);
    }

    public static Queen createBlackQueen() {
        return new Queen(Color.BLACK);
    }

    public static King createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static King createBlackKing() {
        return new King(Color.BLACK);
    }

}
