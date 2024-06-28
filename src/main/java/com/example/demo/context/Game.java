package com.example.demo.context;

import com.example.demo.event.*;
import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;
import com.example.demo.rules.RuleManager;

import java.util.*;

public class Game extends Board {
    private Color currentTurnColor = Color.WHITE;
    private RuleManager ruleManager = RuleManager.getInstance();
    private final EventPublisher publisher;
    private final User whiteUser;
    private final User blackUser;
    private boolean isEnd = false;
    private Location promotionPieceLocation = null;
    private int turn = 1;

    // builder class
    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private User whiteUser;
        private User blackUser;

        private GameBuilder() {
            this.whiteUser = new User("user1", Color.WHITE);
            this.blackUser = new User("user2", Color.BLACK);
        }

        public GameBuilder whiteUser(User whiteUser) {
            this.whiteUser = whiteUser;
            return this;
        }

        public GameBuilder blackUser(User blackUser) {
            this.blackUser = blackUser;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

    public Game(GameBuilder builder) {
        this.whiteUser = builder.whiteUser;
        this.blackUser = builder.blackUser;
        this.publisher = new EventPublisher();
    }

    public void start() {
    }

    public void end() {
        isEnd = true;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void printBoard() {
        System.out.println(this);
    }

    /**
     * event 처리 메서드
     */
    public void handle(Event event) {
        if (event instanceof PromotionEvent) {
            PromotionEvent promotionEvent = (PromotionEvent) event;
            Location location = promotionEvent.getLocation();

            Piece piece = this.getPiece(location);

            if ((location.rank() == Rank.ONE && piece.getColor() == Color.BLACK & piece.getType() == Type.PAWN) ||
                    (location.rank() == Rank.EIGHT && piece.getColor() == Color.WHITE & piece.getType() == Type.PAWN)) {
                setPromotionPawn(location);
                setPromotionPawn(location);
                this.currentTurnColor = this.getPiece(location).getColor();
            }
        }

        if (event instanceof MoveActionEvent) {
            MoveActionEvent moveActionEvent = (MoveActionEvent) event;
            move(moveActionEvent.from(), moveActionEvent.to());
        }

        if (event instanceof EnPassantEvent) {
            EnPassantEvent enPassantEvent = (EnPassantEvent) event;
            this.getPiece(enPassantEvent.rank(), enPassantEvent.file())
                    .setEnpassantTarget(true);
        }

        if (event instanceof DeletePieceEvent) {
            DeletePieceEvent deletePieceEvent = (DeletePieceEvent) event;
            this.setPiece(deletePieceEvent.rank(), deletePieceEvent.file(), null);
        }
    }

    /**
     * from에서 to로 체스의 말을 이동하도록 명령합니다.
     *
     * @param from 이동할 말이 위치한 좌표
     * @param to   이동할 목표 좌표
     * @throws RuntimeException 이동할 수 없는 경우
     */
    public void handleMoveCommand(Location from, Location to) {

        // check user color
        checkTurn(from);

        // check promotion rule
        if (promotionPieceLocation != null) {
            throw new RuntimeException("먼저 폰의 승진이 필요합니다.");
        }


        if (!ruleManager.accept(this, from, to, publisher)) {
            throw new RuntimeException("이동할 수 없습니다.");
        }

        move(from, to);
        nextTurn();
        while (!this.publisher.isEmpty()) {
            handle(this.publisher.consume());
        }
    }

    private void move(Location from, Location to) {
        var piece = this.getPiece(from);
        this.setPiece(from, null);
        this.setPiece(to, piece);
        piece.setTurn(turn);
    }

    public Color getTurnColor() {
        return currentTurnColor;
    }

    public int getTurn() {
        return turn;
    }

    private void nextTurn() {
        turn++;
        currentTurnColor = currentTurnColor == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public void forceTurnColor(Color color) {
        currentTurnColor = color;
    }

    /**
     * 승진 명령을 처리하는 메서드
     *
     * @param type 승진할 대상인 말의 타입
     */
    public void promotion(Type type) {

        if (promotionPieceLocation == null) {
            throw new RuntimeException("승진할 폰이 없습니다.");
        }

        checkTurn(promotionPieceLocation);

        Piece piece = Piece.builder(type)
                .color(currentTurnColor)
                .rank(promotionPieceLocation.rank())
                .file(promotionPieceLocation.file())
                .build();

        this.setPiece(promotionPieceLocation, piece);
        promotionPieceLocation = null;
    }

    /**
     * 각 지점의 공격 가능 포인트를 계산합니다.
     */
    public void calculateCheckPoint() {
        Map<Location, Set<Color>> checkPoints = new HashMap<>();
        for (Rank fromR : Rank.values()) {
            for (File fromF : File.values()) {
                if (this.getPiece(fromR, fromF) == null) continue;
                for (Rank toR : Rank.values()) {
                    for (File toF : File.values()) {
                        Location from = new Location(fromR, fromF);
                        Location to = new Location(toR, toF);
                        checkPoints.putIfAbsent(to, new HashSet<>(Color.values().length));
                        if (ruleManager.accept(this, from, to, null)) {
                            checkPoints.get(to).add(this.getPiece(from).getColor());
                        }
                    }
                }
            }
        }
        this.setCheckPoints(checkPoints);
    }

    /**
     * 승진 이벤트가 발생하였을 때, 승진이 필요한 폰의 위치를 게임에 저장하는 메서드
     *
     * @param location 승진을 해야하는 폰의 위치를 나타냅니다.
     * @throws RuntimeException 폰이 아닌 말이 승진을 요청한 경우
     */
    public void setPromotionPawn(Location location) {
        Piece piece = this.getPiece(location);
        if (piece.getType() != Type.PAWN) {
            throw new RuntimeException("폰이 아닙니다.");
        }

        promotionPieceLocation = location;
    }

    //todo
    public List<Location> candidateLocations(Location of) {
        return null;
    }

    /**
     * 변경할 대상이 현재 턴의 색상인지 확인합니다.
     *
     * @param location 변경할 대상의 위치
     * @throws RuntimeException 변경할 대상이 현재 턴의 색상이 아닌 경우
     */
    private void checkTurn(Location location) {
        if (this.getPiece(location).getColor() != currentTurnColor) {
            throw new RuntimeException("현재 턴의 말을 이동해야 합니다.");
        }
    }

}
