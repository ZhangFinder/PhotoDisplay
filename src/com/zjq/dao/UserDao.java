package com.zjq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zjq.model.User;
import com.zjq.tools.JDBConnection;

public class UserDao {
	private static UserDao instance = null;
	private JDBConnection connection = null; // 定义数据库连接类JDBConnection 对象并赋值为null

	private UserDao() {
		connection = new JDBConnection();
	}

	/**
	 * 返回一个UserDao实例,单例模式
	 * 
	 * @return
	 */
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	/**
	 * 根据用户注册输入的用户名 判断该用户是否存在
	 * 
	 * @param name
	 * @return 存在返回true,不存在返回false
	 */

	public boolean getUserByName(String username) {
		boolean result = false;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		String sql = "SELECT * FROM tb_user WHERE username = '" + username
				+ "'";
		ResultSet rs = connection.executeQuery(sql);// 执行查询操作，返回结果是ResultSet结果集
		try {
			if (rs.next()) {// 即使结果集里没有结果，rs也不为null
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection();// 执行关闭数据库的操作
		}

		return result;
	}
	/**
	 * 根据用户名和密码获取数据
	 * 
	 * @param name
	 * @return 存在返回true,不存在返回false
	 */

	public boolean getUserByNameAndPassword(String username,String password) {
		boolean result = false;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		String sql = "SELECT * FROM tb_user WHERE username = '" + username
				+ "' and password = '" +password+"'"; 
		ResultSet rs = connection.executeQuery(sql);// 执行查询操作，返回结果是ResultSet结果集
		try {
			if (rs.next()) {// 即使结果集里没有结果，rs也不为null
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection();// 执行关闭数据库的操作
		}

		return result;
	}
	/**
	 * 得到用户数据
	 * 
	 * @param name
	 * @return 存在返回true,不存在返回false
	 */

	public User getUserInforByNameAndPassword(String username,String password) {
		User user=null;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		String sql = "SELECT * FROM tb_user WHERE username = '" + username
				+ "' and password = '" + password+"'"; 
		ResultSet rs = connection.executeQuery(sql);// 执行查询操作，返回结果是ResultSet结果集
		try {
			if (rs.next()) {// 即使结果集里没有结果，rs也不为null
				user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setQuestion(rs.getString("question"));
				user.setAnswer(rs.getString("answer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection();// 执行关闭数据库的操作
		}

		return user;
	}
	/**
	 * 保存用户
	 * @param name
	 * @return 如果用户信息保存成功，则返回true,否则返回false
	 */
	public boolean saveUser(User user){
		
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		 String sql="INSERT INTO tb_user (`username`,`password`,`email`,`question`,`answer`) VALUES ('"+user.getUsername()+"' , '"
	        +user.getPassword() + "' , '"
	        +user.getEmail() +"', '" +user.getQuestion() +"' , '"
	        +user.getAnswer() +  "' )";
		// System.out.println(sql);
		boolean flag=connection.executeUpdate(sql);
		connection.closeConnection();
		return flag;
	
	}
}
