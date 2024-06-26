package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {
    private final List<Rank> board = new ArrayList<>();
    public static final int BOARD_SIZE = 8;

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Piece> pieceList = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                pieceList.add(Piece.createBlank(new Position(j, i)));
            }
            board.add(new Rank(pieceList));
        }
    }

    public List<Rank> findAll() { return this.board; }

    public List<Piece> findByColor(final Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = board.get(i);
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece piece = rank.get(j);
                if (Objects.equals(color, piece.getColor())) pieces.add(piece);
            }
        }
        return pieces;
    }

    public Piece findPiece(final String stringPosition) {
        Position position = new Position(stringPosition);
        return board.get(position.getY()).get(position.getX());
    }
    public Piece findByPosition(final Position position) {
        return board.get(position.getY()).get(position.getX());
    }

    public void saveByPosition(final Piece piece, final Position position) {
        Rank rank = board.get(position.getY());
        Rank newRank = rank.move(position.getX(), piece, position);
        board.set(position.getY(), newRank);
    }

    public List<Piece> getSortedPieces(final Color color) {
        List<Piece> pieces = findByColor(color);
        Collections.sort(pieces);
        return pieces;
    }
}
