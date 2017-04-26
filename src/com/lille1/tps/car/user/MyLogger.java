package com.lille1.tps.car.user;

import java.util.logging.Logger;

public class MyLogger {
	
	private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
	
	public static void e(String message) {
		LOGGER.severe(message);
	}
	
	public static void i(String message) {
		LOGGER.info(message);
	}
	
	public static void w(String message) {
		LOGGER.warning(message);
	}
	
}
