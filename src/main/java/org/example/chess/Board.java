package org.example.chess;

import static org.example.chess.ChessConst.BLACK_PAWN_START_ROW;
import static org.example.chess.ChessConst.BLACK_PIECE_START_ROW;
import static org.example.chess.ChessConst.BOARD_SIZE;
import static org.example.chess.ChessConst.START_CHAR;
import static org.example.chess.ChessConst.WHITE_PAWN_START_ROW;
import static org.example.chess.ChessConst.WHITE_PIECE_START_ROW;
import static org.example.pieces.PieceFactory.*;
import static org.example.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;
import org.example.pieces.Piece;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;
import org.example.pieces.PieceFactory;

public class Board {

    private final List<Column> columns;

    public Board() {
        columns = createSizeList(BOARD_SIZE);
    }

    public void move(Position start, Position end, Color turn) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("같은 지점으로 이동할 수 없습니다.");
        }

        Piece startPiece = findPiece(start);
        Piece endPiece = findPiece(end);
        Direction direction = Direction.determineDirection(start, end);
        int depth = Direction.depth(start, end);
        // check start piece
        checkStart(startPiece, turn);
        //check start direction
        checkStartDirection(startPiece, start, end);
        //폰이면 예외규칙 적용
        checkPawn(startPiece, endPiece, direction, depth);

        //이동 경로에 다른 말이 있으면 에러
        noOnTheWay(start, end, direction, depth, startPiece, endPiece);
        checkEnd(endPiece, startPiece);
        // checkEnd

        //끝 점에 같은 색의 말이 있으면 에러
        setPiece(end, startPiece);
        setPiece(start, createNoColorPiece());
    }

    private void checkPawn(Piece startPiece, Piece endPiece,
        Direction direction, int depth) {
        if (!startPiece.isPawn()) {
            return;
        }
        // 만약에 대각선으로 2칸이면 에러
        if (Direction.diagonalDirection().contains(direction) && depth == 2) {
            throw new IllegalArgumentException("폰의 이동 규칙에 맞지 않습니다.");
        }

        // 만약에 대각선으로 1칸이면 무조건 말이 있어야함
        if (Direction.diagonalDirection().contains(direction) && depth == 1) {
            if (endPiece.isNoColorPiece()) {
                throw new IllegalArgumentException("폰의 이동 규칙에 맞지 않습니다.");
            }
        }

        // 앞으로 움직일 때 앞에 말이 있으면 에러
        if (Direction.linearDirection().contains(direction) && !endPiece.isNoColorPiece()) {
            throw new IllegalArgumentException("폰의 이동 규칙에 맞지 않습니다.");
        }
    }

    private void checkStartDirection(Piece startPiece, Position start, Position end) {
        if (!startPiece.verifyMove(start, end)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    private void checkEnd(Piece endPiece, Piece startPiece) {
        if (endPiece.isSameColor(startPiece.getColor())) {
            throw new IllegalArgumentException("같은 색의 말이 있습니다.");
        }
    }

    private void checkStart(Piece startPiece, Color turn) {
        if (!startPiece.isSameColor(turn)) {
            throw new IllegalArgumentException("자신의 말만 이동할 수 있습니다.");
        }
        if (startPiece.isNoColorPiece()) {
            throw new IllegalArgumentException("이동할 말이 없습니다.");
        }
    }

    private void noOnTheWay(Position start, Position end, Direction direction, int depth,
        Piece startPiece, Piece endPiece) {
        Position now = start;
        for (int i = 1; i < depth; i++) {
            now = now.next(direction);
            if (!findPiece(now).isNoColorPiece()) {
                throw new IllegalArgumentException("이동 경로에 다른 말이 있습니다.");
            }
        }
    }

    public void setPiece(Position position, Piece piece) {
        columns.get(position.getColIdx()).setPiece(piece, position.getRow());
    }

    private List<Column> createSizeList(int boardSize) {
        List<Column> ret = new ArrayList<>();
        for (char i = START_CHAR; i < START_CHAR + boardSize; i++) {
            ret.add(new Column(i, boardSize));
        }
        return ret;
    }

    //게임 설정
    void initialize() {
        initColoredPiece();
    }

    private void initColoredPiece() {
        placeBlackPiece();
        placeWhitePiece();
    }

    private void placeWhitePiece() {

        for (Column column : columns) {
            column.setPiece(createWhitePawn(), WHITE_PAWN_START_ROW);
        }

        columns.get(0).setPiece(createWhiteRook(), WHITE_PIECE_START_ROW);
        columns.get(1).setPiece(createWhiteKnight(), WHITE_PIECE_START_ROW);
        columns.get(2).setPiece(createWhiteBishop(), WHITE_PIECE_START_ROW);
        columns.get(3).setPiece(createWhiteQueen(), WHITE_PIECE_START_ROW);
        columns.get(4).setPiece(createWhiteKing(), WHITE_PIECE_START_ROW);
        columns.get(5).setPiece(createWhiteBishop(), WHITE_PIECE_START_ROW);
        columns.get(6).setPiece(createWhiteKnight(), WHITE_PIECE_START_ROW);
        columns.get(7).setPiece(createWhiteRook(), WHITE_PIECE_START_ROW);
    }

    private void placeBlackPiece() {
        // Place black pawns
        columns.forEach(
            column -> column.setPiece(createBlackPawn(), BLACK_PAWN_START_ROW));

        // Place other black pieces
        columns.get(0).setPiece(createBlackRook(), BLACK_PIECE_START_ROW);
        columns.get(1).setPiece(createBlackKnight(), BLACK_PIECE_START_ROW);
        columns.get(2).setPiece(createBlackBishop(), BLACK_PIECE_START_ROW);
        columns.get(3).setPiece(createBlackQueen(), BLACK_PIECE_START_ROW);
        columns.get(4).setPiece(createBlackKing(), BLACK_PIECE_START_ROW);
        columns.get(5).setPiece(createBlackBishop(), BLACK_PIECE_START_ROW);
        columns.get(6).setPiece(createBlackKnight(), BLACK_PIECE_START_ROW);
        columns.get(7).setPiece(createBlackRook(), BLACK_PIECE_START_ROW);
    }
    // 게임 설정

    public int nonEmptyPiece() {
        return columns.stream()
            .mapToInt(column -> (int) column.getPieces().stream()
                .filter(piece -> piece.isBlack() || piece.isWhite())
                .count())
            .sum();
    }

    public int countByQuery(Color color, Type type) {
        return columns.stream()
            .mapToInt(column -> (int) column.getPieces().stream()
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(type))
                .count())
            .sum();
    }

    public Piece findPiece(Position position) {
        return columns.get(position.getColIdx()).getPiece(position.getRow());
    }

    public Piece findPiece(String position) {
        return findPiece(new Position(position));
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Piece> sortByPoint(Color color) {
        return columns.stream()
            .flatMap(column -> column.getPieces().stream())
            .filter(piece -> piece.isSameColor(color))
            .sorted((p1, p2) -> Double.compare(p2.getPoint(), p1.getPoint()))
            .toList();
    }
}
