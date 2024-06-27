package com.example.demo.rules;

import com.example.demo.context.Board;
import com.example.demo.context.Location;

@FunctionalInterface
public interface Rule {

    boolean allow(Location from, Location to, Board board);
}
