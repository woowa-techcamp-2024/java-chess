package com.example.demo.rules;

import com.example.demo.context.Board;
import com.example.demo.context.Location;

import java.util.Arrays;

public class KnightRule implements Rule {

    private final int[][] directions = {
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2}
    };

    @Override
    public boolean allow(Location from, Location to, Board board) {

        return Arrays.stream(directions)
                .map(step -> new Location(from.rank().move(step[0]), from.file().move(step[1])))
                .filter(Location::isValid)
                .anyMatch(location -> location.equals(to));
    }
}
