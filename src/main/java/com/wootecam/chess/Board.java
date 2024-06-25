package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.appendNewLine;

import com.wootecam.chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    private static final String EMPTY_PIECES_RESULTS = "........";
    private static final int PIECE_COUNT = 8;

    private final List<Piece> blackPieces = new ArrayList<>();
    private final List<Piece> whitePieces = new ArrayList<>();

    public Board() {
    }

    public void initialize() {
        blackPieces.addAll(createPawns(Piece.COLOR_BLACK, Piece.BLACK_PAWN_REPRESENTATION));
        whitePieces.addAll(createPawns(Piece.COLOR_WHITE, Piece.WHITE_PAWN_REPRESENTATION));
    }

    private List<Piece> createPawns(String color, String representation) {
        return IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> new Piece(color, representation))
                .toList();
    }

    public void add(final Piece piece) {
        whitePieces.add(piece);
    }

    public Piece findPiece(final int pieceIndex) {
        if (pieceIndex < 0 || whitePieces.size() <= pieceIndex) {
            String message = String.format("폰 인덱스는 0미만이거나 폰의 개수보다 크거나 같을 수 없습니다. size = %d", whitePieces.size());
            throw new IllegalArgumentException(message);
        }

        return whitePieces.get(pieceIndex);
    }

    public void print() {
        StringBuilder boardResults = new StringBuilder();

        boardResults.append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(getBlackPawnsResults()))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(getWhitePawnsResults()))
                .append(appendNewLine(EMPTY_PIECES_RESULTS));

        System.out.println(boardResults);
    }

    public int size() {
        return whitePieces.size();
    }

    public String getWhitePawnsResults() {
        StringBuilder results = createPawnsResults(whitePieces);

        return results.toString();
    }

    public String getBlackPawnsResults() {
        StringBuilder results = createPawnsResults(blackPieces);

        return results.toString();
    }

    private StringBuilder createPawnsResults(List<Piece> pieces) {
        StringBuilder results = new StringBuilder();
        pieces.forEach(piece -> results.append(piece.getRepresentation()));

        return results;
    }
}
