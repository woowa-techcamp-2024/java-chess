package pe.goblin.chess.application;

import pe.goblin.chess.application.query.BoardQuery;
import pe.goblin.chess.domain.board.type.BoardType;

import java.util.HashMap;
import java.util.Map;

public class BoardQueryImpl implements BoardQuery {
    @Override
    public BoardTypeResponse showPossibleBoardTypes() {
        Map<Integer, String[]> boardTypes = new HashMap<>();
        for (BoardType type : BoardType.values()) {
            boardTypes.put(type.ordinal(), type.getInitialState());
        }
        return new BoardTypeResponse(boardTypes);
    }
}
