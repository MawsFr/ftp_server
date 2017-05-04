package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.utils.MyLogger;

public class InvalidCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		MyLogger.e("Commande non implémentée...");
		writeReturnCode(connection, ReturnCodes.RC_502);
	}

}
