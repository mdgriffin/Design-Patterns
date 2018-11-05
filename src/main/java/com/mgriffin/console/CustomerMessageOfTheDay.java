package com.mgriffin.console;

import com.mgriffin.order.Customer;

public class CustomerMessageOfTheDay implements MessageOfTheDay {

    private MessageOfTheDay baseMessageOfTheDay;
    private Customer customer;

    public CustomerMessageOfTheDay (MessageOfTheDay messageOfTheDay, Customer customer) {
        this.baseMessageOfTheDay = messageOfTheDay;
        this.customer = customer;
    }

    @Override
    public String getMessage() {
        return baseMessageOfTheDay.getMessage() + " " + customer.getName();
    }
}
