package com.lille1.tps.car.command;

import java.io.IOException;

import com.lille1.tps.car.user.UserConnection;

public abstract class Command {
	protected static final char SPACE = ' ';
	protected static final char RETURN = '\n';

	public abstract void execute(String[] params, UserConnection connection) throws IOException;

	public void writeReturnCode(UserConnection connection, String returnCode) throws IOException {
		connection.getCommandSocket().getDos().writeBytes(returnCode + RETURN);
		connection.getCommandSocket().getDos().flush();
	}

	public void writeData(UserConnection connection, Object data) throws IOException {
		connection.getTransferConnection().getDos().writeBytes(String.valueOf(data) + RETURN);
		connection.getTransferConnection().getDos().flush();
	}
	
}
