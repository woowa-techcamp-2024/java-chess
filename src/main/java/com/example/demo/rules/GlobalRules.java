package com.example.demo.rules;

import com.example.demo.context.Board;
import com.example.demo.context.Location;

public enum GlobalRules implements Rule{

    DO_NOT_ATTACK_SAME_COLOR_PIECE((from, to, board) -> {
        var fromPiece = board.getPiece(from);
        var toPiece = board.getPiece(to);
        if(toPiece == null) return true;
        return fromPiece.getColor() != toPiece.getColor();
    }),
    ;

    private final Rule rule;

    GlobalRules(Rule rule) {
        this.rule = rule;
    }

    @Override
    public boolean allow(Location from, Location to, Board board) {
        return rule.allow(from, to, board);
    }
}
