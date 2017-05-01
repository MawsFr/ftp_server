package com.lille1.tps.car.command;

import java.io.File;
import java.io.IOException;

import com.lille1.tps.car.user.UserConnection;

public class PwdCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		final File file = new File(connection.getUser().getAssociatedPath());
		writeReturnCode(connection, ReturnCodes.compile(ReturnCodes.RC_257, "\"" + file.getAbsolutePath() + "\""));
	}

}