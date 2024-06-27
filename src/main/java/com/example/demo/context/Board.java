package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Pawn;
import com.example.demo.piece.Piece;

import java.util.*;

import static com.example.demo.piece.Type.*;

public class Board {

    private Piece[][] pieceLocation = new Piece[8][8];
    private Map<Location, Set<Color>> checkPoints = new HashMap<>();

    public boolean isCheckPoint(Location targetLocation, Color color) {
        return checkPoints.getOrDefault(targetLocation, new HashSet<>()).contains(color);
    }

    /**
     * <p>
     *     체스판의 초기 상태를 설정합니다.
     * </p>
     * <img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Immortal_game_animation.gif" />
     */
    public void initBoard() {
        this.pieceLocation = new Piece[8][8];
        this.initPawn();
        this.initKing();
        this.initQueen();
        this.initBishop();
        this.initKnight();
        this.initRook();
    }

    //--------------init board start----------------
    private void initPawn() {
        for (File file : File.values()) {
            addPiece(Piece.builder(PAWN)
                    .color(Color.WHITE)
                    .rank(Rank.TWO)
                    .file(file)
                    .build());
            addPiece(Piece.builder(PAWN)
                    .color(Color.BLACK)
                    .rank(Rank.SEVEN)
                    .file(file)
                    .build());
        }
    }

    private void initKing() {
        addPiece(Piece.builder(KING)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.D)
                .build());
        addPiece(Piece.builder(KING)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.D)
                .build());
    }

    private void initQueen() {
        addPiece(Piece.builder(QUEEN)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.E)
                .build());
        addPiece(Piece.builder(QUEEN)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.E)
                .build());
    }

    private void initBishop() {
        addPiece(Piece.builder(BISHOP)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.C)
                .build());
        addPiece(Piece.builder(BISHOP)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.F)
                .build());
        addPiece(Piece.builder(BISHOP)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.C)
                .build());
        addPiece(Piece.builder(BISHOP)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.F)
                .build());
    }

    private void initKnight() {
        addPiece(Piece.builder(KNIGHT)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.B)
                .build());
        addPiece(Piece.builder(KNIGHT)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.G)
                .build());
        addPiece(Piece.builder(KNIGHT)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.B)
                .build());
        addPiece(Piece.builder(KNIGHT)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.G)
                .build());
    }

    private void initRook() {
        addPiece(Piece.builder(ROOK)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.A)
                .build());
        addPiece(Piece.builder(ROOK)
                .color(Color.WHITE)
                .rank(Rank.ONE)
                .file(File.H)
                .build());
        addPiece(Piece.builder(ROOK)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.A)
                .build());
        addPiece(Piece.builder(ROOK)
                .color(Color.BLACK)
                .rank(Rank.EIGHT)
                .file(File.H)
                .build());
    }

    //--------------init board end  ----------------

    /**
     * 보드에 새로운 말을 추가합니다. 위치 정보는 piece의 초기 위치 값을 통해서 조회합니다.
     *
     * @param piece 추가할 말
     */
    public void addPiece(Piece piece) {
        Rank row = piece.getRank();
        File column = piece.getFile();
        pieceLocation[row.index()][column.index()] = piece;
    }

    public void setPiece(Rank row, File column, Piece piece) {
        pieceLocation[row.index()][column.index()] = piece;
    }

    public void setPiece(Location location, Piece piece) {
        setPiece(location.rank(), location.file(), piece);
    }

    public Piece getPiece(Rank row, File column) {
        return pieceLocation[row.index()][column.index()];
    }

    public Piece getPiece(Location location) {
        return getPiece(location.rank(), location.file());
    }

    public void setCheckPoints(Map<Location, Set<Color>> checkPoints) {
        this.checkPoints = checkPoints;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (File file : File.values()) {
            sb.append(file.name() + " ");
        }
        sb.append("\n");
        for (Rank rank : Rank.values()) {
            sb.append(rank.index() + 1 + " ");
            for (File file : File.values()) {
                Piece piece = getPiece(rank, file);
                if (piece != null) {
                    sb.append(piece);
                } else {
                    sb.append(".");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 다음과 같은 규칙을 반영하여 플레이어의 점수를 계산하여 반환한다.
     * <li>각 기물의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.</li>
     * <li>pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.</li>
     *
     * @param color 점수를 계산하는 플레이어의 색상
     * @return 계산된 점수
     */
    public float getScore(Color color) {
        float score = 0;
        for (File file : File.values()) {
            int pawnCount = 0;
            for (Rank rank : Rank.values()) {
                Piece piece = getPiece(rank, file);
                if (piece != null && piece.getColor() == color) {
                    if (piece instanceof Pawn) {
                        pawnCount++;
                    }
                    score += piece.getPoint();
                }
            }
            if (pawnCount > 1) {
                score += 0.5f * pawnCount;
            }
        }
        return score;
    }

    /**
     * 기물 조회시 높은 점수를 가진 기물순으로 순차적으로 정렬된 컬랙션을 반환한다.
     *
     * @param color 조회할 기물의 색상
     * @return 정렬된 기물 컬랙션
     */
    public Collection<Piece> getPieces(Color color) {
        return Arrays.stream(this.pieceLocation).flatMap(Arrays::stream)
                .filter(piece -> piece != null && piece.getColor() == color)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}