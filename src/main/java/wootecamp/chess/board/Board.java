package wootecamp.chess.board;

import wootecamp.chess.pieces.PieceComparator;
import wootecamp.chess.pieces.Piece;
import wootecamp.chess.pieces.PieceFactory;

import java.util.ArrayList;
import java.util.List;

import static wootecamp.chess.util.StringUtils.appendNewline;

public class Board {
    public static int BOARD_SIZE = 8;

    private List<Rank> board = new ArrayList<>(BOARD_SIZE);

    public void initialize() {
        board = new ArrayList<>(BOARD_SIZE);
        board.add(Rank.createInitialRank1());
        board.add(Rank.createInitialRank2());

        final int emptyRankCount = 4;
        for (int i = 0; i < emptyRankCount; i++) {
            board.add(Rank.createEmptyRank());
        }

        board.add(Rank.createInitialRank7());
        board.add(Rank.createInitialRank8());
    }


    public void initializeEmpty() {
        board = new ArrayList<>(BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.add(Rank.createEmptyRank());
        }
    }

    public int pieceCount() {
        int pieceCount = 0;
        for (Rank rank : board) {
            pieceCount += rank.pieceCount();
        }

        return pieceCount;
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        int pieceCount = 0;
        for (Rank rank : board) {
            pieceCount += rank.pieceCount(color, type);
        }

        return pieceCount;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (int rank = BOARD_SIZE; rank > 0; rank--) {
            final String shownRank = board.get(rank - 1).showRank();
            builder.append(appendNewline(shownRank));
        }

        return builder.toString();
    }

    public Piece findPiece(final BoardPosition boardPosition) {
        final Rank rank = board.get(boardPosition.getY());
        return rank.findPiece(boardPosition.getX());
    }

    public void move(final String position, final Piece piece) {
        final BoardPosition boardPosition = new BoardPosition(position);

        Rank rank = board.get(boardPosition.getY());
        rank.setPiece(boardPosition.getX(), piece);
    }


    public void move(BoardPosition source, BoardPosition target) {
        Piece piece = findPiece(source);
        setPiece(source, PieceFactory.createEmptyPiece());
        setPiece(target, piece);
    }

    private void setPiece(final BoardPosition position, final Piece piece) {
        Rank rank = board.get(position.getY());
        rank.setPiece(position.getX(), piece);
    }

    public double calculatePoint(final Piece.Color color) {
        //TODO: King만 남은 경우 어떻게 처리하는가?
        if(!hasKing(color)) {
            return 0.0;
        }

        double point = calculateDefaultPoint(color) - calculateReducePointByPawn(color);
        return point;
    }

    private boolean hasKing(final Piece.Color color) {
        for (Rank rank : board) {
            if(rank.hasKing(color)) {
                return true;
            }
        }
        return false;
    }

    private double calculateDefaultPoint(final Piece.Color color) {
        double point = 0.0;

        for (Rank rank : board) {
            point += rank.calculatePoint(color);
        }

        return point;
    }

    private double calculateReducePointByPawn(final Piece.Color color) {
        final double minusPointPerPawn = 0.5;

        double point = 0.0;
        for (int filePosition = 0; filePosition < BOARD_SIZE; filePosition++) {
            int pawnCount = countPawnInFile(filePosition, color);
            if(pawnCount >= 2) {
                point += countPawnInFile(filePosition, color) * minusPointPerPawn;
            }
        }

        return point;
    }

    private int countPawnInFile(final int filePosition, final Piece.Color color) {
        int count = 0;
        for (Rank rank : board) {
            Piece piece = rank.findPiece(filePosition);
            if(piece.isPawn(color)) {
                count++;
            }
        }

        return count;
    }

    public List<Piece> collectPieces(Piece.Color color, PieceComparator pieceComparator) {
        List<Piece> pieces = new ArrayList<>();
        for (Rank rank : board) {
            pieces.addAll(rank.collectPieces(color));
        }

        pieces.sort(pieceComparator.getComparator());
        return pieces;
    }
}
