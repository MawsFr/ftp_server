package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.UserConnection;

public class StorCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		final String fileName = params[1];
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		file.setWritable(true);
		FileOutputStream fos = new FileOutputStream(file);
		int c = -1;
		while ((c = connection.getTransferConnection().getBr().read()) >= 0) {
			fos.write(c);
			fos.flush();
		}
		fos.close();
		writeReturnCode(connection, ReturnCodes.RC_226);
		connection.close();
	}
	
}