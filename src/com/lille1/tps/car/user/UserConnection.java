package com.lille1.tps.car.user;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.lille1.tps.car.command.CommandService;

public class UserConnection {
	
	private Thread thread;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
	
	private OutputStream os;
	private DataOutputStream dos;
	
	private String command;
	
	private boolean running;
	
	public UserConnection(Socket socket) {
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
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				running = true;
				MyLogger.i("Connexion réussie ...");
				do {
					try {
						command = br.readLine();
						MyLogger.i("Commande : " + command);
						CommandService.getInstance().processCommand(command, UserConnection.this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} while(running);
				
			}
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
	
	

}
