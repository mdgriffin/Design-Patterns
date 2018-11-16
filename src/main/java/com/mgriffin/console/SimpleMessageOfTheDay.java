package com.mgriffin.console;

public class SimpleMessageOfTheDay implements MessageOfTheDay {

    public SimpleMessageOfTheDay () {}

    @Override
    public String getMessage() {
        return "Thanks for your order";
    }
}
