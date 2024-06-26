package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.NEW_LINE;
import static com.wootecam.chess.utils.StringUtils.appendNewLine;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private static final int PIECE_COUNT = 8;
    private static final List<Type> CHESS_PIECE_ORDER_TYPES = List.of(
            Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN,
            Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK
    );

    private final List<List<Piece>> ranks;

    public Board() {
        ranks = new ArrayList<>(PIECE_COUNT);

        ranks.add(createBlackOtherPieces());
        ranks.add(createPawns(Color.BLACK));
        ranks.add(createBlanks());
        ranks.add(createBlanks());
        ranks.add(createBlanks());
        ranks.add(createBlanks());
        ranks.add(createPawns(Color.WHITE));
        ranks.add(createWhiteOtherPieces());
    }

    private List<Piece> createPawns(Color color) {
        return IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> new Piece(color, Type.PAWN))
                .toList();
    }

    private List<Piece> createBlackOtherPieces() {
        return CHESS_PIECE_ORDER_TYPES.stream()
                .map(Piece::createBlack)
                .toList();
    }

    private List<Piece> createWhiteOtherPieces() {
        return CHESS_PIECE_ORDER_TYPES.stream()
                .map(Piece::createWhite)
                .toList();
    }

    private List<Piece> createBlanks() {
        return IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> Piece.createBlank())
                .toList();
    }

    public String showBoard() {
        return appendNewLine(ranks.stream()
                .map(this::createPiecesResults)
                .collect(Collectors.joining(NEW_LINE)));
    }

    public int pieceCount() {
        return (int) ranks.stream()
                .flatMap(List::stream)
                .filter(piece -> !piece.isBlank())
                .count();
    }

    private String createPiecesResults(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getRepresentation)
                .collect(Collectors.joining());
    }

    public void print() {
        System.out.println(showBoard());
    }
}
