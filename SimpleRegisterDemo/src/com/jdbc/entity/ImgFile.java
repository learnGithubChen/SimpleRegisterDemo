package com.jdbc.entity;

import java.io.Serializable;

/*author xdchen
 date 2016/09/25*/
public class ImgFile implements Serializable {
	private static final long serialVersionUID = 123456;
	private int id;
	private String fname;
	private byte[] fcontent;

	public ImgFile(String fname, byte[] fcontent) {
		this.fname = fname;
		this.fcontent = fcontent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public byte[] getFcontent() {
		return fcontent;
	}

	public void setFcontent(byte[] fcontent) {
		this.fcontent = fcontent;
	}
}
