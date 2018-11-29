package com.mgriffin.designpatterns.console;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RandomWordsOfWisdomTest {

    private static int NUM_QUOTES = 5;
    private Map<String, Integer> quotes;

    @Test
    public void getMessage_shouldReturnRandomQuotes () {
        WordsOfWisdom wordsOfWisdom = new RandomWordsOfWisdom();
        quotes = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            String quote = wordsOfWisdom.getMessage();
            if (quotes.containsKey(quote)) {
                Integer val = quotes.get(quote);
                quotes.put(quote, ++val);
            } else {
                quotes.put(quote, 1);
            }
        }

        assertEquals(NUM_QUOTES, quotes.size());

        quotes.values().forEach(val -> {
            assertTrue(val >= 10);
            assertTrue(val <= 30);
        });
    }

}
