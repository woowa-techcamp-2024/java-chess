package com.example.demo.rules;

import com.example.demo.context.Game;
import com.example.demo.context.Location;
import com.example.demo.event.Hook;
import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;

import java.util.*;

public class RuleManager {

    private static final RuleManager instance = new RuleManager();

    private final Map<Type, List<Rule>> attackRules = new HashMap<>();
    private final Map<Type, List<Rule>> rules = new HashMap<>();

    public static RuleManager getInstance() {
        instance.initPawnRules();
        instance.initBishopRules();
        instance.initRookRules();
        instance.initQueenRules();
        instance.initKingRules();
        instance.initKnightRules();
        return instance;
    }

    private void initPawnRules() {
        List<Rule> pawnRules = new ArrayList<>();
        List<Rule> attackPawnRules = new ArrayList<>();

        Rule whiteRule = NormalRule.Builder.create(Type.PAWN, Color.WHITE, 1, 0)
                .addHook(Hook.PROMOTION)
                .build();

        Rule white2StepRule = NormalRule.Builder.create(Type.PAWN, Color.WHITE, 2, 0).isApplyFirstMove().build();

        Rule whiteAttackRule1 = NormalRule.Builder.create(Type.PAWN, Color.WHITE, 1, 1)
                .addHook(Hook.PROMOTION)
                .isAttackRule()
                .build();
        Rule whiteAttackRule2 = NormalRule.Builder.create(Type.PAWN, Color.WHITE, 1, -1)
                .addHook(Hook.PROMOTION)
                .isAttackRule()
                .build();

        Rule blackRule = NormalRule.Builder.create(Type.PAWN, Color.BLACK, -1, 0)
                .addHook(Hook.PROMOTION)
                .build();

        Rule black2StepRule = NormalRule.Builder.create(Type.PAWN, Color.BLACK, -2, 0).isApplyFirstMove().build();

        Rule blackAttackRule1 = NormalRule.Builder.create(Type.PAWN, Color.BLACK, -1, 1)
                .addHook(Hook.PROMOTION)
                .isAttackRule()
                .build();
        Rule blackAttackRule2 = NormalRule.Builder.create(Type.PAWN, Color.BLACK, -1, -1)
                .addHook(Hook.PROMOTION)
                .isAttackRule()
                .build();

        pawnRules.add(whiteRule);
        pawnRules.add(white2StepRule);
        pawnRules.add(blackRule);
        pawnRules.add(black2StepRule);

        pawnRules.add(whiteAttackRule1);
        pawnRules.add(whiteAttackRule2);
        pawnRules.add(blackAttackRule1);
        pawnRules.add(blackAttackRule2);

        attackPawnRules.add(whiteAttackRule1);
        attackPawnRules.add(whiteAttackRule2);
        attackPawnRules.add(blackAttackRule1);
        attackPawnRules.add(blackAttackRule2);

        rules.put(Type.PAWN, pawnRules);
        attackRules.put(Type.PAWN, attackPawnRules);
    }

    private void initBishopRules() {
        List<Rule> bishopRules = new ArrayList<>();
        int[][] directions = {
                {7, 7},
                {-7, 7},
                {7, -7},
                {-7, -7}
        };

        for (int[] direction : directions) {
            for (Color color : Color.values()) {
                Rule rule = NormalRule.Builder.create(Type.BISHOP, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                bishopRules.add(rule);
            }
        }
        attackRules.put(Type.BISHOP, bishopRules);
        rules.put(Type.BISHOP, bishopRules);
    }

    private void initRookRules() {
        List<Rule> rookRules = new ArrayList<>();
        int[][] directions = {
                {7, 0},
                {-7, 0},
                {0, 7},
                {0, -7}
        };
        for (int[] direction : directions) {
            for (Color color : Color.values()) {
                Rule rule = NormalRule.Builder.create(Type.ROOK, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                rookRules.add(rule);
            }
        }
        attackRules.put(Type.ROOK, rookRules);
        rules.put(Type.ROOK, rookRules);
    }

    private void initQueenRules() {
        List<Rule> queenRules = new ArrayList<>();
        int[][] directions = {
                {7, 0},
                {-7, 0},
                {0, 7},
                {0, -7},
                {7, 7},
                {-7, 7},
                {7, -7},
                {-7, -7}
        };

        for (int[] direction : directions) {
            for (Color color : Color.values()) {
                Rule rule = NormalRule.Builder.create(Type.QUEEN, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                queenRules.add(rule);
            }
        }
        attackRules.put(Type.QUEEN, queenRules);
        rules.put(Type.QUEEN, queenRules);
    }

    private void initKingRules() {
        List<Rule> kingRules = new ArrayList<>();
        List<Rule> kingMoveRules = new ArrayList<>(kingRules);

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
                {1, 1},
                {-1, 1},
                {1, -1},
                {-1, -1}
        };

        for (int[] direction : directions) {
            for (Color color : Color.values()) {
                Rule rule = NormalRule.Builder.create(Type.KING, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                kingRules.add(rule);
                kingMoveRules.add(rule);
            }
        }

        // castling rule
        kingMoveRules.addAll(Arrays.asList(CastlingRule.values()));

        attackRules.put(Type.KING, kingRules);
        rules.put(Type.KING, kingMoveRules);
    }

    private void initKnightRules() {
        List<Rule> knightRules = new ArrayList<>();
        knightRules.add(new KnightRule());
        attackRules.put(Type.KNIGHT, knightRules);
        rules.put(Type.KNIGHT, knightRules);
    }

    /**
     * 구현된 체스 규칙에 맞는지 확인할 때, 사용합니다.
     *
     * @param from 이동할 체스말의 현재 위치를 나타냅니다.
     * @param to   이동할 위치를 나타냅니다.
     * @return 이동이 가능한 경우 true를 반환하고 이동이 불가능한 경우에는 false를 반환합니다.
     */
    public boolean accept(Game board, Location from, Location to) {

        // check global rules
        var notAllowedRules = Arrays.stream(GlobalRules.values())
                .filter(rule -> !rule.allow(from, to, board))
                .toList();

        if (!notAllowedRules.isEmpty()) {
            return false;
        }

        // check each piece rules
        Piece piece = board.getPiece(from);

        if (piece == null) {
            return false;
        }

        return rules.getOrDefault(piece.getType(), new ArrayList<>())
                .stream()
                .anyMatch(rule -> rule.allow(from, to, board));
    }

    /**
     * 공격 가능한지 여부를 확인할 때, 사용합니다.
     */
    public boolean canAttack(Game board, Location from, Location to) {
        // check global rules
        var notAllowedRules = Arrays.stream(GlobalRules.values())
                .filter(rule -> !rule.allow(from, to, board))
                .toList();

        if (!notAllowedRules.isEmpty()) {
            return false;
        }

        // check each piece rules
        Piece piece = board.getPiece(from);

        if (piece == null) {
            return false;
        }

        return attackRules.getOrDefault(piece.getType(), new ArrayList<>())
                .stream()
                .anyMatch(rule -> rule.allow(from, to, board));
    }
}
