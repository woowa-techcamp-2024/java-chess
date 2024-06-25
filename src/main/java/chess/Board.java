package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Piece;
import chess.pieces.PieceTypes;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private List<ChessPiece> pieces;
    private ChessPiece[][] board;
    public Board(){
        pieces = new ArrayList<>();
        board = new ChessPiece[HEIGHT][WIDTH];
    }

    public int size(){
        return pieces.size();
    }

    public void add(ChessPiece piece){
        pieces.add(piece);
    }

    public ChessPiece findPiece(int index){
        return pieces.get(index);
    }

    public void initialize(){
        for(int w=0;w<WIDTH;w++){
            Piece whitePawn = Piece.createPiece(PieceTypes.WHITE_PAWN);
            Piece blackPawn = Piece.createPiece(PieceTypes.BLACK_PAWN);
            pieces.add(whitePawn);
            pieces.add(blackPawn);
            board[1][w] = blackPawn;
            board[6][w] = whitePawn;
        }
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
