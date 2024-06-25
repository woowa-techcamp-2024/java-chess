package chess;

import chess.piece.*;
import chess.util.ChessPoint;
import chess.util.StringUtils;

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

        // Add pawns
        for (int i = 0; i < 8; i++) {
            addPiece(Pawn.createWhitePawn(), ChessPoint.of((char) ('a' + i), 2));
            addPiece(Pawn.createBlackPawn(), ChessPoint.of((char) ('a' + i), 7));
        }

        // Add other pieces
        addPiece(Rook.createWhiteRook(), ChessPoint.of("a1"));
        addPiece(Rook.createWhiteRook(), ChessPoint.of("h1"));
        addPiece(Rook.createBlackRook(), ChessPoint.of("a8"));
        addPiece(Rook.createBlackRook(), ChessPoint.of("h8"));

        addPiece(Knight.createWhiteKnight(), ChessPoint.of("b1"));
        addPiece(Knight.createWhiteKnight(), ChessPoint.of("g1"));
        addPiece(Knight.createBlackKnight(), ChessPoint.of("b8"));
        addPiece(Knight.createBlackKnight(), ChessPoint.of("g8"));

        addPiece(Bishop.createWhiteBishop(), ChessPoint.of("c1"));
        addPiece(Bishop.createWhiteBishop(), ChessPoint.of("f1"));
        addPiece(Bishop.createBlackBishop(), ChessPoint.of("c8"));
        addPiece(Bishop.createBlackBishop(), ChessPoint.of("f8"));

        addPiece(Queen.createWhiteQueen(), ChessPoint.of("d1"));
        addPiece(Queen.createBlackQueen(), ChessPoint.of("d8"));

        addPiece(King.createWhiteKing(), ChessPoint.of("e1"));
        addPiece(King.createBlackKing(), ChessPoint.of("e8"));
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
}
