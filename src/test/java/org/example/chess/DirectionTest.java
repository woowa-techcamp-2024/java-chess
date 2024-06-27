package org.example.chess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DirectionTest {

    @Test
    void testLinearDirection() {
        List<Direction> expected = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
        assertEquals(expected, Direction.linearDirection());
    }

    @Test
    void testDiagonalDirection() {
        List<Direction> expected = List.of(Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
        assertEquals(expected, Direction.diagonalDirection());
    }

    @Test
    void testEveryDirection() {
        List<Direction> expected = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST,
            Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
        assertEquals(expected, Direction.everyDirection());
    }

    @Test
    void testKnightDirection() {
        List<Direction> expected = List.of(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
            Direction.EEN, Direction.EES, Direction.WWN, Direction.WWS);
        assertEquals(expected, Direction.knightDirection());
    }

    @Test
    void testWhitePawnDirection() {
        List<Direction> expected = List.of(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST);
        assertEquals(expected, Direction.whitePawnDirection());
    }

    @Test
    void testBlackPawnDirection() {
        List<Direction> expected = List.of(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST);
        assertEquals(expected, Direction.blackPawnDirection());
    }

    @Test
    void testDetermineDirection() {
        assertEquals(Direction.NORTH, Direction.determineDirection(new Position("d4"), new Position("d6")));
        assertEquals(Direction.SOUTH, Direction.determineDirection(new Position("d4"), new Position("d2")));
        assertEquals(Direction.EAST, Direction.determineDirection(new Position("d4"), new Position("f4")));
        assertEquals(Direction.WEST, Direction.determineDirection(new Position("d4"), new Position("b4")));
        assertEquals(Direction.NORTHEAST, Direction.determineDirection(new Position("d4"), new Position("f6")));
        assertEquals(Direction.SOUTHEAST, Direction.determineDirection(new Position("d4"), new Position("f2")));
        assertEquals(Direction.SOUTHWEST, Direction.determineDirection(new Position("d4"), new Position("b2")));
        assertEquals(Direction.NORTHWEST, Direction.determineDirection(new Position("d4"), new Position("b6")));
        assertEquals(Direction.NOBODY, Direction.determineDirection(new Position("d4"), new Position("d4")));

        // Testing knight directions
        assertEquals(Direction.NNE, Direction.determineDirection(new Position("d4"), new Position("e6")));
        assertEquals(Direction.NNW, Direction.determineDirection(new Position("d4"), new Position("c6")));
        assertEquals(Direction.SSE, Direction.determineDirection(new Position("d4"), new Position("e2")));
        assertEquals(Direction.SSW, Direction.determineDirection(new Position("d4"), new Position("c2")));
        assertEquals(Direction.EEN, Direction.determineDirection(new Position("d4"), new Position("f5")));
        assertEquals(Direction.EES, Direction.determineDirection(new Position("d4"), new Position("f3")));
        assertEquals(Direction.WWN, Direction.determineDirection(new Position("d4"), new Position("b5")));
        assertEquals(Direction.WWS, Direction.determineDirection(new Position("d4"), new Position("b3")));
    }

    @Test
    void testInvalidPosition() {
        assertThrows(IllegalArgumentException.class, () -> new Position("i9"));
        assertThrows(IllegalArgumentException.class, () -> new Position("a9"));
        assertThrows(IllegalArgumentException.class, () -> new Position("i1"));
        assertThrows(IllegalArgumentException.class, () -> new Position("aa"));
        assertThrows(IllegalArgumentException.class, () -> new Position("11"));
        assertThrows(IllegalArgumentException.class, () -> new Position("a"));
        assertThrows(IllegalArgumentException.class, () -> new Position("a11"));
    }
}
