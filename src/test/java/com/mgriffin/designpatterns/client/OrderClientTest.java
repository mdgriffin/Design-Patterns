package com.mgriffin.designpatterns.client;

import com.mgriffin.designpatterns.order.OrderService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderClientTest {

    @Test
    public void orderClient_canRun () throws IOException {
        BufferedReader in = mock(BufferedReader.class);
        PrintWriter out = mock(PrintWriter.class);
        OrderService orderService = mock(OrderService.class);

        ArgumentCaptor<String> printCapture = ArgumentCaptor.forClass(String.class);

        //when(out.println(printCapture.capture())).thenCallRealMethod();
        //verify(out).printLn(printCapture.capture());
        //verify(out).println();
        verify(out).println(printCapture.capture());

        when(in.readLine()).thenAnswer(new Answer<String>() {
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
        });


        OrderClient orderClient = new OrderClient(orderService, in, out);

        orderClient.run();


        List<String> values = printCapture.getAllValues();


        assertEquals(0, values.size());
        //assertEquals("", values.get(0));
    }

}
