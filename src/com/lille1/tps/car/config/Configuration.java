package com.lille1.tps.car.config;

public class Configuration {
	/*
	 *  TYPE
	 */
	private Type typeFile;
	
	/*
	 * MODE passive / active
	 */
	private Mode mode;

	/*
	 * IPV4 / IPV6
	 */
	private NetworkProtocol networkProtocol;
	private String ip;
	private Integer port;

	public Configuration() {
		typeFile = Type.ASCII;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Type getTypeFile() {
		return typeFile;
	}
	
	public void setTypeFile(Type typeFile) {
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
	
	public NetworkProtocol getNetworkProtocol() {
		return networkProtocol;
	}
	
	public void setNetworkProtocol(NetworkProtocol networkProtocol) {
		this.networkProtocol = networkProtocol;
	}
	
}
