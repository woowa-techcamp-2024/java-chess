package com.example.demo.rules;

import com.example.demo.context.Game;
import com.example.demo.context.Location;
import com.example.demo.event.EventPublisher;

public enum GlobalRules implements Rule{

    DO_NOT_ATTACK_SAME_COLOR_PIECE((from, to, board, publisher) -> {
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
    public boolean allow(Location from, Location to, Game board, EventPublisher publisher) {
        return rule.allow(from, to, board, publisher);
    }
}
