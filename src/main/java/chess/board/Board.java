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

    public Piece findPiece(Position position) {
        int rank = position.getRank();
        int file = position.getFile();
        return ranks.get(rank).get(file);
    }

    public boolean existsPiece(Representation representation) {
        return ranks.stream()
                .map(rank -> rank.count(representation))
                .anyMatch(count -> count > 0);
    }

    public void setPiece(Piece piece) {
        int rank = piece.getPosition().getRank();
        int file = piece.getPosition().getFile();
        ranks.get(rank).set(file, piece);
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

    public double getScore(Color color) {
        return calculator.calc(color);
    }

    public List<Piece> sortByScore(Color color, OrderBy orderBy) {
        return calculator.sort(color, orderBy);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : ranks) {
            sb.append(appendNewLine(rank.toString()));
        }
        return sb.toString();
    }
}
