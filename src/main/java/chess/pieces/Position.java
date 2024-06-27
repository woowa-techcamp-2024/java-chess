package chess.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Position {
    a1(0, 0), a2(1, 0), a3(2, 0), a4(3, 0), a5(4, 0), a6(5, 0), a7(6, 0), a8(7, 0),
    b1(0, 1), b2(1, 1), b3(2, 1), b4(3, 1), b5(4, 1), b6(5, 1), b7(6, 1), b8(7, 1),
    c1(0, 2), c2(1, 2), c3(2, 2), c4(3, 2), c5(4, 2), c6(5, 2), c7(6, 2), c8(7, 2),
    d1(0, 3), d2(1, 3), d3(2, 3), d4(3, 3), d5(4, 3), d6(5, 3), d7(6, 3), d8(7, 3),
    e1(0, 4), e2(1, 4), e3(2, 4), e4(3, 4), e5(4, 4), e6(5, 4), e7(6, 4), e8(7, 4),
    f1(0, 5), f2(1, 5), f3(2, 5), f4(3, 5), f5(4, 5), f6(5, 5), f7(6, 5), f8(7, 5),
    g1(0, 6), g2(1, 6), g3(2, 6), g4(3, 6), g5(4, 6), g6(5, 6), g7(6, 6), g8(7, 6),
    h1(0, 7), h2(1, 7), h3(2, 7), h4(3, 7), h5(4, 7), h6(5, 7), h7(6, 7), h8(7, 7);

    private int rankNumber;
    private int fileNumber;


    Position(int rankNumber, int fileNumber) {
        this.rankNumber = rankNumber;
        this.fileNumber = fileNumber;
    }

    public static Position fromString(String position) {
        return Position.valueOf(position);
    }

    private static Position valueOf(int rankNumber, int fileNumber) {
        //todo 검색 속도 개선하기
        return Arrays.stream(Position.values())
                .filter(p -> p.rankNumber == rankNumber && p.fileNumber == fileNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 포지션 입니다"));
    }


    public int getRankNumber() {
        return rankNumber;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public Degree degree(Position dest) {
        return new Degree(dest.getFileNumber() - this.getFileNumber(), dest.getRankNumber() - this.getRankNumber());
    }

    public List<Position> getPath(Direction direction, Position target) {
        List<Position> path = new ArrayList<>();
        getPath(path, direction, this, target);
        return path;
    }

    private void getPath(List<Position> path, Direction direction, Position src, Position target) {
        if (src == target) {
            return;
        }
        Position next = next(direction);
        path.add(next);
        getPath(path, direction, next, target);
    }

    private Position next(Direction direction) {
        return Position.valueOf(rankNumber + direction.getYDegree(), fileNumber + direction.getXDegree());
    }

    public static class Degree {
        private int xDegree;
        private int yDegree;

        public Degree(int xDegree, int yDegree) {
            this.xDegree = xDegree;
            this.yDegree = yDegree;
        }

        public int getXDegree() {
            return xDegree;
        }

        public int getYDegree() {
            return yDegree;
        }

        public Degree getDegreeOne() {
            return new Degree(convertToOne(xDegree), convertToOne(yDegree));
        }

        public boolean isLinear() {
            return xDegree == 0 || yDegree == 0;
        }

        public boolean isDiagonal() {
            if (yDegree == 0) {
                return false;
            }
            return xDegree % yDegree == 0;
        }

        public static int convertToOne(int number) {
            if (number == 0) {
                return 0;
            }
            if (number > 0) {
                return 1;
            }
            return -1;
        }

        public boolean isOverOneYDegree() {
            return yDegree > 1;
        }

        public boolean isOverOneXDegree() {
            return xDegree > 1;
        }
    }
}
