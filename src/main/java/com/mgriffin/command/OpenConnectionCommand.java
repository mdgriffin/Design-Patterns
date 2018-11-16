package com.mgriffin.command;

import com.mgriffin.server.ClientConnection;

public class OpenConnectionCommand implements Command {

    private ClientConnection connection;

    public OpenConnectionCommand (ClientConnection connection) {
        this.connection = connection;
    }

    @Override
    public void execute() {
        connection.open();
    }
}
