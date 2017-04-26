package com.lille1.tps.car.command;

import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.mode.MODE;
import com.lille1.tps.car.user.UserConnection;

public class PasvCommand extends Command {

	@Override
	public String execute(final String[] params, final UserConnection connection) {
		ConfigurationService.getInstance().setMode(MODE.PASSIVE, connection);
		return ReturnCodes.RC_227;
	}

}
