package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.UserConnection;

public class QuitCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		connection.setRunning(false);
		writeReturnCode(connection, ReturnCodes.RC_221);
	}

}