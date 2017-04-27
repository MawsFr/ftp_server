package com.lille1.tps.car.user;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.lille1.tps.car.command.CommandService;
import com.lille1.tps.car.command.ReturnCodes;
import com.lille1.tps.car.config.Configuration;

public class UserConnection {
	
	private Thread thread;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
	
	private OutputStream os;
	private DataOutputStream dos;
	
	private Socket socket;
	
	private String command;
	
	private User user;
	
	private Configuration config;
	
	private boolean running;
	
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
				String returnCode = "";
				do {
					try {
						command = br.readLine();
						MyLogger.i("Commande : " + command);
						System.out.println(command);
						returnCode = CommandService.getInstance().processCommand(command, UserConnection.this);
						MyLogger.i("ReturnCode : " + returnCode);
						CommandService.getInstance().returnCode(returnCode, UserConnection.this);
					} catch (IOException e) {
						e.printStackTrace();
						running = false;
					}
					
				} while(running && !returnCode.equals(ReturnCodes.RC_430));
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
	

}
