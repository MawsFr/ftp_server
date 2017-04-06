package com.lille1.tps.car.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.UserConnection;

public class CommandService {
	public static final String COMMAND_USER = "USER";
	public static final String COMMAND_PASS = "PASS";
	public static final String COMMAND_PWD = "PWD";
	public static final String COMMAND_PWD_WINDOWS = "XPWD";
	public static final String COMMAND_TYPE = "TYPE";
	
	public static final String READY_COMMAND = "READY";
	public static final String INVALID_COMMAND = "INVALID_COMMAND";
	
	private Map<String, Command> commands;
	
	private static CommandService instance;
	
	public static CommandService getInstance() {
		if(instance == null) {
			instance = new CommandService();
		}
		
		return instance;
	}
	
	private CommandService() {
		this.commands = new HashMap<>();
		this.commands.put(READY_COMMAND, new ReadyCommand());
		this.commands.put(INVALID_COMMAND, new InvalidCommand());
		this.commands.put(COMMAND_USER, new UserCommand());
		this.commands.put(COMMAND_PASS, new PassCommand());
		final PwdCommand pwd = new PwdCommand();
		this.commands.put(COMMAND_PWD, pwd);
		this.commands.put(COMMAND_PWD_WINDOWS, pwd);
		this.commands.put(COMMAND_TYPE, new TypeCommand());
	}

	public synchronized void processCommand(String theCommand, UserConnection connection) throws IOException {
		Command command = null;
		String[] tokens = null;
		if(theCommand == null) {
			command = getCommand(READY_COMMAND);
		} else {
			tokens = theCommand.split(" ");
			command = getCommand(tokens[0]);
			if(command == null) {
				command = getCommand(INVALID_COMMAND);
			}
		}
		String returnCode = command.execute(tokens, connection);
		MyLogger.i("Return code : " + returnCode);
		this.returnCode(returnCode, connection);
	}
	
	public synchronized void returnCode(String returnCode, UserConnection connection) throws IOException {
		connection.getDos().writeBytes(returnCode);
		
	}

	public synchronized Command getCommand(String command) {
		return this.commands.get(command);
	}

}
