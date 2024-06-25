package com.woowatechcamp.chess;
import static org.junit.Assert.*;

import com.woowatechcamp.chess.pieces.Color;
import com.woowatechcamp.chess.pieces.Pawn;
import org.junit.Test;

import java.util.List;

public class BoardTest {
    @Test
    public void create() {
        Board board = new Board();
        List<Color> colors = List.of(Color.WHITE, Color.BLACK);
        int size = 0;
        for (int i = 0; i < colors.size(); i++) {
            Color color = colors.get(i);
            Pawn pawn = new Pawn(color);
            board.add(pawn);
            ++size;
            assertEquals(size, board.size());
            assertEquals(pawn, board.findPawn(i));
        }
    }
}
