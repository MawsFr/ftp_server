package com.lille1.tps.car.command;

import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.user.UserConnection;

public class PortCommand extends Command {

	@Override
	public String execute(final String[] params, final UserConnection connection) {
		ConfigurationService.getInstance().setPort(params, connection);
		return ReturnCodes.RC_200;
	}

}
