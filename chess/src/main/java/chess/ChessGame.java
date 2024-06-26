package chess;


import pieces.Piece;
import pieces.PieceFactory;

public class ChessGame {
    private final Board board;
    private final PieceFactory pieceFactory;

    public ChessGame() {
        this.board = new Board();
        this.pieceFactory = new PieceFactory();
    }

    public void initialize(){
        initBlackPieces();
        initBlackPawns();
        initWhitePawns();
        initWhitePieces();
    }

    private void initBlackPieces() {
        board.setPiece(makePosition("a8"), pieceFactory.createBlackRook());
        board.setPiece(makePosition("b8"), pieceFactory.createBlackKnight());
        board.setPiece(makePosition("c8"), pieceFactory.createBlackBishop());
        board.setPiece(makePosition("d8"), pieceFactory.createBlackQueen());
        board.setPiece(makePosition("e8"), pieceFactory.createBlackKing());
        board.setPiece(makePosition("f8"), pieceFactory.createBlackBishop());
        board.setPiece(makePosition("g8"), pieceFactory.createBlackKnight());
        board.setPiece(makePosition("h8"), pieceFactory.createBlackRook());
    }

    private void initBlackPawns() {
        char start = 'a';
        for(int i=0; i < BoardArea.COL.getNum(); i++){
            char col = (char) (start + i);
            board.setPiece(makePosition(col + Integer.toString(7)), pieceFactory.createBlackPawn());
        }
    }

    private void initWhitePawns(){
        char start = 'a';
        for(int i=0; i < BoardArea.COL.getNum(); i++){
            char col = (char) (start + i);
            board.setPiece(makePosition(col + Integer.toString(2)), pieceFactory.createWhitePawn());
        }
    }

    private void initWhitePieces(){
        board.setPiece(makePosition("a1"), pieceFactory.createWhiteRook());
        board.setPiece(makePosition("b1"), pieceFactory.createWhiteKnight());
        board.setPiece(makePosition("c1"), pieceFactory.createWhiteBishop());
        board.setPiece(makePosition("d1"), pieceFactory.createWhiteQueen());
        board.setPiece(makePosition("e1"), pieceFactory.createWhiteKing());
        board.setPiece(makePosition("f1"), pieceFactory.createWhiteBishop());
        board.setPiece(makePosition("g1"), pieceFactory.createWhiteKnight());
        board.setPiece(makePosition("h1"), pieceFactory.createWhiteRook());
    }

    public void setPiece(String positionStr, Piece piece){
        board.setPiece(makePosition(positionStr), piece);
    }

    public Piece findPiece(String positionStr){
        return board.findPiece(makePosition(positionStr));
    }

    public void move(String sourcePositionStr, String targetPositionStr){
        Position sourcePosition = makePosition(sourcePositionStr);
        Position targetPosition = makePosition(targetPositionStr);
        Piece piece = board.findPiece(sourcePosition);
        board.setPiece(sourcePosition, pieceFactory.createBlank());
        board.setPiece(targetPosition, piece);
    }

    private Position makePosition(String position){
        return new Position(position);
    }


    // print
    public String showBoard(){
        return board.show();
    }
}
