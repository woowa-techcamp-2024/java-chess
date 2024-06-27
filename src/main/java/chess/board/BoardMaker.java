package chess.board;

import java.util.ArrayList;
import java.util.List;

import static chess.board.RankMaker.*;

public class BoardMaker {
    private BoardMaker() {}

    public static List<Rank> standard() {
        List<Rank> ranks = new ArrayList<>();

        ranks.add(initBlackGoodPieces());
        ranks.add(initBlackPawns());
        ranks.add(initEmptyRank(2));
        ranks.add(initEmptyRank(3));
        ranks.add(initEmptyRank(4));
        ranks.add(initEmptyRank(5));
        ranks.add(initWhitePawns());
        ranks.add(initWhiteGoodPieces());

        return ranks;
    }

    public static List<Rank> empty() {
        List<Rank> ranks = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ranks.add(initEmptyRank(i));
        }
        return ranks;
    }
}
