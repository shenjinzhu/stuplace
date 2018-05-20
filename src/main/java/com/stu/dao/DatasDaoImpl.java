package com.stu.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.stu.bean.Datas;

@Component
public class DatasDaoImpl extends DataBaseDao {

	public boolean insert(Datas data) {
		String sql = "insert into files(title,display,url,author,size,up_time,useful,can_use,file_id)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement psmt = getConnection().prepareStatement(sql);
			psmt.setString(1, data.getTitle());
			psmt.setString(2, data.getDisplay());
			psmt.setString(3, data.getUrl());
			psmt.setString(4, data.getAuthor());
			psmt.setInt(5, data.getSize());
			psmt.setDate(6, (Date) data.getUp_time());
			psmt.setBoolean(7, data.isUseful());
			psmt.setLong(8, data.getCan_use());
			psmt.setString(9, data.getFile_id());
			return psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Datas data) {
		return false;
	}

	public boolean delete(String id) {
		String sql="delete from files where file_id=?";
		try {
			PreparedStatement psmt = getConnection().prepareStatement(sql);
			psmt.setString(1, id);
			return psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
