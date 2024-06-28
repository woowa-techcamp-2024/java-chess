package com.example.demo.event;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public record EnPassantEvent(
        Rank rank,
        File file
) implements Event {
}
