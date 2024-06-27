package com.example.demo.rules;

import com.example.demo.context.Game;
import com.example.demo.context.Location;

@FunctionalInterface
public interface Rule {

    boolean allow(Location from, Location to, Game board);
}
