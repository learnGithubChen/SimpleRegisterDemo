package com.jdbc.socket;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.jdbc.entity.ImgFile;
import com.jdbc.entity.User;

public class SocketClient {
	private static final String SHOW_MENU = "欢迎使用QQ\n" + "1.注册\n" + "2.登录\n";
	private static final String REGISTER = "register";
	private static final String LOGIN = "login";

	public void showMenu() {
		System.out.println(SHOW_MENU);
		Scanner input = new Scanner(System.in);
		System.out.println("请输入");
		String previous = null;
		User user = null;
		Socket socket = null;
		OutputStream output = null;
		ObjectOutputStream objectOutput = null;
		try {
			socket = new Socket("127.0.0.1", 8800);
			output = socket.getOutputStream();
			objectOutput = new ObjectOutputStream(output);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int step = 1;
		while (input.hasNext()) {
			String value = input.next();
			if ("1".equals(value) || REGISTER.equals(previous)) {
				previous = REGISTER;
				if (step == 1) {
					user = new User();
					System.out.println("请输入姓名");
				} else if (step == 2) {
					user.setUsername(value);
					System.out.println("请输入密码");
				} else if (step == 3) {
					user.setPassword(value);
					System.out.println("请再次输入密码");
				} else if (step == 4) {
					if (value.equals(user.getPassword())) {
						try {
							user.setFlag(1);
							objectOutput.writeObject(user);
							objectOutput.flush();
							System.out.println("注册成功,请登录");
							previous = null;
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {
						System.out.println("两次输入密码不同，请重新注册");
						step = 0;
					}
				}

				if (step != 4) {
					step++;
				} else {
					step = 1;
				}

			} else if ("2".equals(value) || LOGIN.equals(previous)) {
				previous = LOGIN;
				if (step == 1) {
					user = new User();
					System.out.println("请输入姓名");

				} else if (step == 2) {
					user.setUsername(value);
					System.out.println("请输入密码");
				} else if (step == 3) {
					user.setPassword(value);
					user.setFlag(2);
					try {
						objectOutput.writeObject(user);
						objectOutput.flush();
						previous = null;
						System.out.println("请上传文件,输入文件路径，诸如e://a.png");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (step != 3) {
					step++;
				} else {
					step = 1;
				}

			} else {
				File file = new File(value);
				try {
					FileInputStream inputImg = new FileInputStream(file);
					DataInputStream dataInput = new DataInputStream(inputImg);
					byte[] img = new byte[inputImg.available()];
					int imgValue;
					int count = 0;
					while ((imgValue = dataInput.read()) != -1) {
						img[count++] = (byte) imgValue;
					}

					ImgFile imgFile = new ImgFile(file.getPath(), img);
					objectOutput.writeObject(imgFile);
					objectOutput.writeObject(null);
					objectOutput.flush();
					socket.shutdownOutput();
					socket.close();
					break;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
