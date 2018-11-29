package com.mgriffin.designpatterns.console;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageOfTheDayTest {

    @Test
    public void messageShouldIncludeTimeInformation () {
        WordsOfWisdom wordsOfWisdom = mock(WordsOfWisdom.class);

        when(wordsOfWisdom.getMessage()).thenReturn("Testing String");

        WordsOfWisdom messageOfTheDay = new MessageOfTheDay(wordsOfWisdom);

        String dow = LocalDate.now().getDayOfWeek().getDisplayName(
                TextStyle.FULL,
                Locale.UK
        );

        assertTrue(messageOfTheDay.getMessage().contains(dow));
    }

}
