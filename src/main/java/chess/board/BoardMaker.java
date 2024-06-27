package chess.board;

import static chess.board.RankMaker.getEmptyRank;
import static chess.board.RankMaker.getGoodPiecesRank;
import static chess.board.RankMaker.getPawnsRank;

import chess.pieces.type.Color;
import java.util.ArrayList;
import java.util.List;

public class BoardMaker {
    private BoardMaker() {}

    public static List<Rank> standard() {
        List<Rank> ranks = new ArrayList<>();

        ranks.add(getGoodPiecesRank(Color.BLACK));
        ranks.add(getPawnsRank(Color.BLACK));
        ranks.add(getEmptyRank());
        ranks.add(getEmptyRank());
        ranks.add(getEmptyRank());
        ranks.add(getEmptyRank());
        ranks.add(getPawnsRank(Color.WHITE));
        ranks.add(getGoodPiecesRank(Color.WHITE));
        return ranks;
    }

    public static List<Rank> empty() {
        List<Rank> ranks = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ranks.add(getEmptyRank());
        }
        return ranks;
    }
}
