package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.mode.MODE;
import com.lille1.tps.car.user.UserConnection;

public class EprtCommand extends Command {

	@Override
	public void execute(final String[] params, final UserConnection connection) throws IOException {
		ConfigurationService.getInstance().setMode(MODE.ACTIVE, connection);
		ConfigurationService.getInstance().setExtendedPort(params, connection);
		connection.updateMode();
		writeReturnCode(connection, ReturnCodes.RC_200);
	}

}