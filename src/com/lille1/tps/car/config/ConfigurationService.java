package com.lille1.tps.car.config;

import com.lille1.tps.car.command.CommandService;
import com.lille1.tps.car.config.type.TYPE_FILE;
import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.user.UserService;

public class ConfigurationService {

	private static ConfigurationService instance;
	
	private ConfigurationService() {
	}
	
	public static ConfigurationService getInstance() {
		if(instance == null) {
			instance = new ConfigurationService();
		}
		
		return instance;
	}

	
	/*
	 * TYPE
	 */
	public void setType(TYPE_FILE type, UserConnection connection) { // TODO : Add printing mode
		connection.getConfig().setTypeFile(type);
	}
	
}
