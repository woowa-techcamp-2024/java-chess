package chess;

import chess.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.BoardFactory.*;
import static chess.utils.StringUtils.appendNewLine;

public class Board {

    private static final int BOARD_SIZE = 8;

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

    public Piece findPiece(final String position) {
        File file = File.of(position.charAt(0));
        Rank rank = Rank.of(Character.getNumericValue(position.charAt(1)));

        return this.board.get(Position.of(file, rank));
    }

    public long getPieceResult(final PieceColor color, final Type type) {
        return board.values().stream()
                .filter(pawn -> pawn.getColor().equals(color))
                .filter(pawn -> pawn.getType().equals(type))
                .count();
    }

    public String showBoard() {
        StringBuilder chessBoard = new StringBuilder();

        for (int row = BOARD_SIZE; row >= 1; row--) {
            for (int col = 1; col <= BOARD_SIZE; col++) {
                Position position = Position.of(File.of(col), Rank.of(row));
                char representation = getPieceInPosition(position);

                chessBoard.append(representation);
            }
            chessBoard.append(appendNewLine(String.valueOf(Rank.of(row).getIndex())));
        }

        chessBoard.append(appendNewLine("abcdefgh"));

        return chessBoard.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    private char getPieceInPosition(final Position position) {
        Piece piece = board.getOrDefault(position, Blank.create());

        return piece.getType().getRepresentation(piece.getColor());
    }
}