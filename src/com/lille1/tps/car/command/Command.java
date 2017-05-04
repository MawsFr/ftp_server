package com.lille1.tps.car.command;

import java.io.IOException;

import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.user.UserConnection;

public abstract class Command {
	protected static final char SPACE = ' ';
	protected static final char RETURN = '\n';

	public abstract void execute(String[] params, UserConnection connection) throws IOException;

	public void writeReturnCode(UserConnection connection, String returnCode) throws IOException {
		switch (ConfigurationService.getInstance().getType(connection)) {
		case ASCII:
			connection.getCommandSocket().getBos().write(returnCode.getBytes(), 0, returnCode.length());
			connection.getCommandSocket().getBos().flush();
			break;

		case IMAGE:
			connection.getCommandSocket().getDos().writeBytes(returnCode);
			connection.getCommandSocket().getDos().flush();
			break;
		default:
			break;
		}
	}

	public void writeData(UserConnection connection, Object data) throws IOException {
		String value = String.valueOf(data) + RETURN;
		switch (ConfigurationService.getInstance().getType(connection)) {
		case ASCII:
			connection.getCommandSocket().getBos().write(value.getBytes(), 0, value.length());
			connection.getCommandSocket().getBos().flush();
			break;
		case IMAGE:
			connection.getTransferConnection().getDos().writeBytes(value);
			connection.getTransferConnection().getDos().flush();
			break;
		default:
			break;
		}
	}
}
