package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.board.type.BoardType;
import pe.goblin.chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                initialPieces.get(row).set(col, Piece.of(ch));
            }
        }
        this.pieces = initialPieces;
    }

    private List<List<Piece>> createEmptyBoard() {
        List<List<Piece>> initialPieces = new ArrayList<>();
        for (int row = 0; row < rowSize; row++) {
            List<Piece> rowPieces = new ArrayList<>();
            for (int col = 0; col < columnSize; col++) {
                rowPieces.add(Piece.createBlank());
            }
            initialPieces.add(rowPieces);
        }
        return initialPieces;
    }

    public Piece findPieceAt(String posStr) {
        Position position = new Position(posStr, rowSize, columnSize);
        return pieces.get(position.row()).get(position.col());
    }

    public void move(String sourcePosition, String targetPosition) {
        Position from = new Position(sourcePosition, rowSize, columnSize);
        Position to = new Position(targetPosition, rowSize, columnSize);
        Piece pieceAtFrom = pieces.get(from.row()).get(from.col());
        pieces.get(to.row()).set(to.col(), pieceAtFrom);
        pieces.get(from.row()).set(from.col(), Piece.createBlank());
    }

    public List<List<Piece>> getPieces() {
        return pieces;
    }
}
