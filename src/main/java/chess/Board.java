package chess;

import chess.pieces.ChessPiece;
import chess.pieces.PieceFactory;
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
    private final PieceCreator pieceCreator;


    private List<ChessPiece> chessPieces;
    private ChessPiece[][] board;
    public Board(PieceCreator pieceCreator){
        chessPieces = new ArrayList<>();
        board = new ChessPiece[HEIGHT][WIDTH];
        this.pieceCreator = pieceCreator;
    }

    public int size(){
        return chessPieces.size();
    }

    public void add(ChessPiece chessPiece){
        chessPieces.add(chessPiece);
    }

    public ChessPiece findPiece(int index){
        return chessPieces.get(index);
    }

    protected int getCol(String position){
        char col = position.charAt(0);
        return col-'a';
    }

    protected int getRow(String position){
        char row = position.charAt(1);
        return row-'1';
    }

    private boolean isIn(int x,int y){
        return 0<=x&&x<HEIGHT&&0<=y&&y<WIDTH;
    }

    protected boolean isIn(String position){
        int row = getRow(position);
        int col = getCol(position);

        return isIn(row,col);
    }

    public ChessPiece findPiece(String position){
        int row = getRow(position);
        int col = getCol(position);
        return board[row][col];
    }

    public int pieceCount(){
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(p->p!=null&&!Type.NO_PIECE.equals(p.getType()))
                .toList()
                .size();
    }

    private void setPiece(int x, int y, ChessPiece chessPiece){
        if(!isIn(x,y)) throw new IllegalArgumentException("범위 안의 좌표를 입력해주세요");
        board[x][y] = chessPiece;
    }
    private void setPiece(String position, ChessPiece chessPiece){
        int row = getRow(position);
        int col = getCol(position);
        setPiece(row,col, chessPiece);
    }

    public void initialize(){
        //pawn 셋팅
        for(int w=0;w<WIDTH;w++){
            ChessPiece whitePawn = PieceFactory.createPiece(WHITE_PAWN);
            ChessPiece blackPawn = PieceFactory.createPiece(BLACK_PAWN);
            setPiece(WHITE_PAWN_START_LINE,w,whitePawn);
            setPiece(BLACK_PAWN_START_LINE,w,blackPawn);
        }

        //rook 셋팅
        ChessPiece whiteRookLeft = PieceFactory.createPiece(WHITE_ROOK);
        ChessPiece whiteRookRight = PieceFactory.createPiece(WHITE_ROOK);
        ChessPiece blackRookLeft = PieceFactory.createPiece(BLACK_ROOK);
        ChessPiece blackRookRight = PieceFactory.createPiece(BLACK_ROOK);
        setPiece(BLACK_START_LINE,0,blackRookLeft);
        setPiece(BLACK_START_LINE,WIDTH-1,blackRookRight);
        setPiece(WHITE_START_LINE,0,whiteRookLeft);
        setPiece(WHITE_START_LINE,WIDTH-1,whiteRookRight);

        //night 셋팅
        ChessPiece whiteNightLeft = PieceFactory.createPiece(WHITE_KNIGHT);
        ChessPiece whiteNightRight = PieceFactory.createPiece(WHITE_KNIGHT);
        ChessPiece blackNightLeft = PieceFactory.createPiece(BLACK_KNIGHT);
        ChessPiece blackNightRight = PieceFactory.createPiece(BLACK_KNIGHT);
        setPiece(BLACK_START_LINE,1,blackNightLeft);
        setPiece(BLACK_START_LINE,WIDTH-2,blackNightRight);
        setPiece(WHITE_START_LINE,1,whiteNightLeft);
        setPiece(WHITE_START_LINE,WIDTH-2,whiteNightRight);

        //bishop 셋팅
        ChessPiece whiteBishopLeft = PieceFactory.createPiece(WHITE_BISHOP);
        ChessPiece whiteBishopRight = PieceFactory.createPiece(WHITE_BISHOP);
        ChessPiece blackBishopLeft = PieceFactory.createPiece(BLACK_BISHOP);
        ChessPiece blackBishopRight = PieceFactory.createPiece(BLACK_BISHOP);
        setPiece(BLACK_START_LINE,2,blackBishopLeft);
        setPiece(BLACK_START_LINE,WIDTH-3,blackBishopRight);
        setPiece(WHITE_START_LINE,2,whiteBishopLeft);
        setPiece(WHITE_START_LINE,WIDTH-3,whiteBishopRight);

        //queen & king setting
        ChessPiece whiteQueen = PieceFactory.createPiece(WHITE_QUEEN);
        ChessPiece whiteKing = PieceFactory.createPiece(WHITE_KING);
        ChessPiece blackQueen = PieceFactory.createPiece(BLACK_QUEEN);
        ChessPiece blackKing = PieceFactory.createPiece(BLACK_KING);
        setPiece(BLACK_START_LINE,3,blackQueen);
        setPiece(BLACK_START_LINE,WIDTH-4,blackKing);
        setPiece(WHITE_START_LINE,3,whiteQueen);
        setPiece(WHITE_START_LINE,WIDTH-4,whiteKing);

        //아무것도 없는 칸들을 blank piece로 채우기
        for(int x=0;x<HEIGHT;x++){
            for(int y=0;y<WIDTH;y++){
                if(board[x][y] == null){
                    board[x][y] = PieceFactory.createPiece(NO_PIECE);
                }
            }
        }
    }

    public void initializeEmpty(){
        for(int h=0;h<HEIGHT;h++){
            for(int w=0;w<WIDTH;w++){
                board[h][w] = PieceFactory.createPiece(NO_PIECE);
            }
        }
    }

    private void removePiece(ChessPiece chessPiece){
        for(int h=0;h<HEIGHT;h++){
            for(int w=0;w<WIDTH;w++){
                if(board[h][w].equals(chessPiece)){
                    board[h][w] = PieceFactory.createPiece(NO_PIECE);
                    return;
                }
            }
        }
    }

    public void move(String position, ChessPiece chessPiece){
        removePiece(chessPiece);
        int row = getRow(position);
        int col = getCol(position);

        board[row][col] = chessPiece;
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

    public double calculatePoint(Color color){
        double sum = 0.0;
        for(int h=0;h<HEIGHT;h++){
            for(int w=0;w<WIDTH;w++){
                Color c = board[h][w].getColor();
                Type t = board[h][w].getType();

                if(color.equals(c)){
                    if(Type.PAWN.equals(t) && hasPawnVertically(h,w)){
                        sum+=0.5;
                    }
                    else{
                        sum+=t.getPoint();
                    }
                }
            }
        }
        return sum;
    }

    public List<ChessPiece> getPieceOrderByPoint(Color color, boolean isAsc){
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(c->color.equals(c.getColor()))
                .sorted((o1,o2)-> isAsc ? Double.compare(o1.getType().getPoint(),o2.getType().getPoint()) : Double.compare(o2.getType().getPoint(),o1.getType().getPoint()))
                .toList();
    }

    private boolean hasPawnVertically(int x,int y){
        return (isIn(x+1,y) && Type.PAWN.equals(board[x+1][y].getType()) || isIn(x-1,y) && Type.PAWN.equals(board[x-1][y].getType()));
    }

    public void move(String sourcePosition,String targetPosition){
        ChessPiece source = findPiece(sourcePosition);
        if(Type.NO_PIECE.equals(source.getType())){
            return;
        }
        ChessPiece target = findPiece(targetPosition);

        removePiece(target);
        move(targetPosition,source);
        setPiece(sourcePosition, PieceFactory.createPiece(NO_PIECE));
    }
}
