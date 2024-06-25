package pe.goblin.chess.board;

import pe.goblin.chess.pawn.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.
 * Pawn 이외의 다른 객체를 추가하면 컴파일 에러가 발생한다.
 *
 * @see #add(Pawn)
 */
public class Board {
    public static final int MIN_ROWS = 0;
    public static final int MAX_ROWS = 8;
    private static final int MAX_PAWN_PIECE = MAX_ROWS - MIN_ROWS;

    private List<Pawn> pawns = new ArrayList<>();

    public void add(Pawn pawn) throws ExceedPawnException {
        if (pawns.size() >= MAX_PAWN_PIECE * 2) {
            throw new ExceedPawnException();
        }
        long count = countPawns(pawn.getColor());
        if (count >= MAX_PAWN_PIECE) {
            throw new ExceedPawnException();
        }
        pawns.add(pawn);
    }

    private long countPawns(String color) {
        return pawns.stream()
                .filter(p -> Objects.equals(color, p.getColor()))
                .count();
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int idx) {
        return pawns.get(idx);
    }

    public void initialize() {
        ArrayList<Pawn> initializingPawns = new ArrayList<>();
        for (int i = MIN_ROWS; i < MAX_ROWS; i++) {
            initializingPawns.add(new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION));
            initializingPawns.add(new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
        }
        this.pawns = initializingPawns;
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        long countWhitePawn = countPawns(Pawn.WHITE_COLOR);
        for (int i = 0; i < countWhitePawn; i++) {
            sb.append(Pawn.WHITE_REPRESENTATION);
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        long countBlackPawn = countPawns(Pawn.BLACK_COLOR);
        for (int i = 0; i < countBlackPawn; i++) {
            sb.append(Pawn.BLACK_REPRESENTATION);
        }
        return sb.toString();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int row = MIN_ROWS; row < MAX_ROWS; row++) {
            switch (row) {
                case 1:
                    sb.append(getBlackPawnsResult()).append('\n');
                    break;
                case 6:
                    sb.append(getWhitePawnsResult()).append('\n');
                    break;
                default:
                    sb.append(getBlankRowResult()).append('\n');
            }
        }
        return sb.toString();
    }

    private String getBlankRowResult() {
        return "********";
    }
}
