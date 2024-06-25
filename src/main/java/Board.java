import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;
    private final List<Pawn> pawns = new ArrayList<>();

    public void add(Pawn pawn){
        pawns.add(pawn);
        size++;
    }

    public int size(){
        return this.size;
    }

    public Pawn findPawn(int idx) {
        return pawns.get(idx);
    }
}
