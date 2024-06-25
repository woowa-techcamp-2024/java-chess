package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chess.pieces.PieceTypes.*;

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
        board[x][y] = piece;
    }

    public void initialize(){
        //pawn 셋팅
        for(int w=0;w<WIDTH;w++){
            Piece whitePawn = Piece.createPiece(WHITE_PAWN);
            Piece blackPawn = Piece.createPiece(BLACK_PAWN);
            setPiece(WHITE_PAWN_START_LINE,w,whitePawn);
            setPiece(BLACK_PAWN_START_LINE,w,blackPawn);
        }

        //rook 셋팅
        Piece whiteRookLeft = Piece.createPiece(WHITE_ROOK);
        Piece whiteRookRight = Piece.createPiece(WHITE_ROOK);
        Piece blackRookLeft = Piece.createPiece(BLACK_ROOK);
        Piece blackRookRight = Piece.createPiece(BLACK_ROOK);
        setPiece(BLACK_START_LINE,0,blackRookLeft);
        setPiece(BLACK_START_LINE,WIDTH-1,blackRookRight);
        setPiece(WHITE_START_LINE,0,whiteRookLeft);
        setPiece(WHITE_START_LINE,WIDTH-1,whiteRookRight);

        //night 셋팅
        Piece whiteNightLeft = Piece.createPiece(WHITE_KNIGHT);
        Piece whiteNightRight = Piece.createPiece(WHITE_KNIGHT);
        Piece blackNightLeft = Piece.createPiece(BLACK_KNIGHT);
        Piece blackNightRight = Piece.createPiece(BLACK_KNIGHT);
        setPiece(BLACK_START_LINE,1,blackNightLeft);
        setPiece(BLACK_START_LINE,WIDTH-2,blackNightRight);
        setPiece(WHITE_START_LINE,1,whiteNightLeft);
        setPiece(WHITE_START_LINE,WIDTH-2,whiteNightRight);

        //bishop 셋팅
        Piece whiteBishopLeft = Piece.createPiece(WHITE_BISHOP);
        Piece whiteBishopRight = Piece.createPiece(WHITE_BISHOP);
        Piece blackBishopLeft = Piece.createPiece(BLACK_BISHOP);
        Piece blackBishopRight = Piece.createPiece(BLACK_BISHOP);
        setPiece(BLACK_START_LINE,2,blackBishopLeft);
        setPiece(BLACK_START_LINE,WIDTH-3,blackBishopRight);
        setPiece(WHITE_START_LINE,2,whiteBishopLeft);
        setPiece(WHITE_START_LINE,WIDTH-3,whiteBishopRight);

        //queen & king setting
        Piece whiteQueen = Piece.createPiece(WHITE_QUEEN);
        Piece whiteKing = Piece.createPiece(WHITE_KING);
        Piece blackQueen = Piece.createPiece(BLACK_QUEEN);
        Piece blackKing = Piece.createPiece(BLACK_KING);
        setPiece(BLACK_START_LINE,3,blackQueen);
        setPiece(BLACK_START_LINE,WIDTH-4,blackKing);
        setPiece(WHITE_START_LINE,3,whiteQueen);
        setPiece(WHITE_START_LINE,WIDTH-4,whiteKing);

        //아무것도 없는 칸들을 blank piece로 채우기
        for(int x=0;x<HEIGHT;x++){
            for(int y=0;y<WIDTH;y++){
                if(board[x][y] == null){
                    board[x][y] = Piece.createPiece(NO_PIECE);
                }
            }
        }
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

    public int findPieceWithColorAndType(Color color,Type type){
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(p->p!=null&&color.equals(p.getColor())&&type.equals(p.getType()))
                .toList().size();
    }
}
