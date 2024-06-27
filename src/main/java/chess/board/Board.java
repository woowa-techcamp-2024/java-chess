package chess.board;

import chess.board.calculator.OrderBy;
import chess.pieces.type.Color;
import chess.pieces.Piece;
import chess.pieces.type.Representation;
import chess.board.calculator.ScoreCalculator;

import java.util.List;

import static utils.StringUtils.*;

public class Board {
    private final List<Rank> ranks;
    private final ScoreCalculator calculator;

    public Board(List<Rank> ranks) {
        this.ranks = ranks;
        calculator = new ScoreCalculator(ranks);
    }

    public double getScore(Color color) {
        return calculator.calc(color);
    }

    public List<Piece> sortByScore(Color color, OrderBy orderBy) {
        return calculator.sort(color, orderBy);
    }

    public void setPiece(Piece piece) {
        ranks.get(piece.getPosition().getRank())
                .set(piece.getPosition().getFile(), piece);
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

    public long pieceCount(Representation representation) {
        return ranks.stream()
                .mapToLong(rank -> rank.count(representation))
                .sum();
    }

    public Piece findPiece(Position position) {
        Rank rank = ranks.get(position.getRank());
        return rank.get(position.getFile());
    }

    public Piece findPiece(String rankFile) {
        Position position = Position.from(rankFile);
        return findPiece(position);
    }
}
