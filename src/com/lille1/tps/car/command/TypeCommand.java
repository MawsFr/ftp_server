package com.lille1.tps.car.command;

import com.lille1.tps.car.FTPServer;
import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.type.TYPE_FILE;
import com.lille1.tps.car.user.UserConnection;

public class TypeCommand extends Command {

	@Override
	public String execute(String[] params, UserConnection connection) {
		final String code = params[1];
		TYPE_FILE type = TYPE_FILE.valueOfCode(code);
		if(type != null) {
			ConfigurationService.getInstance().setType(type, connection);
			return ReturnCodes.RC_200;
		}
		return ReturnCodes.RC_501;
	}

}
