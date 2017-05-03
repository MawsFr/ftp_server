package com.lille1.tps.car.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.lille1.tps.car.user.UserConnection;

public class RetrCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		final String fileName = params[1];
		File file = new File(fileName);
		if (file.exists()) {
			file.setReadable(true);
			Files.lines(file.toPath()).forEach(l -> {
				// for (byte b : l.getBytes()) {
				try {
					writeData(connection, l);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// }
			});
			writeReturnCode(connection, ReturnCodes.RC_226);
		} else {
			// TODO : File doesnt exist
			writeReturnCode(connection, ReturnCodes.RC_226);
		}
		connection.close();
	}
	
}