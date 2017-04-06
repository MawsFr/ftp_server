package com.lille1.tps.car.command;

import com.lille1.tps.car.user.User;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.user.UserService;

public class PassCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		final String password = params[1];
		final User user = connection.getUser();
		if(UserService.getInstance().exists(user.getLogin(), password)) {
			return ReturnCodes.RC_230;
		}
		return ReturnCodes.RC_430;
	}

}
