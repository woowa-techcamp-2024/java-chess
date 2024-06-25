package com.example.demo.context;

/**
 * 체스에서 Rank는 행을 나타냅니다.
 * Rank는 0부터 7까지 총 8개의 행을 가집니다.
 */
public enum Rank {
    ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5), SEVEN(6), EIGHT(7);

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public int index() {
        return this.index;
    }
}
