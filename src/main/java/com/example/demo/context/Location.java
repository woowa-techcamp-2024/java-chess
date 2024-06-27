package com.example.demo.context;

public record Location(
        Rank rank,
        File file
) {
    public boolean isValid() {
        return rank != null && file != null;
    }
}
