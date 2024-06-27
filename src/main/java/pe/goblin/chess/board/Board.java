package pe.goblin.chess.board;

import pe.goblin.chess.piece.Piece;
import pe.goblin.chess.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Board {
    public static final int MIN_ROWS = 0;
    public static final int MAX_ROWS = 7;
    public static final int MIN_COLS = 0;
    public static final int MAX_COLS = 7;

    private List<List<Piece>> pieces = Collections.emptyList();

    public void initializeEmpty() {
        this.pieces = createEmptyBoard();
    }

    public void initialize() {
        List<List<Piece>> initialPieces = createEmptyBoard();
        // 백색 말 배치
        Piece[] fullMajorWhitePieces = {Piece.createWhiteRook(), Piece.createWhiteKnight(), Piece.createWhiteBishop(), Piece.createWhiteQueen(), Piece.createWhiteKing(), Piece.createWhiteBishop(), Piece.createWhiteKnight(), Piece.createWhiteRook()};
        for (int col = MIN_COLS; col <= MAX_COLS; col++) {
            initialPieces.get(7).set(col, fullMajorWhitePieces[col]);
            initialPieces.get(6).set(col, Piece.createWhitePawn());
        }
        // 흑색 말 배치
        Piece[] fullMajorBlackPieces = {Piece.createBlackRook(), Piece.createBlackKnight(), Piece.createBlackBishop(), Piece.createBlackQueen(), Piece.createBlackKing(), Piece.createBlackBishop(), Piece.createBlackKnight(), Piece.createBlackRook()};
        for (int col = MIN_COLS; col <= MAX_COLS; col++) {
            initialPieces.get(0).set(col, fullMajorBlackPieces[col]);
            initialPieces.get(1).set(col, Piece.createBlackPawn());
        }
        // pieces 필드에 초기 보드 설정
        this.pieces = initialPieces;
    }

    private List<List<Piece>> createEmptyBoard() {
        // 초기 보드 생성
        List<List<Piece>> initialPieces = new ArrayList<>();
        // 각 행을 생성하고 mutable 리스트로 초기화
        for (int row = MIN_ROWS; row <= MAX_ROWS; row++) {
            List<Piece> rowPieces = new ArrayList<>();
            initialPieces.add(rowPieces);
            // 각 열에 null로 초기화된 Piece 추가
            for (int col = MIN_COLS; col <= MAX_COLS; col++) {
                rowPieces.add(Piece.createBlank());
            }
        }
        return initialPieces;
    }

    public int countAllPieces() {
        return countPiece(Piece.Color.NOCOLOR, Piece.Type.NO_PIECE);
    }

    public int countPiece(Piece.Color color, Piece.Type type) {
        return (int) pieces.parallelStream()
                .flatMap(List::stream)
                .filter(piece -> piece.getColor() == color && piece.getType() == type)
                .count();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int row = MIN_ROWS; row <= MAX_ROWS; row++) {
            for (int col = MIN_COLS; col <= MAX_COLS; col++) {
                Piece piece = pieces.get(row).get(col);
                sb.append(piece.getRepresentation());
            }
            sb.append(StringUtils.NEW_LINE); // 각 행 끝에 줄 바꿈 추가
        }
        return sb.toString();
    }

    public Piece findPiece(String posStr) {
        Position position = new Position(posStr);
        return pieces.get(position.row()).get(position.col());
    }

    public void move(String posStr, Piece piece) {
        Position position = new Position(posStr);
        pieces.get(position.row()).set(position.col(), piece);
    }

    public double calculatePoint(Piece.Color color) {
        double score = getTotalScore(color);
        score = evaluateSuccessivePawn(color, score);
        return score;
    }

    private double getTotalScore(Piece.Color color) {
        double score = 0.0;
        for (int row = 0; row <= MAX_ROWS; row++) {
            for (int col = 0; col <= MAX_COLS; col++) {
                Piece piece = pieces.get(row).get(col);
                if (piece.getColor() == color) {
                    score += piece.getType().getDefaultPoint();
                }
            }
        }
        return score;
    }

    public List<Piece> orderByScore(Piece.Color color, boolean naturalOrder) {
        List<Piece> result = new ArrayList<>();
        for (int row = 0; row <= MAX_ROWS; row++) {
            for (int col = 0; col <= MAX_COLS; col++) {
                Piece piece = pieces.get(row).get(col);
                if (piece.getColor() == color) {
                    result.add(piece);
                }
            }
        }
        result.sort(Comparator.comparing(p -> p.getType().getDefaultPoint()));
        return naturalOrder ? result : result.reversed();
    }

    private double evaluateSuccessivePawn(Piece.Color color, double score) {
        for (int col = 0; col <= MAX_COLS; col++) {
            boolean isPawnBefore = false;
            int pawnInCol = 1;
            for (int row = 0; row <= MAX_ROWS; row++) {
                Piece piece = pieces.get(row).get(col);
                if (piece.getColor() == color && piece.getType() == Piece.Type.PAWN) {
                    if (isPawnBefore) {
                        pawnInCol++;
                    }
                    isPawnBefore = true;
                }
            }
            if (pawnInCol != 1) {
                score -= 0.5 * pawnInCol;
            }
        }
        return score;
    }

    private record Position(int row, int col) {
        Position(String posStr) {
            this(MAX_COLS - (posStr.charAt(1) - '1'), posStr.charAt(0) - 'a');
        }
    }
}
