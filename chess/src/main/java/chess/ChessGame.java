package chess;

import exception.InvalidMoveException;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceType;

public class ChessGame {
    private final Board board;
    private final PieceFactory pieceFactory;
    private boolean isWhiteTurn = true;

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

    public Piece findPiece(String positionStr) {
        return board.findPiece(makePosition(positionStr));
    }

    public void move(String sourcePositionStr, String targetPositionStr) {
        Position sourcePosition = makePosition(sourcePositionStr);
        Position targetPosition = makePosition(targetPositionStr);
        checkMoveOnSpot(sourcePosition, targetPosition);
        Piece piece = board.findPiece(sourcePosition);
        isCorrectTurn(piece);
        Piece targetPiece = board.findPiece(targetPosition);

        if (piece.canMove(targetPiece)) {
            checkPath(piece, sourcePosition, targetPosition);
            moveOnBoard(piece, sourcePosition, targetPosition);
            switchTurn();
        } else {
            throw new InvalidMoveException("움직일 수 없는 위치입니다. piece: " + piece + " source: " + sourcePosition + " target: " + targetPosition);
        }
    }

    private void moveOnBoard(Piece piece, Position sourcePosition, Position targetPosition) {
        board.setPiece(sourcePosition, pieceFactory.createBlank(sourcePosition));
        piece.move(targetPosition);
        board.setPiece(targetPosition, piece);
    }

    private void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    private void checkMoveOnSpot(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new InvalidMoveException("현재 위치로 이동 불가합니다.");
        }
    }

    private void checkPath(Piece piece, Position sourcePosition, Position targetPosition) {
        if (!piece.getType().equals(PieceType.KNIGHT) && !isPathClear(sourcePosition, targetPosition)) {
            throw new InvalidMoveException("경로상에 장애물이 있습니다." + " piece: " + piece + " source: " + sourcePosition + " target: " + targetPosition);
        }
    }

    private void isCorrectTurn(Piece piece) {
        if (isWhiteTurn && piece.isBlack()) {
            throw new InvalidMoveException("지금은 흰색 차례입니다.");
        } else if (!isWhiteTurn && piece.isWhite()) {
            throw new InvalidMoveException("지금은 검은색 차례입니다.");
        }
    }

    // 퀸, 비숍, 룩
    private boolean isPathClear(Position sourcePosition, Position targetPosition) {
        int ux = Integer.signum(targetPosition.getX() - sourcePosition.getX());
        int uy = Integer.signum(targetPosition.getY() - sourcePosition.getY());
        int x = sourcePosition.getX() + ux;
        int y = sourcePosition.getY() + uy;
        while (x != targetPosition.getX() || y != targetPosition.getY()) {
            if (!board.findPiece(new Position(x, y)).getType().equals(PieceType.NO_PIECE)) {
                return false;
            }
            x += ux;
            y += uy;
        }
        return true;
    }

    private Position makePosition(String position) {
        return new Position(position);
    }

    // print
    public String showBoard() {
        return board.show();
    }
}
