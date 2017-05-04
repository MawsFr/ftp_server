package com.lille1.tps.car.user;

import java.net.Socket;


public class UserService {
	
	private static UserService instance;
	
	private UserService() {
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
		final UserConnection uc = new UserConnection(socket);
		uc.start();
	}

	public void connect(User user) {
		final User user2 = UsersDB.getInstance().get(user.getLogin());
		user.setHomeDir(user2.getHomeDir());
		user.setCurrentDir(user2.getCurrentDir());

	}

}
