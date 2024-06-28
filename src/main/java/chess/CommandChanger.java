package chess;

import java.util.HashMap;
import java.util.Map;

public class CommandChanger {
    private static Map<String, Position> cmdToPos = new HashMap<>();

    public static Position getPosition(String cmd) {
        if (!cmdToPos.containsKey(cmd)) {
            throw new NullPointerException();
        }
        return cmdToPos.get(cmd);
    }

    public static void setPosition(String cmd, Position pos) {
        cmdToPos.put(cmd, pos);
    }
}
