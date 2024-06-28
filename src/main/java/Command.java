import java.util.Arrays;

public enum Command {
    START("start"), END("end"), MOVE("move");

    private String value;
    private Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        return Arrays.stream(values())
            .filter(command -> command.value.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("그런 명령은 없습니다?? " + value));
    }
}
