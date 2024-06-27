package chess;

import chess.constant.Color;
import chess.constant.Direction;
import chess.constant.Type;
import chess.pieces.*;

import java.util.List;
import java.util.Objects;

import static chess.Board.BOARD_SIZE;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public int pieceCount() {
        List<Rank> ranks = board.findAll();

        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            count += ranks.get(i).getCount();
        }
        return count;
    }

    public void start() {
        board.initialize();
        initializeWhitePiece();
        initializeBlackPiece();
    }

    private void initializeWhitePiece() {
        board.saveByPosition(PieceFactory.createRook(Color.WHITE, null), new Position("a1"));
        board.saveByPosition(PieceFactory.createKnight(Color.WHITE, null), new Position("b1"));
        board.saveByPosition(PieceFactory.createBishop(Color.WHITE, null), new Position("c1"));
        board.saveByPosition(PieceFactory.createQueen(Color.WHITE, null), new Position("d1"));
        board.saveByPosition(PieceFactory.createKing(Color.WHITE, null), new Position("e1"));
        board.saveByPosition(PieceFactory.createBishop(Color.WHITE, null), new Position("f1"));
        board.saveByPosition(PieceFactory.createKnight(Color.WHITE, null), new Position("g1"));
        board.saveByPosition(PieceFactory.createRook(Color.WHITE, null), new Position("h1"));

        for (int i = 0; i < BOARD_SIZE; i++) {
            board.saveByPosition(PieceFactory.createPawn(Color.WHITE, null), new Position(i, 6));
        }
    }

    private void initializeBlackPiece() {
        board.saveByPosition(PieceFactory.createRook(Color.BLACK, null), new Position("a8"));
        board.saveByPosition(PieceFactory.createKnight(Color.BLACK, null), new Position("b8"));
        board.saveByPosition(PieceFactory.createBishop(Color.BLACK, null), new Position("c8"));
        board.saveByPosition(PieceFactory.createQueen(Color.BLACK, null), new Position("d8"));
        board.saveByPosition(PieceFactory.createKing(Color.BLACK, null), new Position("e8"));
        board.saveByPosition(PieceFactory.createBishop(Color.BLACK, null), new Position("f8"));
        board.saveByPosition(PieceFactory.createKnight(Color.BLACK, null), new Position("g8"));
        board.saveByPosition(PieceFactory.createRook(Color.BLACK, null), new Position("h8"));

        for (int i = 0; i < BOARD_SIZE; i++) {
            board.saveByPosition(PieceFactory.createPawn(Color.BLACK, null), new Position(i, 1));
        }
    }

    public int countPieces(final Color color, final Type type) {
        List<Piece> pieces = board.findByColor(color);
        return (int) pieces.stream().filter(piece -> Objects.equals(type, piece.getType())).count();
    }

    private double calculatePawns(int[] pawns) {
        double point = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (pawns[i] == 1) point += Type.PAWN.getDefaultPoint();
            else point += (Type.PAWN.getDefaultPoint() / 2) * pawns[i];
        }
        return point;
    }

    public double calculatePoint(final Color color) {
        double point = 0;
        int[] pawns = new int[BOARD_SIZE];
        List<Piece> pieces = board.findByColor(color);

        for (Piece piece : pieces) {
            if (!Objects.equals(piece.getType(), Type.PAWN)) point += piece.getType().getDefaultPoint();
            else pawns[piece.getPosition().getX()] += 1;
        }
        point += calculatePawns(pawns);
        return point;
    }

    public void move(final String position, final Piece piece) {
        Position piecePosition = new Position(position);
        board.saveByPosition(piece, piecePosition);
    }

    public void move(final String source, final String destination) throws Exception {
        Position sourcePosition = new Position(source);
        Position destinationPosition = new Position(destination);

        Piece piece = board.findByPosition(sourcePosition);
        if (destinationPosition.isOutOfIndex()) throw new Exception("보드를 벗어났습니다");

        Piece destinationPiece = board.findByPosition(destinationPosition);
        if (isColorSame(piece.getColor(), destinationPosition)) throw new Exception("같은 편의 기물이 존재합니다");
        if (!piece.verifyMovePosition(destinationPiece)) throw new Exception("이동하려는 위치가 기물의 이동 규칙과 다릅니다");

        Direction direction = piece.getDirection(destinationPosition);
        if (!verifyMoveDirection(direction, sourcePosition, destinationPosition)) throw new Exception("해당 위치로 이동할 수 없습니다");

        board.saveByPosition(piece, destinationPosition);
        board.saveByPosition(PieceFactory.createBlank(sourcePosition), sourcePosition);
    }

    private boolean isColorSame(final Color color, final Position position) {
        Piece piece = board.findByPosition(position);
        return Objects.equals(color, piece.getColor());
    }

    private boolean verifyMoveDirection(final Direction direction, final Position source, final Position destination) {
        Position position = new Position(source.getX() + direction.getXDegree(), source.getY() + direction.getYDegree());
        while (!Objects.equals(position, destination)) {
            Piece piece = board.findByPosition(position);
            if (!Objects.equals(piece.getType(), Type.NO_PIECE)) return false;

            position = new Position(position.getX() + direction.getXDegree(), position.getY() + direction.getYDegree());
        }
        return true;
    }
}
