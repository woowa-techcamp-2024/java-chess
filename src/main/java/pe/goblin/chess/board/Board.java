package pe.goblin.chess.board;

import pe.goblin.chess.piece.Piece;
import pe.goblin.chess.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {
    public static final int MIN_ROWS = 0;
    public static final int MAX_ROWS = 8;
    public static final int MIN_COLS = 0;
    public static final int MAX_COLS = 8;

    private List<List<Piece>> pieces = Collections.emptyList();

    /**
     * 초기화시 piece의 위치
     * RNBQKBNR
     * PPPPPPPP
     * blankRank * 4
     * pppppppp
     * rnbqkbnr
     */
    public void initialize() {
        // 초기 보드 생성
        List<List<Piece>> initialPieces = new ArrayList<>();
        // 각 행을 생성하고 mutable 리스트로 초기화
        for (int row = MIN_ROWS; row < MAX_ROWS; row++) {
            List<Piece> rowPieces = new ArrayList<>();
            initialPieces.add(rowPieces);
            // 각 열에 null로 초기화된 Piece 추가
            for (int col = MIN_COLS; col < MAX_COLS; col++) {
                rowPieces.add(Piece.createBlank());
            }
        }
        // 백색 말 배치
        Piece[] fullMajorWhitePieces = {Piece.createWhiteRook(), Piece.createWhiteKnight(), Piece.createWhiteBishop(), Piece.createWhiteQueen(), Piece.createWhiteKing(), Piece.createWhiteBishop(), Piece.createWhiteKnight(), Piece.createWhiteRook()};
        for (int col = MIN_ROWS; col < MAX_ROWS; col++) {
            initialPieces.get(0).set(col, fullMajorWhitePieces[col]);
            initialPieces.get(1).set(col, Piece.createWhitePawn());
        }
        // 흑색 말 배치
        Piece[] fullMajorBlackPieces = {Piece.createBlackRook(), Piece.createBlackKnight(), Piece.createBlackBishop(), Piece.createBlackQueen(), Piece.createBlackKing(), Piece.createBlackBishop(), Piece.createBlackKnight(), Piece.createBlackRook()};
        for (int col = MIN_ROWS; col < MAX_ROWS; col++) {
            initialPieces.get(7).set(col, fullMajorBlackPieces[col]);
            initialPieces.get(6).set(col, Piece.createBlackPawn());
        }
        // pieces 필드에 초기 보드 설정
        this.pieces = initialPieces;
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

    private long countPawns(Piece.Color color) {
        return pieces.parallelStream()
                .flatMap(List::stream)
                .filter(piece -> Objects.equals(piece.getColor(), color) && Objects.equals(piece.getType(), Piece.Type.PAWN))
                .count();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int row = MAX_ROWS - 1; row >= MIN_ROWS; row--) {
            for (int col = MIN_ROWS; col < MAX_ROWS; col++) {
                Piece piece = pieces.get(row).get(col);
                if (piece == null) {
                    sb.append('.');
                } else {
                    sb.append(piece.getRepresentation());
                }
            }
            sb.append(StringUtils.NEW_LINE); // 각 행 끝에 줄 바꿈 추가
        }
        return sb.toString();
    }
}
