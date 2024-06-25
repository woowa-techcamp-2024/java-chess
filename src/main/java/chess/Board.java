package chess;

import chess.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static chess.BoardFactory.*;
import static chess.utils.StringUtils.BLANK;
import static chess.utils.StringUtils.appendNewLine;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();

    public void initialize() {
        createPawn(this);
        createBishop(this);
        createQueen(this);
        createKing(this);
        createKnight(this);
        createRook(this);
    }

    public void add(final Position position, final Piece piece) {
        this.board.put(position, piece);
    }

    public int pieceCount() {
        return this.board.size();
    }

    public Piece findPiece(final Position position) {
        return this.board.get(position);
    }

    public String getPieceResult(final PieceColor color, final Type type) {
        long count = board.values().stream()
                .filter(pawn -> pawn.getColor().equals(color))
                .filter(pawn -> pawn.getType().equals(type))
                .count();

        char representation = PieceRepresentation.getPieceRepresentation(color, type);

        return String.join("", Collections.nCopies((int) count, String.valueOf(representation)));
    }

    public String showBoard() {
        StringBuilder chessBoard = new StringBuilder();

        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                Position position = Position.of(File.of(col), Rank.of(row));
                char representation = getPieceInPosition(position);

                chessBoard.append(representation);
            }
            chessBoard.append(appendNewLine(""));
        }

        return chessBoard.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    private char getPieceInPosition(final Position position) {
        Optional<Piece> piece = Optional.ofNullable(board.get(position));

        return piece.map(value ->
                        PieceRepresentation.getPieceRepresentation(value.getColor(), value.getType()))
                .orElse(BLANK);
    }
}