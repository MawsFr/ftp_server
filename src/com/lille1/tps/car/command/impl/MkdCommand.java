package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.files.FileManager;
import com.lille1.tps.car.user.UserConnection;

public class MkdCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		String directoryName = params[1];
		synchronized (this) {
			while (FileManager.getInstance().writing(directoryName)
					|| FileManager.getInstance().reading(directoryName)) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					writeReturnCode(connection, ReturnCodes.RC_550);
				}
			}
			FileManager.getInstance().startWriting(directoryName);
		}
		directoryName = FileManager.getInstance().getAbsolutePath(directoryName, connection.getUser().getCurrentDir());
		File file = new File(directoryName);
		if (!file.exists()) {
			Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
			FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
			Files.createDirectory(file.toPath(), attr);
			// Files.createDirectory(file.toPath());
			// file.setWritable(true);
			// file.setReadable(true);
			// file.setExecutable(true);
			writeReturnCode(connection, ReturnCodes.compile(ReturnCodes.RC_257, directoryName));
		} else {
			writeReturnCode(connection, ReturnCodes.RC_550);
		}

		synchronized (this) {
			FileManager.getInstance().stopWriting(directoryName);
			notifyAll();
		}


		connection.close();
	}
	
}