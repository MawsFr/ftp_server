package com.lille1.tps.car.user;

import java.net.Socket;


public class UserService {
	
	private static UserService instance;
	
	private UserService() {}
	
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserService();
		}
		
		return instance;
	}
	
	public void connect(User user) {
		
	}
	
	public boolean exists(String login) {
		return UsersDB.getInstance().exists(login);
	}

	public void createConnection(Socket socket) {
		UserConnection uc = new UserConnection(socket);
		uc.start();
		//TODO : SEE IF MANAGER IS NEEDED
		
	}

}
