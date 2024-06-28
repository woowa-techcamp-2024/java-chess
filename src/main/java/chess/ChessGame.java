package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Course;
import chess.pieces.Direction;
import chess.pieces.PieceTypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ChessGame {
    private final Board board;
    private boolean whiteTurn;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void init() {
        board.initialize();
        setWhiteTurn(true);
    }

    public void startGame() {
        init();
    }

    public String showBoard() {
        return board.print();
    }

    public boolean move(String source, String target) {
        Optional<ChessPiece> sourcePiece = board.findPiece(source);

        return sourcePiece.map(sp -> {
            if (!isOwner(sp)) throw new IllegalArgumentException("다른사람의 기물을 움직일 수 없습니다.");
            if (isPossibleMove(sp, source, target)) {
                board.move(source, target);
                return true;
            } else {
                return false;
            }
        }).orElse(false);
    }

    public List<String> possibleMovePositions(String position) {
        List<String> result = new LinkedList<>();
        Optional<ChessPiece> piece = board.findPiece(position);
        piece.ifPresent(p -> {
            if(!PieceTypes.Type.NO_PIECE.equals(p.getType())) {
                findPossiblePositions(result, p, position);
            }
        });
        return result;
    }

    public boolean isEnd() {
        return board.findPieceWithColorAndType(PieceTypes.Color.BLACK, PieceTypes.Type.KING) == 0
                || board.findPieceWithColorAndType(PieceTypes.Color.WHITE, PieceTypes.Type.KING) == 0;
    }

    public void finishGame() {
        board.initializeEmpty();
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    protected void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public void turnChange() {
        setWhiteTurn(!isWhiteTurn());
    }

    private boolean isOwner(ChessPiece piece) {
        return whiteTurn && PieceTypes.Color.WHITE.equals(piece.getColor())
                || !whiteTurn && PieceTypes.Color.BLACK.equals(piece.getColor());
    }

    private void findPossiblePositions(List<String> result, ChessPiece piece, String source) {
        Course course = piece.getCourse();
        List<Direction> directions = course.getDirections();

        for (Direction direction : directions) {
            findPossiblePositionsWithDirection(result, direction, piece, getNextPosition(source, direction));
        }
    }

    private void findPossiblePositionsWithDirection(List<String> result, Direction direction, ChessPiece piece, String source) {
        if (!board.isIn(source)) {
            return;
        }

        boolean isPiece = board.findPiece(source)
                .map(p -> {
                    if (!isSameTeam(piece, p)) {
                        result.add(source);
                    }
                    return !PieceTypes.Type.NO_PIECE.equals(p.getType());
                }).orElse(false);

        if (!piece.getCourse().isRecursive()) return;
        if (!piece.getType().isJumpable() && isPiece) return;

        findPossiblePositionsWithDirection(result, direction, piece, getNextPosition(source, direction));
    }

    private boolean isSameTeam(ChessPiece p1, ChessPiece p2) {
        return p1 != null && p2 != null && p1.getColor().equals(p2.getColor());
    }

    private boolean isPossibleMove(ChessPiece piece, String source, String target) {
        Optional<ChessPiece> sourcePiece = board.findPiece(source);
        return sourcePiece.map(p -> innerIsPossibleMove(piece, source, target)).orElse(false);
    }

    private boolean innerIsPossibleMove(ChessPiece piece, String source, String target) {
        Course pieceCourse = piece.getCourse();
        List<Direction> directions = pieceCourse.getDirections();
        List<String> possiblePositions = new LinkedList<>();
        for (Direction direction : directions) {
            findPossiblePositionsWithDirection(possiblePositions,direction,piece,getNextPosition(source, direction));
        }
        return possiblePositions.contains(target);
    }

    private String getNextPosition(String sourcePosition, Direction direction) {
        return getNextPosition(sourcePosition, direction, 1);
    }

    private String getNextPosition(String sourcePosition, Direction direction, int multiple) {
        int row = getRow(sourcePosition);
        int col = getCol(sourcePosition);
        return switchPosition(row + direction.getXDegree() * multiple, col + direction.getYDegree() * multiple);
    }

    private String switchPosition(int row, int col) {
        return new StringBuilder().append((char) ('a' + col)).append(Character.forDigit(row + 1, 10)).toString();
    }

    private int getCol(String position) {
        if (position == null || position.length() > 2)
            throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        char col = position.charAt(0);
        if (!('a' <= col && col <= 'h')) throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        return col - 'a';
    }

    private int getRow(String position) {
        if (position == null || position.length() > 2)
            throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        char row = position.charAt(1);

        if (!('1' <= row && row <= '8')) throw new IllegalArgumentException("position은 a1 ~ h8 까지 입력해주세요");
        return row - '1';
    }
}
