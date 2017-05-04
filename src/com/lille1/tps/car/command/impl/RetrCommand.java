package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.utils.MyLogger;

public class RetrCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		final String fileName = params[1];
		final String absoluteFileName = FileManager.getInstance().getAbsolutePath(fileName,
				connection.getUser().getCurrentDir());
		synchronized (this) {
			while (FileManager.getInstance().writing(fileName)) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					writeReturnCode(connection, ReturnCodes.RC_550);
				}
			}
			FileManager.getInstance().startReading(fileName);
		}
		File file = new File(absoluteFileName);
		if (file.exists()) {
			file.setReadable(true);
			MyLogger.i("Retrieving " + absoluteFileName);
			FileInputStream fis = new FileInputStream(file);
			int theByte = -1;
			do {
				theByte = fis.read();
				writeData(connection, theByte);
			} while (theByte != -1);
			fis.close();
			writeReturnCode(connection, ReturnCodes.RC_226);
		} else {
			writeReturnCode(connection, ReturnCodes.RC_550);
		}
		synchronized (this) {
			FileManager.getInstance().stopReading(fileName);
			notifyAll();
		}
		connection.close();
	}
	
}