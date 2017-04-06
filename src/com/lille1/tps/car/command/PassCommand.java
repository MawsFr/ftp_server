package com.lille1.tps.car.command;

import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.User;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.user.UserService;

public class PassCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		MyLogger.i("Tentative de validation du mot de passe");
		final String password = params[1];
		final User user = connection.getUser();
		if(UserService.getInstance().exists(user.getLogin(), password)) {
			MyLogger.i("Authentification réussie");
			return ReturnCodes.RC_230;
		}
		MyLogger.i("Authentification échouée");
		return ReturnCodes.RC_430;
	}

}
