package com.lille1.tps.car.user;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection implements Closeable {
	protected Socket socket;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;

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

	/**
	 * @return Le socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket
	 *            Le nouveau socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return Le is
	 */
	public InputStream getIs() {
		return is;
	}

	/**
	 * @param is
	 *            Le nouveau is
	 */
	public void setIs(InputStream is) {
		this.is = is;
	}

	/**
	 * @return Le isr
	 */
	public InputStreamReader getIsr() {
		return isr;
	}

	/**
	 * @param isr
	 *            Le nouveau isr
	 */
	public void setIsr(InputStreamReader isr) {
		this.isr = isr;
	}

	/**
	 * @return Le br
	 */
	public BufferedReader getBr() {
		return br;
	}

	/**
	 * @param br
	 *            Le nouveau br
	 */
	public void setBr(BufferedReader br) {
		this.br = br;
	}

	/**
	 * @return Le os
	 */
	public OutputStream getOs() {
		return os;
	}

	/**
	 * @param os
	 *            Le nouveau os
	 */
	public void setOs(OutputStream os) {
		this.os = os;
	}

	/**
	 * @return Le dos
	 */
	public DataOutputStream getDos() {
		return dos;
	}

	/**
	 * @param dos
	 *            Le nouveau dos
	 */
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public boolean isClosed() {
		return socket.isClosed();
	}

}
