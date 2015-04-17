package com.zjq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zjq.model.PhotoInfo;
import com.zjq.tools.JDBConnection;



public class PhotoInfoDao {
	private static PhotoInfoDao instance = null;
	private JDBConnection connection = null; // �������ݿ�������JDBConnection ���󲢸�ֵΪnull

	private PhotoInfoDao() {
		connection = new JDBConnection();
	}

	/**
	 * ����һ��PhotoInfoDaoʵ��,����ģʽ
	 * 
	 * @return
	 */
	public static PhotoInfoDao getInstance() {
		if (instance == null) {
			instance = new PhotoInfoDao();
		}
		return instance;
	}

	/**
	 * �жϸ��û��Ƿ��ڸñ���������
	 * @param name
	 * @return ���ڷ���true,�����ڷ���false
	 */

	public boolean isExistByName(String username) {
		boolean result = false;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		String sql = "SELECT * FROM tb_photoinfo WHERE username = '" + username
				+ "'";
		ResultSet rs = connection.executeQuery(sql);// ִ�в�ѯ���������ؽ����ResultSet�����
		try {
			if (rs.next()) {// ��ʹ�������û�н����rsҲ��Ϊnull
				result = true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connection.closeConnection();// ִ�йر����ݿ�Ĳ���
		}

		return result;
	}

	/**
	 * �õ��û����������
	 * 
	 * @param name
	 * @return �������
	 */

	public PhotoInfo getPhotoInfoByUserName(String username) {
		PhotoInfo photoInfo=null;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		String sql = "SELECT * FROM tb_photoinfo WHERE username = '" + username
		+ "'";
		ResultSet rs = connection.executeQuery(sql);// ִ�в�ѯ���������ؽ����ResultSet�����
		try {
			if (rs.next()) {// ��ʹ�������û�н����rsҲ��Ϊnull
				photoInfo=new PhotoInfo();
				photoInfo.setId(rs.getInt("id"));
				photoInfo.setUsername(rs.getString("username"));
				photoInfo.setUrlname(rs.getString("urlname"));
				photoInfo.setHasRose(rs.getInt("hasRose"));
				photoInfo.setPhotoinfo(rs.getString("photoinfo"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connection.closeConnection();// ִ�йر����ݿ�Ĳ���
		}

		return photoInfo;
	}
	/**
	 * �����û���Ƭ��Ϣ
	 * @param name
	 * @return ����û���Ϣ����ɹ����򷵻�true,���򷵻�false
	 */
	public boolean savePhotoInfo(PhotoInfo photoInfo){
		String sql;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		if(isExistByName(photoInfo.getUsername())){
			sql ="update tb_photoinfo set  urlname = '"+photoInfo.getUrlname()+"' ,hasRose = '"+photoInfo.getHasRose()+"', photoinfo='"+photoInfo.getPhotoinfo()+"' where username = '"+photoInfo.getUsername()+"'";
		}else{
			sql="insert into tb_photoinfo(`username`,`urlname`,`hasRose`,`photoinfo`)  values ('"+photoInfo.getUsername()
			+"','"+photoInfo.getUrlname()+"','"+photoInfo.getHasRose()+"','"+photoInfo.getPhotoinfo()+"')";
		}
		 System.out.println(sql);
		boolean flag=connection.executeUpdate(sql);
		connection.closeConnection();
		return flag;
	}
   /**
    * ����û���URL�Ƿ�Ψһ
    * @return true Ψһ  false ��Ψһ
    * ***/
	public boolean  isUniqueUrl(String url,String username) {
		 boolean flag=true;
		if (connection == null) {
			connection = new JDBConnection();// ��JDBC�������ʵ����
		}
		String sql = "SELECT * FROM tb_photoinfo WHERE urlname = '" + url+ "'";
		
		ResultSet rs = connection.executeQuery(sql);// ִ�в�ѯ���������ؽ����ResultSet�����
		try {
			if (rs.next()) {// ��ʹ�������û�н����rsҲ��Ϊnull
				String name=rs.getString("username");
				if(!name.equals(username)){
					flag=false;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connection.closeConnection();// ִ�йر����ݿ�Ĳ���
		}

		return flag;
	}
}
