package chess;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representations;

import java.util.ArrayList;
import java.util.List;

import static utils.StringUtils.*;

public class Board {
    List<Rank> ranks = new ArrayList<>();

    public void initialize() {
        ranks.add(getGoodPiecesRank(Color.BLACK));
        ranks.add(getPawnsRank(Color.BLACK));
        ranks.add(getEmptyRank());
        ranks.add(getEmptyRank());
        ranks.add(getEmptyRank());
        ranks.add(getEmptyRank());
        ranks.add(getPawnsRank(Color.WHITE));
        ranks.add(getGoodPiecesRank(Color.WHITE));
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        for (Rank rank : ranks) {
            sb.append(appendNewLine(rank.toString()));
        }

        return sb.toString();
    }

    public long pieceCount() {
        return ranks.stream()
                .mapToLong(Rank::count)
                .sum();
    }

    public long pieceCount(Representations representations) {
        return ranks.stream()
                .mapToLong(rank -> rank.count(representations))
                .sum();
    }

    public Piece findPiece(String rankFile) {
        Position position = Position.from(rankFile);

        Rank rank = ranks.get(position.getRank());
        return rank.get(position.getFile());
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    private static Rank getPawnsRank(Color color) {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Representations.Type.PAWN, color);
            rank.add(pawn);
        }
        return rank;
    }

    private static Rank getEmptyRank() {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Representations.Type.NO_PIECE, Color.NOCOLOR);
            rank.add(pawn);
        }
        return rank;
    }

    private static Rank getGoodPiecesRank(Color color) {
        Rank rank = new Rank();
        rank.add(Piece.create(Representations.Type.ROOK, color));
        rank.add(Piece.create(Representations.Type.KNIGHT, color));
        rank.add(Piece.create(Representations.Type.BISHOP, color));
        rank.add(Piece.create(Representations.Type.QUEEN, color));
        rank.add(Piece.create(Representations.Type.KING, color));
        rank.add(Piece.create(Representations.Type.BISHOP, color));
        rank.add(Piece.create(Representations.Type.KNIGHT, color));
        rank.add(Piece.create(Representations.Type.ROOK, color));
        return rank;
    }
}
