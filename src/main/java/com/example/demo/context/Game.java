package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Pawn;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;
import com.example.demo.rules.GlobalRules;
import com.example.demo.rules.KnightRule;
import com.example.demo.rules.Rule;

import java.util.*;

import static com.example.demo.context.Board.Location;
import static com.example.demo.rules.NormalRule.Builder;

public class Game {
    private final Board board;
    private Color currentTurnColor = Color.WHITE;
    private final User whiteUser;
    private final User blackUser;
    private boolean isEnd = false;
    private final Map<Type, List<Rule>> rules = new HashMap<>();
    private Location promotionPieceLocation = null;

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
        Rule whiteRule = Builder.create(Type.PAWN, Color.WHITE, 1, 0).build();
        Rule white2StepRule = Builder.create(Type.PAWN, Color.WHITE, 2, 0).isApplyFirstMove().build();

        Rule blackRule = Builder.create(Type.PAWN, Color.BLACK, -1, 0).build();
        Rule black2StepRule = Builder.create(Type.PAWN, Color.BLACK, -2, 0).isApplyFirstMove().build();

        pawnRules.add(whiteRule);
        pawnRules.add(white2StepRule);
        pawnRules.add(blackRule);
        pawnRules.add(black2StepRule);
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
            for (Color color : Color.values()) {
                Rule rule = Builder.create(Type.BISHOP, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                bishopRules.add(rule);
            }
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
            for (Color color : Color.values()) {
                Rule rule = Builder.create(Type.ROOK, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                rookRules.add(rule);
            }
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
            for (Color color : Color.values()) {
                Rule rule = Builder.create(Type.QUEEN, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                queenRules.add(rule);
            }
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
            for (Color color : Color.values()) {
                Rule rule = Builder.create(Type.KING, color, direction[0], direction[1])
                        .isAttackRule()
                        .build();
                kingRules.add(rule);
            }
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
        checkTurn(from);

        // check promotion rule
        if(promotionPieceLocation != null){
            throw new RuntimeException("먼저 폰의 승진이 필요합니다.");
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
     * 승진 명령을 처리하는 메서드
     * @param type 승진할 대상인 말의 타입
     */
    public void promotion(Type type){

        if(promotionPieceLocation == null){
            throw new RuntimeException("승진할 폰이 없습니다.");
        }

        checkTurn(promotionPieceLocation);

        Piece piece = switch (type){
            case QUEEN -> new Pawn(currentTurnColor, promotionPieceLocation.rank(), promotionPieceLocation.file());
            case ROOK -> new Pawn(currentTurnColor, promotionPieceLocation.rank(), promotionPieceLocation.file());
            case BISHOP -> new Pawn(currentTurnColor, promotionPieceLocation.rank(), promotionPieceLocation.file());
            case KNIGHT -> new Pawn(currentTurnColor, promotionPieceLocation.rank(), promotionPieceLocation.file());
            default -> throw new RuntimeException("대상 타입으로 승진할 수 없습니다.");
        };

        board.setPiece(promotionPieceLocation, piece);
        promotionPieceLocation = null;
    }

    /**
     * 승진 이벤트가 발생하였을 때, 승진이 필요한 폰의 위치를 게임에 저장하는 메서드
     * @param location 승진을 해야하는 폰의 위치를 나타냅니다.
     * @throws RuntimeException 폰이 아닌 말이 승진을 요청한 경우
     */
    public void setPromotionPawn(Location location){
        Piece piece = board.getPiece(location);
        if(piece.getType() != Type.PAWN){
            throw new RuntimeException("폰이 아닙니다.");
        }

        promotionPieceLocation = location;
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

    /**
     * 변경할 대상이 현재 턴의 색상인지 확인합니다.
     * @param location 변경할 대상의 위치
     * @throws RuntimeException 변경할 대상이 현재 턴의 색상이 아닌 경우
     */
    private void checkTurn(Location location){
        if(board.getPiece(location).getColor() != currentTurnColor) {
            throw new RuntimeException("현재 턴의 말을 이동해야 합니다.");
        }
    }
}
