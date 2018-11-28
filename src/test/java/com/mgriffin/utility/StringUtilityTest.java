package com.mgriffin.utility;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringUtilityTest {

    private static String lineSeperator = System.getProperty("line.separator");

    @Test
    public void canJoingString () {
        assertEquals("ABC", StringUtils.join("A", "B", "C"));
    }

    @Test
    public void canAddLineBreakBefore() {
        assertEquals(lineSeperator + "ABC", StringUtils.lineBreak("ABC"));
    }


    @Test
    public void canAddLineBreakAfter() {
        assertEquals("ABC" + lineSeperator, StringUtils.lineBreakAfter("ABC"));
    }

}
