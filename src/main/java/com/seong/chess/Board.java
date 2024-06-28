package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;

import com.seong.chess.pieces.Direction;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board {

    private static final int EMPTY_ROW_BEGIN = 2;
    private static final int EMPTY_ROW_END = 6;
    static final int BOARD_LENGTH = 8;

    private final List<Rank> ranks = new ArrayList<>();

    public void initializeEmpty() {
        ranks.clear();
        initializeBlank(0, BOARD_LENGTH);
    }

    public void initialize() {
        ranks.clear();
        ranks.add(Rank.createBlackPiecesRank());
        ranks.add(Rank.createBlackPawnRank());
        initializeBlank(EMPTY_ROW_BEGIN, EMPTY_ROW_END);
        ranks.add(Rank.createWhitePawnRank());
        ranks.add(Rank.createWhitePiecesRank());
    }

    private void initializeBlank(int beginRow, int endRow) {
        for (int i = beginRow; i < endRow; i++) {
            ranks.add(Rank.createBlackRank());
        }
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            sb.append(appendNewLine(ranks.get(i).getRepresentation()));
        }
        return sb.toString();
    }

    public int pieceCount() {
        return ranks.stream()
                .map(Rank::pieceCount)
                .reduce(0, Integer::sum);
    }

    public int pieceCount(Piece piece, Color color) {
        return ranks.stream()
                .map(rank -> rank.pieceCount(piece, color))
                .reduce(0, Integer::sum);
    }

    public void move(String rawPosition, Piece piece) {
        Position position = Position.convert(rawPosition);
        ranks.get(position.row()).move(position.col(), piece);
    }

    public Piece findPiece(String rawPosition) {
        Position position = Position.convert(rawPosition);
        return ranks.get(position.row()).get(position.col());
    }

    public double getColumnPawnCount(Color color, int row) {
        double pawnCount = 0;
        for (int col = 0; col < BOARD_LENGTH; col++) {
            Piece piece = ranks.get(col).get(row);
            if (piece.isPawn(color)) {
                pawnCount++;
            }
        }
        return pawnCount;
    }

    public List<Piece> getPiecesOrderByHighestScore(Color color) {
        return getPiecesOrderBy(color, (piece1, piece2) -> {
            if (piece1.getDefaultPoint() == piece2.getDefaultPoint()) {
                return 0;
            }
            return piece2.getDefaultPoint() > piece1.getDefaultPoint() ? 1 : -1;
        });
    }

    public List<Piece> getPiecesOrderByLowest(Color color) {
        return getPiecesOrderBy(color, (piece1, piece2) -> {
            if (piece1.getDefaultPoint() == piece2.getDefaultPoint()) {
                return 0;
            }
            return piece1.getDefaultPoint() > piece2.getDefaultPoint() ? 1 : -1;
        });
    }

    private List<Piece> getPiecesOrderBy(Color color, Comparator<Piece> order) {
        return ranks.stream()
                .flatMap(rank -> rank.getSameColorPieces(color).stream())
                .sorted(order)
                .toList();
    }

    public List<Position> getPawnMovable(String rawSourcePosition, String rawTargetPosition) {
        List<Position> movablePositions = new ArrayList<>();
        if (!findPiece(rawTargetPosition).isNotBlank()) {
            return movablePositions;
        }
        Piece sourcePiece = findPiece(rawSourcePosition);
        if (!sourcePiece.isPawn()) {
            return movablePositions;
        }
        Position sourcePosition = Position.convert(rawSourcePosition);
        Position targetPosition = Position.convert(rawTargetPosition);

        if (sourcePiece.isWhite() && Direction.isNorthDiagonal(sourcePosition, targetPosition)) {
            movablePositions.add(targetPosition);
        }
        if (sourcePiece.isBlack() && Direction.isSouthDiagonal(sourcePosition, targetPosition)) {
            movablePositions.add(targetPosition);
        }
        return movablePositions;
    }

    public void checkIsBlocked(String rawSourcePosition, String rawTargetPosition) {
        Position sourcePosition = Position.convert(rawSourcePosition);
        Position targetPosition = Position.convert(rawTargetPosition);
        Piece sourcePiece = findPiece(sourcePosition.convert());
        if (sourcePiece.isPawn() || sourcePiece.isKnight()) {
            return;
        }
        if (sourcePosition.equals(targetPosition)) {
            return;
        }

        Direction direction = Direction.getDirection(sourcePosition, targetPosition);
        checkIsBlocked(sourcePosition, targetPosition, direction);
    }

    private void checkIsBlocked(Position sourcePosition, Position targetPosition, Direction direction) {
        Position nextPosition = new Position(sourcePosition.col() + direction.getCol(),
                sourcePosition.row() + direction.getRow());
        if (nextPosition.equals(targetPosition)) {
            return;
        }
        if (findPiece(nextPosition.convert()).isNotBlank()) {
            throw new IllegalArgumentException("경로상 기물이 존재하여 이동할 수 없습니다.");
        }
        checkIsBlocked(nextPosition, targetPosition, direction);
    }

    public List<Position> getPawnCanNotMovable(String sourcePosition, String targetPosition) {
        List<Position> positions = new ArrayList<>();
        Piece sourcePiece = findPiece(sourcePosition);
        if (!sourcePiece.isPawn()) {
            return positions;
        }
        for (Direction direction : Direction.values()) {
            if (!sourcePiece.isPiecesDirection(direction)) {
                continue;
            }
            Position position = Position.convert(sourcePosition);
            Position rightPosition = new Position(
                    position.col() + direction.getCol(), position.row() + direction.getRow());
            if (rightPosition.equals(Position.convert(targetPosition))) {
                positions.add(rightPosition);
            }
        }
        return positions;
    }
}
