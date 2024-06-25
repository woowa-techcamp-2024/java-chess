package pe.goblin.chess.board;

import pe.goblin.chess.pawn.Piece;
import pe.goblin.chess.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.
 * Pawn 이외의 다른 객체를 추가하면 컴파일 에러가 발생한다.
 *
 * @see #add(Piece)
 */
public class Board {
    public static final int MIN_ROWS = 0;
    public static final int MAX_ROWS = 8;
    private static final int MAX_PAWN_PIECE = MAX_ROWS - MIN_ROWS;

    private List<Piece> pieces = new ArrayList<>();

    public void add(Piece piece) throws ExceedPawnException {
        if (pieces.size() >= MAX_PAWN_PIECE * 2) {
            throw new ExceedPawnException();
        }
        long count = countPawns(piece.getColor());
        if (count >= MAX_PAWN_PIECE) {
            throw new ExceedPawnException();
        }
        pieces.add(piece);
    }

    private long countPawns(String color) {
        return pieces.stream()
                .filter(p -> Objects.equals(color, p.getColor()))
                .count();
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(int idx) {
        return pieces.get(idx);
    }

    public void initialize() {
        ArrayList<Piece> initializingPieces = new ArrayList<>();
        for (int i = MIN_ROWS; i < MAX_ROWS; i++) {
            initializingPieces.add(new Piece(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION));
            initializingPieces.add(new Piece(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION));
        }
        this.pieces = initializingPieces;
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        long countWhitePawn = countPawns(Piece.WHITE_COLOR);
        for (int i = 0; i < countWhitePawn; i++) {
            sb.append(Piece.WHITE_REPRESENTATION);
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        long countBlackPawn = countPawns(Piece.BLACK_COLOR);
        for (int i = 0; i < countBlackPawn; i++) {
            sb.append(Piece.BLACK_REPRESENTATION);
        }
        return sb.toString();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int row = MIN_ROWS; row < MAX_ROWS; row++) {
            switch (row) {
                case 1:
                    sb.append(StringUtils.appendNewLine(getBlackPawnsResult()));
                    break;
                case 6:
                    sb.append(StringUtils.appendNewLine(getWhitePawnsResult()));
                    break;
                default:
                    sb.append(StringUtils.appendNewLine(getBlankRowResult()));
            }
        }
        return sb.toString();
    }

    private String getBlankRowResult() {
        return "********";
    }
}
