package chess.board;

import chess.pieces.*;
import chess.pieces.Piece.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Rank> ranks;
    private static final int BOARD_SIZE = 8;

    public Board() {
        ranks = new ArrayList<>();
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public void initialize() {
        initializeEmpty();
        initializeBlackPiece();
        initializeWhitePiece();
    }

    public void initializeBlackPiece() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Rook.createBlackRook());
        pieces.add(Knight.createBlackKnight());
        pieces.add(Bishop.createBlackBishop());
        pieces.add(Queen.createBlackQueen());
        pieces.add(King.createBlackKing());
        pieces.add(Bishop.createBlackBishop());
        pieces.add(Knight.createBlackKnight());
        pieces.add(Rook.createBlackRook());
        ranks.set(0, new Rank(pieces));
        pieces.clear();

        for (int i = 0; i < BOARD_SIZE; i++) {
            pieces.add(Pawn.createBlackPawn());
        }
        ranks.set(1, new Rank(pieces));
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Piece> pieces = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                pieces.add(Blank.createBlank());
            }
            ranks.add(new Rank(pieces));
        }
    }

    public void initializeWhitePiece() {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            pieces.add(Pawn.createWhitePawn());
        }
        ranks.set(6, new Rank(pieces));
        pieces.clear();

        pieces.add(Rook.createWhiteRook());
        pieces.add(Knight.createWhiteKnight());
        pieces.add(Bishop.createWhiteBishop());
        pieces.add(Queen.createWhiteQueen());
        pieces.add(King.createWhiteKing());
        pieces.add(Bishop.createWhiteBishop());
        pieces.add(Knight.createWhiteKnight());
        pieces.add(Rook.createWhiteRook());
        ranks.set(7, new Rank(pieces));
    }

    public int countPiece(Color color, Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.countPiece(color, type))
                .sum();
    }

    public Piece findPiece(String stringPosition) {
        Position position = new Position(stringPosition);
        return ranks.get(position.getRank()).getPiece(position.getFile());
    }

    public void move(String stringPosition, Piece piece) {
        Position position = new Position(stringPosition);
        ranks.get(position.getRank()).setPiece(position.getFile(), piece);
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece piece = findPiece(sourcePosition);
        move(sourcePosition, Blank.createBlank());
        move(targetPosition, piece);
    }

    public double calculatePoint(Color color) {
        int[] pawnCount = new int[8];
        double totalPoint = 0.0;

        // board에서 rank 단위로 점수 계산이 필요한 기물들을 Rank class에서 점수 계산하는 방법은 어떤지?
        // 그렇게 하는 경우 폰이 세로줄에 겹치는 경우를 어떻게 처리해야할지 고민
        for (Rank rank : ranks) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                Piece piece = rank.getPiece(i);
                if (piece.getColor() != color) {
                    continue;
                }
                if (piece.getType() == Type.PAWN) {
                    pawnCount[i]++;
                }
                totalPoint += piece.getType().getDefaultPoint();
            }
        }

        for (int p : pawnCount) {
            if (p > 1) {
                totalPoint = totalPoint - 0.5 * p;
            }
        }

        return totalPoint;
    }

    public List<Piece> sortPiece(Color color, Order order) {
        List<Piece> sortedPieces = new ArrayList<>();
        for (Rank rank : ranks) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if(color != rank.getPiece(i).getColor() || rank.getPiece(i).getType() == Type.NO_PIECE) {
                    continue;
                }
                sortedPieces.add(rank.getPiece(i));
            }
        }

        return sortedPieces.stream()
                .sorted(order.getComparator())
                .toList();
    }

}