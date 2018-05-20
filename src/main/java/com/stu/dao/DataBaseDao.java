package com.stu.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.pool.DruidDataSource;
import com.stu.orm.ORM;

public abstract class DataBaseDao {

	@Autowired
	private DruidDataSource ds;

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		return (Connection) ds.getConnection();
	}

	private static final Logger log = LoggerFactory.getLogger(DataBaseDao.class);

	/**
	 * 创建查询
	 * 
	 * @Title: createQuery
	 * @param sql
	 * @param param
	 * @return List<?>
	 * @throws Exception
	 */
	protected List<?> createQuery(String sql, Object... param) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if (param != null) {
				setParams(pstmt, param);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				Object[] objs = new Object[columnCount];
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					objs[i - 1] = rs.getObject(i);
				}
				list.add(objs);
			}
		} catch (Exception e) {
			log.error("查询出错", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}

	/**
	 * 查询结果集大小
	 * 
	 * @param sql
	 * @param param
	 * @return Integer
	 * @throws Exception
	 */
	protected Integer QueryRows(String sql, Object... param) throws Exception {
		Integer result = null;
		if (sql != null) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				sql = "select count(1) from(" + sql + ")";
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				if (param != null) {
					setParams(pstmt, param);
				}
				rs = pstmt.executeQuery();
				if (rs.next())
					result = rs.getInt(1);
			} catch (Exception e) {
				log.error("查询结果集出错", e);
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
				}
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
				}
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	/**
	 * 执行sql语句（无参数或参数不含null）
	 * 
	 * @param sql
	 * @param param
	 * @throws Exception
	 * @return void
	 */
	protected void execute(String sql, Object... param) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if (param != null) {
				setParams(pstmt, param);
			}
			pstmt.execute();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 执行sql语句（参数可含有null）
	 * 
	 * @param sql
	 * @param clazz
	 * @param fields
	 * @param param
	 * @throws Exception
	 * @return void
	 */
	protected void execute(String sql, Class<?> clazz, String fields, Object... param) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if (param != null) {
				setParamsEx(clazz, pstmt, fields, param);
			}
			pstmt.execute();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/***
	 * 设置查询参数，参数一定不为null
	 * 
	 * @Description: 设置查询参数，参数一定不为null
	 * @return void
	 */
	protected void setParams(PreparedStatement pstmt, Object[] param) throws Exception {
		for (int i = 0; i < param.length; i++) {
			int parameterIndex = i + 1;
			if (param[i] instanceof String)
				pstmt.setString(parameterIndex, (String) param[i]);
			else if (param[i] instanceof Short)
				pstmt.setShort(parameterIndex, (short) param[i]);
			else if (param[i] instanceof Long)
				pstmt.setLong(parameterIndex, (long) param[i]);
			else if (param[i] instanceof Integer)
				pstmt.setInt(parameterIndex, (int) param[i]);
			else if (param[i] instanceof Timestamp)
				pstmt.setTimestamp(parameterIndex, (Timestamp) param[i]);
			else if (param[i] instanceof Date)
				pstmt.setDate(parameterIndex, new java.sql.Date(((Date) param[i]).getTime()));
			else if (param[i] instanceof Byte)
				pstmt.setByte(parameterIndex, (byte) param[i]);
			else if (param[i] instanceof Double)
				pstmt.setFloat(parameterIndex, (float) param[i]);
			else if (param[i] instanceof Boolean)
				pstmt.setBoolean(parameterIndex, (boolean) param[i]);
			else {
				throw new Exception("createQuery: Param Type unknown:" + param[i]);
			}
		}
	}

	/***
	 * 设置查询参数，参数可以为null
	 * 
	 * @Description: 设置查询参数，参数可以为null
	 * @return void
	 */
	protected void setParamsEx(Class<?> clazz, PreparedStatement pstmt, String fields, Object... param)
			throws Exception {
		String[] attrs = fields.replaceAll(" ", "").split(",");
		for (int i = 0; i < param.length; i++) {
			int parameterIndex = i + 1;
			if (param[i] == null) {
				Method method = ORM.getMethod(clazz, "get" + ORM.captureName(attrs[i]));
				Class<?> clz = method.getReturnType();
				pstmt.setNull(parameterIndex, getDBType(clz));
			} else {
				if (param[i] instanceof String)
					pstmt.setString(parameterIndex, (String) param[i]);
				else if (param[i] instanceof Short)
					pstmt.setShort(parameterIndex, (short) param[i]);
				else if (param[i] instanceof Long)
					pstmt.setLong(parameterIndex, (long) param[i]);
				else if (param[i] instanceof Integer)
					pstmt.setInt(parameterIndex, (int) param[i]);
				else if (param[i] instanceof Timestamp)
					pstmt.setTimestamp(parameterIndex, (Timestamp) param[i]);
				else if (param[i] instanceof Date)
					pstmt.setDate(parameterIndex, new java.sql.Date(((Date) param[i]).getTime()));
				else if (param[i] instanceof Byte)
					pstmt.setByte(parameterIndex, (byte) param[i]);
				else if (param[i] instanceof Double)
					pstmt.setDouble(parameterIndex, (double) param[i]);
				else if (param[i] instanceof Boolean)
					pstmt.setBoolean(parameterIndex, (boolean) param[i]);
				else {
					throw new Exception("setParamsAndNull: Param Type unknown:" + param[i]);
				}
			}
		}
	}

	/**
	 * 获取数据库基本类型
	 * 
	 * @param clazz
	 * @throws Exception
	 * @return int
	 */
	private static int getDBType(Class<?> clazz) throws Exception {
		if (clazz == String.class)
			return Types.VARCHAR;
		else if (clazz == Short.class)
			return Types.SMALLINT;
		else if (clazz == Long.class)
			return Types.BIGINT;
		else if (clazz == Integer.class)
			return Types.INTEGER;
		else if (clazz == Timestamp.class)
			return Types.TIMESTAMP;
		else if (clazz == Date.class)
			return Types.TIME;
		else if (clazz == Byte.class)
			return Types.SMALLINT;
		else if (clazz == Double.class)
			return Types.DECIMAL;
		else if (clazz == Boolean.class)
			return Types.BOOLEAN;
		else if (clazz == Short.class)
			return Types.SMALLINT;
		else if (clazz == Short.class)
			return Types.SMALLINT;
		else {
			throw new Exception("getDBType: Param Type unknown:" + clazz);
		}
	}
}
