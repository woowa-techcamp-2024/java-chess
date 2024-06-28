package chess.board;

import java.util.ArrayList;
import java.util.List;

import static chess.board.RankFactory.*;

public class BoardFactory {
    private final static int RANK_SIZE = BoardSize.RANK.getSize();

    private BoardFactory() {}

    public static Board createStandard() {
        return new Board(standard());
    }

    public static Board createEmpty() {
        return new Board(empty());
    }

    private static List<Rank> standard() {
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

    private static List<Rank> empty() {
        List<Rank> ranks = new ArrayList<>();

        for (int i = 0; i < RANK_SIZE; i++) {
            ranks.add(initEmptyRank(i));
        }
        return ranks;
    }
}
