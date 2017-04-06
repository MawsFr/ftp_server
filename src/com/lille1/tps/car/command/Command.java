package com.lille1.tps.car.command;

import java.io.IOException;

import com.lille1.tps.car.user.UserConnection;

public abstract class Command {
	public abstract String execute(String[] params, UserConnection connection);
	
	public void write(UserConnection connection, String message) throws IOException {
		connection.getDos().writeBytes(message + "\n"); // FIXME : Créer une constante
	}
	
}
