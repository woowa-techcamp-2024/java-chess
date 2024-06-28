package com.example.demo.rules;

import com.example.demo.context.*;
import com.example.demo.event.EventPublisher;
import com.example.demo.event.MoveActionEvent;
import com.example.demo.piece.Color;
import com.example.demo.piece.Type;

public enum CastlingRule implements Rule {

    WHITE_KING_SIDE((from, to, board, publisher) -> {
        Location kingLocation = new Location(Rank.ONE, File.E);
        Location middleLocation = new Location(Rank.ONE, File.F);
        Location targetLocation = new Location(Rank.ONE, File.G);
        Location rookLocation = new Location(Rank.ONE, File.H);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.WHITE, publisher);
    }),

    BLACK_KING_SIDE((from, to, board, publisher) -> {
        Location kingLocation = new Location(Rank.EIGHT, File.E);
        Location middleLocation = new Location(Rank.EIGHT, File.F);
        Location targetLocation = new Location(Rank.EIGHT, File.G);
        Location rookLocation = new Location(Rank.EIGHT, File.H);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.BLACK, publisher);
    }),

    WHITE_QUEEN_SIDE((from, to, board, publisher) -> {
        Location kingLocation = new Location(Rank.ONE, File.E);
        Location middleLocation = new Location(Rank.ONE, File.D);
        Location targetLocation = new Location(Rank.ONE, File.C);
        Location rookLocation = new Location(Rank.ONE, File.A);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.WHITE, publisher);
    }),

    BLACK_QUEEN_SIDE((from, to, board, publisher) -> {
        Location kingLocation = new Location(Rank.EIGHT, File.E);
        Location middleLocation = new Location(Rank.EIGHT, File.D);
        Location targetLocation = new Location(Rank.EIGHT, File.C);
        Location rookLocation = new Location(Rank.EIGHT, File.A);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.BLACK, publisher);
    }),
    ;

    private final Rule rule;

    CastlingRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public boolean allow(Location from, Location to, Game board, EventPublisher publisher) {
        return this.rule.allow(from, to, board, publisher);
    }

    public static boolean castlingRuleAllow(
            Board board,
            Location from,
            Location to,
            Location kingLocation,
            Location middleLocation,
            Location targetLocation,
            Location rookLocation,
            Color color,
            EventPublisher publisher
    ) {
        var king = board.getPiece(kingLocation);
        var rook = board.getPiece(rookLocation);

        if(king == null || rook == null){
            return false;
        }

        if (!from.equals(kingLocation)) {
            return false;
        }
        if (!to.equals(targetLocation)) {
            return false;
        }

        if (rook.getType() != Type.ROOK || king.getType() != Type.KING) {
            return false;
        }

        if (king.getColor() != rook.getColor() || king.getColor() != color) {
            return false;
        }

        if (board.getPiece(middleLocation) != null || board.getPiece(targetLocation) != null) {
            return false;
        }

        Color enemy = king.getColor().opposite();
        if (board.isCheckPoint(kingLocation, enemy) || board.isCheckPoint(middleLocation, enemy) || board.isCheckPoint(targetLocation, enemy)) {
            return false;
        }

        if(publisher != null){
            publisher.publish(new MoveActionEvent(rookLocation, middleLocation));
        }
        return true;
    }
}
