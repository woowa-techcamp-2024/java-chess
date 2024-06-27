package chess;

import chess.pieces.ChessPiece;
import chess.pieces.PieceFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        if(position.length() != 2) return false;
        int row = getRow(position);
        int col = getCol(position);

        return isIn(row,col);
    }

    public Optional<ChessPiece> findPiece(String position){
        int row = getRow(position);
        int col = getCol(position);
        return Optional.ofNullable(isIn(row,col) ? board[row][col] : null);
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
            ChessPiece whitePawn = pieceCreator.createPiece(WHITE_PAWN);
            ChessPiece blackPawn = pieceCreator.createPiece(BLACK_PAWN);
            setPiece(WHITE_PAWN_START_LINE,w,whitePawn);
            setPiece(BLACK_PAWN_START_LINE,w,blackPawn);
        }

        //rook 셋팅
        ChessPiece whiteRookLeft = pieceCreator.createPiece(WHITE_ROOK);
        ChessPiece whiteRookRight = pieceCreator.createPiece(WHITE_ROOK);
        ChessPiece blackRookLeft = pieceCreator.createPiece(BLACK_ROOK);
        ChessPiece blackRookRight = pieceCreator.createPiece(BLACK_ROOK);
        setPiece(BLACK_START_LINE,0,blackRookLeft);
        setPiece(BLACK_START_LINE,WIDTH-1,blackRookRight);
        setPiece(WHITE_START_LINE,0,whiteRookLeft);
        setPiece(WHITE_START_LINE,WIDTH-1,whiteRookRight);

        //night 셋팅
        ChessPiece whiteNightLeft = pieceCreator.createPiece(WHITE_KNIGHT);
        ChessPiece whiteNightRight = pieceCreator.createPiece(WHITE_KNIGHT);
        ChessPiece blackNightLeft = pieceCreator.createPiece(BLACK_KNIGHT);
        ChessPiece blackNightRight = pieceCreator.createPiece(BLACK_KNIGHT);
        setPiece(BLACK_START_LINE,1,blackNightLeft);
        setPiece(BLACK_START_LINE,WIDTH-2,blackNightRight);
        setPiece(WHITE_START_LINE,1,whiteNightLeft);
        setPiece(WHITE_START_LINE,WIDTH-2,whiteNightRight);

        //bishop 셋팅
        ChessPiece whiteBishopLeft = pieceCreator.createPiece(WHITE_BISHOP);
        ChessPiece whiteBishopRight = pieceCreator.createPiece(WHITE_BISHOP);
        ChessPiece blackBishopLeft = pieceCreator.createPiece(BLACK_BISHOP);
        ChessPiece blackBishopRight = pieceCreator.createPiece(BLACK_BISHOP);
        setPiece(BLACK_START_LINE,2,blackBishopLeft);
        setPiece(BLACK_START_LINE,WIDTH-3,blackBishopRight);
        setPiece(WHITE_START_LINE,2,whiteBishopLeft);
        setPiece(WHITE_START_LINE,WIDTH-3,whiteBishopRight);

        //queen & king setting
        ChessPiece whiteQueen = pieceCreator.createPiece(WHITE_QUEEN);
        ChessPiece whiteKing = pieceCreator.createPiece(WHITE_KING);
        ChessPiece blackQueen = pieceCreator.createPiece(BLACK_QUEEN);
        ChessPiece blackKing = pieceCreator.createPiece(BLACK_KING);
        setPiece(BLACK_START_LINE,3,blackQueen);
        setPiece(BLACK_START_LINE,WIDTH-4,blackKing);
        setPiece(WHITE_START_LINE,3,whiteQueen);
        setPiece(WHITE_START_LINE,WIDTH-4,whiteKing);

        //아무것도 없는 칸들을 blank piece로 채우기
        for(int x=0;x<HEIGHT;x++){
            for(int y=0;y<WIDTH;y++){
                if(board[x][y] == null){
                    board[x][y] = pieceCreator.createPiece(NO_PIECE);
                }
            }
        }
    }

    public void initializeEmpty(){
        for(int h=0;h<HEIGHT;h++){
            for(int w=0;w<WIDTH;w++){
                board[h][w] = pieceCreator.createPiece(NO_PIECE);
            }
        }
    }

    private void removePiece(ChessPiece chessPiece){
        for(int h=0;h<HEIGHT;h++){
            for(int w=0;w<WIDTH;w++){
                if(board[h][w].equals(chessPiece)){
                    board[h][w] = pieceCreator.createPiece(NO_PIECE);
                    return;
                }
            }
        }
    }

    protected boolean move(String position, ChessPiece chessPiece){
        if(!isIn(position)) return false;
        removePiece(chessPiece);
        int row = getRow(position);
        int col = getCol(position);

        board[row][col] = chessPiece;
        return true;
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        for(int h=HEIGHT-1;h>=0;h--){
            for(int w=0;w<WIDTH;w++){
                sb.append(board[h][w] == null ? '.' : board[h][w].getRepresentation());
            }
            sb.append(System.lineSeparator());
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

    public boolean move(String sourcePosition,String targetPosition){
        Optional<ChessPiece> source = findPiece(sourcePosition);
        Optional<ChessPiece> target = findPiece(targetPosition);

        if(source.isEmpty() || target.isEmpty()) return false;

        ChessPiece s = source.get();
        ChessPiece t = target.get();
        if(Type.NO_PIECE.equals(s.getType())){
            return false;
        }
        removePiece(t);
        boolean moved = move(targetPosition,s);
        if(moved) {
            setPiece(sourcePosition, pieceCreator.createPiece(NO_PIECE));
        }
        return moved;
    }
}
