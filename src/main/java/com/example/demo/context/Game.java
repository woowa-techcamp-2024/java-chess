package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;
import com.example.demo.rules.GlobalRules;
import com.example.demo.rules.KnightRule;
import com.example.demo.rules.NormalRule;
import com.example.demo.rules.Rule;

import java.util.*;

import static com.example.demo.context.Board.Location;

public class Game {
    private final Board board;
    private Color currentTurnColor = Color.WHITE;
    private final User whiteUser;
    private final User blackUser;
    private boolean isEnd = false;
    private final Map<Type, List<Rule>> rules = new HashMap<>();

    // todo : 추후 빌더나 추상 팩토리로 리펙터링 예정
    public Game(Board board, User whiteUser, User blackUser) {
        this.board = board;
        this.whiteUser = whiteUser;
        this.blackUser = blackUser;
        initPawnRules();
        initBishopRules();
        initRookRules();
        initQueenRules();
        initKingRules();
        initKnightRules();
    }

    public Game(Board board) {
        this.board = board;
        initPawnRules();
        initBishopRules();
        initRookRules();
        initQueenRules();
        initKingRules();
        initKnightRules();
        this.whiteUser = new User("user1", Color.WHITE);
        this.blackUser = new User("user2", Color.BLACK);
    }

    //--------------init game start----------------
    private void initPawnRules() {
        List<Rule> pawnRules = new ArrayList<>();
        pawnRules.add(new NormalRule(1, 0, Color.WHITE, Type.PAWN, false));
        pawnRules.add(new NormalRule(2, 0, Color.WHITE, Type.PAWN, true));
        pawnRules.add(new NormalRule(-1, 0, Color.BLACK, Type.PAWN, false));
        pawnRules.add(new NormalRule(-2, 0, Color.BLACK, Type.PAWN, true));
        rules.put(Type.PAWN, pawnRules);
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
            bishopRules.add(new NormalRule(direction[0], direction[1], Color.WHITE, Type.BISHOP, false, true));
            bishopRules.add(new NormalRule(direction[0], direction[1], Color.BLACK, Type.BISHOP, false, true));
        }
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
            rookRules.add(new NormalRule(direction[0], direction[1], Color.WHITE, Type.ROOK, false, true));
            rookRules.add(new NormalRule(direction[0], direction[1], Color.BLACK, Type.ROOK, false, true));
        }
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
            queenRules.add(new NormalRule(direction[0], direction[1], Color.WHITE, Type.QUEEN, false, true));
            queenRules.add(new NormalRule(direction[0], direction[1], Color.BLACK, Type.QUEEN, false, true));
        }
        rules.put(Type.QUEEN, queenRules);
    }

    private void initKingRules() {
        List<Rule> kingRules = new ArrayList<>();
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
            kingRules.add(new NormalRule(direction[0], direction[1], Color.WHITE, Type.KING, false, true));
            kingRules.add(new NormalRule(direction[0], direction[1], Color.BLACK, Type.KING, false, true));
        }
        rules.put(Type.KING, kingRules);
    }

    private void initKnightRules() {
        List<Rule> knightRules = new ArrayList<>();
        knightRules.add(new KnightRule());
        rules.put(Type.KNIGHT, knightRules);
    }
    //--------------init game end  ----------------

    public void start() {
    }

    public void end() {
        isEnd = true;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void printBoard() {
        System.out.println(board);
    }

    /**
     * from에서 to로 체스의 말을 이동하도록 명령합니다.
     *
     * @param from 이동할 말이 위치한 좌표
     * @param to   이동할 목표 좌표
     * @throws RuntimeException 이동할 수 없는 경우
     */
    public void move(Location from, Location to) {

        // check user color
        if(board.getPiece(from).getColor() != currentTurnColor) {
            throw new RuntimeException("현재 턴의 말을 이동해야 합니다.");
        }

        Piece piece = board.getPiece(from.rank(), from.file());

        if (!accept(from, to)) {
            throw new RuntimeException("이동할 수 없습니다.");
        }

        board.setPiece(from, null);
        board.setPiece(to, piece);
    }

    public Color getTurn(){
        return currentTurnColor;
    }

    public void nextTurn(){
        currentTurnColor = currentTurnColor == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    /**
     * 구현된 체스 규칙에 맞는지 확인할 때, 사용합니다.
     *
     * @param from 이동할 체스말의 현재 위치를 나타냅니다.
     * @param to   이동할 위치를 나타냅니다.
     * @return 이동이 가능한 경우 true를 반환하고 이동이 불가능한 경우에는 false를 반환합니다.
     */
    public boolean accept(Location from, Location to) {

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
}
