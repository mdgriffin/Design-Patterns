package com.mgriffin.designpatterns.console;

public class QuotedWordsOfWisdom implements WordsOfWisdom {

    private WordsOfWisdom wordsOfWisdom;

    public QuotedWordsOfWisdom (WordsOfWisdom wordsOfWisdom) {
        this.wordsOfWisdom = wordsOfWisdom;
    }

    @Override
    public String getMessage() {
        return "\"" + wordsOfWisdom.getMessage() + "\"";
    }
}
