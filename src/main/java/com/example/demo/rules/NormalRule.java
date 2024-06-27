package com.example.demo.rules;

import com.example.demo.context.Board;
import com.example.demo.context.File;
import com.example.demo.context.Rank;
import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;

/**
 * 일반 규칙을 나타내는 클래스입니다.
 * 단순히 행과 열의 이동을 나타내는 모든 규칙을 정의합니다.
 * 이동 경로에 말이 있다면 해당 칸으로 이동할 수 없습니다.
 */
public class NormalRule implements Rule {

    private final int rankStep;
    private final int fileStep;
    private final int minRankStep;
    private final int minFileStep;
    private final Color targetColor;
    private final Type targetType;
    private final boolean isApplyFirstMove;
    private final boolean isAttackRule;

    public NormalRule(Builder builder){
        this.rankStep = builder.rankStep;
        this.fileStep = builder.fileStep;
        this.targetColor = builder.targetColor;
        this.targetType = builder.targetType;
        this.isApplyFirstMove = builder.isApplyFirstMove;
        this.isAttackRule = builder.isAttackRule;

        if (rankStep != 0) this.minRankStep = rankStep / Math.abs(rankStep);
        else this.minRankStep = 0;
        if (fileStep != 0) this.minFileStep = fileStep / Math.abs(fileStep);
        else this.minFileStep = 0;
    }

    @Override
    public boolean allow(Board.Location from, Board.Location to, Board board) {

        Piece piece = board.getPiece(from);

        if (isApplyFirstMove && !piece.isLocatedAtInitLocation(from.rank(), from.file()))
            return false;
        if (piece.getType() != targetType || piece.getColor() != targetColor)
            return false;

        int moveRank = minRankStep;
        int moveFile = minFileStep;
        int attackChanceCount = isAttackRule ? 1 : 0;

        Rank nextRank = from.rank().move(minRankStep);
        File nextFile = from.file().move(minFileStep);

        while (nextRank != null && nextFile != null) {

            if (Math.abs(moveRank) > Math.abs(rankStep) || Math.abs(moveFile) > Math.abs(fileStep))
                return false;

            if (board.getPiece(nextRank, nextFile) != null) {
                if (attackChanceCount == 0) return false;
                else attackChanceCount--;
            }

            if (nextRank == to.rank() && nextFile == to.file())
                return true;

            nextRank = nextRank.move(minRankStep);
            nextFile = nextFile.move(minFileStep);
            moveRank += minRankStep;
            moveFile += minFileStep;
        }

        return false;
    }

    /**
     * Noraml rule builder 클래스
     */
    public static class Builder {

        private final int rankStep;
        private final int fileStep;
        private final Color targetColor;
        private final Type targetType;
        private boolean isApplyFirstMove = false;
        private boolean isAttackRule = false;

        public Builder(int rankStep, int fileStep, Color targetColor, Type targetType) {
            this.rankStep = rankStep;
            this.fileStep = fileStep;
            this.targetColor = targetColor;
            this.targetType = targetType;
        }

        public static Builder create(Type targetType, Color targetColor, int rankStep, int fileStep) {
            return new Builder(rankStep, fileStep, targetColor, targetType);
        }

        public Builder isApplyFirstMove() {
            this.isApplyFirstMove = true;
            return this;
        }

        public Builder isAttackRule() {
            this.isAttackRule = true;
            return this;
        }

        public NormalRule build() {
            return new NormalRule(this);
        }
    }
}
