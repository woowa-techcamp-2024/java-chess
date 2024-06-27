package chess.utils;

import org.junit.jupiter.api.Test;
import utils.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void appendNewLineTest(){
        String str = "helloworld";
        assertEquals("helloworld".concat(System.lineSeparator()), StringUtils.appendNewLine(str));
    }

    @Test
    public void appendNewLineNullTest(){
        assertNull(StringUtils.appendNewLine(null));
    }
}