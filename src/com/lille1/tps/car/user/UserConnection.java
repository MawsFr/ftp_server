package com.lille1.tps.car.user;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.lille1.tps.car.command.CommandService;
import com.lille1.tps.car.command.ReturnCodes;
import com.lille1.tps.car.config.Configuration;

public class UserConnection implements Cloneable {

	private Thread thread;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;

	private OutputStream os;
	private DataOutputStream dos;

	private Socket socket;

	private String command;

	private User user;
	
	private Socket downloadSocket;
	private ServerSocket uploadSocket;

	private UserConnection downloadConnection;
	private UserConnection uploadConnection;

	private Configuration config;

	private boolean running;
	private String returnCode;

	public UserConnection() {
	}

	public UserConnection(Socket socket) {
		this.socket = socket;
		this.config = new Configuration();
		try {
			MyLogger.i("Création de la connexion ...");
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		} catch (IOException e) {
			MyLogger.e("Erreur lors de la récupération du flux d'échange ...");
			e.printStackTrace();
		}
	}

	public void start() {
		thread = new Thread(() -> {
			running = true;
			MyLogger.i("Connexion réussie ...");
			returnCode = "";
			try {
				processCommand(null);
				do {
					command = br.readLine();
					MyLogger.i("Commande : " + command);
					System.out.println(command);
					processCommand(command);

				} while(running && !returnCode.equals(ReturnCodes.RC_430));
			} catch (IOException e) {
				e.printStackTrace();
				running = false;
			}
			running = false;

			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(user != null) {
				MyLogger.i("Interruption de la connection avec le user " + user.getLogin());
			} else{
				MyLogger.i("Interruption de la connection");
			}
			thread.interrupt();

		});

		thread.start();
	}

	public void processCommand(String command2) throws IOException {
		returnCode = CommandService.getInstance().processCommand(command, UserConnection.this);
		MyLogger.i("ReturnCode : " + returnCode);
		CommandService.getInstance().returnCode(returnCode, UserConnection.this);
	}
	
	public void updateMode() throws UnknownHostException, IOException, CloneNotSupportedException {
		switch (config.getMode()) {
		case EXTENDED_PASSIVE: // download
			if (uploadSocket != null) {
				uploadSocket.close();
			}
			uploadSocket = new ServerSocket(config.getPort());
			break;
		case ACTIVE: // upload
			downloadSocket = new Socket(InetAddress.getByName(config.getIp()), this.config.getPort());
			downloadConnection = (UserConnection) this.clone();
			downloadConnection.setSocket(downloadSocket);
			downloadConnection.start();
			// RETR
			// STOR
			break;
		default:
			break;
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		final UserConnection clone = new UserConnection();
		// clone.config = (Configuration) this.config.clone();
		clone.setConfig(this.config);
		clone.setUser(user);
		clone.setBr(br);
		clone.setDos(dos);
		clone.setIs(is);
		clone.setIsr(isr);
		clone.setOs(os);
		clone.setRunning(running);
		return clone;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
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

	public BufferedReader getBr() {
		return br;
	}

	public String getCommand() {
		return command;
	}

	public UserConnection getDownloadConnection() {
		return downloadConnection;
	}

	public InputStream getIs() {
		return is;
	}

	public Socket getDownloadSocket() {
		return downloadSocket;
	}

	public InputStreamReader getIsr() {
		return isr;
	}

	public OutputStream getOs() {
		return os;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public Socket getSocket() {
		return socket;
	}

	public Thread getThread() {
		return thread;
	}

	public UserConnection getUploadConnection() {
		return uploadConnection;
	}

	public ServerSocket getUploadSocket() {
		return uploadSocket;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	/**
	 * @param thread
	 *            Le nouveau thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	/**
	 * @param is
	 *            Le nouveau is
	 */
	public void setIs(InputStream is) {
		this.is = is;
	}

	/**
	 * @param isr
	 *            Le nouveau isr
	 */
	public void setIsr(InputStreamReader isr) {
		this.isr = isr;
	}

	/**
	 * @param os
	 *            Le nouveau os
	 */
	public void setOs(OutputStream os) {
		this.os = os;
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
	 * @param downloadSocket
	 *            Le nouveau downloadSocket
	 */
	public void setDownloadSocket(Socket downloadSocket) {
		this.downloadSocket = downloadSocket;
	}

	/**
	 * @param uploadSocket
	 *            Le nouveau uploadSocket
	 */
	public void setUploadSocket(ServerSocket uploadSocket) {
		this.uploadSocket = uploadSocket;
	}

	/**
	 * @param downloadConnection
	 *            Le nouveau downloadConnection
	 */
	public void setDownloadConnection(UserConnection downloadConnection) {
		this.downloadConnection = downloadConnection;
	}

	/**
	 * @param uploadConnection
	 *            Le nouveau uploadConnection
	 */
	public void setUploadConnection(UserConnection uploadConnection) {
		this.uploadConnection = uploadConnection;
	}

	/**
	 * @param config
	 *            Le nouveau config
	 */
	public void setConfig(Configuration config) {
		this.config = config;
	}

	/**
	 * @param returnCode
	 *            Le nouveau returnCode
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

}
