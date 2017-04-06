package com.lille1.tps.car.config;

import com.lille1.tps.car.config.mode.MODE;
import com.lille1.tps.car.config.type.TYPE_FILE;

public class Configuration {
	/*
	 *  TYPE
	 */
	private TYPE_FILE typeFile;
	
	/*
	 * MODE passive / active
	 */
	private MODE mode;

	private String ip;

	private Integer port;
	
	public MODE getMode() {
		return mode;
	}

	public void setMode(MODE mode) {
		this.mode = mode;
	}

	public TYPE_FILE getTypeFile() {
		return typeFile;
	}
	
	public void setTypeFile(TYPE_FILE typeFile) {
		this.typeFile = typeFile;
	}

	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
}
