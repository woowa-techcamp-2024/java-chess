package application;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import chess.pieces.enums.Type;
import chess.pieces.values.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("현재 게임의 점수를 계산합니다.")
    void calculateScoreByColor() {
        // Arrange
        var board = new Board();
        var chessGame = new ChessGame(board);
        board.addPiece(Location.of(1, 'a'), Piece.generatePiece(Type.ROOK, Color.WHITE));
        board.addPiece(Location.of(2, 'a'), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.of(3, 'a'), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.of(4, 'a'), Piece.generatePiece(Type.PAWN, Color.WHITE));

        // Act
        var actualResult = chessGame.calculateScoreByColor(Color.WHITE);

        // Assert
        assertThat(actualResult).isEqualTo(6.5);
    }
}