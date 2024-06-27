package com.example.demo.event;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

import java.util.function.BiConsumer;

public enum Hook {

    PROMOTION((rank, file) -> {
        EventPublisher.INSTANCE.publish(new PromotionEvent(rank, file));
    }),
    ;

    private final BiConsumer<Rank, File> logic;

    Hook(BiConsumer<Rank, File> logic) {
        this.logic = logic;
    }

    public void run(Rank rank, File file) {
        logic.accept(rank, file);
    }
}
