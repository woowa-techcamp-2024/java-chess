package pe.goblin.chess.application.query;

import java.util.Map;

public interface BoardQuery {
    BoardTypeResponse showPossibleBoardTypes();

    record BoardTypeResponse(Map<Integer, String[]> boardTypes) {
    }
}
