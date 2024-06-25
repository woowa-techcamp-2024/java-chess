package com.seong.chess.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    @DisplayName("줄바꿈이 적용된다.")
    void appendNewLine() {
        String hello = "Hello, world!";
        String woowa = "Hello, Woowa!";

        String result = StringUtils.appendNewLine(hello) + woowa;

        assertThat(result).isEqualTo(
                """
                        Hello, world!
                        Hello, Woowa!""");
    }
}
