package woowa.camp.chess;

import static woowa.camp.chess.BoardConstants.MAX_COL;
import static woowa.camp.pieces.Piece.Type.NO_PIECE;

import java.util.List;
import woowa.camp.pieces.Piece;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;

public class BoardGame {

    private final Board board;

    private BoardGame(final Board board) {
        this.board = board;
    }

    public static BoardGame createWithInitialize(final Board board) {
        board.initialize();
        return new BoardGame(board);
    }

    public static BoardGame createWithEmptyInitialize(final Board board) {
        board.initializeEmpty();
        return new BoardGame(board);
    }

    public void move(final String source, final String destination) {
        final Position sourcePosition = Position.mapBy(source);
        final Position destinationPosition = Position.mapBy(destination);

        final Piece sourcePiece = board.getPieceBy(sourcePosition.getRow(), sourcePosition.getCol());
        final Piece destinationPiece = board.getPieceBy(destinationPosition.getRow(), destinationPosition.getCol());

        validateMove(sourcePiece, sourcePosition, destinationPiece);

        doMove(sourcePiece, destinationPosition, sourcePosition);
    }

    private void doMove(final Piece sourcePiece, final Position destinationPosition, final Position sourcePosition) {
        board.replace(sourcePiece, destinationPosition);
        board.remove(sourcePosition);
    }

    public double calculateScore(final Color color) {
        double score = 0.0;
        for (int col = 0; col < MAX_COL.getCount(); col++) {
            final List<Piece> file = board.extractPiecesByFile(col, color);
            score += Type.calculateScore(file);
        }
        return score;
    }

    private void validateMove(final Piece sourcePiece, final Position sourcePosition, final Piece destinationPiece) {
        // TODO: move validation 추가
        if (sourcePiece.isPieceOf(NO_PIECE)) {
            throw new IllegalArgumentException(sourcePosition + "에 움직일 수 있는 기물이 없습니다.");
        }
    }

}
