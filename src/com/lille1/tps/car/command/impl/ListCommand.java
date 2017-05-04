package com.lille1.tps.car.command.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.UserConnection;

public class ListCommand extends Command {

	private static final char SPACE = ' ';
	private static final char RETURN = '\n';

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		writeData(connection, getPathInfos(connection.getUser().getAssociatedPath()));
		writeReturnCode(connection, ReturnCodes.RC_226);
		connection.close();
	}

	public String getPathInfos(final String path) throws IOException {
		// permission linkCount owner group size lastModified name
		final StringBuilder sb = new StringBuilder();
		final File file = new File(path);
		for (File subFile : file.listFiles()) {
			final PosixFileAttributes posixFileAttributes = Files.readAttributes(subFile.toPath(),
					PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			final String permission = getPermissions(subFile);
			final String linkCount = "" + Files.readAttributes(subFile.toPath(), "unix:nlink").get("nlink");
			final String owner = posixFileAttributes.owner().getName();
			final String group = posixFileAttributes.group().getName();
			final String size = "" + posixFileAttributes.size();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM MM yyyy", Locale.ENGLISH);
			final String lastModified = "" + sdf.format(new Date(subFile.lastModified()));
			sb.append(permission).append(SPACE).append(SPACE).append(SPACE).append(linkCount).append(SPACE)
					.append(owner).append(SPACE).append(group).append(SPACE).append(size).append(SPACE)
					.append(lastModified).append(SPACE).append(subFile.getName()).append(RETURN);
		}

		return sb.toString();
	}
	
	public String getPermissions(final File file) {
		char isDirectory = file.isDirectory() ? 'd' : '-';
		char isReadable = file.canRead() ? 'r' : '-';
		char isWritable = file.canWrite() ? 'w' : '-';
		char isAccessible = file.isDirectory() ? 'x' : '-';
		return "" + isDirectory + isReadable + isWritable + isAccessible + "------";

	}

}