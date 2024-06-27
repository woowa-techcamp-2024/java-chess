package woowa.camp.chess;

import static woowa.camp.chess.BoardConstants.MAX_COL;
import static woowa.camp.chess.BoardConstants.MAX_ROW;
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

    private final List<Rank> board = new ArrayList<>();

    /* ----- biz logic -----*/
    public void initialize() {
        initBoard();
        initBlackPieces();
        initWhitePieces();
    }

    public void initializeEmpty() {
        initBoard();
    }

    private void initBoard() {
        IntStream.range(0, MAX_ROW.getCount()).forEach(count -> {
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
    }

    private void initBlackKnight() {
        final Piece leftBlackKnight = Piece.createBlackPieceOf(KNIGHT);
        final Piece rightBlackKnight = Piece.createBlackPieceOf(KNIGHT);
        board.get(0).replace(1, leftBlackKnight);
        board.get(0).replace(6, rightBlackKnight);
    }

    private void initBlackBishop() {
        final Piece leftBlackBishop = Piece.createBlackPieceOf(BISHOP);
        final Piece rightBlackBishop = Piece.createBlackPieceOf(BISHOP);
        board.get(0).replace(2, leftBlackBishop);
        board.get(0).replace(5, rightBlackBishop);
    }

    private void initBlackQueen() {
        final Piece blackQueen = Piece.createBlackPieceOf(QUEEN);
        board.get(0).replace(3, blackQueen);
    }

    private void initBlackKing() {
        final Piece blackKing = Piece.createBlackPieceOf(KING);
        board.get(0).replace(4, blackKing);
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
    }

    private void initWhiteKnight() {
        final Piece leftWhiteKnight = Piece.createWhitePieceOf(KNIGHT);
        final Piece rightWhiteKnight = Piece.createWhitePieceOf(KNIGHT);
        board.get(7).replace(1, leftWhiteKnight);
        board.get(7).replace(6, rightWhiteKnight);
    }

    private void initWhiteBishop() {
        final Piece leftWhiteBishop = Piece.createWhitePieceOf(BISHOP);
        final Piece rightWhiteBishop = Piece.createWhitePieceOf(BISHOP);
        board.get(7).replace(2, leftWhiteBishop);
        board.get(7).replace(5, rightWhiteBishop);
    }

    private void initWhiteQueen() {
        final Piece whiteQueen = Piece.createWhitePieceOf(QUEEN);
        board.get(7).replace(3, whiteQueen);
    }

    private void initWhiteKing() {
        final Piece whiteKing = Piece.createWhitePieceOf(KING);
        board.get(7).replace(4, whiteKing);
    }

    private void initPawns(final int initRow, final Color color) {
        IntStream.range(0, MAX_COL.getCount()).forEach(col -> {
            final Piece piece = Piece.createPiece(PAWN, color);
            final Position position = new Position(initRow, col);
            move(piece, position);
        });
    }

    // TODO: step1-6 움직임 요구사항 반영
    public void move(final Piece piece, final String position) {
        final Position chessPosition = Position.mapBy(position);
        final int row = chessPosition.getRow();
        final int col = chessPosition.getCol();
        board.get(row).replace(col, piece);
    }

    public void move(final Piece piece, final Position position) {
        final int row = position.getRow();
        final int col = position.getCol();
        board.get(row).replace(col, piece);
    }

    public String showBoard() {
        final StringBuilder sb = new StringBuilder();
        for (int row = 0; row < MAX_ROW.getCount(); row++) {
            appendRowRepresentation(row, sb);
            sb.append(StringUtils.appendNewLine(""));
        }
        return sb.toString();
    }

    private void appendRowRepresentation(final int row, final StringBuilder sb) {
        for (int col = 0; col < MAX_COL.getCount(); col++) {
            final int specificCol = col;
            getPawnByPosition(row, col)
                    .map(piece -> sb.append(piece.getRepresentation().getName()))
                    .orElseThrow(() -> new IllegalStateException(
                            String.format("(%d, %d)에 기물이 존재하지 않습니다.", row, specificCol)));
        }
    }

    public double calculateScore(final Color color) {
        double score = 0.0;
        for (int col = 0; col < MAX_COL.getCount(); col++) {
            final List<Piece> file = extractPiecesByFile(col, color);
            score += Type.calculateScore(file);
        }
        return score;
    }

    private List<Piece> extractPiecesByFile(final int col, final Color color) {
        final List<Piece> piecesByFile = new ArrayList<>();
        for (int row = 0; row < MAX_ROW.getCount(); row++) {
            final Piece piece = getPieceByPosition(row, col);
            if (piece.isSameColor(color)) {
                piecesByFile.add(piece);
            }
        }
        return piecesByFile;
    }

    /* ----- getter ----- */
    public int getPieceCount() {
        return board.stream()
                .map(Rank::getPieces)
                .mapToInt(List::size)
                .sum();
    }

    public Piece getPieceBy(final String position) {
        final Position chessPosition = Position.mapBy(position);
        return board.get(chessPosition.getRow()).get(chessPosition.getCol());
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
        final List<Piece> filteredPieces = new ArrayList<>();

        board.stream()
                .map(rank -> rank.getPiecesFilterBy(type, color))
                .forEach(filteredPieces::addAll);

        return filteredPieces;
    }

}
