package chess.pieces;

import chess.exception.PositionOutOfBoardException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Position {
    a1(0, 0), a2(0, 1), a3(0, 2), a4(0, 3), a5(0, 4), a6(0, 5), a7(0, 6), a8(0, 7),
    b1(1, 0), b2(1, 1), b3(1, 2), b4(1, 3), b5(1, 4), b6(1, 5), b7(1, 6), b8(1, 7),
    c1(2, 0), c2(2, 1), c3(2, 2), c4(2, 3), c5(2, 4), c6(2, 5), c7(2, 6), c8(2, 7),
    d1(3, 0), d2(3, 1), d3(3, 2), d4(3, 3), d5(3, 4), d6(3, 5), d7(3, 6), d8(3, 7),
    e1(4, 0), e2(4, 1), e3(4, 2), e4(4, 3), e5(4, 4), e6(4, 5), e7(4, 6), e8(4, 7),
    f1(5, 0), f2(5, 1), f3(5, 2), f4(5, 3), f5(5, 4), f6(5, 5), f7(5, 6), f8(5, 7),
    g1(6, 0), g2(6, 1), g3(6, 2), g4(6, 3), g5(6, 4), g6(6, 5), g7(6, 6), g8(6, 7),
    h1(7, 0), h2(7, 1), h3(7, 2), h4(7, 3), h5(7, 4), h6(7, 5), h7(7, 6), h8(7, 7);

    private int rankNumber;
    private int fileNumber;


    Position(int fileNumber, int rankNumber) {
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
                .orElseThrow(PositionOutOfBoardException::new);
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
        Position next = src.next(direction);
        path.add(next);
        if (next == target) {
            return;
        }
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
            return Math.abs(yDegree) > 1;
        }

        public boolean isOverOneXDegree() {
            return Math.abs(xDegree) > 1;
        }
    }
}
