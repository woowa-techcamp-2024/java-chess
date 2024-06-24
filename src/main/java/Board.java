import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<ChessPiece> pieces;
    public Board(){
        pieces = new ArrayList<>();
    }

    public int size(){
        return pieces.size();
    }

    public void add(ChessPiece piece){
        pieces.add(piece);
    }

    public ChessPiece findPawn(int index){
        return pieces.get(index);
    }
}
