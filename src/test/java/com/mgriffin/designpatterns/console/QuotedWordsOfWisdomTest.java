package com.mgriffin.designpatterns.console;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuotedWordsOfWisdomTest {

    @Test
    public void wordsOfWisdom_shouldBeQuoted () {
        WordsOfWisdom wordsOfWisdom = mock(WordsOfWisdom.class);

        when(wordsOfWisdom.getMessage()).thenReturn("Testing String");

        WordsOfWisdom quotedWisdom = new QuotedWordsOfWisdom(wordsOfWisdom);

        assertEquals("\"Testing String\"", quotedWisdom.getMessage());
    }

}