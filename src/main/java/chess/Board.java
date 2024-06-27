package chess;

import chess.piece.*;
import chess.util.ChessPoint;
import chess.util.MoveRule;
import chess.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final Map<ChessPoint, Piece> boardMap = new HashMap<>();


    public int size() {
        return boardMap.size();
    }

    public void initializeEmpty() {
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

        addPiece(Knight.createWhite(), ChessPoint.of("b1"));
        addPiece(Knight.createWhite(), ChessPoint.of("g1"));
        addPiece(Knight.createBlack(), ChessPoint.of("b8"));
        addPiece(Knight.createBlack(), ChessPoint.of("g8"));

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

    public <T extends Piece> List<T> findAllPieces(Class<T> type) {
        return filterPiecesByType(type)
                .collect(Collectors.toList());
    }

    public <T extends Piece> List<T> findAllPieces(Class<T> type, Piece.Color color) {
        return filterPiecesByType(type)
                .filter(piece -> piece.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public <T extends Piece> List<Map.Entry<ChessPoint, T>> findAllPiecesMap(Class<T> type, Piece.Color color) {
        return boardMap.entrySet().stream()
                .filter(entry -> entry.getValue().getClass().equals(type) && entry.getValue().getColor().equals(color))
                .map(entry -> Map.entry(entry.getKey(), type.cast(entry.getValue())))
                .collect(Collectors.toList());
    }

    public <T extends Piece> Map.Entry<ChessPoint, T> findPiece(Class<T> type, Piece.Color color) {
        return boardMap.entrySet().stream()
                .filter(entry -> entry.getValue().getClass().equals(type) && entry.getValue().getColor().equals(color))
                .map(entry -> Map.entry(entry.getKey(), type.cast(entry.getValue())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("맞는 Piece가 없습니다."));
    }

    private <T extends Piece> Stream<T> filterPiecesByType(Class<T> type) {
        return boardMap.values().stream()
                .filter(type::isInstance)
                .map(type::cast);
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
        return findPiece(ChessPoint.of(point));
    }

    public Piece findPiece(ChessPoint point) {
        return boardMap.get(point);
    }

    public void putPiece(String pointString, Piece piece) {
        ChessPoint point = ChessPoint.of(pointString);
        if (boardMap.containsKey(point)) {
            throw new IllegalArgumentException("해당 위치에는 이미 말이 있습니다.");
        }
        addPiece(piece, point);
    }

    public void putPiece(ChessPoint point, Piece piece) {
        if (boardMap.containsKey(point)) {
            throw new IllegalArgumentException("해당 위치에는 이미 말이 있습니다.");
        }
        addPiece(piece, point);
    }

    public double calculatePoint(Piece.Color color) {
        double point = boardMap.values().stream()
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
        return boardMap.values().stream()
                .sorted((p1, p2) -> Double.compare(p1.getDefaultPoint(), p2.getDefaultPoint()))
                .collect(Collectors.toList());
    }

    public List<Piece> sortByPointDesc() {
        return boardMap.values().stream()
                .sorted((p1, p2) -> Double.compare(p2.getDefaultPoint(), p1.getDefaultPoint()))
                .collect(Collectors.toList());
    }

    public void move(String source, String target) {
        Piece piece = findPiece(source);
        if (piece == null) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        MoveRule moveRule = piece.getMoveRule(this, ChessPoint.of(source), ChessPoint.of(target));
        moveRule.move(this, source, target);
    }

    public void removePieceIfExist(ChessPoint point) {
        Piece piece = boardMap.get(point);
        if (piece != null) {
            boardMap.remove(point);
        }
    }

    public boolean isKingDead(Piece.Color color) {
        try {
            findPiece(King.class, color);
        }
        catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }

    public boolean isCheck(Piece.Color color) {
        Map.Entry<ChessPoint, King> kingEntry = findPiece(King.class, color);
        ChessPoint kingPoint = kingEntry.getKey();
        return isAttackablePoint(kingPoint, color);
    }

    public boolean isAttackablePoint(ChessPoint point, Piece.Color allyColor) {
        return boardMap.entrySet().stream()
                .filter(entry -> !entry.getValue().getColor().equals(allyColor))
                .anyMatch(entry -> entry.getValue().getAttackablePoints(entry.getKey(), this).containsKey(point));
    }

    public boolean isAttackablePointIfKingMove(ChessPoint point, Piece.Color allyColor) {
        Piece tmp = boardMap.get(point);
        boardMap.put(point, allyColor == Piece.Color.WHITE ? King.createWhite() : King.createBlack());
        boolean result = isAttackablePoint(point, allyColor);
        if (tmp == null) {
            boardMap.remove(point);
        }
        else {
            boardMap.put(point, tmp);
        }
        return result;
    }

    @Override
    public String toString() {
        return showBoard();
    }
}
