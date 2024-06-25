package chess;

import chess.piece.*;
import chess.util.ChessPoint;
import chess.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final List<Piece> pieces = new ArrayList<>();

    private final Map<ChessPoint, Piece> boardMap = new HashMap<>();

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public void initializeEmpty() {
        pieces.clear();
        boardMap.clear();
    }

    public void initialize() {
        initializeEmpty();

        // Add pawns
        for (int i = 0; i < 8; i++) {
            addPiece(Pawn.createWhite(), ChessPoint.of((char) ('a' + i), 2));
            addPiece(Pawn.createBlack(), ChessPoint.of((char) ('a' + i), 7));
        }

        // Add other pieces
        addPiece(Rook.createWhite(), ChessPoint.of("a1"));
        addPiece(Rook.createWhite(), ChessPoint.of("h1"));
        addPiece(Rook.createBlack(), ChessPoint.of("a8"));
        addPiece(Rook.createBlack(), ChessPoint.of("h8"));

        addPiece(Knight.createWhiteKnight(), ChessPoint.of("b1"));
        addPiece(Knight.createWhiteKnight(), ChessPoint.of("g1"));
        addPiece(Knight.createBlackKnight(), ChessPoint.of("b8"));
        addPiece(Knight.createBlackKnight(), ChessPoint.of("g8"));

        addPiece(Bishop.createWhite(), ChessPoint.of("c1"));
        addPiece(Bishop.createWhite(), ChessPoint.of("f1"));
        addPiece(Bishop.createBlack(), ChessPoint.of("c8"));
        addPiece(Bishop.createBlack(), ChessPoint.of("f8"));

        addPiece(Queen.createWhite(), ChessPoint.of("d1"));
        addPiece(Queen.createBlack(), ChessPoint.of("d8"));

        addPiece(King.createWhite(), ChessPoint.of("e1"));
        addPiece(King.createBlack(), ChessPoint.of("e8"));
    }

    private void addPiece(Piece piece, ChessPoint point) {
        pieces.add(piece);
        boardMap.put(point, piece);
    }

    public void print() {
        System.out.println(showBoard());
    }

    private String getFile() {
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'h'; c++) {
            sb.append('\t').append(c);
        }
        return sb.toString();
    }

    public Pawn findPawn(int index) {
        return findPiece(index, Pawn.class);
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Piece.Color.WHITE);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Pawn.Color.BLACK);
    }

    private String getPawnsResult(final Piece.Color color) {
        StringBuilder sb = new StringBuilder();
        List<Pawn> pawns = findAllPieces(Pawn.class);
        for (Pawn pawn : pawns) {
            if (pawn.getColor().equals(color)) {
                sb.append(pawn.getRepresentation());
            }
        }
        return sb.toString();
    }

    public long count(Piece.Color color, Class<? extends Piece> type) {
        return filterPiecesByType(type)
                .filter(piece -> piece.getColor().equals(color))
                .count();
    }

    private <T extends Piece> List<T> findAllPieces(Class<T> type) {
        return filterPiecesByType(type)
                .collect(Collectors.toList());
    }

    private <T extends Piece> T findPiece(int index, Class<T> type) {
        return filterPiecesByType(type)
                .skip(index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 index에 맞는 Piece가 없습니다."));
    }

    private <T extends Piece> Stream<T> filterPiecesByType(Class<T> type) {
        return pieces.stream()
                .filter(type::isInstance)
                .map(type::cast);
    }

    public int pieceCount() {
        return pieces.size();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFile()).append(StringUtils.NEW_LINE);
        for (int i = 8; i >= 1; i--) {
            sb.append(i).append('\t');
            for (char c = 'a'; c <= 'h'; c++) {
                Piece piece = boardMap.get(ChessPoint.of(c, i));
                sb.append(piece == null ? "." : piece.getRepresentation()).append('\t');
            }
            sb.append(i).append(StringUtils.NEW_LINE);
        }
        sb.append(getFile());
        return sb.toString();
    }

    public Piece findPiece(String point) {
        return boardMap.get(ChessPoint.of(point));
    }

    public void putPiece(String pointString, Piece piece) {
        ChessPoint point = ChessPoint.of(pointString);
        if (boardMap.containsKey(point)) {
            throw new IllegalArgumentException("해당 위치에는 이미 말이 있습니다.");
        }
        addPiece(piece, point);
    }

    public double calculatePoint(Piece.Color color) {
        Double point = pieces.stream()
                .filter(piece -> piece.getColor().equals(color))
                .mapToDouble(Piece::getDefaultPoint)
                .sum();

        for (char c = 'a'; c <= 'h'; c++) {
            int pawnCount = getPawnCountInSameFile(color, c);
            if (pawnCount >= 2) {
                point -= 0.5 * pawnCount;
            }
        }

        return point;
    }

    private int getPawnCountInSameFile(Piece.Color color, char file) {
        int count = 0;
        for (int i = 1; i <= 8; i++) {
            Piece piece = boardMap.get(ChessPoint.of(file, i));
            if (piece instanceof Pawn && piece.getColor().equals(color)) {
                count++;
            }
        }
        return count;
    }

    public List<Piece> sortByPointAsc() {
        return pieces.stream()
                .sorted((p1, p2) -> Double.compare(p1.getDefaultPoint(), p2.getDefaultPoint()))
                .collect(Collectors.toList());
    }

    public List<Piece> sortByPointDesc() {
        return pieces.stream()
                .sorted((p1, p2) -> Double.compare(p2.getDefaultPoint(), p1.getDefaultPoint()))
                .collect(Collectors.toList());
    }
}
