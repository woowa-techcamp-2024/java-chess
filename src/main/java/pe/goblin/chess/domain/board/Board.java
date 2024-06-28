package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.board.type.BoardType;
import pe.goblin.chess.domain.piece.Blank;
import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.factory.PieceFactory;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.*;

public class Board {
    private final int rowSize;
    private final int columnSize;
    private final List<String> initialState;

    private List<List<Piece>> pieces = Collections.emptyList();

    public Board(BoardType boardType) {
        rowSize = boardType.getRowSize();
        columnSize = boardType.getColumnSize();
        initialState = boardType.getInitialState();
        initialize();
    }

    public void initialize() {
        List<List<Piece>> initialPieces = createEmptyBoard();
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {
                char ch = initialState.get(row).charAt(col);
                initialPieces.get(row).set(col, PieceFactory.of(ch));
            }
        }
        this.pieces = initialPieces;
    }

    private List<List<Piece>> createEmptyBoard() {
        List<List<Piece>> initialPieces = new ArrayList<>();
        for (int row = 0; row < rowSize; row++) {
            List<Piece> rowPieces = new ArrayList<>();
            for (int col = 0; col < columnSize; col++) {
                rowPieces.add(new Blank());
            }
            initialPieces.add(rowPieces);
        }
        return initialPieces;
    }

    public void move(String sourcePosition, String targetPosition) {
        Position from = new Position(sourcePosition, rowSize, columnSize);
        Position to = new Position(targetPosition, rowSize, columnSize);
        Piece pieceToMove = findPieceAt(from);
        Set<Position> possibleMoves = searchPossibleMoves(pieceToMove, from);
        if (!possibleMoves.contains(to)) {
            throw new RuntimeException("not a valid move");
        }
        setPieceAt(from, pieceToMove);
        setPieceAt(to, new Blank());
    }

    private Set<Position> searchPossibleMoves(Piece pieceToMove, Position from) {
        Set<Position> possibleMoves = new HashSet<>();
        putAllRoutes(pieceToMove, from, possibleMoves);
        if (pieceToMove.getType() == PieceType.PAWN) {
            putAllAttacks(pieceToMove, from, possibleMoves);
        }
        return possibleMoves;
    }

    private void putAllAttacks(Piece pieceToMove, Position from, Set<Position> possibleMoves) {
        for (Direction direction : pieceToMove.getAttackableDirections()) {
            iterate(pieceToMove, from, possibleMoves, direction);
        }
    }

    private void putAllRoutes(Piece pieceToMove, Position from, Set<Position> possibleMoves) {
        for (Direction direction : pieceToMove.getMovableDirections()) {
            iterate(pieceToMove, from, possibleMoves, direction);
        }
    }

    private void iterate(Piece pieceToMove, Position from, Set<Position> possibleMoves, Direction direction) {
        for (int dist = 1; dist < pieceToMove.getMovableDistance(); dist++) {
            int dr = direction.getYDegree() * dist;
            int dc = direction.getXDegree() * dist;
            Position newPosition = Position.addDelta(from, dr, dc);
            if (isOutOfBound(newPosition)) {
                break;
            }
            Piece pieceAtPosition = findPieceAt(newPosition);
            if (!pieceAtPosition.isBlank()) {
                if (pieceToMove.getColor() != pieceAtPosition.getColor() && pieceToMove.getType() != PieceType.PAWN) {
                    possibleMoves.add(newPosition);
                }
                break;
            }
            possibleMoves.add(newPosition);
        }
    }

    private boolean isOutOfBound(Position position) {
        return 0 <= position.row() && position.row() < rowSize && 0 <= position.col() && position.col() < columnSize;
    }

    public Piece findPieceAt(String posStr) {
        Position position = new Position(posStr, rowSize, columnSize);
        return findPieceAt(position);
    }

    private Piece findPieceAt(Position position) {
        return pieces.get(position.row()).get(position.col());
    }

    private void setPieceAt(Position position, Piece piece) {
        pieces.get(position.row()).set(position.col(), piece);
    }

    public List<List<Piece>> getPieces() {
        return pieces;
    }
}
