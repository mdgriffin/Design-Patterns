package com.mgriffin.designpatterns.helper;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class InputReaderAnswer implements Answer<String> {

    private int numInvocations = 0;

    public String answer (InvocationOnMock invocation) {
        String res = "1";

        if (numInvocations == 0) {
            res = "abc";
        } else if (numInvocations == 3) {
            res = "no";
        }

        numInvocations++;

        return res;
    }
}