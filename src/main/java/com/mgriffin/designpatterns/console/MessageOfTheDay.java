package com.mgriffin.designpatterns.console;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.mgriffin.designpatterns.utility.StringUtils.lineBreak;

public class MessageOfTheDay implements WordsOfWisdom {

    private WordsOfWisdom wordsOfWisdom;

    public MessageOfTheDay(WordsOfWisdom messageOfTheDay) {
        this.wordsOfWisdom = messageOfTheDay;
    }

    @Override
    public String getMessage() {
        String dayOfWeek = LocalDate.now().getDayOfWeek().getDisplayName(
            TextStyle.FULL,
            Locale.UK
        );

        return dayOfWeek + "'s message of the day:" + lineBreak(wordsOfWisdom.getMessage());
    }
}
