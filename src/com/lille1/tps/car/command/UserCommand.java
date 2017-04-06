package com.lille1.tps.car.command;

import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.user.UserService;

public class UserCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		String login = params[1];
		MyLogger.i("Vérification du login :" + login);
		if(UserService.getInstance().exists(login)) {
			MyLogger.i("Login validé ...");
			return ReturnCodes.RC_230;
		} else {
			MyLogger.i("Login inexistant...");
			return ReturnCodes.RC_502;
		}
		
	}

}
