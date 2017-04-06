package com.lille1.tps.car.command;

import com.lille1.tps.car.user.UserConnection;

public class PwdCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		return ReturnCodes.compile(ReturnCodes.RC_257, "\"/home/joe\"");
	}

}