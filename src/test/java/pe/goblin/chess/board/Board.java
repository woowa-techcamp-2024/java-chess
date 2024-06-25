package pe.goblin.chess.board;

import pe.goblin.chess.pawn.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * 체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.
 * Pawn 이외의 다른 객체를 추가하면 컴파일 에러가 발생한다.
 *
 * @see #add(Pawn)
 */
public class Board {
    private static final int MAX_PAWN_PIECE = 2;

    private final List<Pawn> pawns = new ArrayList<>();

    public void add(Pawn pawn) throws ExceedPawnException {
        if (pawns.size() >= MAX_PAWN_PIECE) {
            throw new ExceedPawnException();
        }
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int idx) {
        return pawns.get(idx);
    }
}
