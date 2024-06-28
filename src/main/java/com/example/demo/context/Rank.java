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

    /**
     * 현재 Rank에서 step만큼 이동한 Rank를 반환합니다.
     * @param step
     * @return 이동한 위치의 Rank입니다. 만약 이동한 위치가 존재하지 않는다면 null을 반환합니다.
     */
    public Rank move(int step){
        try {
            return values()[index + step];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static Rank of(char rank) {
        return switch (rank) {
            case '1' -> ONE;
            case '2' -> TWO;
            case '3' -> THREE;
            case '4' -> FOUR;
            case '5' -> FIVE;
            case '6' -> SIX;
            case '7' -> SEVEN;
            case '8' -> EIGHT;
            default -> throw new IllegalArgumentException("Invalid rank: " + rank);
        };
    }

}
