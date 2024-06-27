package woowa.camp.chess;

import static woowa.camp.pieces.Piece.Type.NO_PIECE;

import woowa.camp.pieces.Piece;

public class BoardGame {

    private final Board board;

    public BoardGame(final Board board) {
        board.initialize();
        this.board = board;
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

    private void validateMove(final Piece sourcePiece, final Position sourcePosition, final Piece destinationPiece) {
        // TODO: move validation 추가
        if (sourcePiece.isPieceOf(NO_PIECE)) {
            throw new IllegalArgumentException(sourcePosition + "에 움직일 수 있는 기물이 없습니다.");
        }
    }

}
