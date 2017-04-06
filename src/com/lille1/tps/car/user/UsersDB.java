package com.lille1.tps.car.user;
import java.util.HashMap;
import java.util.Map;


public class UsersDB {

	private static UsersDB db;
	
	private Map<String, User> users;
	
	private UsersDB() {
		
	}
	
	public void init() {
		this.users = new HashMap<>();
		this.addUser(new User("maws", "maws"));
		this.addUser(new User("mat", "mut"));
		this.addUser(new User("ludo", "odul"));
	}
	
	public void addUser(User user) {
		this.users.put(user.getLogin(), user);
	}
	
	public static UsersDB getInstance() {
		if(db == null) {
			db = new UsersDB();
		}
		
		return db;
	}
	
	public boolean exists(final String login) {
		return login != null && this.users.containsKey(login);
	}
	
	public boolean isValid(User user) {
		final User dbUser = users.get(user.getLogin());
		return dbUser != null && dbUser.equals(user);
	}
}
