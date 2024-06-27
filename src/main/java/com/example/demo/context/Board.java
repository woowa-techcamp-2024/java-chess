package com.example.demo.context;

import com.example.demo.piece.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Board {

    Piece[][] pieceLocation = new Piece[8][8];

    public record Location(
            Rank rank,
            File file
    ) {
        public boolean isValid(){
            return rank != null && file != null;
        }
    }

    /**
     * <p>
     * 보드는 생성시에 정해진 규칙에 따라서 말을 배치한 초기상태를 가져야한다.
     * </p>
     * <img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Immortal_game_animation.gif" />
     */
    public static Board createBoard() {
        Board board = new Board();

        board.initPawn();
        board.initKing();
        board.initQueen();
        board.initBishop();
        board.initKnight();
        board.initRook();

        return board;
    }

    //--------------init board start----------------
    private void initPawn() {
        for (File file : File.values()) {
            addPiece(new Pawn(Color.WHITE, Rank.TWO, file));
            addPiece(new Pawn(Color.BLACK, Rank.SEVEN, file));
        }
    }

    private void initKing() {
        addPiece(new King(Color.WHITE, Rank.ONE, File.D));
        addPiece(new King(Color.BLACK, Rank.EIGHT, File.D));
    }

    private void initQueen() {
        addPiece(new Queen(Color.WHITE, Rank.ONE, File.E));
        addPiece(new Queen(Color.BLACK, Rank.EIGHT, File.E));
    }

    private void initBishop() {
        addPiece(new Bishop(Color.WHITE, Rank.ONE, File.C));
        addPiece(new Bishop(Color.WHITE, Rank.ONE, File.F));
        addPiece(new Bishop(Color.BLACK, Rank.EIGHT, File.C));
        addPiece(new Bishop(Color.BLACK, Rank.EIGHT, File.F));
    }

    private void initKnight() {
        addPiece(new Knight(Color.WHITE, Rank.ONE, File.B));
        addPiece(new Knight(Color.WHITE, Rank.ONE, File.G));
        addPiece(new Knight(Color.BLACK, Rank.EIGHT, File.B));
        addPiece(new Knight(Color.BLACK, Rank.EIGHT, File.G));
    }

    private void initRook() {
        addPiece(new Rook(Color.WHITE, Rank.ONE, File.A));
        addPiece(new Rook(Color.WHITE, Rank.ONE, File.H));
        addPiece(new Rook(Color.BLACK, Rank.EIGHT, File.A));
        addPiece(new Rook(Color.BLACK, Rank.EIGHT, File.H));
    }

    //--------------init board end  ----------------

    /**
     * 보드에 새로운 말을 추가합니다. 위치 정보는 piece의 초기 위치 값을 통해서 조회합니다.
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Piece[] pieces : pieceLocation) {
            for (Piece piece : pieces) {
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