package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;

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
				}
			}
			FileManager.getInstance().startReading(fileName);
		}
		File file = new File(absoluteFileName);
		if (file.exists()) {
			file.setReadable(true);
			Files.lines(file.toPath()).forEach(l -> {
				try {
					writeData(connection, l);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			writeReturnCode(connection, ReturnCodes.RC_226);
		} else {
			// TODO : File doesnt exist
			// writeReturnCode(connection, ReturnCodes.RC_226);
		}
		synchronized (this) {
			FileManager.getInstance().stopReading(fileName);
			notifyAll();
		}
		connection.close();
	}
	
}