package com.example.demo.rules;

import com.example.demo.context.Game;
import com.example.demo.context.Location;
import com.example.demo.context.Rank;
import com.example.demo.event.DeletePieceEvent;
import com.example.demo.event.EventPublisher;
import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;

public class EnpassantRule implements Rule {

    @Override
    public boolean allow(Location from, Location to, Game game, EventPublisher publisher) {

        var whitePawn = game.getPiece(from);

        if (from.rank() != Rank.FIVE) {
            return false;
        }

        if (to.rank() != Rank.SIX) {
            return false;
        }

        Piece enemy = game.getPiece(from.rank(), to.file());

        if (enemy == null || enemy.getType() != Type.PAWN || enemy.getColor() == whitePawn.getColor() || enemy.getColor() == Color.WHITE) {
            return false;
        }

        if (!enemy.isEnpassantTarget()) {
            return false;
        }

        if (enemy.getTurn() + 1 != game.getTurn()) {
            return false;
        }

        if(game.getPiece(to) != null && game.getPiece(to).getColor() == Color.WHITE){
            return false;
        }

        if(publisher != null)
            publisher.publish(new DeletePieceEvent(Rank.FIVE, to.file()));

        return true;
    }
}
