package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.board.type.BoardType;
import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    // TODO: 삭제
    public static final int MAX_COLS = 7;

    private final int rowSize;
    private final int columnSize;
    private final List<String> initialState;

    private List<List<Piece>> pieces = Collections.emptyList();
    private ScoreEvaluator scoreEvaluator = new DefaultScoreEvaluator();

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

    public int countAllPieces() {
        return countPiece(Color.NOCOLOR, PieceType.NO_PIECE);
    }

    public int countPiece(Color color, PieceType pieceType) {
        return (int) pieces.parallelStream()
                .flatMap(List::stream)
                .filter(piece -> piece.getColor() == color && piece.getType() == pieceType)
                .count();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {
                Piece piece = pieces.get(row).get(col);
                sb.append(piece.getRepresentation());
            }
            sb.append(System.lineSeparator()); // 각 행 끝에 줄 바꿈 추가
        }
        return sb.toString();
    }

    public Piece findPieceAt(String posStr) {
        Position position = new Position(posStr);
        return pieces.get(position.row()).get(position.col());
    }

    public void move(String sourcePosition, String targetPosition) {
        Position from = new Position(sourcePosition);
        Position to = new Position(targetPosition);
        Piece pieceAtFrom = pieces.get(from.row()).get(from.col());
        pieces.get(to.row()).set(to.col(), pieceAtFrom);
        pieces.get(from.row()).set(from.col(), Piece.createBlank());
    }

    public double getScoreOf(Color color) {
        return scoreEvaluator.evaluate(color, this.pieces);
    }
}
