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

        // add pawns
        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = Pawn.createWhitePawn();
            Pawn blackPawn = Pawn.createBlackPawn();
            pieces.add(whitePawn);
            pieces.add(blackPawn);
            boardMap.put(ChessPoint.of((char) ('a' + i), 2), whitePawn);
            boardMap.put(ChessPoint.of((char) ('a' + i), 7), blackPawn);
        }

        // add other pieces
        // Rook
        Rook whiteRook1 = Rook.createWhiteRook();
        Rook whiteRook2 = Rook.createWhiteRook();
        pieces.add(whiteRook1);
        pieces.add(whiteRook2);
        boardMap.put(ChessPoint.of('a', 1), whiteRook1);
        boardMap.put(ChessPoint.of('h', 1), whiteRook2);

        Rook blackRook1 = Rook.createBlackRook();
        Rook blackRook2 = Rook.createBlackRook();
        pieces.add(blackRook1);
        pieces.add(blackRook2);
        boardMap.put(ChessPoint.of('a', 8), blackRook1);
        boardMap.put(ChessPoint.of('h', 8), blackRook2);

        // Knight
        Knight whiteKnight1 = Knight.createWhiteKnight();
        Knight whiteKnight2 = Knight.createWhiteKnight();
        pieces.add(whiteKnight1);
        pieces.add(whiteKnight2);
        boardMap.put(ChessPoint.of('b', 1), whiteKnight1);
        boardMap.put(ChessPoint.of('g', 1), whiteKnight2);

        Knight blackKnight1 = Knight.createBlackKnight();
        Knight blackKnight2 = Knight.createBlackKnight();
        pieces.add(blackKnight1);
        pieces.add(blackKnight2);
        boardMap.put(ChessPoint.of('b', 8), blackKnight1);
        boardMap.put(ChessPoint.of('g', 8), blackKnight2);

        // Bishop
        Bishop whiteBishop1 = Bishop.createWhiteBishop();
        Bishop whiteBishop2 = Bishop.createWhiteBishop();
        pieces.add(whiteBishop1);
        pieces.add(whiteBishop2);
        boardMap.put(ChessPoint.of('c', 1), whiteBishop1);
        boardMap.put(ChessPoint.of('f', 1), whiteBishop2);

        Bishop blackBishop1 = Bishop.createBlackBishop();
        Bishop blackBishop2 = Bishop.createBlackBishop();
        pieces.add(blackBishop1);
        pieces.add(blackBishop2);
        boardMap.put(ChessPoint.of('c', 8), blackBishop1);
        boardMap.put(ChessPoint.of('f', 8), blackBishop2);

        // Queen
        Queen whiteQueen = Queen.createWhiteQueen();
        pieces.add(whiteQueen);
        boardMap.put(ChessPoint.of('d', 1), whiteQueen);

        Queen blackQueen = Queen.createBlackQueen();
        pieces.add(blackQueen);
        boardMap.put(ChessPoint.of('d', 8), blackQueen);

        // King
        King whiteKing = King.createWhiteKing();
        pieces.add(whiteKing);
        boardMap.put(ChessPoint.of('e', 1), whiteKing);

        King blackKing = King.createBlackKing();
        pieces.add(blackKing);
        boardMap.put(ChessPoint.of('e', 8), blackKing);
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
