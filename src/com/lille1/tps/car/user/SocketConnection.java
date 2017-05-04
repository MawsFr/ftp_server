package com.lille1.tps.car.user;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.lille1.tps.car.utils.MyLogger;

public class SocketConnection implements Closeable {
	protected Socket socket;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;

	private BufferedOutputStream bos;

	private OutputStream os;
	private DataOutputStream dos;

	public SocketConnection(Socket socket) {
		this.socket = socket;
		try {
			MyLogger.i("Création de la connexion ...");
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			bos = new BufferedOutputStream(os);
		} catch (IOException e) {
			MyLogger.e("Erreur lors de la récupération du flux d'échange ...");
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
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
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public InputStreamReader getIsr() {
		return isr;
	}

	public void setIsr(InputStreamReader isr) {
		this.isr = isr;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public boolean isClosed() {
		return socket.isClosed();
	}

	public BufferedOutputStream getBos() {
		return bos;
	}

	public void setBos(BufferedOutputStream bos) {
		this.bos = bos;
	}


}
