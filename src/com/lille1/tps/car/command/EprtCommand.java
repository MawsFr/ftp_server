package com.lille1.tps.car.command;

import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.mode.MODE;
import com.lille1.tps.car.user.UserConnection;

public class EprtCommand extends Command {

	@Override
	public String execute(final String[] params, final UserConnection connection) {
		ConfigurationService.getInstance().setMode(MODE.ACTIVE, connection);
		ConfigurationService.getInstance().setExtendedPort(params, connection);
		return ReturnCodes.RC_200;
	}

}
