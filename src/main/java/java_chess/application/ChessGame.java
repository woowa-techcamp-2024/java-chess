package java_chess.application;

import java.util.stream.Collectors;
import java_chess.chess.Board;
import java_chess.chess.pieces.Piece;
import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Type;
import java_chess.chess.pieces.values.Location;

public class ChessGame {

    private final Board board;
    private Color current;

    public ChessGame(Board board) {
        this.board = board;
        this.current = Color.WHITE;
    }

    /**
     * 게임을 시작합니다.
     */
    public void startGame() {
        board.initialize();
        this.current = Color.WHITE;
    }

    /**
     * 현재 순서를 반환합니다.
     *
     * @return 현재 순서
     */
    public Color getTurn() {
        return current;
    }

    /**
     * 현재 게임의 점수를 계산합니다.
     *
     * @param color 색상
     * @return 현재 게임의 점수
     */
    public double calculateScoreByColor(Color color) {
        var score = board.getPiecesByColor(color).stream()
            .filter(piece -> !Type.PAWN.isInstance(piece))
            .mapToDouble(Piece::getScore).sum();
        score += calculatePawnScore(color);
        return score;
    }

    /**
     * 말을 이동합니다.
     *
     * @param from 이동할 말의 위치
     * @param to   이동할 위치
     * @throws IllegalArgumentException 이동할 수 없는 위치일 경우
     * @throws IllegalArgumentException 상대의 말을 옮기려고 하는 경우
     */
    public void movePiece(Location from, Location to) {
        var possibleMoves = board.getLocationsThatPieceCanMoveByLocation(from);
        validateCurrentTurn(from);
        if (!possibleMoves.contains(to)) {
            throw new IllegalArgumentException("This piece cannot move to the location.");
        }
        board.addPiece(to, board.getPiece(from));
        board.removePiece(from);
        //verifyCheck();
        swapTurn();
    }

    /**
     * 체크 메이트 상태인지 확인합니다.
     *
     * @throws IllegalStateException 체크 메이트 상태일 경우
     */
    public void verifyCheckMate() {
        if (board.verifyCheckMate(current.oppositeColor())) {
            throw new IllegalStateException("Checkmate!");
        }
    }

    private double calculatePawnScore(Color color) {
        return board.getPawnsLocationsByColor(color).stream()
            .collect(Collectors.toMap(
                Location::getY,
                entry -> 1.0,
                Double::sum))
            .values().stream()
            .mapToDouble(score -> score <= 1 ? 1 : score / 2)
            .sum();
    }

    private void validateCurrentTurn(Location location) {
        if (!board.getPiece(location).verifySameColor(current)) {
            throw new IllegalArgumentException("This piece is not yours.");
        }
    }

    private void swapTurn() {
        current = current.oppositeColor();
    }

}
