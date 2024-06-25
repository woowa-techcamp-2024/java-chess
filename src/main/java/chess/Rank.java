package chess;

import java.util.Arrays;

public enum Rank {
    ONE(1), TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7), EIGHT(8);

    public final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int index) {
        return Arrays.stream(Rank.values()).filter(rank -> rank.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("범위 밖의 값입니다."));
    }
}
