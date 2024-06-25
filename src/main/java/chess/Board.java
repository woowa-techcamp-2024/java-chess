package chess;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.util.ChessPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final List<Piece> pieces = new ArrayList<>();

    private final Map<ChessPoint, Piece> boardMap = new HashMap<>();

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public void initialize() {
        pieces.clear();
        boardMap.clear();

        // add pawns
        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
            Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);
            pieces.add(whitePawn);
            pieces.add(blackPawn);
            boardMap.put(ChessPoint.of((char) ('a' + i), 2), whitePawn);
            boardMap.put(ChessPoint.of((char) ('a' + i), 7), blackPawn);
        }
    }


    public void print() {
        printFile();
        for (int i = 8; i >= 1; i--) {
            StringBuilder sb = new StringBuilder();
            sb.append(i).append('\t');
            for (char c = 'a'; c <= 'h'; c++) {
                Piece piece = boardMap.get(ChessPoint.of(c, i));
                sb.append(piece == null ? "." : piece.getRepresentation()).append('\t');
            }
            sb.append(i);
            System.out.println(sb);
        }
        printFile();
    }

    private void printFile() {
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'h'; c++) {
            sb.append('\t').append(c);
        }
        System.out.println(sb);
    }

    public Pawn findPawn(int index) {
        return findPiece(index, Pawn.class);
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Pawn.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Pawn.BLACK_COLOR);
    }

    private String getPawnsResult(final String color) {
        StringBuilder sb = new StringBuilder();
        List<Pawn> pawns = findAllPieces(Pawn.class);
        for (Pawn pawn : pawns) {
            if (pawn.getColor().equals(color)) {
                sb.append(pawn.getRepresentation());
            }
        }
        return sb.toString();
    }

    private <T extends Piece> List<T> findAllPieces(Class<T> type) {
        return pieces.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    private <T extends Piece> T findPiece(int index, Class<T> type) {
        return pieces.stream()
                .filter(type::isInstance)
                .skip(index)
                .map(type::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 index에 맞는 Piece가 없습니다."));
    }
}
