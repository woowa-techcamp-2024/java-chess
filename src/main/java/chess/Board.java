package chess;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representation;
import utils.ScoreCalculator;

import java.util.ArrayList;
import java.util.List;

import static utils.RankMaker.*;
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

    public void initializeEmpty() {
        for (int i = 0; i < 8; i++) {
            ranks.add(getEmptyRank());
        }
    }

    public double getScore(Color color) {
        return ScoreCalculator.calc(ranks, color);
    }

    public void move(String position, Piece piece) {
        Position pos = Position.from(position);
        move(pos, piece);
    }

    public void move(Position position, Piece piece) {
        // TODO 움직임 구현 (현재는 add에 가까움)
        ranks.get(position.getRank())
                .set(position.getFile(), piece);
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

    public Piece findPiece(String rankFile) {
        Position position = Position.from(rankFile);

        Rank rank = ranks.get(position.getRank());
        return rank.get(position.getFile());
    }
}
