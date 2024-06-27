package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.example.chess.board.sort.PieceComparator;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Color;
import org.example.chess.pieces.Piece.PieceFactory;
import org.example.chess.pieces.Piece.Type;

public class Board {

    protected static final int BOARD_SIZE = 8;

    private final List<Rank> board = new ArrayList<>();

    public void print() {
        System.out.println(showBoard());
    }

    public int pieceCount() {
        return (int) board.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(p -> (p.isBlack() || p.isWhite()))
                .count();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (Rank row : board) {
            sb.append(appendNewLine(row.getPieces().stream()
                    .map(Piece::getRepresentation)
                    .collect(Collectors.joining())));
        }
        return sb.toString();
    }

    public int countPiecesByColorAndType(Color color, Type type) {
        int count = 0;
        for (Rank rank : board) {
            if (color == Color.BLACK) {
                count += rank.countBlackPiecesWithType(type);
                continue;
            }

            if (color == Color.WHITE) {
                count += rank.countWhitePiecesWithType(type);
                continue;
            }
        }

        return count;
    }

    protected List<Rank> getBoard() {
        return board;
    }

    public static class Rank {

        private final List<Piece> pieces = new ArrayList<>();

        public void addPiece(Piece piece) {
            pieces.add(piece);
        }

        public void changePiece(int col, Piece piece) {
            pieces.set(col, piece);
        }

        public List<Piece> getPieces() {
            return pieces;
        }

        public int countWhitePiecesWithType(Type type) {
            return (int) pieces.stream()
                    .filter(piece -> piece.isWhite() && piece.getType() == type)
                    .count();
        }

        public int countBlackPiecesWithType(Type type) {
            return (int) pieces.stream()
                    .filter(piece -> piece.isBlack() && piece.getType() == type)
                    .count();
        }

        public double calculateRankPoint(Color color) {
            double points = 0.0;
            for (Piece piece : pieces) {
                if (piece.getColor() == color) {
                    points += piece.getType().getDefaultPoint();
                }
            }
            return points;
        }

        public List<Piece> findPieces(Color color) {
            List<Piece> piecesInRank = new ArrayList<>();
            for (Piece piece : pieces) {
                if (piece.getColor() == color) {
                    piecesInRank.add(piece);
                }
            }
            return piecesInRank;
        }
    }
}
