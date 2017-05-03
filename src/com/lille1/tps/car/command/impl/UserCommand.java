package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.User;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.user.UserService;

public class UserCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		String login = params[1];
		MyLogger.i("Vérification du login :" + login);
		if(UserService.getInstance().exists(login)) {
			MyLogger.i("Login validé ...");
			final User user = new User();
			user.setLogin(login);
			connection.setUser(user);
			writeReturnCode(connection, ReturnCodes.RC_331);
		} else {
			MyLogger.i("Login inexistant...");
			writeReturnCode(connection, ReturnCodes.RC_502);
		}
		
	}

}
