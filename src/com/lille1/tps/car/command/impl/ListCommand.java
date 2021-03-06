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

	private static final String DATE_FORMAT = "MMM MM yyyy";

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_150);
		writeData(connection, getPathInfos(connection.getUser().getCurrentDir()));
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
			final String size = String.valueOf(posixFileAttributes.size());
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
			final String lastModified = "" + sdf.format(new Date(subFile.lastModified()));
			sb.append(permission).append(SPACE).append(SPACE).append(SPACE).append(linkCount).append(SPACE)
					.append(owner).append(SPACE).append(group).append(SPACE).append(size).append(SPACE)
					.append(lastModified).append(SPACE).append(subFile.getName())
					.append(ReturnCodes.CRLF);
		}
		return sb.toString();
	}
	
	public String getPermissions(final File file) {
		StringBuilder sb = new StringBuilder();
		char isDirectory = file.isDirectory() ? 'd' : '-';
		char isReadable = file.canRead() ? 'r' : '-';
		char isWritable = file.canWrite() ? 'w' : '-';
		char isAccessible = file.isDirectory() ? 'x' : '-';
		sb.append(isDirectory).append(isReadable).append(isWritable).append(isAccessible).append("------");
		return sb.toString();
		
	}

}