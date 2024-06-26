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
    private static final int BLACK_INIT_ROW = 0;
    private static final int BLACK_PAWN_INIT_ROW = 1;
    private static final int WHITE_INIT_ROW = 7;
    private static final int WHITE_PAWN_INIT_ROW = 6;
    private static final double PAWN_SCORE_DECREMENT = 0.5;

    private final List<Rank> board = new ArrayList<>();

    public void initialize() {
        initializeEmpty();
        addPieceToBoard();
    }

    private void addPieceToBoard() {
        initBlackPiece();
        initWhitePiece();
    }

    private void initBlackPiece() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece blackPawn = PieceFactory.createBlackPawn();
            board.get(BLACK_PAWN_INIT_ROW).changePiece(i, blackPawn);
        }

        Rank blackPiecesExceptPawn = new Rank();
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackRook());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackQueen());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackKing());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackRook());

        board.set(BLACK_INIT_ROW, blackPiecesExceptPawn);
    }

    private void initWhitePiece() {
        // 폰 8개, 루크2개, 나이트 2개, 비숍2개, 킹1개, 퀸 1개
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece whitePawn = PieceFactory.createWhitePawn();
            board.get(WHITE_PAWN_INIT_ROW).changePiece(i, whitePawn);
        }

        Rank whitePieceExceptPawn = new Rank();
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteRook());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteQueen());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteKing());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteRook());

        board.set(WHITE_INIT_ROW, whitePieceExceptPawn);
    }

    public void print() {
        System.out.println(showBoard());
    }

    public Board() {
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

    public Piece findPiece(String pos) {
        Position position = new Position(pos);
        int r = position.getR();
        int c = position.getC();

        return board.get(r).getPieces().get(c);
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = new Rank();
            for (int j = 0; j < BOARD_SIZE; j++) {
                rank.addPiece(PieceFactory.createBlank());
            }
            board.add(rank);
        }
    }

    public void move(String position, Piece piece) {
        //TODO: 해당 말이 해당 위치로 이동이 가능한 말인지 확인하는 로직 필요.
        Position pos = new Position(position);
        int r = pos.getR();
        int c = pos.getC();

        Rank row = board.get(r);
        row.changePiece(c, piece);
    }

    public double calculatePoint(Color color) {
        double points = 0.0;
        for (Rank rank : board) {
            points += rank.calculateRankPoint(color);
        }

        int totalInColumnPawnCount = countPawnsInColumnsByColor(color);
        return points - PAWN_SCORE_DECREMENT * totalInColumnPawnCount;
    }

    private int countPawnsInColumnsByColor(Color color) {
        int totalInColumnPawnCount = 0;
        for (int c = 0; c < BOARD_SIZE; c++) {
            int columnCount = 0;
            for (int r = 0; r < BOARD_SIZE; r++) {
                Piece piece = board.get(r).getPieces().get(c);
                if (piece.isPawn() && piece.getColor() == color) {
                    columnCount++;
                }
            }
            if (columnCount > 1) {
                totalInColumnPawnCount += columnCount;
            }
        }
        return totalInColumnPawnCount;
    }

    public List<Piece> findAllPiecesSortByPoint(Color color, PieceComparator comparator) {
        List<Piece> pieces = new ArrayList<>();

        for (Rank rank : board) {
            pieces.addAll(rank.findPieces(color));
        }

        pieces.sort(comparator.getComparator());

        return pieces;
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
