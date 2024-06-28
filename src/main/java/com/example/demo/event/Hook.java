package com.example.demo.event;

import com.example.demo.context.File;
import com.example.demo.context.Location;
import com.example.demo.context.Rank;

import java.util.function.BiConsumer;

public enum Hook {

    PROMOTION((location, publisher) -> {
        if (publisher != null) {
            publisher.publish(new PromotionEvent(location.rank(), location.file()));
        }
    }),

    SET_EN_PASSANT((location, publisher) -> {
        if (publisher != null) {
            publisher.publish(new EnPassantEvent(location.rank(), location.file()));
        }
    }),
    ;

    private final BiConsumer<Location, EventPublisher> logic;

    Hook(BiConsumer<Location, EventPublisher> logic) {
        this.logic = logic;
    }

    public void run(Rank rank, File file, EventPublisher publisher) {
        logic.accept(new Location(rank, file), publisher);
    }
}
