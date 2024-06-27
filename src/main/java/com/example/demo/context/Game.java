package com.example.demo.context;

import com.example.demo.event.Event;
import com.example.demo.event.EventPublisher;
import com.example.demo.event.MoveActionEvent;
import com.example.demo.event.PromotionEvent;
import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;
import com.example.demo.rules.RuleManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.example.demo.context.Board.Location;

public class Game {
    private final Board board;
    private Color currentTurnColor = Color.WHITE;
    private RuleManager ruleManager = RuleManager.getInstance();
    private final User whiteUser;
    private final User blackUser;
    private boolean isEnd = false;
    private Location promotionPieceLocation = null;

    // builder class
    public static GameBuilder builder(Board board) {
        return new GameBuilder(board);
    }

    public static class GameBuilder {
        private User whiteUser;
        private User blackUser;
        private Board board;

        private GameBuilder(Board board) {
            this.whiteUser = new User("user1", Color.WHITE);
            this.blackUser = new User("user2", Color.BLACK);
            this.board = board;
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
        this.board = builder.board;
        this.whiteUser = builder.whiteUser;
        this.blackUser = builder.blackUser;
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
        System.out.println(board);
    }

    /**
     * event 처리 메서드
     */
    public void handle(Event event) {
        if (event instanceof PromotionEvent) {
            PromotionEvent promotionEvent = (PromotionEvent) event;
            Location location = promotionEvent.getLocation();
            setPromotionPawn(location);
            this.currentTurnColor = board.getPiece(location).getColor();
        }

        if (event instanceof MoveActionEvent) {
            MoveActionEvent moveActionEvent = (MoveActionEvent) event;
            var rook = board.getPiece(moveActionEvent.from());
            board.setPiece(moveActionEvent.to(), rook);
            board.setPiece(moveActionEvent.from(), null);
        }
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
        if (promotionPieceLocation != null) {
            throw new RuntimeException("먼저 폰의 승진이 필요합니다.");
        }

        Piece piece = board.getPiece(from.rank(), from.file());

        if (!ruleManager.accept(board, from, to)) {
            throw new RuntimeException("이동할 수 없습니다.");
        }

        board.setPiece(from, null);
        board.setPiece(to, piece);
    }

    public Color getTurn() {
        return currentTurnColor;
    }

    public void nextTurn() {
        currentTurnColor = currentTurnColor == Color.WHITE ? Color.BLACK : Color.WHITE;
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

        board.setPiece(promotionPieceLocation, piece);
        promotionPieceLocation = null;
    }

    /**
     * 각 지점의 공격 가능 포인트를 계산합니다.
     */
    public void calculateCheckPoint() {
        Map<Location, Set<Color>> checkPoints = new HashMap<>();
        for (Rank fromR : Rank.values()) {
            for (File fromF : File.values()) {
                if (board.getPiece(fromR, fromF) == null) continue;
                for (Rank toR : Rank.values()) {
                    for (File toF : File.values()) {
                        Location from = new Location(fromR, fromF);
                        Location to = new Location(toR, toF);
                        checkPoints.putIfAbsent(to, new HashSet<>(Color.values().length));
                        if (ruleManager.accept(board, from, to)) {
                            checkPoints.get(to).add(board.getPiece(from).getColor());
                        }
                    }
                }
            }
        }
        EventPublisher.INSTANCE.clear();
        board.setCheckPoints(checkPoints);
    }

    /**
     * 승진 이벤트가 발생하였을 때, 승진이 필요한 폰의 위치를 게임에 저장하는 메서드
     *
     * @param location 승진을 해야하는 폰의 위치를 나타냅니다.
     * @throws RuntimeException 폰이 아닌 말이 승진을 요청한 경우
     */
    public void setPromotionPawn(Location location) {
        Piece piece = board.getPiece(location);
        if (piece.getType() != Type.PAWN) {
            throw new RuntimeException("폰이 아닙니다.");
        }

        promotionPieceLocation = location;
    }

    /**
     * 변경할 대상이 현재 턴의 색상인지 확인합니다.
     *
     * @param location 변경할 대상의 위치
     * @throws RuntimeException 변경할 대상이 현재 턴의 색상이 아닌 경우
     */
    private void checkTurn(Location location) {
        if (board.getPiece(location).getColor() != currentTurnColor) {
            throw new RuntimeException("현재 턴의 말을 이동해야 합니다.");
        }
    }
}
