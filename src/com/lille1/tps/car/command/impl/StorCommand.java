package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.utils.MyLogger;

public class StorCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		final String fileName = params[1];
		final String absoluteFileName = FileManager.getInstance().getAbsolutePath(fileName,
				connection.getUser().getCurrentDir());
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
		File file = new File(absoluteFileName);
		MyLogger.i("Storing File" + absoluteFileName);
		if (file.exists()) {
			file.delete();
		}
		
		try {
			Files.createFile(file.toPath());
			// file.setReadable(true);
			// file.setWritable(true);
			final FileOutputStream fos = new FileOutputStream(file);
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
		} catch (IOException e) {
			e.printStackTrace();
			writeReturnCode(connection, ReturnCodes.RC_451);
		}
		connection.close();
	}
	
}