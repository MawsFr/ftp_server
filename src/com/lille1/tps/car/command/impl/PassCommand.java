package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.User;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.user.UserService;
import com.lille1.tps.car.utils.MyLogger;

public class PassCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		MyLogger.i("Tentative de validation du mot de passe");
		final String password = params[1];
		final User user = connection.getUser();
		if(UserService.getInstance().exists(user.getLogin(), password)) {
			UserService.getInstance().connect(user);
			MyLogger.i("Authentification réussie");
			writeReturnCode(connection, ReturnCodes.RC_230);
		} else {
			MyLogger.i("Authentification échouée");
			writeReturnCode(connection, ReturnCodes.RC_430);
		}
	}

}
