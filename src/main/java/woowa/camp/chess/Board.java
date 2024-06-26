package woowa.camp.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import woowa.camp.pieces.Color;
import woowa.camp.pieces.Piece;
import woowa.camp.utils.StringUtils;

public class Board {

    public static final int MAX_PAWN = 8;
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    private final List<List<Piece>> board = new ArrayList<>(new ArrayList<>());
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
        for (int row = 0; row < MAX_ROW; row++) {
            List<Piece> rows = new ArrayList<>(Collections.nCopies(8, null));
            board.add(rows);
        }
    }

    private void initBlackPieces() {
        initBlackRook();
        initBlackKnight();
        initBlackBishop();
        initBlackQueen();
        initBlackKing();
        initPawns(1, Color.PAWN_BLACK);
    }

    private void initBlackRook() {
        final Piece leftBlackRook = Piece.createBlackRook();
        final Piece rightBlackRook = Piece.createBlackRook();
        board.get(0).set(0, leftBlackRook);
        board.get(0).set(7, rightBlackRook);
        pieces.add(leftBlackRook);
        pieces.add(rightBlackRook);
    }

    private void initBlackKnight() {
        final Piece leftBlackKnight = Piece.createBlackKnight();
        final Piece rightBlackKnight = Piece.createBlackKnight();
        board.get(0).set(1, leftBlackKnight);
        board.get(0).set(6, rightBlackKnight);
        pieces.add(leftBlackKnight);
        pieces.add(rightBlackKnight);
    }

    private void initBlackBishop() {
        final Piece leftBlackBishop = Piece.createBlackBishop();
        final Piece rightBlackBishop = Piece.createBlackBishop();
        board.get(0).set(2, leftBlackBishop);
        board.get(0).set(5, rightBlackBishop);
        pieces.add(leftBlackBishop);
        pieces.add(rightBlackBishop);
    }

    private void initBlackQueen() {
        final Piece blackQueen = Piece.createBlackQueen();
        board.get(0).set(3, blackQueen);
        pieces.add(blackQueen);
    }

    private void initBlackKing() {
        final Piece blackKing = Piece.createBlackKing();
        board.get(0).set(4, blackKing);
        pieces.add(blackKing);
    }

    private void initWhitePieces() {
        initWhiteRook();
        initWhiteKnight();
        initWhiteBishop();
        initWhiteQueen();
        initWhiteKing();
        initPawns(6, Color.PAWN_WHITE);
    }

    private void initWhiteRook() {
        final Piece leftWhiteRook = Piece.createWhiteRook();
        final Piece rightWhiteRook = Piece.createWhiteRook();
        board.get(7).set(0, leftWhiteRook);
        board.get(7).set(7, rightWhiteRook);
        pieces.add(leftWhiteRook);
        pieces.add(rightWhiteRook);
    }

    private void initWhiteKnight() {
        final Piece leftWhiteKnight = Piece.createWhiteKnight();
        final Piece rightWhiteKnight = Piece.createWhiteKnight();
        board.get(7).set(1, leftWhiteKnight);
        board.get(7).set(6, rightWhiteKnight);
        pieces.add(leftWhiteKnight);
        pieces.add(rightWhiteKnight);
    }

    private void initWhiteBishop() {
        final Piece leftWhiteBishop = Piece.createWhiteBishop();
        final Piece rightWhiteBishop = Piece.createWhiteBishop();
        board.get(7).set(2, leftWhiteBishop);
        board.get(7).set(5, rightWhiteBishop);
        pieces.add(leftWhiteBishop);
        pieces.add(rightWhiteBishop);
    }

    private void initWhiteQueen() {
        final Piece whiteQueen = Piece.createWhiteQueen();
        board.get(7).set(3, whiteQueen);
        pieces.add(whiteQueen);
    }

    private void initWhiteKing() {
        final Piece whiteKing = Piece.createWhiteKing();
        board.get(7).set(4, whiteKing);
        pieces.add(whiteKing);
    }

    private void initPawns(final int initRow, final Color color) {
        IntStream.range(0, MAX_COL).forEach(col -> {
            final Piece piece = Piece.createPiece(Piece.PAWN, color);
            addPawn(initRow, col, piece);
        });
    }

    private void addPawn(final int row, final int col, final Piece piece) {
        board.get(row).set(col, piece);
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
            getPawnByPosition(row, col)
                    .map(Piece::getRepresentation)
                    .ifPresentOrElse(sb::append, () -> sb.append("."));
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

    public String getPiecesResult(final String pieceName, final Color color) {
        final StringBuilder sb = new StringBuilder();
        final List<Piece> filteredPieces = getPiecesFilterBy(pieceName, color);
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

    public int getPieceCount(final String pieceName, final Color color) {
        return getPiecesFilterBy(pieceName, color).size();
    }

    private List<Piece> getPiecesFilterBy(final String pieceName, final Color color) {
        return pieces.stream()
                .filter(piece -> piece.isPieceOf(pieceName))
                .filter(piece -> piece.isSameColor(color))
                .toList();
    }

}
