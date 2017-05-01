package com.lille1.tps.car.command;

import java.io.IOException;

import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.user.UserConnection;

public class PortCommand extends Command {

	@Override
	public void execute(final String[] params, final UserConnection connection) throws IOException {
		ConfigurationService.getInstance().setPort(params, connection);
		writeReturnCode(connection, ReturnCodes.RC_200);
	}

}
