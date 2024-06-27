package com.example.demo.rules;

import com.example.demo.context.Board;

import static com.example.demo.context.Board.Location;

@FunctionalInterface
public interface Rule {

    boolean allow(Location from, Location to, Board board);
}
