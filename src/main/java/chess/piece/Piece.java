package chess.piece;

import chess.Board;
import chess.util.ChessPoint;
import chess.util.Direction;
import chess.util.MoveRule;

import java.util.*;

public abstract class Piece {
    public boolean isMoved() {
        return isMoved;
    }

    public List<Direction> getMovableDirections() {
        return movableDirections;
    }

    public int getMoveDistance() {
        return moveDistance;
    }

    public enum Color {
        WHITE, BLACK;

        public Color opposite() {
            return this == WHITE ? BLACK : WHITE;
        }
    }

    public static final int MAX_MOVE_DISTANCE = 16;

    protected final Color color;
    protected final char representation;
    protected final List<Direction> movableDirections;
    protected final int moveDistance;
    protected final List<MoveRule> specialMoveRules;
    private boolean isMoved = false;

    protected Piece(Color color, char representation, List<Direction> movableDirections, int moveDistance, List<MoveRule> specialMoveRules) {
        this.color = color;
        this.representation = representation;
        this.movableDirections = movableDirections;
        this.moveDistance = moveDistance;
        this.specialMoveRules = specialMoveRules;
    }

    protected Piece(Color color, char representation, List<Direction> movableDirections, int moveDistance) {
        this(color, representation, movableDirections, moveDistance, List.of(MoveRule.Common));
    }

    public char getRepresentation() {
        return representation;
    }

    public Color getColor(){
        return color;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public Map<ChessPoint, MoveRule> getMovablePoints(ChessPoint source, Board board, boolean onlyAttackable) {
        Map<ChessPoint, MoveRule> moveablePoints = MoveRule.Common.getMovablePoints(board, source, this);
        moveablePoints.putAll(getSpecialMovablePoints(source, board, onlyAttackable));
        return moveablePoints;
    }

    public Map<ChessPoint, MoveRule> getMovablePoints(ChessPoint source, Board board) {
        return getMovablePoints(source, board, false);
    }

    public Map<ChessPoint, MoveRule> getAttackablePoints(ChessPoint source, Board board) {
        return getMovablePoints(source, board, true);
    }

    private Map<ChessPoint, MoveRule> getSpecialMovablePoints(ChessPoint source, Board board, boolean onlyAttackable) {
        Map<ChessPoint, MoveRule> resultMap = new HashMap<>();
        specialMoveRules.forEach(moveRule -> moveRule.adapt(resultMap, board, source, this, onlyAttackable));
        return resultMap;
    }

    public void setMoved() {
        isMoved = true;
    }

    public MoveRule getMoveRule(Board board, ChessPoint source, ChessPoint target) {
        MoveRule moveRule = getMovablePoints(source, board).get(target);
        return moveRule == null ? MoveRule.None : moveRule;
    }

    public abstract double getDefaultPoint();
}
