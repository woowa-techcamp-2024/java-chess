package chess;

import java.util.Arrays;

public enum File {
    A(1), B(2), C(3), D(4),
    E(5), F(6), G(7), H(8);

    public final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final int index) {
        return Arrays.stream(File.values()).filter(file -> file.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("범위 밖의 값입니다."));
    }
}
