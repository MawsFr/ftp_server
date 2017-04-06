package com.lille1.tps.car.command;

import com.lille1.tps.car.user.UserConnection;

public class ReadyCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		return ReturnCodes.RC_220;
	}

}
