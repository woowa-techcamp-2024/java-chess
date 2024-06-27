package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Order;
import org.example.chess.pieces.global.Position;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Board {
    private List<Rank> pieces;

    public Board() {
        initializeEmpty();
    }

    public void moveTo(Position from, Position to) throws RuntimeException{
        Board.Rank fromRow = pieces.get(from.getRow());
        Board.Rank toRow = pieces.get(to.getRow());

        Piece piece = fromRow.getPiece(from.getCol());
        Piece dest = toRow.getPiece(to.getCol());

        validateNotEmpty(piece);

        if (dest.getName() == Piece.Type.NO_PIECE) {
            fromRow.emptyPiece(from.getCol());
            toRow.placePiece(to.getCol(), piece);
        } else if (dest.getColor() == piece.getColor()) {
            throw new RuntimeException("자리에 이미 우리팀 말이 존재합니다.");
        } else {
            fromRow.emptyPiece(from.getCol());
            toRow.placePiece(to.getCol(), piece);
            System.out.printf("%s 말이 잡아 먹혔습니다%n", to);
        }
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
        //TODO rank 객체와의 협력 필요
        return pieces.get(position.getRow()).pieceRow.get(position.getCol());
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
        for(int i = 0; i < 8; i++) {
            board.add(new Rank());
        }
        pieces = board;
    }

    public void initialize() {
        for(int i = 0; i < 8; i++) {
            this.place(Piece.createBlackPawn(), new Position(1, i));
            this.place(Piece.createWhitePawn(), new Position(6, i));
        }

        this.place(Piece.createBlackRook(), new Position(0, 0));
        this.place(Piece.createBlackKnight(), new Position(0, 1));
        this.place(Piece.createBlackBishop(), new Position(0, 2));
        this.place(Piece.createBlackQueen(), new Position(0, 3));
        this.place(Piece.createBlackKing(), new Position(0, 4));
        this.place(Piece.createBlackBishop(), new Position(0, 5));
        this.place(Piece.createBlackKnight(), new Position(0, 6));
        this.place(Piece.createBlackRook(), new Position(0, 7));

        this.place(Piece.createWhiteRook(), new Position(7, 0));
        this.place(Piece.createWhiteKnight(), new Position(7, 1));
        this.place(Piece.createWhiteBishop(), new Position(7, 2));
        this.place(Piece.createWhiteQueen(), new Position(7, 3));
        this.place(Piece.createWhiteKing(), new Position(7, 4));
        this.place(Piece.createWhiteBishop(), new Position(7, 5));
        this.place(Piece.createWhiteKnight(), new Position(7, 6));
        this.place(Piece.createWhiteRook(), new Position(7, 7));

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

    public double calculatePoint(Piece.Color color) {
        revisePawnScore();
        return this.pieces.stream()
                .mapToDouble(rank -> rank.calculateScore(color))
                .sum();
    }

    // TODO pawn 점수 저장하지 않도록 수정 필요
    // board pawn 들의 점수를 재조정하는 함수
    private void revisePawnScore() {
        for(int colIdx= 0; colIdx < 8; colIdx++) {
            int finalColIdx = colIdx;
            long blackPawnCount = this.pieces.stream()
                    .map(rank -> rank.getPiece(finalColIdx))
                    .filter(Piece::isPawn)
                    .filter(Piece::isBlack)
                    .count();

            long whitePawnCount = this.pieces.stream()
                    .map(rank -> rank.getPiece(finalColIdx))
                    .filter(Piece::isPawn)
                    .filter(Piece::isWhite)
                    .count();
            
            if (blackPawnCount >= 2) {
                this.pieces.stream()
                        .map(rank -> rank.getPiece(finalColIdx))
                        .filter(Piece::isPawn)
                        .filter(Piece::isBlack)
                        .forEach(piece -> piece.setPoint(0.5));
            }

            if (whitePawnCount >= 2) {
                this.pieces.stream()
                        .map(rank -> rank.getPiece(finalColIdx))
                        .filter(Piece::isPawn)
                        .filter(Piece::isWhite)
                        .forEach(piece -> piece.setPoint(0.5));
            }
        }
    }

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
            for(int i = 0; i < 8; i++) {
                pieceRow.add(Piece.createBlank());
            }
            this.pieceRow = pieceRow;
        }

        public void setPiece(int idx, Piece piece) {
            this.pieceRow.set(idx, piece);
        }

        public void emptyPiece(int idx) {
            this.pieceRow.set(idx, Piece.createBlank());
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

        public double calculateScore(Piece.Color color) {
            return this.pieceRow.stream()
                    .filter(piece -> piece.getColor() == color)
                    .mapToDouble(Piece::getPoint)
                    .sum();
        }

        public String showScore() {
            return Arrays.toString(this.pieceRow.stream()
                    .mapToDouble(Piece::getPoint)
                    .toArray());
        }
    }
}
