package com.lille1.tps.car.command;

import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.mode.MODE;
import com.lille1.tps.car.user.UserConnection;

public class EpsvCommand extends Command {

	@Override
	public String execute(final String[] params, final UserConnection connection) {
		ConfigurationService.getInstance().setMode(MODE.EXTENDED_PASSIVE, connection);
		int port = ConfigurationService.getInstance().getPort(connection);
		return ReturnCodes.compile(ReturnCodes.RC_229, "" + port);
	}

}
