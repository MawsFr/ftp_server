package com.lille1.tps.car.user;

import java.io.File;

public class User {
	
	private String login;
	private String password;
	private String homeDir;
	private String currentDir;
	
	public User(String login, String password) {
		this(login, password, "");
	}
	
	public User(String login, String password, String homeDir) {
		super();
		this.login = login;
		this.password = password;
		String absoluteHomeDir = new File(homeDir).getAbsolutePath();
		this.homeDir = absoluteHomeDir;
		this.currentDir = absoluteHomeDir;
	}
	public User() {}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(final String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getHomeDir() {
		return homeDir;
	}
	public void setHomeDir(final String associatedPath) {
		this.homeDir = associatedPath;
	}

	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) {
		this.currentDir = currentDir;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	

}
