package chess.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void appendNewLineTest(){
        String str = "helloworld";
        assertEquals("helloworld\n",StringUtils.appendNewLine(str));
    }

    @Test
    public void appendNewLineNullTest(){
        assertNull(StringUtils.appendNewLine(null));
    }
}