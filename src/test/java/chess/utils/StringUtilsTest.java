package chess.utils;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.utils.StringUtils.NEWLINE;
import static chess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @DisplayName("String에 개행문자를 추가할 수 있다")
    @Test
    void appendNewLineWithString() {
        // given
        String givenString = "Hello, World!";

        // when
        String appendedString = appendNewLine(givenString);

        // then
        assertThat(appendedString)
                .isEqualTo("Hello, World!"+NEWLINE);
    }

    @DisplayName("StringBuilder에 개행문자를 추가할 수 있다")
    @Test
    void appendNewLineWithStringBuilder() {
        // given
        StringBuilder givenString = new StringBuilder("Hello, World!");

        // when
        StringBuilder appendedString = appendNewLine(givenString);

        // then
        assertThat(appendedString.toString())
                .isEqualTo("Hello, World!"+NEWLINE);
    }

}