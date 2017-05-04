package com.lille1.tps.car.user;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.lille1.tps.car.utils.MyLogger;


public class UsersDB {

	public static final String USERS_DIR = "users";

	private static UsersDB db;
	
	private Map<String, User> users;
	
	private UsersDB() {
		// init();
	}
	
	public void init() {
		this.users = new HashMap<>();
		createDirectory(USERS_DIR);
		this.addUser(new User("maws", "maws", USERS_DIR + File.separator + "maws" + File.separator));
		this.addUser(new User("mat", "mut", USERS_DIR + File.separator + "mat" + File.separator));
		this.addUser(new User("ludo", "odul", USERS_DIR + File.separator + "ludo" + File.separator));
	}
	
	public void addUser(User user) {
		this.users.put(user.getLogin(), user);
		createDirectory(user.getHomeDir());
	}
	
	public void createDirectory(String path) {
		final File directory = new File(path);
		if (!directory.exists()) {
			MyLogger.i("Création du dossier " + path);
			try {
				directory.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static UsersDB getInstance() {
		if(db == null) {
			db = new UsersDB();
		}
		
		return db;
	}
	
	public boolean exists(final String login, final String password) {
		final User user = new User(login, password);
		return  login != null && password != null && this.users.get(login).equals(user);
	}
	
	public boolean exists(final String login) {
		return login != null && this.users.containsKey(login);
	}
	
	public boolean isValid(User user) {
		final User dbUser = users.get(user.getLogin());
		return dbUser != null && dbUser.equals(user);
	}

	public User get(String name) {
		return this.users.get(name);
	}
}
