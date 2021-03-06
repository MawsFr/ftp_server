package com.lille1.tps.car.user;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.lille1.tps.car.command.CommandService;
import com.lille1.tps.car.config.Configuration;
import com.lille1.tps.car.utils.MyLogger;

public class UserConnection {

	protected Thread thread;

	protected SocketConnection commandSocket;
	protected SocketConnection transferConnection; // active
	protected ServerSocket uploadSocket; // Passive

	protected Socket socket;

	protected String command;

	protected User user;


	protected Configuration config;

	protected boolean running;

	public UserConnection() {
	}

	public UserConnection(Socket socket) {
		this.socket = socket;
		this.config = new Configuration();
		this.commandSocket = new SocketConnection(socket);
	}

	public void start() {
		thread = new Thread(() -> {
			running = true;
			MyLogger.i("Connexion réussie ...");
			try {
				CommandService.getInstance().processCommand(null, UserConnection.this);
				do {
					command = commandSocket.getBr().readLine();
					MyLogger.i("Commande : " + command);
					System.out.println(command);
					CommandService.getInstance().processCommand(command, UserConnection.this);
				} while (running);
			} catch (IOException e) {
				e.printStackTrace();
				running = false;
			}
			try {
				commandSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (user != null) {
					MyLogger.i("Interruption de la connection avec le user " + user.getLogin());
				} else {
					MyLogger.i("Interruption de la connection");
				}
				thread.interrupt();
			}
		});

		thread.start();
	}

	public void updateMode() throws UnknownHostException, IOException {
		switch (config.getMode()) {
		case EXTENDED_PASSIVE:
			close();
			uploadSocket = new ServerSocket(config.getPort());
			final Socket socket = uploadSocket.accept();
			transferConnection = new SocketConnection(socket);
			break;
		case ACTIVE:
			close();
			final Socket s = new Socket(InetAddress.getByName(config.getIp()), this.config.getPort());
			transferConnection = new SocketConnection(s);
			break;
		default:
			break;
		}
	}

	public void close() throws IOException {
		if (transferConnection != null && !transferConnection.isClosed()) {
			transferConnection.close();
			transferConnection = null;
		}
		if (uploadSocket != null && !uploadSocket.isClosed()) {
			uploadSocket.close();
			uploadSocket = null;
		}
	}
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Configuration getConfig() {
		return config;
	}

	public String getCommand() {
		return command;
	}

	public Socket getSocket() {
		return socket;
	}

	public Thread getThread() {
		return thread;
	}

	public ServerSocket getUploadSocket() {
		return uploadSocket;
	}

	/**
	 * @param thread
	 *            Le nouveau thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	/**
	 * @param socket
	 *            Le nouveau socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @param command
	 *            Le nouveau command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @param uploadSocket
	 *            Le nouveau uploadSocket
	 */
	public void setUploadSocket(ServerSocket uploadSocket) {
		this.uploadSocket = uploadSocket;
	}

	/**
	 * @param config
	 *            Le nouveau config
	 */
	public void setConfig(Configuration config) {
		this.config = config;
	}

	/**
	 * @return Le commandSocket
	 */
	public SocketConnection getCommandSocket() {
		return commandSocket;
	}

	/**
	 * @param commandSocket
	 *            Le nouveau commandSocket
	 */
	public void setCommandSocket(SocketConnection commandSocket) {
		this.commandSocket = commandSocket;
	}

	/**
	 * @return Le transferConnection
	 */
	public SocketConnection getTransferConnection() {
		return transferConnection;
	}

	/**
	 * @param transferConnection
	 *            Le nouveau transferConnection
	 */
	public void setTransferConnection(SocketConnection transferConnection) {
		this.transferConnection = transferConnection;
	}

}
