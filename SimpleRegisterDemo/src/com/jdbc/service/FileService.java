package com.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.entity.ImgFile;
import com.jdbc.util.DBUtil;

public class FileService {
	/* 文件上传 */
	public void uploadFile(ImgFile imgFile) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into imgfile(fname,fcontent)values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, imgFile.getFname());
		ps.setBytes(2, imgFile.getFcontent());
		ps.execute();
	}

}
