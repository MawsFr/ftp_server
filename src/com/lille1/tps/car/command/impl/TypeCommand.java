package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.TYPE_FILE;
import com.lille1.tps.car.user.UserConnection;

public class TypeCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		final String code = params[1];
		TYPE_FILE type = TYPE_FILE.valueOfCode(code);
		if(type != null) {
			ConfigurationService.getInstance().setType(type, connection);
			writeReturnCode(connection, ReturnCodes.RC_200);
		} else {
			writeReturnCode(connection, ReturnCodes.RC_501);
		}
	}

}
