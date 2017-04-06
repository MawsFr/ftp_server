package com.lille1.tps.car.user;

import java.io.IOException;
import java.net.Socket;

import com.lille1.tps.car.command.CommandService;


public class UserService {
	
	private static UserService instance;
	private CommandService commandService;
	
	private UserService() {
		commandService = CommandService.getInstance();
	}
	
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserService();
		}
		
		return instance;
	}
	
	public boolean exists(final String login, final String password) {
		return UsersDB.getInstance().exists(login, password);
	}
	
	public boolean exists(String login) {
		return UsersDB.getInstance().exists(login);
	}

	public void createConnection(Socket socket) {
		UserConnection uc = new UserConnection(socket);
		try {
			commandService.processCommand(null, uc);
			uc.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO : SEE IF MANAGER IS NEEDED
		
	}

}
