package org.example.chess;

import org.example.chess.pieces.*;
import org.example.chess.pieces.global.*;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Board {
    private List<Rank> pieces;

    public Board() {
        initializeEmpty();
    }

    public void moveTo(Position from, Position to) {
        Piece piece = findPiece(from);
        Rank fromRank = pieces.get(from.getRow());
        Rank toRank = pieces.get(to.getRow());

        List<MoveSeq> moveSeqs = piece.getMoveSeqs();
        validateNotEmpty(piece);

        for(MoveSeq moveSeq: moveSeqs) {
            if (isReachable(from, moveSeq, to)) {
                fromRank.emptyPiece(from.getCol());
                toRank.setPiece(to.getCol(), piece);

                System.out.println("move success");
                return;
            }
        }

        throw new RuntimeException("move fail");
    }

    private boolean isReachable(Position from, MoveSeq moveSeq, Position to) {
        Position cur = from.copy();
        for (Move move : moveSeq.getMoves()) {
            if (!cur.movable(move.getDir()) || !isOnceMovable(cur, move)) {
                return false;
            }

            cur = cur.move(move.getDir());
            if (cur.equals(to)) {
                return true;
            }
        }

        return false;
    }

    private boolean isOnceMovable(Position from, Move move) throws RuntimeException {
        Piece startPiece = findPiece(from);
        Piece destPiece = findPiece(from.move(move.getDir()));

        if (destPiece.getName() == Piece.Type.NO_PIECE) {
            return true;
        }

        return move.isJumpable() || startPiece.getColor() != destPiece.getColor();
    }

    private void validateNotEmpty(Piece piece) {
        if (piece.isBlank()) {
            throw new RuntimeException("시작 자리에 말이 존재하지 않습니다.");
        }
    }

    public Rank getUnmodifiableRank(int idx) {
        return pieces.get(idx);
    }

    public void setPiece(Position position, Piece piece) {
        pieces.get(position.getRow()).setPiece(position.getCol(), piece);
    }

    public int countPieces() {
        return (int) pieces.stream().mapToLong(Rank::countPieces).sum();
    }

    public Piece findPiece(Position position) {
        return pieces.get(position.getRow()).getPiece(position.getCol());
    }

    public String getWhitePawnsRepresentation() {
        return pieces.stream()
                .flatMap(rank -> rank.pieceRow.stream())
                .filter(piece -> piece.getName() == Piece.Type.PAWN)
                .filter(piece -> piece.getColor() == Piece.Color.WHITE)
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(joining());
    }

    public String getBlackPawnsRepresentation() {
        return pieces.stream()
                .flatMap(rank -> rank.pieceRow.stream())
                .filter(piece -> piece.getName() == Piece.Type.PAWN)
                .filter(piece -> piece.getColor() == Piece.Color.BLACK)
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(joining());
    }

    public void initializeEmpty() {
        List<Rank> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(new Rank());
        }
        pieces = board;
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            this.setPiece(new Position(1, i), Pawn.of(Piece.Color.BLACK));
            this.setPiece(new Position(6, i), Pawn.of(Piece.Color.WHITE));
        }

        this.setPiece(new Position(0, 0), Rook.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 1), Knight.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 2), Bishop.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 3), Queen.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 4), King.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 5), Bishop.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 6), Knight.of(Piece.Color.BLACK));
        this.setPiece(new Position(0, 7), Rook.of(Piece.Color.BLACK));

        this.setPiece(new Position(7, 0), Rook.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 1), Knight.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 2), Bishop.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 3), Queen.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 4), King.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 5), Bishop.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 6), Knight.of(Piece.Color.WHITE));
        this.setPiece(new Position(7, 7), Rook.of(Piece.Color.WHITE));

        this.showBoard();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        this.pieces.forEach(rank -> {
            sb.append(rank.getRepresentation()).append(System.lineSeparator());
        });

        System.out.println(sb);

        return sb.toString();
    }

//    public String showScore() {
//        StringBuilder sb = new StringBuilder();
//
//        this.pieces.forEach(rank -> {
//            sb.append(rank.showScore()).append(System.lineSeparator());
//        });
//
//        System.out.println(sb);
//
//        return sb.toString();
//    }

//    public double calculatePoint(Piece.Color color) {
////        revisePawnScore();
//        return this.pieces.stream()
//                .mapToDouble(rank -> rank.calculateScore(color))
//                .sum();
//    }

//    // TODO pawn 점수 저장하지 않도록 수정 필요
//    // board pawn 들의 점수를 재조정하는 함수
//    private void revisePawnScore() {
//        for(int colIdx= 0; colIdx < 8; colIdx++) {
//            int finalColIdx = colIdx;
//            long blackPawnCount = this.pieces.stream()
//                    .map(rank -> rank.getPiece(finalColIdx))
//                    .filter(Piece::isPawn)
//                    .filter(Piece::isBlack)
//                    .count();
//
//            long whitePawnCount = this.pieces.stream()
//                    .map(rank -> rank.getPiece(finalColIdx))
//                    .filter(Piece::isPawn)
//                    .filter(Piece::isWhite)
//                    .count();
//
//            if (blackPawnCount >= 2) {
//                this.pieces.stream()
//                        .map(rank -> rank.getPiece(finalColIdx))
//                        .filter(Piece::isPawn)
//                        .filter(Piece::isBlack)
//                        .forEach(piece -> piece.setPoint(0.5));
//            }
//
//            if (whitePawnCount >= 2) {
//                this.pieces.stream()
//                        .map(rank -> rank.getPiece(finalColIdx))
//                        .filter(Piece::isPawn)
//                        .filter(Piece::isWhite)
//                        .forEach(piece -> piece.setPoint(0.5));
//            }
//        }
//    }

    public List<Piece> sort(Order order) {
        Comparator<Piece> asc = Comparator.comparingDouble(piece -> piece.getName().getDefaultPoint());
        Comparator<Piece> desc = (p1, p2) -> Double.compare(p2.getName().getDefaultPoint(), p1.getName().getDefaultPoint());

        if (order == Order.DESC) {
            return this.pieces.stream()
                    .map(Rank::getPieces)
                    .flatMap(List::stream)
                    .filter(Piece::isExist)
                    .sorted(desc)
                    .toList();
        }
        return this.pieces.stream()
                .map(Rank::getPieces)
                .flatMap(List::stream)
                .filter(Piece::isExist)
                .sorted(asc)
                .toList();
    }

    private static class Rank {
        private List<Piece> pieceRow;

        public Rank() {
            List<Piece> pieceRow = new ArrayList<Piece>();
            for (int i = 0; i < 8; i++) {
                pieceRow.add(NoPiece.of());
            }
            this.pieceRow = pieceRow;
        }

        public void setPiece(int idx, Piece piece) {
            this.pieceRow.set(idx, piece);
        }

        public void emptyPiece(int idx) {
            this.pieceRow.set(idx, NoPiece.of());
        }

        public Piece getPiece(int idx) {
            return pieceRow.get(idx);
        }

        public List<Piece> getPieces() {
            return Collections.unmodifiableList(pieceRow);
        }

        public String getRepresentation() {
            return this.pieceRow.stream().map(Piece::getRepresentation).map(String::valueOf).collect(joining());
        }

        public long countPieces() {
            return this.pieceRow.stream().filter(Piece::isExist).count();
        }

        //TODO 계산
//        public double calculateScore(Piece.Color color) {
//            return this.pieceRow.stream()
//                    .filter(piece -> piece.getColor() == color)
//                    .mapToDouble(Piece::)
//                    .sum();
//        }

//        public String showScore() {
//            return Arrays.toString(this.pieceRow.stream()
//                    .mapToDouble(Piece::getPoint)
//                    .toArray());
//        }
    }
}
