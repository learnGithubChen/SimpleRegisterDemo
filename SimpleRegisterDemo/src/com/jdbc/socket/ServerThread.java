package com.jdbc.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

import com.jdbc.entity.ImgFile;
import com.jdbc.entity.User;
import com.jdbc.service.FileService;
import com.jdbc.service.UserService;

public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			ObjectInputStream objectis = new ObjectInputStream(is);
			Object object = null;
			while ((object = objectis.readObject()) != null) {

				if (object instanceof User) {
					User user = (User) object;
					UserService userService = new UserService();
					if (user.getFlag() == 1) {
						userService.rigister(user);// ×¢²á
					} else {
						userService.login(user);// µÇÂ½

					}
				} else if (object instanceof ImgFile) {
					ImgFile imgFile = (ImgFile) object;
					FileService fileService = new FileService();
					fileService.uploadFile(imgFile);

				}
			}
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
