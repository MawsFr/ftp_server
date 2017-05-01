package com.lille1.tps.car.command;

import java.io.IOException;

import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.UserConnection;

public class InvalidCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		MyLogger.e("Commande non implémentée...");
		writeReturnCode(connection, ReturnCodes.RC_502);
	}

}
