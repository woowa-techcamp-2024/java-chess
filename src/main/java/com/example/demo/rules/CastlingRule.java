package com.example.demo.rules;

import com.example.demo.context.Board;
import com.example.demo.context.File;
import com.example.demo.context.Rank;
import com.example.demo.event.EventPublisher;
import com.example.demo.event.MoveActionEvent;
import com.example.demo.piece.Color;
import com.example.demo.piece.Type;

import static com.example.demo.context.Board.Location;

public enum CastlingRule implements Rule {

    WHITE_KING_SIDE((from, to, board) -> {
        Location kingLocation = new Location(Rank.ONE, File.E);
        Location middleLocation = new Location(Rank.ONE, File.F);
        Location targetLocation = new Location(Rank.ONE, File.G);
        Location rookLocation = new Location(Rank.ONE, File.H);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.WHITE);
    }),

    BLACK_KING_SIDE((from, to, board) -> {
        Location kingLocation = new Location(Rank.EIGHT, File.E);
        Location middleLocation = new Location(Rank.EIGHT, File.F);
        Location targetLocation = new Location(Rank.EIGHT, File.G);
        Location rookLocation = new Location(Rank.EIGHT, File.H);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.BLACK);
    }),

    WHITE_QUEEN_SIDE((from, to, board) -> {
        Location kingLocation = new Location(Rank.ONE, File.E);
        Location middleLocation = new Location(Rank.ONE, File.D);
        Location targetLocation = new Location(Rank.ONE, File.C);
        Location rookLocation = new Location(Rank.ONE, File.A);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.WHITE);
    }),

    BLACK_QUEEN_SIDE((from, to, board) -> {
        Location kingLocation = new Location(Rank.EIGHT, File.E);
        Location middleLocation = new Location(Rank.EIGHT, File.D);
        Location targetLocation = new Location(Rank.EIGHT, File.C);
        Location rookLocation = new Location(Rank.EIGHT, File.A);

        return castlingRuleAllow(board, from, to, kingLocation, middleLocation, targetLocation, rookLocation, Color.BLACK);
    }),
    ;

    private final Rule rule;

    CastlingRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public boolean allow(Location from, Location to, Board board) {
        return this.rule.allow(from, to, board);
    }

    public static boolean castlingRuleAllow(
            Board board,
            Location from,
            Location to,
            Location kingLocation,
            Location middleLocation,
            Location targetLocation,
            Location rookLocation,
            Color color
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

        EventPublisher.INSTANCE.publish(new MoveActionEvent(rookLocation, middleLocation));
        return true;
    }
}
