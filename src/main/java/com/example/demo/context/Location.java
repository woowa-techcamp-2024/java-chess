package com.example.demo.context;

public record Location(
        Rank rank,
        File file
) {
    public boolean isValid() {
        return rank != null && file != null;
    }

    public static Location of(String location) {
        if(location.length() != 2) return null;
        var file = File.of(location.charAt(0));
        var rank = Rank.of(location.charAt(1));
        return new Location(rank, file);
    }
}
