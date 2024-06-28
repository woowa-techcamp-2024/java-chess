package pe.goblin.chess.application.query;

import java.util.List;
import java.util.Map;

public interface BoardQuery {
    BoardTypeResponse showPossibleBoardTypes();

    record BoardTypeResponse(Map<Integer, List<String>> boardTypes) {
    }
}
