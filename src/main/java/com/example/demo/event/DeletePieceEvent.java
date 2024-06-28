package com.example.demo.event;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public record DeletePieceEvent(
        Rank rank,
        File file
) implements Event{
}
