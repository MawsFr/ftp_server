package com.lille1.tps.car.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lille1.tps.car.user.UserConnection;

public class CommandService {
	public static final String COMMAND_USER = "USER";
	public static final String COMMAND_PASS = "PASS";
	public static final String COMMAND_PWD = "PWD";
	public static final String COMMAND_PWD_WINDOWS = "XPWD";
	public static final String COMMAND_TYPE = "TYPE";
	public static final String COMMAND_PASV = "PASV";
	public static final String COMMAND_EPSV = "EPSV";
	
	public static final String READY_COMMAND = "READY";
	public static final String INVALID_COMMAND = "INVALID_COMMAND";
	private static final String COMMAND_PORT = "PORT";
	private static final String COMMAND_EPRT = "EPRT";
	private static final String COMMAND_LIST = "LIST";
	
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
		this.commands.put(COMMAND_PASV, new PasvCommand());
		this.commands.put(COMMAND_EPSV, new EpsvCommand());
		this.commands.put(COMMAND_PORT, new PortCommand());
		this.commands.put(COMMAND_EPRT, new EprtCommand());
		this.commands.put(COMMAND_LIST, new ListCommand());
	}

	public synchronized String processCommand(String theCommand, UserConnection connection) throws IOException {
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
		return command.execute(tokens, connection);
	}
	
	public synchronized void returnCode(String returnCode, UserConnection connection) throws IOException {
		connection.getDos().writeBytes(returnCode);
		connection.getDos().flush();
		
	}

	public synchronized Command getCommand(String command) {
		return this.commands.get(command);
	}

}
