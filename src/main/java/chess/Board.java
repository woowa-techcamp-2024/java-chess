package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Piece;
import chess.pieces.PieceTypes;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private final int WHITE_START_LINE = 0;
    private final int WHITE_PAWN_START_LINE = WHITE_START_LINE+1;
    private final int BLACK_START_LINE = HEIGHT-1;
    private final int BLACK_PAWN_START_LINE = BLACK_START_LINE-1;
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

    private void setPiece(int x,int y,ChessPiece piece){
        if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) throw new IllegalArgumentException("범위 안의 좌표를 입력해주세요");
        pieces.add(piece);
        board[x][y] = piece;
    }

    public void initialize(){
        //pawn 셋팅
        for(int w=0;w<WIDTH;w++){
            Piece whitePawn = Piece.createPiece(PieceTypes.WHITE_PAWN);
            Piece blackPawn = Piece.createPiece(PieceTypes.BLACK_PAWN);
            setPiece(WHITE_PAWN_START_LINE,w,whitePawn);
            setPiece(BLACK_PAWN_START_LINE,w,blackPawn);
        }

        //rook 셋팅
        Piece whiteRookLeft = Piece.createPiece(PieceTypes.WHITE_ROOK);
        Piece whiteRookRight = Piece.createPiece(PieceTypes.WHITE_ROOK);
        Piece blackRookLeft = Piece.createPiece(PieceTypes.BLACK_ROOK);
        Piece blackRookRight = Piece.createPiece(PieceTypes.BLACK_ROOK);
        setPiece(BLACK_START_LINE,0,blackRookLeft);
        setPiece(BLACK_START_LINE,WIDTH-1,blackRookRight);
        setPiece(WHITE_START_LINE,0,whiteRookLeft);
        setPiece(WHITE_START_LINE,WIDTH-1,whiteRookRight);

        //night 셋팅
        Piece whiteNightLeft = Piece.createPiece(PieceTypes.WHITE_KNIGHT);
        Piece whiteNightRight = Piece.createPiece(PieceTypes.WHITE_KNIGHT);
        Piece blackNightLeft = Piece.createPiece(PieceTypes.BLACK_KNIGHT);
        Piece blackNightRight = Piece.createPiece(PieceTypes.BLACK_KNIGHT);
        setPiece(BLACK_START_LINE,1,blackNightLeft);
        setPiece(BLACK_START_LINE,WIDTH-2,blackNightRight);
        setPiece(WHITE_START_LINE,1,whiteNightLeft);
        setPiece(WHITE_START_LINE,WIDTH-2,whiteNightRight);

        //bishop 셋팅
        Piece whiteBishopLeft = Piece.createPiece(PieceTypes.WHITE_BISHOP);
        Piece whiteBishopRight = Piece.createPiece(PieceTypes.WHITE_BISHOP);
        Piece blackBishopLeft = Piece.createPiece(PieceTypes.BLACK_BISHOP);
        Piece blackBishopRight = Piece.createPiece(PieceTypes.BLACK_BISHOP);
        setPiece(BLACK_START_LINE,2,blackBishopLeft);
        setPiece(BLACK_START_LINE,WIDTH-3,blackBishopRight);
        setPiece(WHITE_START_LINE,2,whiteBishopLeft);
        setPiece(WHITE_START_LINE,WIDTH-3,whiteBishopRight);

        //queen & king setting
        Piece whiteQueen = Piece.createPiece(PieceTypes.WHITE_QUEEN);
        Piece whiteKing = Piece.createPiece(PieceTypes.WHITE_KING);
        Piece blackQueen = Piece.createPiece(PieceTypes.BLACK_QUEEN);
        Piece blackKing = Piece.createPiece(PieceTypes.BLACK_KING);
        setPiece(BLACK_START_LINE,3,blackQueen);
        setPiece(BLACK_START_LINE,WIDTH-4,blackKing);
        setPiece(WHITE_START_LINE,3,whiteQueen);
        setPiece(WHITE_START_LINE,WIDTH-4,whiteKing);
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        for(int h=HEIGHT-1;h>=0;h--){
            for(int w=0;w<WIDTH;w++){
                sb.append(board[h][w] == null ? '.' : board[h][w].getRepresentation());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
