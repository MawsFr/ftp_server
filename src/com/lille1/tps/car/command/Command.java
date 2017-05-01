package com.lille1.tps.car.command;

import java.io.IOException;

import com.lille1.tps.car.user.UserConnection;

public abstract class Command {
	public abstract void execute(String[] params, UserConnection connection) throws IOException;

	public void writeReturnCode(UserConnection connection, String returnCode) throws IOException {
		// FIXME : Créer une constante
		connection.getCommandSocket().getDos().writeBytes(returnCode + "\n");
		connection.getCommandSocket().getDos().flush();
	}

	public void writeData(UserConnection connection, Object data) throws IOException {
		// FIXME : Créer une constante
		System.out.println(data);
		connection.getTransferConnection().getDos().writeBytes(data + "\n");
		connection.getTransferConnection().getDos().flush();
	}
	
}
