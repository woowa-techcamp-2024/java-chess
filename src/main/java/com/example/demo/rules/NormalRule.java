package com.example.demo.rules;

import com.example.demo.context.File;
import com.example.demo.context.Game;
import com.example.demo.context.Location;
import com.example.demo.context.Rank;
import com.example.demo.event.EventPublisher;
import com.example.demo.event.Hook;
import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;

import java.util.ArrayList;
import java.util.List;

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
    private final List<Hook> hooks = new ArrayList<>();

    public NormalRule(Builder builder) {
        this.rankStep = builder.rankStep;
        this.fileStep = builder.fileStep;
        this.targetColor = builder.targetColor;
        this.targetType = builder.targetType;
        this.isApplyFirstMove = builder.isApplyFirstMove;
        this.isAttackRule = builder.isAttackRule;
        this.hooks.addAll(builder.hooks);

        if (rankStep != 0) this.minRankStep = rankStep / Math.abs(rankStep);
        else this.minRankStep = 0;
        if (fileStep != 0) this.minFileStep = fileStep / Math.abs(fileStep);
        else this.minFileStep = 0;
    }

    @Override
    public boolean allow(Location from, Location to, Game board, EventPublisher publisher) {

        Piece piece = board.getPiece(from);

        if (piece == null)
            return false;
        if (piece.getType() == Type.PAWN && isAttackRule && board.getPiece(to) == null) {
            return false;
        }
        if (isApplyFirstMove && piece.hasMoveHistory())
            return false;
        if (piece.getType() != targetType || piece.getColor() != targetColor)
            return false;

        int moveRank = minRankStep;
        int moveFile = minFileStep;
        int attackChanceCount = isAttackRule ? 1 : 0;

        Rank nextRank = from.rank().move(minRankStep);
        File nextFile = from.file().move(minFileStep);

        boolean isAccept = false;
        while (nextRank != null && nextFile != null) {

            if (Math.abs(moveRank) > Math.abs(rankStep) || Math.abs(moveFile) > Math.abs(fileStep)) {
                isAccept = false;
                break;
            }

            if (board.getPiece(nextRank, nextFile) != null) {
                if (attackChanceCount == 0) {
                    isAccept = false;
                    break;
                } else attackChanceCount--;
            }

            if (nextRank == to.rank() && nextFile == to.file()) {
                isAccept = true;
                break;
            }

            nextRank = nextRank.move(minRankStep);
            nextFile = nextFile.move(minFileStep);
            moveRank += minRankStep;
            moveFile += minFileStep;
        }

        // run hook
        for (Hook hook : hooks) {
            hook.run(to.rank(), to.file(), publisher);
        }

        return isAccept;
    }

    public void addHook(Hook hook) {
        hooks.add(hook);
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
        private List<Hook> hooks = new ArrayList<>();

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

        public Builder addHook(Hook hook) {
            hooks.add(hook);
            return this;
        }

        public NormalRule build() {
            return new NormalRule(this);
        }
    }
}
