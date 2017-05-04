package com.lille1.tps.car.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lille1.tps.car.command.impl.CdupCommand;
import com.lille1.tps.car.command.impl.CwdCommand;
import com.lille1.tps.car.command.impl.DeleCommand;
import com.lille1.tps.car.command.impl.EprtCommand;
import com.lille1.tps.car.command.impl.EpsvCommand;
import com.lille1.tps.car.command.impl.InvalidCommand;
import com.lille1.tps.car.command.impl.ListCommand;
import com.lille1.tps.car.command.impl.MkdCommand;
import com.lille1.tps.car.command.impl.NoopCommand;
import com.lille1.tps.car.command.impl.PassCommand;
import com.lille1.tps.car.command.impl.PasvCommand;
import com.lille1.tps.car.command.impl.PortCommand;
import com.lille1.tps.car.command.impl.PwdCommand;
import com.lille1.tps.car.command.impl.QuitCommand;
import com.lille1.tps.car.command.impl.ReadyCommand;
import com.lille1.tps.car.command.impl.RetrCommand;
import com.lille1.tps.car.command.impl.RmdCommand;
import com.lille1.tps.car.command.impl.StorCommand;
import com.lille1.tps.car.command.impl.TypeCommand;
import com.lille1.tps.car.command.impl.UserCommand;
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
	private static final String COMMAND_STOR = "STOR";
	private static final String COMMAND_RETR = "RETR";
	private static final String COMMAND_CWD = "CWD";
	private static final String COMMAND_CDUP = "CDUP";
	private static final String COMMAND_MDK = "MKD";
	private static final String COMMAND_DELE = "DELE";
	private static final String COMMAND_RMD = "RMD";
	private static final String COMMAND_NOOP = "NOOP";
	private static final String COMMAND_QUIT = "QUIT";
	

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
		this.commands.put(COMMAND_STOR, new StorCommand());
		this.commands.put(COMMAND_RETR, new RetrCommand());
		this.commands.put(COMMAND_CWD, new CwdCommand());
		this.commands.put(COMMAND_CDUP, new CdupCommand());
		this.commands.put(COMMAND_MDK, new MkdCommand());
		this.commands.put(COMMAND_DELE, new DeleCommand());
		this.commands.put(COMMAND_RMD, new RmdCommand());
		this.commands.put(COMMAND_NOOP, new NoopCommand());
		this.commands.put(COMMAND_QUIT, new QuitCommand());
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
		command.execute(tokens, connection);
	}
	
	public synchronized Command getCommand(String command) {
		return this.commands.get(command);
	}

}
