package com.zjq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zjq.model.PhotoInfo;
import com.zjq.tools.JDBConnection;



public class PhotoInfoDao {
	private static PhotoInfoDao instance = null;
	private JDBConnection connection = null; // 定义数据库连接类JDBConnection 对象并赋值为null

	private PhotoInfoDao() {
		connection = new JDBConnection();
	}

	/**
	 * 返回一个PhotoInfoDao实例,单例模式
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
	 * 判断该用户是否在该表中有数据
	 * @param name
	 * @return 存在返回true,不存在返回false
	 */

	public boolean isExistByName(String username) {
		boolean result = false;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		String sql = "SELECT * FROM tb_photoinfo WHERE username = '" + username
				+ "'";
		ResultSet rs = connection.executeQuery(sql);// 执行查询操作，返回结果是ResultSet结果集
		try {
			if (rs.next()) {// 即使结果集里没有结果，rs也不为null
				result = true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connection.closeConnection();// 执行关闭数据库的操作
		}

		return result;
	}

	/**
	 * 得到用户的相册数据
	 * 
	 * @param name
	 * @return 相册数据
	 */

	public PhotoInfo getPhotoInfoByUserName(String username) {
		PhotoInfo photoInfo=null;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		String sql = "SELECT * FROM tb_photoinfo WHERE username = '" + username
		+ "'";
		ResultSet rs = connection.executeQuery(sql);// 执行查询操作，返回结果是ResultSet结果集
		try {
			if (rs.next()) {// 即使结果集里没有结果，rs也不为null
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
			connection.closeConnection();// 执行关闭数据库的操作
		}

		return photoInfo;
	}
	/**
	 * 保存用户相片信息
	 * @param name
	 * @return 如果用户信息保存成功，则返回true,否则返回false
	 */
	public boolean savePhotoInfo(PhotoInfo photoInfo){
		String sql;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
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
    * 检查用户的URL是否唯一
    * @return true 唯一  false 不唯一
    * ***/
	public boolean  isUniqueUrl(String url,String username) {
		 boolean flag=true;
		if (connection == null) {
			connection = new JDBConnection();// 将JDBC对象进行实例化
		}
		String sql = "SELECT * FROM tb_photoinfo WHERE urlname = '" + url+ "'";
		
		ResultSet rs = connection.executeQuery(sql);// 执行查询操作，返回结果是ResultSet结果集
		try {
			if (rs.next()) {// 即使结果集里没有结果，rs也不为null
				String name=rs.getString("username");
				if(!name.equals(username)){
					flag=false;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connection.closeConnection();// 执行关闭数据库的操作
		}

		return flag;
	}
}
