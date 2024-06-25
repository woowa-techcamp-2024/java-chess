package woowa.camp.chess;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command fromName(final String name) {
        return Arrays.stream(values())
                .filter(command -> command.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("잘못된 커맨드{%s}입니다. %s, %s 중에 입력해주세요.",
                        name, START.getName(), END.getName())));
    }

    public String getName() {
        return name;
    }
}
