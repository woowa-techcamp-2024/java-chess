package chess;


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
        board.addPiece(new Position("a8"), pieceFactory.createBlackRook());
        board.addPiece(new Position("b8"), pieceFactory.createBlackKnight());
        board.addPiece(new Position("c8"), pieceFactory.createBlackBishop());
        board.addPiece(new Position("d8"), pieceFactory.createBlackQueen());
        board.addPiece(new Position("e8"), pieceFactory.createBlackKing());
        board.addPiece(new Position("f8"), pieceFactory.createBlackBishop());
        board.addPiece(new Position("g8"), pieceFactory.createBlackKnight());
        board.addPiece(new Position("h8"), pieceFactory.createBlackRook());
    }

    private void initBlackPawns() {
        char start = 'a';
        for(int i=0; i < BoardArea.COL.getNum(); i++){
            char col = (char) (start + i);
            board.addPiece(new Position(col + Integer.toString(7)), pieceFactory.createBlackPawn());
        }
    }

    private void initWhitePawns(){
        char start = 'a';
        for(int i=0; i < BoardArea.COL.getNum(); i++){
            char col = (char) (start + i);
            board.addPiece(new Position(col + Integer.toString(2)), pieceFactory.createWhitePawn());
        }
    }

    private void initWhitePieces(){
        board.addPiece(new Position("a1"), pieceFactory.createWhiteRook());
        board.addPiece(new Position("b1"), pieceFactory.createWhiteKnight());
        board.addPiece(new Position("c1"), pieceFactory.createWhiteBishop());
        board.addPiece(new Position("d1"), pieceFactory.createWhiteQueen());
        board.addPiece(new Position("e1"), pieceFactory.createWhiteKing());
        board.addPiece(new Position("f1"), pieceFactory.createWhiteBishop());
        board.addPiece(new Position("g1"), pieceFactory.createWhiteKnight());
        board.addPiece(new Position("h1"), pieceFactory.createWhiteRook());
    }

    // get


    // print
    public String showBoard(){
        return board.show();
    }

}
