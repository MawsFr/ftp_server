package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;

public class StorCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		final String fileName = params[1];
		synchronized (this) {
			while (FileManager.getInstance().writing(fileName) || FileManager.getInstance().reading(fileName)) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			FileManager.getInstance().startWriting(fileName);
		}

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
		synchronized (this) {
			FileManager.getInstance().stopWriting(fileName);
			notifyAll();
		}

		writeReturnCode(connection, ReturnCodes.RC_226);
		connection.close();
	}
	
}