package com.jdbc.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8800);
		Socket socket = null;
		System.out.println("服务器已经启动");
		while (true) {
			socket = serverSocket.accept();
			new ServerThread(socket).start();
		}

	}
}
