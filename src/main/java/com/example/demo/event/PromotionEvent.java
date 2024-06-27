package com.example.demo.event;

import com.example.demo.context.Board;
import com.example.demo.context.File;
import com.example.demo.context.Rank;

public record PromotionEvent(
        Rank rank,
        File file
) implements Event {

    public Board.Location getLocation() {
        return new Board.Location(rank, file);
    }
}
