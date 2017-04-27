package com.lille1.tps.car.user;

public class User {
	
	private String login;
	private String password;
	private String associatedPath;
	
	public User(String login, String password) {
		this(login, password, "");
	}
	
	public User(String login, String password, String associatedPath) {
		super();
		this.login = login;
		this.password = password;
		this.associatedPath = associatedPath;
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
	public String getAssociatedPath() {
		return associatedPath;
	}
	public void setAssociatedPath(final String associatedPath) {
		this.associatedPath = associatedPath;
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
