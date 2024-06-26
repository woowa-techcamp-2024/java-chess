package chess.piece;

public class Blank extends Piece {

    private Blank(final PieceColor color) {
        super(color);

        if(!color.equals(PieceColor.NO_COLOR)) {
            throw new IllegalArgumentException("빈 칸이 아닙니다.");
        }
    }

    public static Blank create() {
        return new Blank(PieceColor.NO_COLOR);
    }

    @Override
    public Type getType() {
        return Type.NO_PIECE;
    }
}
