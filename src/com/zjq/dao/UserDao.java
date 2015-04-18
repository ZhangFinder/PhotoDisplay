package com.zjq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zjq.model.User;
import com.zjq.tools.JDBConnection;

public class UserDao {
	private static UserDao instance = null;
	private JDBConnection connection = null; // �������ݿ�������JDBConnection ���󲢸�ֵΪnull

	private UserDao() {
		connection = new JDBConnection();
	}

	/**
	 * ����һ��UserDaoʵ��,����ģʽ
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
	 * �����û�ע��������û��� �жϸ��û��Ƿ����
	 * 
	 * @param name
	 * @return ���ڷ���true,�����ڷ���false
	 */

	public boolean getUserByName(String username) {
		boolean result = false;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		String sql = "SELECT * FROM tb_user WHERE username = '" + username
				+ "'";
		ResultSet rs = connection.executeQuery(sql);// ִ�в�ѯ���������ؽ����ResultSet�����
		try {
			if (rs.next()) {// ��ʹ�������û�н����rsҲ��Ϊnull
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection();// ִ�йر����ݿ�Ĳ���
		}

		return result;
	}
	/**
	 * �����û����������ȡ����
	 * 
	 * @param name
	 * @return ���ڷ���true,�����ڷ���false
	 */

	public boolean getUserByNameAndPassword(String username,String password) {
		boolean result = false;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		String sql = "SELECT * FROM tb_user WHERE username = '" + username
				+ "' and password = '" +password+"'"; 
		ResultSet rs = connection.executeQuery(sql);// ִ�в�ѯ���������ؽ����ResultSet�����
		try {
			if (rs.next()) {// ��ʹ�������û�н����rsҲ��Ϊnull
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection();// ִ�йر����ݿ�Ĳ���
		}

		return result;
	}
	/**
	 * �õ��û�����
	 * 
	 * @param name
	 * @return ���ڷ���true,�����ڷ���false
	 */

	public User getUserInforByNameAndPassword(String username,String password) {
		User user=null;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		String sql = "SELECT * FROM tb_user WHERE username = '" + username
				+ "' and password = '" + password+"'"; 
		ResultSet rs = connection.executeQuery(sql);// ִ�в�ѯ���������ؽ����ResultSet�����
		try {
			if (rs.next()) {// ��ʹ�������û�н����rsҲ��Ϊnull
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
			connection.closeConnection();// ִ�йر����ݿ�Ĳ���
		}

		return user;
	}
	/**
	 * �����û�
	 * @param name
	 * @return ����û���Ϣ����ɹ����򷵻�true,���򷵻�false
	 */
	public boolean saveUser(User user){
		
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
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
