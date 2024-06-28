package com.example.demo.event;

import com.example.demo.context.File;
import com.example.demo.context.Location;
import com.example.demo.context.Rank;

public record PromotionEvent(
        Rank rank,
        File file
) implements Event {

    public Location getLocation() {
        return new Location(rank, file);
    }
}
