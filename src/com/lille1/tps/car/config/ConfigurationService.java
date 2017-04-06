package com.lille1.tps.car.config;

import com.lille1.tps.car.config.mode.MODE;
import com.lille1.tps.car.config.type.TYPE_FILE;
import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.UserConnection;

public class ConfigurationService {

	private static ConfigurationService instance;
	
	private ConfigurationService() {}
	
	public static ConfigurationService getInstance() {
		if(instance == null) {
			instance = new ConfigurationService();
		}
		
		return instance;
	}
	
	/*
	 * TYPE
	 */
	// TODO : Add printing mode
	public void setType(TYPE_FILE type, UserConnection connection) { 
		connection.getConfig().setTypeFile(type);
		MyLogger.i("TYPE de fichier a tranférer : " + type);
	}
	
	/*
	 * MODE
	 */
	
	public void setMode(MODE mode, UserConnection connection) {
		connection.getConfig().setMode(mode);
		MyLogger.i("Passage en mode passif");
	}

	/*
	 * PORT
	 */
	public void setPort(String[] params, UserConnection connection) {
		MyLogger.i("Configuration du port pour le prochain envoi");
		final String[] tokens = params[1].split(",");
		String ip = "";
		for(int i = 0; i < 4; ++i) {
			ip += tokens[i];
		}
		final Integer port = Integer.valueOf(tokens[4]) * 256 + Integer.valueOf(tokens[5]);
		connection.getConfig().setIp(ip);
		connection.getConfig().setPort(port);
		MyLogger.i("IP : " + ip);
		MyLogger.i("PORT : " + port);
	}
	
}
