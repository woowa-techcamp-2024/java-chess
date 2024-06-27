package woowa.camp.chess;

import static woowa.camp.pieces.Piece.Type.BISHOP;
import static woowa.camp.pieces.Piece.Type.KING;
import static woowa.camp.pieces.Piece.Type.KNIGHT;
import static woowa.camp.pieces.Piece.Type.PAWN;
import static woowa.camp.pieces.Piece.Type.QUEEN;
import static woowa.camp.pieces.Piece.Type.ROOK;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import woowa.camp.pieces.Piece;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;
import woowa.camp.utils.StringUtils;

public class Board {

    public static final int MAX_PAWN = 8;
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    private final List<Rank> board = new ArrayList<>();
    private final List<Piece> pieces = new ArrayList<>();

    /* ----- biz logic -----*/
    public void add(final Piece piece) {
        pieces.add(piece);
    }

    private void validateFindPawn(final int pawnIndex) {
        final int pieceCount = getPieceCount();
        if (isOutOfRange(pieceCount, pawnIndex)) {
            throw new IllegalArgumentException(String.format("범위를 벗어난 PawnIndex = %d, 현재 Pawn Size = %d",
                    pawnIndex, pieceCount));
        }
    }

    private boolean isOutOfRange(int size, int index) {
        return index < 0 || index >= size;
    }

    public void initialize() {
        initBoard();
        initBlackPieces();
        initWhitePieces();
    }

    private void initBoard() {
        IntStream.range(0, MAX_ROW).forEach(count -> {
            board.add(new Rank());
        });
    }

    private void initBlackPieces() {
        initBlackRook();
        initBlackKnight();
        initBlackBishop();
        initBlackQueen();
        initBlackKing();
        initPawns(1, Color.BLACK);
    }

    private void initBlackRook() {
        final Piece leftBlackRook = Piece.createBlackPieceOf(ROOK);
        final Piece rightBlackRook = Piece.createBlackPieceOf(ROOK);
        board.get(0).replace(0, leftBlackRook);
        board.get(0).replace(7, rightBlackRook);
        pieces.add(leftBlackRook);
        pieces.add(rightBlackRook);
    }

    private void initBlackKnight() {
        final Piece leftBlackKnight = Piece.createBlackPieceOf(KNIGHT);
        final Piece rightBlackKnight = Piece.createBlackPieceOf(KNIGHT);
        board.get(0).replace(1, leftBlackKnight);
        board.get(0).replace(6, rightBlackKnight);
        pieces.add(leftBlackKnight);
        pieces.add(rightBlackKnight);
    }

    private void initBlackBishop() {
        final Piece leftBlackBishop = Piece.createBlackPieceOf(BISHOP);
        final Piece rightBlackBishop = Piece.createBlackPieceOf(BISHOP);
        board.get(0).replace(2, leftBlackBishop);
        board.get(0).replace(5, rightBlackBishop);
        pieces.add(leftBlackBishop);
        pieces.add(rightBlackBishop);
    }

    private void initBlackQueen() {
        final Piece blackQueen = Piece.createBlackPieceOf(QUEEN);
        board.get(0).replace(3, blackQueen);
        pieces.add(blackQueen);
    }

    private void initBlackKing() {
        final Piece blackKing = Piece.createBlackPieceOf(KING);
        board.get(0).replace(4, blackKing);
        pieces.add(blackKing);
    }

    private void initWhitePieces() {
        initWhiteRook();
        initWhiteKnight();
        initWhiteBishop();
        initWhiteQueen();
        initWhiteKing();
        initPawns(6, Color.WHITE);
    }

    private void initWhiteRook() {
        final Piece leftWhiteRook = Piece.createWhitePieceOf(ROOK);
        final Piece rightWhiteRook = Piece.createWhitePieceOf(ROOK);
        board.get(7).replace(0, leftWhiteRook);
        board.get(7).replace(7, rightWhiteRook);
        pieces.add(leftWhiteRook);
        pieces.add(rightWhiteRook);
    }

    private void initWhiteKnight() {
        final Piece leftWhiteKnight = Piece.createWhitePieceOf(KNIGHT);
        final Piece rightWhiteKnight = Piece.createWhitePieceOf(KNIGHT);
        board.get(7).replace(1, leftWhiteKnight);
        board.get(7).replace(6, rightWhiteKnight);
        pieces.add(leftWhiteKnight);
        pieces.add(rightWhiteKnight);
    }

    private void initWhiteBishop() {
        final Piece leftWhiteBishop = Piece.createWhitePieceOf(BISHOP);
        final Piece rightWhiteBishop = Piece.createWhitePieceOf(BISHOP);
        board.get(7).replace(2, leftWhiteBishop);
        board.get(7).replace(5, rightWhiteBishop);
        pieces.add(leftWhiteBishop);
        pieces.add(rightWhiteBishop);
    }

    private void initWhiteQueen() {
        final Piece whiteQueen = Piece.createWhitePieceOf(QUEEN);
        board.get(7).replace(3, whiteQueen);
        pieces.add(whiteQueen);
    }

    private void initWhiteKing() {
        final Piece whiteKing = Piece.createWhitePieceOf(KING);
        board.get(7).replace(4, whiteKing);
        pieces.add(whiteKing);
    }

    private void initPawns(final int initRow, final Color color) {
        IntStream.range(0, MAX_COL).forEach(col -> {
            final Piece piece = Piece.createPiece(PAWN, color);
            addPawn(initRow, col, piece);
        });
    }

    private void addPawn(final int row, final int col, final Piece piece) {
        board.get(row).replace(col, piece);
        pieces.add(piece);
    }

    public String showBoard() {
        final StringBuilder sb = new StringBuilder();
        for (int row = 0; row < MAX_ROW; row++) {
            appendRowRepresentation(row, sb);
            sb.append(StringUtils.appendNewLine(""));
        }
        return sb.toString();
    }

    private void appendRowRepresentation(final int row, final StringBuilder sb) {
        for (int col = 0; col < MAX_COL; col++) {
            final int specificCol = col;
            getPawnByPosition(row, col)
                    .map(piece -> sb.append(piece.getRepresentation().getName()))
                    .orElseThrow(() -> new IllegalStateException(
                            String.format("(%d, %d)에 기물이 존재하지 않습니다.", row, specificCol)));
        }
    }

    /* ----- getter ----- */
    public int getPieceCount() {
        return pieces.size();
    }

    public Piece getPawn(final int pawnIndex) {
        validateFindPawn(pawnIndex);
        return pieces.get(pawnIndex);
    }

    public String getPiecesResult(final Type type, final Color color) {
        final StringBuilder sb = new StringBuilder();
        final List<Piece> filteredPieces = getPiecesFilterBy(type, color);
        filteredPieces.forEach(filteredPiece -> sb.append(filteredPiece.getRepresentation()));
        return sb.toString();
    }

    private Optional<Piece> getPawnByPosition(final int row, final int col) {
        return Optional.ofNullable(board.get(row).get(col));
    }

    public int getBoardRowSize() {
        return board.get(0).size();
    }

    public int getBoardColSize() {
        return board.size();
    }

    public int getPieceCount(final Type type, final Color color) {
        return getPiecesFilterBy(type, color).size();
    }

    private List<Piece> getPiecesFilterBy(final Type type, final Color color) {
        return pieces.stream()
                .filter(piece -> piece.isPieceOf(type))
                .filter(piece -> piece.isSameColor(color))
                .toList();
    }

}
