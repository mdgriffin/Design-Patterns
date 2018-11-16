package com.mgriffin.command;

import com.mgriffin.server.ClientConnection;

public class CloseConnectionCommand implements Command {

    private ClientConnection connection;

    public CloseConnectionCommand(ClientConnection connection) {
        this.connection = connection;
    }

    @Override
    public void execute() {
        connection.close();
    }
}
