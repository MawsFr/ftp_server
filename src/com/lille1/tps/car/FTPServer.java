package com.lille1.tps.car;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.lille1.tps.car.command.CommandService;
import com.lille1.tps.car.user.MyLogger;
import com.lille1.tps.car.user.UserService;

public class FTPServer {
	
	public static final int PORT = 9876;
	public static final String CRLF = "\r\n";
	
	// Services
	private CommandService commandService;
	private UserService userService;
	
	private static FTPServer instance;
	private boolean started;
	private ServerSocket server;
	private Socket socket;
	
	
	private FTPServer() {
		commandService = CommandService.getInstance();
		userService = UserService.getInstance();
		
	}
	
	public void start() {
		if(!started) {
			try {
				MyLogger.i("Tentative de démarrage du serveur");
				server = new ServerSocket(PORT);
				started = true;
				MyLogger.i("Démarrage du serveur réussi");
			} catch (IOException e) {
				MyLogger.e("Echec du démarrage du serveur");
				e.printStackTrace();
			}
			this.startListening();
		}
	}
	
	public void startListening() {
		if(started) {
			while(true) {
				try {
					MyLogger.i("Démarrage de l'écoute");
					socket = server.accept();
					MyLogger.i("Tentative de connexion du socket : " + socket);
					userService.createConnection(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}

	public static FTPServer getInstance() {
		if(instance == null) {
			instance = new FTPServer();
		}
		
		return instance;
	}
	
	public static void main(String[] args) {
		FTPServer.getInstance().start();
	}

}
