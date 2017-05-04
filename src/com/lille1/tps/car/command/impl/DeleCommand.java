package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;

public class DeleCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		String fileName = params[1];
		synchronized (this) {
			while (FileManager.getInstance().writing(fileName) || FileManager.getInstance().reading(fileName)) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					writeReturnCode(connection, ReturnCodes.RC_550);
				}
			}
			FileManager.getInstance().startWriting(fileName);
		}

		fileName = FileManager.getInstance().getAbsolutePath(fileName, connection.getUser().getCurrentDir());
		File file = new File(fileName);
		if (file.exists() && !file.isDirectory() && file.delete()) {
			writeReturnCode(connection, ReturnCodes.RC_250);
		} else {
			writeReturnCode(connection, ReturnCodes.RC_550);
		}
		synchronized (this) {
			FileManager.getInstance().stopWriting(fileName);
			FileManager.getInstance().delete(fileName);
			notifyAll();
		}

		connection.close();
	}
	
}