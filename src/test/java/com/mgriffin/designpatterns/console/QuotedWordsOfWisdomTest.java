package com.mgriffin.designpatterns.console;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.mgriffin.designpatterns.utility.StringUtils.join;
import static com.mgriffin.designpatterns.utility.StringUtils.lineBreakAfter;
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

    @Test
    public void messagesOfTheDay_decoratesWordsOfWisdom_withCurrentDay () {
        String currentDay = LocalDate.now().getDayOfWeek().getDisplayName(
                TextStyle.FULL,
                Locale.UK
        );
        WordsOfWisdom wordsOfWisdom = mock(WordsOfWisdom.class);

        when(wordsOfWisdom.getMessage()).thenReturn("Testing String");

        WordsOfWisdom quotedWisdom = new MessageOfTheDay(new QuotedWordsOfWisdom(wordsOfWisdom));

        assertEquals(join(lineBreakAfter(currentDay +"'s message of the day:"), "\"Testing String\""), quotedWisdom.getMessage());
    }

}