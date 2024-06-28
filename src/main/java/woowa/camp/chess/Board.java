package woowa.camp.chess;

import static woowa.camp.chess.BoardConstants.MAX_COL;
import static woowa.camp.chess.BoardConstants.MAX_ROW;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import woowa.camp.pieces.Bishop;
import woowa.camp.pieces.Blank;
import woowa.camp.pieces.King;
import woowa.camp.pieces.Knight;
import woowa.camp.pieces.Pawn;
import woowa.camp.pieces.Piece;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;
import woowa.camp.pieces.Queen;
import woowa.camp.pieces.Rook;

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
        IntStream.range(0, MAX_ROW.getCount())
                .forEach(count -> board.add(new Rank()));
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
        final Piece leftBlackRook = Rook.createBlack();
        final Piece rightBlackRook = Rook.createBlack();
        board.get(0).replace(0, leftBlackRook);
        board.get(0).replace(7, rightBlackRook);
    }

    private void initBlackKnight() {
        final Piece leftBlackKnight = Knight.createBlack();
        final Piece rightBlackKnight = Knight.createBlack();
        board.get(0).replace(1, leftBlackKnight);
        board.get(0).replace(6, rightBlackKnight);
    }

    private void initBlackBishop() {
        final Piece leftBlackBishop = Bishop.createBlack();
        final Piece rightBlackBishop = Bishop.createBlack();
        board.get(0).replace(2, leftBlackBishop);
        board.get(0).replace(5, rightBlackBishop);
    }

    private void initBlackQueen() {
        final Piece blackQueen = Queen.createBlack();
        board.get(0).replace(3, blackQueen);
    }

    private void initBlackKing() {
        final Piece blackKing = King.createBlack();
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
        final Piece leftWhiteRook = Rook.createWhite();
        final Piece rightWhiteRook = Rook.createWhite();
        board.get(7).replace(0, leftWhiteRook);
        board.get(7).replace(7, rightWhiteRook);
    }

    private void initWhiteKnight() {
        final Piece leftWhiteKnight = Knight.createWhite();
        final Piece rightWhiteKnight = Knight.createWhite();
        board.get(7).replace(1, leftWhiteKnight);
        board.get(7).replace(6, rightWhiteKnight);
    }

    private void initWhiteBishop() {
        final Piece leftWhiteBishop = Bishop.createWhite();
        final Piece rightWhiteBishop = Bishop.createWhite();
        board.get(7).replace(2, leftWhiteBishop);
        board.get(7).replace(5, rightWhiteBishop);
    }

    private void initWhiteQueen() {
        final Piece whiteQueen = Queen.createWhite();
        board.get(7).replace(3, whiteQueen);
    }

    private void initWhiteKing() {
        final Piece whiteKing = King.createWhite();
        board.get(7).replace(4, whiteKing);
    }

    private void initPawns(final int initRow, final Color color) {
        IntStream.range(0, MAX_COL.getCount()).forEach(col -> {
            final Piece piece = Pawn.create(color);
            final Position position = new Position(initRow, col);
            replace(piece, position);
        });
    }

    public Piece findPieceBy(final String position) {
        final Position chessPosition = Position.mapBy(position);
        return board.get(chessPosition.getRow()).get(chessPosition.getCol());
    }

    public void replace(final Piece piece, final Position destination) {
        final int destinationRow = destination.getRow();
        final int destinationCol = destination.getCol();
        board.get(destinationRow).replace(destinationCol, piece);
    }

    public void remove(final Position target) {
        final int targetRow = target.getRow();
        final int targetCol = target.getCol();
        board.get(targetRow).replace(targetCol, Blank.create());
    }

    public List<Piece> extractPiecesByFile(final int col, final Color color) {
        final List<Piece> piecesByFile = new ArrayList<>();
        for (int row = 0; row < MAX_ROW.getCount(); row++) {
            final Piece piece = getPieceBy(row, col);
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

    public String getPiecesResult(final Type type, final Color color) {
        final StringBuilder sb = new StringBuilder();
        final List<Piece> filteredPieces = getPiecesFilterBy(type, color);
        filteredPieces.forEach(filteredPiece -> sb.append(filteredPiece.getRepresentation()));
        return sb.toString();
    }

    public Piece getPieceBy(final int row, final int col) {
        return Optional.ofNullable(board.get(row).get(col))
                .orElseThrow(() -> new IllegalArgumentException(String.format("(%d, %d)에 기물이 존재하지 않습니다.", row, col)));
    }

    public List<Piece> getAscendingSortedPiecesFilterBy(final Color color) {
        return board.stream()
                .flatMap(rank -> rank.getPiecesFilterBy(color).stream())
                .sorted(Comparator.comparing(Piece::getDefaultScore))
                .toList();
    }

    public List<Piece> getDescendingSortedPiecesFilterBy(final Color color) {
        return board.stream()
                .flatMap(rank -> rank.getPiecesFilterBy(color).stream())
                .sorted(Comparator.comparing(Piece::getDefaultScore).reversed())
                .toList();
    }

    public int getPieceCount(final Type type, final Color color) {
        return getPiecesFilterBy(type, color).size();
    }

    private List<Piece> getPiecesFilterBy(final Type type, final Color color) {
        return board.stream()
                .flatMap(rank -> rank.getPiecesFilterBy(type, color).stream())
                .toList();
    }

    public int getBoardRowSize() {
        return board.get(0).size();
    }

    public int getBoardColSize() {
        return board.size();
    }

}
