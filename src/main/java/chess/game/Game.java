package chess.game;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import chess.pieces.Piece;
import chess.pieces.PieceFactory;
import chess.pieces.type.Color;
import chess.pieces.type.Representation;
import chess.pieces.type.Type;

public class Game {
    private Board board;

    public void newGame() {
        board = BoardFactory.createStandard();
    }

    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }

    public GameState getGameState() {
        if (!board.existsPiece(Representation.BLACK_KING)) return GameState.WHITE_WIN;
        if (!board.existsPiece(Representation.WHITE_KING)) return GameState.BLACK_WIN;
        return GameState.PLAYING;
    }

    public GameState getWinnerByScore() {
        double whiteScore = board.getScore(Color.WHITE);
        double blackScore = board.getScore(Color.BLACK);

        return GameState.getWinner(whiteScore, blackScore);
    }

    public void move(Position source, Position target) {
        final Piece sourcePiece = findPiece(source);
        final Piece targetPiece = findPiece(target);

        verifyMove(source, target, sourcePiece, targetPiece);
        movePiece(source, target, sourcePiece);
    }

    private void movePiece(Position source, Position target, Piece sourcePiece) {
        sourcePiece.moveTo(target);
        board.setPiece(PieceFactory.createBlank(source));
        board.setPiece(sourcePiece);
    }

    private void verifyMove(Position source, Position target, Piece sourcePiece, Piece targetPiece) {
        // 움직일 수 있는지 검증
        if (sourcePiece.isPieceOf(Representation.BLANK)) {
            throw new IllegalArgumentException("빈칸을 움직일 수 없습니다");
        }
        if (!sourcePiece.canMove(targetPiece)) {
            throw new IllegalArgumentException("이동 가능 범위 초과~");
        }
        // 현재 위치부터 목표방향까지 모든 경로를 탐색하며 중간에 장애물이 있는지 확인
        if ((sourcePiece.getType() != Type.KNIGHT && sourcePiece.getType() != Type.KING) && isPathNotEmpty(source, target)) {
            throw new IllegalArgumentException("이동할 경로에 장애물 발견!!");
        }
    }

    private boolean isPathNotEmpty(Position source, Position target) {
        Position current = source;
        int rankStep = Integer.compare(target.getRank(), source.getRank());
        int fileStep = Integer.compare(target.getFile(), source.getFile());

        current = current.next(fileStep, rankStep);
        while (!current.equals(target)) {
            if (!board.findPiece(current).isPieceOf(Representation.BLANK)) {
                return true;
            }
            current = current.next(fileStep, rankStep);
        }
        return false;
    }

    public String print() {
        return board.print();
    }
}
