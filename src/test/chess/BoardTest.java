package chess;

import org.junit.jupiter.api.Test;
import chess.piece.Color;
import chess.piece.Pawn;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void create() {
        Board board = new Board();

        Pawn white = new Pawn(Color.WHITE);
        board.set(1, 2, white);
        assertThat(board.get(1, 2)).isEqualTo(white);
        assertThat(board.findPawns()).hasSize(1);
        assertThat(board.findPawns().get(0)).isEqualTo(white);

        Pawn black = new Pawn(Color.BLACK);
        board.set(0, 3, black);
        assertThat(board.get(0, 3)).isEqualTo(black);
        assertThat(board.findPawns()).hasSize(2);
        assertThat(board.findPawns().get(0)).isEqualTo(black);
    }

}
