package com.mgriffin.designpatterns.client;

import com.mgriffin.designpatterns.command.Command;
import com.mgriffin.designpatterns.helper.InputReaderAnswer;
import com.mgriffin.designpatterns.order.CoffeeOrder;
import com.mgriffin.designpatterns.order.OrderObserver;
import com.mgriffin.designpatterns.order.OrderService;
import com.mgriffin.designpatterns.order.OrderStates;
import com.mgriffin.designpatterns.utility.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OrderClientTest {

    private Answer<String> inputReaderAnswer;
    private BufferedReader in;
    private PrintWriter out;
    private OrderService orderService;
    private ArgumentCaptor<String> printCapture;
    private ArgumentCaptor<OrderObserver> observerArgumentCaptor;
    private ArgumentCaptor<CoffeeOrder> coffeeOrderArgumentCaptor;


    @Before
    public void setup () {
        inputReaderAnswer = new InputReaderAnswer();
        in = mock(BufferedReader.class);
        out = mock(PrintWriter.class);
        orderService = mock(OrderService.class);
        printCapture = ArgumentCaptor.forClass(String.class);
        observerArgumentCaptor = ArgumentCaptor.forClass(OrderObserver.class);
        coffeeOrderArgumentCaptor = ArgumentCaptor.forClass(CoffeeOrder.class);
    }


    @Test
    public void orderClient_printsCorrectValues () throws IOException {
        when(in.readLine()).thenAnswer(inputReaderAnswer);

        OrderClient orderClient = new OrderClient(orderService, in, out);
        orderClient.run();

        verify(orderService).addObserver(coffeeOrderArgumentCaptor.capture(), observerArgumentCaptor.capture());

        OrderObserver capturedObserver = observerArgumentCaptor.getValue();
        capturedObserver.coffeeAdded();

        CoffeeOrder capturedOrder = coffeeOrderArgumentCaptor.getValue();

        verify(out, times(23)).println(printCapture.capture());
        List<String> values = printCapture.getAllValues();

        assertEquals(1.95d, capturedOrder.getPrice(), 0.01d);
        assertEquals(OrderStates.WAITING,capturedOrder.getOrderState());
        assertEquals("Please enter customer name: ", values.get(0));
        assertEquals("Please select Coffee Type from the following options: ", values.get(1));
        assertEquals(StringUtils.lineBreak("Coffee Added to Order"), values.get(22));
    }

    @Test
    public void processCompletedCommand_invokedOnProcessCompleted () throws IOException {
        when(in.readLine()).thenAnswer(inputReaderAnswer);
        Command processCompletedCommand = mock(Command.class);

        OrderClient orderClient = new OrderClient(orderService, in, out);

        orderClient.registerCompletedCommand(processCompletedCommand);
        orderClient.run();

        verify(orderService).addObserver(coffeeOrderArgumentCaptor.capture(), observerArgumentCaptor.capture());

        OrderObserver capturedObserver = observerArgumentCaptor.getValue();
        capturedObserver.orderCompleted("", "");

        verify(processCompletedCommand).execute();
    }

}
