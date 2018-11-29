package com.mgriffin.designpatterns.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mgriffin.designpatterns.utility.StringUtils.join;
import static com.mgriffin.designpatterns.utility.StringUtils.lineBreak;

public class RandomWordsOfWisdom implements WordsOfWisdom {

    private List<String> quotes;
    private Random rand;

    public RandomWordsOfWisdom() {
        quotes = new ArrayList<>();
        populateQuotes();
        rand = new Random();
    }

    @Override
    public String getMessage() {
        int randIndex = rand.nextInt(quotes.size());
        return quotes.get(randIndex);
    }

    private void populateQuotes () {
        quotes.add(join("" +
            "Two roads diverged in a wood, and I—I took the one less traveled by,",
            lineBreak("And that has made all the difference. "),
            lineBreak("- Robert Frost")
        ));

        quotes.add(join(
            "You miss 100% of the shots you don’t take.",
            lineBreak("– Wayne Gretzky")
        ));

        quotes.add(join(
            "I've missed more than 9000 shots in my career. I've lost almost 300 games. ",
            lineBreak("26 times I've been trusted to take the game winning shot and missed. I've "),
            lineBreak("failed over and over and over again in my life. And that is why I succeed."),
            lineBreak("– Michael Jordan")
        ));

        quotes.add(join(
            "Life is what happens to you while you’re busy making other plans.",
            lineBreak("– John Lennon")
        ));

        quotes.add(join(
            " The best time to plant a tree was 20 years ago. The second best time is now.",
            lineBreak("– Chinese Proverb")
        ));
    }
}