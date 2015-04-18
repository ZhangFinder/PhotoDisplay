package com.zjq.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.zjq.dao.PhotoInfoDao;
import com.zjq.model.PhotoInfo;
import com.zjq.model.Temple;
import com.zjq.model.User;
import com.zjq.tools.Encrypt;

public class PhotoServlet extends HttpServlet {
	
	private final static String FOLDER = "savefile";
	private final static String HTMFOLDER = "htmfolder";
	private final static String PREVIEWFOLDER = "previewfolder";
	private final static String ICON = "Icon";
	private final static String HOMEURL = "../index.jsp";//首页地址
    private final static String ROSEFOLDER="rosefolder";
    private final static String IMGFOLDER="img";
    private final static String SYSTEMTEXT_1="3D Photos";
    private final static String SYSTEMTEXT_2="Only for you!";
    private final static String SYSTEMTEXT_TITLE="for you";
    private final static String GOTOHOME="去首页";
    private final int  MAXFILESIZE=3*1024*1024;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String info = request.getParameter("info");
		if(info==null){
			//未知访问强行将URL拉倒首页
			response.sendRedirect("index.jsp");
			return ;
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user==null){
			//由于某种原因，用户没有登录。
			//System.out.println("weizhi: "+info);
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>parent.callback_5('很抱歉，您没有登录，没有权限此页面的操作，请先在右上角点击登录。登录后请先刷新本页面，然后进行操作！');</script>");
			return ;
		}
		if (info.equals("userUploadPhoto")) {
			this.userUploadPhoto(request, response, user.getUsername());//用户上传12张图片
		} else if (info.equals("get3DPhotos")) {
			this.get3DPhotos(request, response, null, false);//生成3D相册
		} else if (info.equals("firstPreview")) {
			this.forPreview(request, response,user.getUsername(),1);//第一次预览
		} else if (info.equals("userUploadIcon")) {
			this.userUploadIcon(request, response, user.getUsername());//上传网站图标
		} else if (info.equals("secondPreview")) {
			this.forPreview(request, response,user.getUsername(),2);
		} else if (info.equals("userText")) {
			this.userText(request, response, user.getUsername());
		} else if(info.equals("thirdPreview")){
			this.forPreview(request, response,user.getUsername(),3);
		} else if(info.equals("userSetting")){
			this.userSetting(request, response,user.getUsername());
		}else if(info.equals("makePhotos")){
			this.forPreview(request, response,user.getUsername(),4);
		}else{
			//未知访问强行将URL拉倒首页
			response.sendRedirect("index.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	// 生成3D相册
	public void get3DPhotos(HttpServletRequest request,
			HttpServletResponse response, Temple temple, boolean isPreview)
			throws ServletException, IOException {
	//	System.out.println("正在生成3D相册");
		String filePath = request.getRealPath("/template.html");

		// 读取模板文件
		FileInputStream fileinputstream = new FileInputStream(filePath);
		InputStreamReader reader = new InputStreamReader(fileinputstream, "utf-8");
		StringBuffer sb = new StringBuffer();
		while (reader.ready()) {
			sb.append((char) reader.read());
		}
		String templateContent=sb.toString();
		reader.close();
		fileinputstream.close();  
//		templateContent = templateContent.replaceAll("###descrptionZJQ###",
//				temple.getDescrptionZJQ());
		templateContent = templateContent.replaceAll("###shortcuticonZJQ###",
				temple.getShortcuticonZJQ());
		templateContent = templateContent.replaceAll("###titleContentZJQ###",
				temple.getTitleContentZJQ());
		templateContent = templateContent.replaceAll("###webSiteOneZJQ###",
				temple.getWebSiteOneZJQ());
		// templateContent = templateContent.replaceAll("###contentOneZJQ###",
		// temple.getContentOneZJQ());
		templateContent = templateContent.replaceAll("###contentTwoZJQ###",
				temple.getContentTwoZJQ());
		templateContent = templateContent.replaceAll("###contentThreeZJQ###",
				temple.getContentThreeZJQ());
		templateContent = templateContent.replaceAll("###contentFourZJQ###",
				temple.getContentFourZJQ());
		String photosAddress[] = temple.getPhotosZJQ();
		// templateContent =templateContent.replaceAll("###photosZJQ0###",
		// "girl_photos/0.jpg");
		for (int j = 0; j < 12; j++) {
			if (templateContent.contains("###photosZJQ" + j + "###")) {
				templateContent = templateContent.replaceAll("###photosZJQ" + j
						+ "###", photosAddress[j]);
			}
		}
		String fileName;
		if (isPreview) {
			fileName = request.getRealPath(java.io.File.separator
					+ PREVIEWFOLDER + java.io.File.separator
					+ temple.getWebSiteTwoZJQ());
		} else {
			fileName = request.getRealPath(java.io.File.separator + HTMFOLDER
					+ java.io.File.separator + temple.getWebSiteTwoZJQ());
		}

		java.io.File file = new java.io.File(fileName);
		if (file.exists()) {
			file.delete();
		}

		FileOutputStream fileoutputstream = new FileOutputStream(fileName);
		OutputStreamWriter writer = new OutputStreamWriter(fileoutputstream, "utf-8");
		writer.append(templateContent);
		writer.flush();
		writer.close();
		fileoutputstream.close();
		// request.getRequestDispatcher("htmfolder/hehe.htm").forward(request,
		// response);
		//开始生成玫瑰页面
		if(temple.isHasRose()){
			String roseFilePath = request.getRealPath("/roseTemplate.html");
			FileInputStream roseFileinputstream = new FileInputStream(roseFilePath);
			int roseLength = roseFileinputstream.available();
			byte roseBytes[] = new byte[roseLength];
			roseFileinputstream.read(roseBytes);
			roseFileinputstream.close();
			String roseTemplateContent = new String(roseBytes);
			roseTemplateContent = roseTemplateContent.replaceAll("###ROSEZJQ###",
					HTMFOLDER + "/" + temple.getWebSiteTwoZJQ());
			roseTemplateContent = roseTemplateContent.replaceAll("###shortcuticonZJQ###",
					temple.getShortcuticonZJQ());
			String roseFileName= request.getRealPath(java.io.File.separator + ROSEFOLDER
					+ java.io.File.separator + temple.getWebSiteTwoZJQ());

			java.io.File roseRile = new java.io.File(roseFileName);
			if (roseRile.exists()) {
				roseRile.delete();
			}

			FileOutputStream roseFileoutputstream = new FileOutputStream(roseFileName);
			byte rose_tag_tytes[] = roseTemplateContent.getBytes();

			roseFileoutputstream.write(rose_tag_tytes);
			roseFileoutputstream.close();
			
		}
		if (isPreview) {
			response.sendRedirect(PREVIEWFOLDER + "/"
					+ temple.getWebSiteTwoZJQ());
		} else {
			response.sendRedirect(ROSEFOLDER + "/" + temple.getWebSiteTwoZJQ());
		}
	}

	// 实现用户上传自己的图片
	public void userUploadPhoto(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		String information = "您输入的数据失误，添加相片失败!";
		try {
			// 设置上传操作的初始化
			su.initialize(this.getServletConfig(), request, response);// 设置长传操作的初始化
			su.setMaxFileSize(7* 1024 * 1024); // 设置上传文件的初始化单位为M
			su.upload();
			Files files = su.getFiles();// 获取上传文件的操作
			checkUploadFolder(request, response, username);// 检查目录是否存在，根据姓名建立目录
			JSONObject json = new JSONObject();
			for (int i = 0; i < files.getCount(); i++) {
				File singleFile = files.getFile(i);// 获取上传文件的单个文件
				String fileType = singleFile.getFileExt(); // 获取上传文件的扩展名

				String type = "BMP,bmp,GIF,gif,JPG,jpg,JPEG,jpeg,PNG,png";
				boolean place = type.contains(fileType);
				if (place) {
					if (!singleFile.isMissing()) {// 判断该文件是否被选择
						if(singleFile.getSize()>MAXFILESIZE){
							information = "第"+(i+1)+"张图片过大，图片大小最好不要超过1MB";
							break;
						}
//					   String photoSize = String.valueOf(singleFile.getSize());
//					   System.out.println(i+" "+"size: "+photoSize);
					// // 获取上传文件的大小MAXFILESIZE
						String filedir = FOLDER + java.io.File.separator
								+ username + java.io.File.separator
								+ String.valueOf(i) + "."
								+ singleFile.getFileExt();
						json.put(String.valueOf(i), fileType);
						singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);// 执行上传操作
						information = "您添加相片成功！";
					}
				} else {
				//	System.out.println("文件类型不正确！");
					information = "文件类型不正确！";
					break;
				}
			}

			if (information.equals("您添加相片成功！")) {
				PhotoInfo photoInfo = new PhotoInfo();
				photoInfo.setUsername(username);
				photoInfo.setUrlname("");
				createSystemIcon(request,response,username);//生成系统默认Icon
				json.put("12", "png");//用户没有上传Icon时使用系统默认Icon,系统默认的Icon为Icon.png
				json.put("13", SYSTEMTEXT_1);//写入系统默认标题1
				json.put("14", SYSTEMTEXT_2);//写入系统默认标题2
				json.put("15", SYSTEMTEXT_TITLE);//写入系统默认标题
				photoInfo.setPhotoinfo(json.toString());
				boolean res = PhotoInfoDao.getInstance().savePhotoInfo(
						photoInfo);
				if (!res) {
					information = "数据库操作数据错误！";
				}
			}

		} catch (Exception e) {
			information = "未知错误(可能是图片过大，图片大小最好不要超过1MB)！";
			e.printStackTrace();
		}
		if (information.equals("您添加相片成功！")) {
			sendErrorMessage(request,response,"图片上传成功",1,true);
		} else {
			sendErrorMessage(request,response,"图片上传失败"+information,1,false);
		}

	}

	/****
	 * 用户上传自己的Icon，用户操作第二步
	 * **/
	public void userUploadIcon(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		String information = "您输入的数据失误，添加图片失败!";
		PhotoInfo photoInfo = PhotoInfoDao.getInstance()
				.getPhotoInfoByUserName(username);
		if (photoInfo == null) {
			sendErrorMessage(request,response,"很抱歉，您没有进行第一步操作，请先进行第一步操作！",5,false);
			return;
		}
		JSONObject json = null;
		try {
			json = new JSONObject(photoInfo.getPhotoinfo());
		} catch (JSONException e) {
			e.printStackTrace();
			information = "系统数据库异常";
			sendErrorMessage(request,response,"很抱歉,操作失败,"+information,2,false);
		}
		try {
			// 设置上传操作的初始化
			su.initialize(this.getServletConfig(), request, response);// 设置长传操作的初始化
			su.setMaxFileSize(7 * 1024 * 1024); // 设置上传文件的初始化
			su.upload();
			Files files = su.getFiles();// 获取上传文件的操作
			checkUploadFolder(request, response, username);// 检查目录是否存在，根据姓名建立目录

			for (int i = 0; i < files.getCount(); i++) {
				File singleFile = files.getFile(i);// 获取上传文件的单个文件
				String fileType = singleFile.getFileExt(); // 获取上传文件的扩展名

				String type = "BMP,bmp,GIF,gif,JPG,jpg,JPEG,jpeg,PNG,png";
				boolean place = type.contains(fileType);
				if (place) {
					if (!singleFile.isMissing()) {// 判断该文件是否被选择
						if(singleFile.getSize()>MAXFILESIZE){
							information ="上传图标过大，图片大小最好不要超过0.5MB";
							break;
						}
					// String photoSize = String.valueOf(singleFile.getSize());
					// // 获取上传文件的大小
						String filedir = FOLDER + java.io.File.separator
								+ username + java.io.File.separator + ICON
								+ "." + singleFile.getFileExt();
						json.put(String.valueOf(12 + i), fileType);
						singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);// 执行上传操作

						information = "您添加图片成功！";
					}
				} else {
				//	System.out.println("文件类型不正确！");
					information = "文件类型不正确！";
					break;
				}
			}

			if (information.equals("您添加图片成功！")) {
				PhotoInfo photoInfoTemp = new PhotoInfo();
				photoInfoTemp.setUsername(username);
				photoInfoTemp.setUrlname("");

				photoInfoTemp.setPhotoinfo(json.toString());

				boolean res = PhotoInfoDao.getInstance().savePhotoInfo(
						photoInfoTemp);
				if (!res) {
					information = "数据库操作数据错误！";
				}
			}

		} catch (Exception e) {
			information = "未知错误！上传图标过大，图片大小最好不要超过0.5MB";
			e.printStackTrace();
		}

		
		if (information.equals("您添加图片成功！")) {
			sendErrorMessage(request,response,"网站标签上传成功",2,true);
		} else {
			sendErrorMessage(request,response,"网站标签上传失败"+information,2,false);
		}

	}

	/****
	 * 用户输入三行文字 *用户操作第三步
	 *****/
	public void userText(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		String text1 = request.getParameter("imageText1");
		String text2 = request.getParameter("imageText2");
		String titleText=request.getParameter("titleText");
		//System.out.println(text1+":"+text2);
		if (text1 == null || text1.equals("")) {
			text1 = SYSTEMTEXT_1;
		} else {
			text1 = text1.trim();
		}
		if (text2 == null || text2.equals("")) {
			text2 = SYSTEMTEXT_2;
		} else {
			text2 = text2.trim();
		}
		if (titleText == null || titleText.equals("")) {
			titleText = SYSTEMTEXT_TITLE;
		} else {
			titleText = titleText.trim();
		}
		PhotoInfo photoInfo = PhotoInfoDao.getInstance()
				.getPhotoInfoByUserName(username);
		if (photoInfo == null) {
			sendErrorMessage(request,response,"很抱歉，您没有进行第一步操作，请先进行第一步操作！",5,false);
			return;
		}
		JSONObject json = null;
		try {
			json = new JSONObject(photoInfo.getPhotoinfo());
			json.put("13", text1);
			json.put("14", text2);
			json.put("15", titleText);
		} catch (JSONException e) {
			e.printStackTrace();
			sendErrorMessage(request,response,"很抱歉，系统数据异常，操作失败！",3,false);
			return;
		}
		PhotoInfo photoInfoTemp = new PhotoInfo();
		photoInfoTemp.setUsername(username);
		photoInfoTemp.setUrlname("");

		photoInfoTemp.setPhotoinfo(json.toString());

		boolean res = PhotoInfoDao.getInstance().savePhotoInfo(photoInfoTemp);

		
		if (res) {
			sendErrorMessage(request,response,"添加文字成功!",3,true);
		} else {
			sendErrorMessage(request,response,"添加文字失败,系统内部错误!",3,false);
		}
	}
    /****
     *     用户操作第四步
     * 用户设置URL和玫瑰页面
     * @author ZJQ
     * *****/
	public void userSetting(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		String url =request.getParameter("urltext");
		String hasRose=request.getParameter("rose");
		int intHasRose=hasRose.equals("y")? 1:0;
		//System.out.println("url="+url+"   "+"hasRose="+intHasRose);
		url=url.trim();
		PhotoInfo photoInfo = PhotoInfoDao.getInstance()
		.getPhotoInfoByUserName(username);
        if (photoInfo == null) {
    	  sendErrorMessage(request,response,"很抱歉，您没有进行第一步操作，请先进行第一步操作！",5,false);
	        return;
          }
        if(!Encrypt.isValidInput(url)){
        	 sendErrorMessage(request,response,"很抱歉，您输入的URL不合法，URL由\"数字，字母和_\"组成!",5,false);
 	        return;
        }
        if(!PhotoInfoDao.getInstance().isUniqueUrl(url, username)){
        	sendErrorMessage(request,response,"添加设置失败，该URL已被他人使用，请更换URL！",4,false);
        	return;
        }
        PhotoInfo photoInfoTemp = new PhotoInfo();
		photoInfoTemp.setUsername(username);
		photoInfoTemp.setUrlname(url);
        photoInfoTemp.setHasRose(intHasRose);
		photoInfoTemp.setPhotoinfo(photoInfo.getPhotoinfo());
		boolean res = PhotoInfoDao.getInstance().savePhotoInfo(photoInfoTemp);
		if (res) {
			sendErrorMessage(request,response,"添加设置成功！",4,true);
		} else {
			sendErrorMessage(request,response,"添加设置失败，系统内部错误！",4,false);
		}
	}
	/**
	 * 检查上传照片的目录是否存在，目录为：FOLDER/foldName
	 * 
	 * @author ZJQ
	 * **/
	public String checkUploadFolder(HttpServletRequest request,
			HttpServletResponse response, String foldName)
			throws ServletException, IOException {
		String fileFolder = request
				.getRealPath(java.io.File.separator + FOLDER);
		java.io.File file = new java.io.File(fileFolder);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir(); // 这里不能同时生成两级目录
		}
		fileFolder = request.getRealPath(java.io.File.separator + FOLDER
				+ java.io.File.separator + foldName);
		file = new java.io.File(fileFolder);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {

			file.mkdir();
			return fileFolder;
		}
		return null;
	}
    /****
     * 将系统默认Icon拷贝到用户目录下面，在用户第一次上层图片时使用，如果用户上传了自己的Icon，系统Icon将会被覆盖
     * ****/
	public void createSystemIcon(HttpServletRequest request,
			HttpServletResponse response, String username)throws ServletException, IOException{
		String fileName=request.getRealPath(FOLDER + java.io.File.separator
				+ username + java.io.File.separator + ICON
				+ "." + "png");
		java.io.File file = new java.io.File(fileName);
		if (file.exists()) {
			return;
		}else{
			String filePath=request.getRealPath(IMGFOLDER + java.io.File.separator
					+ ICON+ "." + "png");
					
			FileInputStream fileinputstream = new FileInputStream(filePath);
			int length = fileinputstream.available();
			byte bytes[] = new byte[length];
			fileinputstream.read(bytes);
			fileinputstream.close();
			FileOutputStream fileoutputstream = new FileOutputStream(fileName);
		    fileoutputstream.write(bytes);
			fileoutputstream.close();
		}
	}
	

	/*****
	 * 系统异常
	 * @author zjq
	 * @param erreromessage 错误信息
	 * @param order 第几步的信息
	 * @param flag 是否出错
	 * ******/
	public void sendErrorMessage(HttpServletRequest request,
			HttpServletResponse response,String errorMessage,int order,boolean flag) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	
		
	    if(order<5){
	    	out.println("<script language='javascript'>parent.callback_"+order+"('"+errorMessage+"','"+flag+"');</script>");
	    }else{
	    	out.println("<script language='javascript'>parent.callback_5('"+errorMessage+"');</script>");
	    }
	}
	/****
	 * 生成预览
	 * @author zjq
	 * @param  username 用户名
	 * @param  order 第几次预览
	 * **/
	public void forPreview(HttpServletRequest request,
			HttpServletResponse response,String username,int order) throws ServletException, IOException {
	//	System.out.println("正在第"+order+"次预览效果...");
		PhotoInfo photoInfo = PhotoInfoDao.getInstance()
				.getPhotoInfoByUserName(username);
		if (photoInfo == null){//不存在用户信息，但是却进了这个方法，肯定系统出了问题，出错处理
			sendErrorMessage(request,response,"很抱歉，系统出现异常，可能是您的操作不合法！",5,false);
			return;
		}
		String photos[] = new String[12];
		String iconFileType = null;
		String text1=null;
		String text2=null;
		String titleText=null;
		try {
			JSONObject json = new JSONObject(photoInfo.getPhotoinfo());

			for (int i = 0; i < 12; i++) {
				photos[i] = FOLDER + "/" + username + "/" + String.valueOf(i)
						+ "." + json.getString(String.valueOf(i));
			}
			iconFileType = json.getString("12");
			text1=json.getString("13");
			text2=json.getString("14");
			titleText=json.getString("15");
//			text1=new String(text1.getBytes("utf-8"),"utf-8");
//	        text2=new String(text2.getBytes("utf-8"),"utf-8");
//	        titleText=new String(titleText.getBytes("utf-8"),"utf-8");
		} catch (JSONException e) {
			e.printStackTrace();
			return;
		}
		 
		Temple temple = new Temple();
		temple.setDescrptionZJQ("DescrptionZJQ");
		temple.setContentOneZJQ("setContentOneZJQ");
		temple.setContentTwoZJQ(text1);
		temple.setContentThreeZJQ(text2);
		temple.setContentFourZJQ(GOTOHOME);
		temple.setPhotosZJQ(photos);
		temple.setShortcuticonZJQ(FOLDER + "/" + username + "/" + ICON + "."
				+ iconFileType);
		temple.setTitleContentZJQ(titleText);
		temple.setWebSiteOneZJQ(HOMEURL);
		
		temple.setHasRose(photoInfo.getHasRose()==1);
		
		if(order==4){
			if(photoInfo.getUrlname()==null||photoInfo.getUrlname().equals("")){
				response.sendRedirect("userForm.jsp");
				return;
			}
			temple.setWebSiteTwoZJQ(photoInfo.getUrlname() + ".html");
			get3DPhotos(request, response, temple, false);
		}else{
			temple.setWebSiteTwoZJQ(username + ".html");
			get3DPhotos(request, response, temple, true);
		}

	}


}
