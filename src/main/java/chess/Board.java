package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private List<ChessPiece> pieces;
    private List<Piece> whitePawns;
    private List<Piece> blackPawns;

    private ChessPiece[][] board;
    public Board(){
        pieces = new ArrayList<>();
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();
        board = new ChessPiece[HEIGHT][WIDTH];
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

    public void initialize(){
        for(int w=0;w<WIDTH;w++){
            Piece whitePawn = new Piece(Piece.WHITE_COLOR);
            Piece blackPawn = new Piece(Piece.BLACK_COLOR);
            whitePawns.add(whitePawn);
            blackPawns.add(blackPawn);
            pieces.add(whitePawn);
            pieces.add(blackPawn);
            board[1][w] = blackPawn;
            board[6][w] = whitePawn;
        }
    }

    private String getResult(List<? extends ChessPiece> pieces){
        return pieces.stream()
                .map(ChessPiece::getRepresentation)
                .map(String::valueOf)
                .reduce((x,y)->x+y)
                .get();
    }

    public String getWhitePawnsResult(){
        return getResult(whitePawns);
    }

    public String getBlackPawnsResult(){
        return getResult(blackPawns);
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        for(int h=0;h<HEIGHT;h++){
            for(int w=0;w<WIDTH;w++){
                sb.append(board[h][w] == null ? '.' : board[h][w].getRepresentation());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
