package com.lille1.tps.car.command;

import com.lille1.tps.car.user.UserConnection;

public class ListCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		return ReturnCodes.compile(ReturnCodes.RC_257, "Bonjour");
	}
	
}