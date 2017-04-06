package com.lille1.tps.car.command;

import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.UserConnection;

public class InvalidCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		MyLogger.e("Commande non implémentée...");
		return ReturnCodes.RC_502;
	}

}
