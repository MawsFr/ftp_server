package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.Mode;
import com.lille1.tps.car.user.UserConnection;

public class PasvCommand extends Command {

	@Override
	public void execute(final String[] params, final UserConnection connection) throws IOException {
		ConfigurationService.getInstance().setMode(Mode.PASSIVE, connection);
		writeReturnCode(connection, ReturnCodes.RC_227);
	}

}
