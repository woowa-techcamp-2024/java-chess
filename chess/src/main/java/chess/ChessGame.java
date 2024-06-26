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

    public void initialize() {
        initBlackPieces();
        initBlackPawns();
        initWhitePawns();
        initWhitePieces();
    }

    private void initBlackPieces() {
        board.setPiece(makePosition("a8"), pieceFactory.createBlackRook(makePosition("a8")));
        board.setPiece(makePosition("b8"), pieceFactory.createBlackKnight(makePosition("b8")));
        board.setPiece(makePosition("c8"), pieceFactory.createBlackBishop(makePosition("c8")));
        board.setPiece(makePosition("d8"), pieceFactory.createBlackQueen(makePosition("d8")));
        board.setPiece(makePosition("e8"), pieceFactory.createBlackKing(makePosition("e8")));
        board.setPiece(makePosition("f8"), pieceFactory.createBlackBishop(makePosition("f8")));
        board.setPiece(makePosition("g8"), pieceFactory.createBlackKnight(makePosition("g8")));
        board.setPiece(makePosition("h8"), pieceFactory.createBlackRook(makePosition("h8")));
    }

    private void initBlackPawns() {
        char start = 'a';
        for (int i = 0; i < BoardArea.X.getMax(); i++) {
            char col = (char) (start + i);
            board.setPiece(makePosition(col + Integer.toString(7)), pieceFactory.createBlackPawn(makePosition(col + Integer.toString(7))));
        }
    }

    private void initWhitePawns() {
        char start = 'a';
        for (int i = 0; i < BoardArea.X.getMax(); i++) {
            char col = (char) (start + i);
            board.setPiece(makePosition(col + Integer.toString(2)), pieceFactory.createWhitePawn(makePosition(col + Integer.toString(2))));
        }
    }

    private void initWhitePieces() {
        board.setPiece(makePosition("a1"), pieceFactory.createWhiteRook(makePosition("a1")));
        board.setPiece(makePosition("b1"), pieceFactory.createWhiteKnight(makePosition("b1")));
        board.setPiece(makePosition("c1"), pieceFactory.createWhiteBishop(makePosition("c1")));
        board.setPiece(makePosition("d1"), pieceFactory.createWhiteQueen(makePosition("d1")));
        board.setPiece(makePosition("e1"), pieceFactory.createWhiteKing(makePosition("e1")));
        board.setPiece(makePosition("f1"), pieceFactory.createWhiteBishop(makePosition("f1")));
        board.setPiece(makePosition("g1"), pieceFactory.createWhiteKnight(makePosition("g1")));
        board.setPiece(makePosition("h1"), pieceFactory.createWhiteRook(makePosition("h1")));
    }

    public void setPiece(String positionStr, Piece piece) {
        board.setPiece(makePosition(positionStr), piece);
    }

    public Piece findPiece(String positionStr){
        return board.findPiece(makePosition(positionStr));
    }

    public void move(String sourcePositionStr, String targetPositionStr) {
        Position sourcePosition = makePosition(sourcePositionStr);
        Position targetPosition = makePosition(targetPositionStr);
        Piece piece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);
        // ToDo: piece가 target에 갈 수 있나 확인
        /*
         * if(piece.ableMove(targetPosition)){
         *
         * }
         * */
        board.setPiece(sourcePosition, pieceFactory.createBlank(sourcePosition));
        piece.move(targetPosition);
        board.setPiece(targetPosition, piece);
    }

    private Position makePosition(String position) {
        return new Position(position);
    }

    // print
    public String showBoard() {
        return board.show();
    }
}
