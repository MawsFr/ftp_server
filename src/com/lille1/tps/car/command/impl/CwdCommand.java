package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;

public class CwdCommand extends Command {
	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		String directoryName = params[1];
		directoryName = FileManager.getInstance().getAbsolutePath(directoryName, connection.getUser().getCurrentDir());
		File file = new File(directoryName);
		if (file.isDirectory()
				&& FileManager.getInstance().isChild(file.toPath(), connection.getUser().getHomeDir())) {
			connection.getUser().setCurrentDir(directoryName);
			writeReturnCode(connection, ReturnCodes.RC_250);

		} else {
			writeReturnCode(connection, ReturnCodes.RC_550);
		}
	}

}