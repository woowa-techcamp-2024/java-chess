package woowa.camp.pieces;

import java.util.Arrays;
import java.util.function.Predicate;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;

public enum Representation {

    p("p", Type.PAWN, Color.WHITE),
    P("P", Type.PAWN, Color.BLACK),
    r("r", Type.ROOK, Color.WHITE),
    R("R", Type.ROOK, Color.BLACK),
    n("n", Type.KNIGHT, Color.WHITE),
    N("N", Type.KNIGHT, Color.BLACK),
    b("b", Type.BISHOP, Color.WHITE),
    B("B", Type.BISHOP, Color.BLACK),
    q("q", Type.QUEEN, Color.WHITE),
    Q("Q", Type.QUEEN, Color.BLACK),
    k("k", Type.KING, Color.WHITE),
    K("K", Type.KING, Color.BLACK);

    private final String name;
    private final Type type;
    private final Color color;

    Representation(String name, Type type, Color color) {
        this.name = name;
        this.type = type;
        this.color = color;
    }

    public static Representation findMatchedRepresentation(final Type targetType, final Color targetColor) {
        return Arrays.stream(values())
                .filter(isMatched(targetType, targetColor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("{%s, %s} 에 대한 Representation이 존재하지 않습니다.", targetType.name(), targetColor)));
    }

    private static Predicate<Representation> isMatched(final Type targetType, final Color targetColor) {
        return representation -> representation.type == targetType && representation.color == targetColor;
    }

}
