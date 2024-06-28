package pieces;

public class PieceDescriptor {
    public static PieceUnicode getUnicode(PieceColor color, PieceType type)
    {
        if(color.equals(PieceColor.NO_COLOR) && type.equals(PieceType.NO_PIECE))
        {
            return PieceUnicode.BLANK;
        }
        return PieceUnicode.valueOf(color.getColor() + "_" + type.getType());
    }
}
