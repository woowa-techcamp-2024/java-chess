package com.example.demo.context;

import com.example.demo.piece.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Board {

    Piece[][] pieceLocation = new Piece[8][8];

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
            setPiece(Rank.TWO, file, new Pawn(Color.WHITE));
            setPiece(Rank.SEVEN, file, new Pawn(Color.BLACK));
        }
    }

    private void initKing() {
        setPiece(Rank.ONE, File.D, new King(Color.WHITE));
        setPiece(Rank.EIGHT, File.D, new King(Color.BLACK));
    }

    private void initQueen() {
        setPiece(Rank.ONE, File.E, new Queen(Color.WHITE));
        setPiece(Rank.EIGHT, File.E, new Queen(Color.BLACK));
    }

    private void initBishop() {
        setPiece(Rank.ONE, File.C, new Bishop(Color.WHITE));
        setPiece(Rank.ONE, File.F, new Bishop(Color.WHITE));
        setPiece(Rank.EIGHT, File.C, new Bishop(Color.BLACK));
        setPiece(Rank.EIGHT, File.F, new Bishop(Color.BLACK));
    }

    private void initKnight() {
        setPiece(Rank.ONE, File.B, new Knight(Color.WHITE));
        setPiece(Rank.ONE, File.G, new Knight(Color.WHITE));
        setPiece(Rank.EIGHT, File.B, new Knight(Color.BLACK));
        setPiece(Rank.EIGHT, File.G, new Knight(Color.BLACK));
    }

    private void initRook() {
        setPiece(Rank.ONE, File.A, new Rook(Color.WHITE));
        setPiece(Rank.ONE, File.H, new Rook(Color.WHITE));
        setPiece(Rank.EIGHT, File.A, new Rook(Color.BLACK));
        setPiece(Rank.EIGHT, File.H, new Rook(Color.BLACK));
    }
    //--------------init board end  ----------------

    public void setPiece(Rank row, File column, Piece piece) {
        pieceLocation[row.index()][column.index()] = piece;
    }

    public Piece getPiece(Rank row, File column) {
        return pieceLocation[row.index()][column.index()];
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
            if (pawnCount != 0) {
                score += 0.5f * pawnCount;
            }
        }
        return score;
    }

    /**
     * 기물 조회시 높은 점수를 가진 기물순으로 순차적으로 정렬된 컬랙션을 반환한다.
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