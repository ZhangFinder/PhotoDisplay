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
	private final static String HOMEURL = "../index.jsp";//��ҳ��ַ
    private final static String ROSEFOLDER="rosefolder";
    private final static String IMGFOLDER="img";
    private final static String SYSTEMTEXT_1="3D Photos";
    private final static String SYSTEMTEXT_2="Only for you!";
    private final static String SYSTEMTEXT_TITLE="for you";
    private final static String GOTOHOME="ȥ��ҳ";
    private final int  MAXFILESIZE=3*1024*1024;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String info = request.getParameter("info");
		if(info==null){
			//δ֪����ǿ�н�URL������ҳ
			response.sendRedirect("index.jsp");
			return ;
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user==null){
			//����ĳ��ԭ���û�û�е�¼��
			//System.out.println("weizhi: "+info);
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>parent.callback_5('�ܱ�Ǹ����û�е�¼��û��Ȩ�޴�ҳ��Ĳ��������������Ͻǵ����¼����¼������ˢ�±�ҳ�棬Ȼ����в�����');</script>");
			return ;
		}
		if (info.equals("userUploadPhoto")) {
			this.userUploadPhoto(request, response, user.getUsername());//�û��ϴ�12��ͼƬ
		} else if (info.equals("get3DPhotos")) {
			this.get3DPhotos(request, response, null, false);//����3D���
		} else if (info.equals("firstPreview")) {
			this.forPreview(request, response,user.getUsername(),1);//��һ��Ԥ��
		} else if (info.equals("userUploadIcon")) {
			this.userUploadIcon(request, response, user.getUsername());//�ϴ���վͼ��
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
			//δ֪����ǿ�н�URL������ҳ
			response.sendRedirect("index.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	// ����3D���
	public void get3DPhotos(HttpServletRequest request,
			HttpServletResponse response, Temple temple, boolean isPreview)
			throws ServletException, IOException {
	//	System.out.println("��������3D���");
		String filePath = request.getRealPath("/template.html");

		// ��ȡģ���ļ�
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
		//��ʼ����õ��ҳ��
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

	// ʵ���û��ϴ��Լ���ͼƬ
	public void userUploadPhoto(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		String information = "�����������ʧ�������Ƭʧ��!";
		try {
			// �����ϴ������ĳ�ʼ��
			su.initialize(this.getServletConfig(), request, response);// ���ó��������ĳ�ʼ��
			su.setMaxFileSize(7* 1024 * 1024); // �����ϴ��ļ��ĳ�ʼ����λΪM
			su.upload();
			Files files = su.getFiles();// ��ȡ�ϴ��ļ��Ĳ���
			checkUploadFolder(request, response, username);// ���Ŀ¼�Ƿ���ڣ�������������Ŀ¼
			JSONObject json = new JSONObject();
			for (int i = 0; i < files.getCount(); i++) {
				File singleFile = files.getFile(i);// ��ȡ�ϴ��ļ��ĵ����ļ�
				String fileType = singleFile.getFileExt(); // ��ȡ�ϴ��ļ�����չ��

				String type = "BMP,bmp,GIF,gif,JPG,jpg,JPEG,jpeg,PNG,png";
				boolean place = type.contains(fileType);
				if (place) {
					if (!singleFile.isMissing()) {// �жϸ��ļ��Ƿ�ѡ��
						if(singleFile.getSize()>MAXFILESIZE){
							information = "��"+(i+1)+"��ͼƬ����ͼƬ��С��ò�Ҫ����1MB";
							break;
						}
//					   String photoSize = String.valueOf(singleFile.getSize());
//					   System.out.println(i+" "+"size: "+photoSize);
					// // ��ȡ�ϴ��ļ��Ĵ�СMAXFILESIZE
						String filedir = FOLDER + java.io.File.separator
								+ username + java.io.File.separator
								+ String.valueOf(i) + "."
								+ singleFile.getFileExt();
						json.put(String.valueOf(i), fileType);
						singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);// ִ���ϴ�����
						information = "�������Ƭ�ɹ���";
					}
				} else {
				//	System.out.println("�ļ����Ͳ���ȷ��");
					information = "�ļ����Ͳ���ȷ��";
					break;
				}
			}

			if (information.equals("�������Ƭ�ɹ���")) {
				PhotoInfo photoInfo = new PhotoInfo();
				photoInfo.setUsername(username);
				photoInfo.setUrlname("");
				createSystemIcon(request,response,username);//����ϵͳĬ��Icon
				json.put("12", "png");//�û�û���ϴ�Iconʱʹ��ϵͳĬ��Icon,ϵͳĬ�ϵ�IconΪIcon.png
				json.put("13", SYSTEMTEXT_1);//д��ϵͳĬ�ϱ���1
				json.put("14", SYSTEMTEXT_2);//д��ϵͳĬ�ϱ���2
				json.put("15", SYSTEMTEXT_TITLE);//д��ϵͳĬ�ϱ���
				photoInfo.setPhotoinfo(json.toString());
				boolean res = PhotoInfoDao.getInstance().savePhotoInfo(
						photoInfo);
				if (!res) {
					information = "���ݿ�������ݴ���";
				}
			}

		} catch (Exception e) {
			information = "δ֪����(������ͼƬ����ͼƬ��С��ò�Ҫ����1MB)��";
			e.printStackTrace();
		}
		if (information.equals("�������Ƭ�ɹ���")) {
			sendErrorMessage(request,response,"ͼƬ�ϴ��ɹ�",1,true);
		} else {
			sendErrorMessage(request,response,"ͼƬ�ϴ�ʧ��"+information,1,false);
		}

	}

	/****
	 * �û��ϴ��Լ���Icon���û������ڶ���
	 * **/
	public void userUploadIcon(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		String information = "�����������ʧ�����ͼƬʧ��!";
		PhotoInfo photoInfo = PhotoInfoDao.getInstance()
				.getPhotoInfoByUserName(username);
		if (photoInfo == null) {
			sendErrorMessage(request,response,"�ܱ�Ǹ����û�н��е�һ�����������Ƚ��е�һ��������",5,false);
			return;
		}
		JSONObject json = null;
		try {
			json = new JSONObject(photoInfo.getPhotoinfo());
		} catch (JSONException e) {
			e.printStackTrace();
			information = "ϵͳ���ݿ��쳣";
			sendErrorMessage(request,response,"�ܱ�Ǹ,����ʧ��,"+information,2,false);
		}
		try {
			// �����ϴ������ĳ�ʼ��
			su.initialize(this.getServletConfig(), request, response);// ���ó��������ĳ�ʼ��
			su.setMaxFileSize(7 * 1024 * 1024); // �����ϴ��ļ��ĳ�ʼ��
			su.upload();
			Files files = su.getFiles();// ��ȡ�ϴ��ļ��Ĳ���
			checkUploadFolder(request, response, username);// ���Ŀ¼�Ƿ���ڣ�������������Ŀ¼

			for (int i = 0; i < files.getCount(); i++) {
				File singleFile = files.getFile(i);// ��ȡ�ϴ��ļ��ĵ����ļ�
				String fileType = singleFile.getFileExt(); // ��ȡ�ϴ��ļ�����չ��

				String type = "BMP,bmp,GIF,gif,JPG,jpg,JPEG,jpeg,PNG,png";
				boolean place = type.contains(fileType);
				if (place) {
					if (!singleFile.isMissing()) {// �жϸ��ļ��Ƿ�ѡ��
						if(singleFile.getSize()>MAXFILESIZE){
							information ="�ϴ�ͼ�����ͼƬ��С��ò�Ҫ����0.5MB";
							break;
						}
					// String photoSize = String.valueOf(singleFile.getSize());
					// // ��ȡ�ϴ��ļ��Ĵ�С
						String filedir = FOLDER + java.io.File.separator
								+ username + java.io.File.separator + ICON
								+ "." + singleFile.getFileExt();
						json.put(String.valueOf(12 + i), fileType);
						singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);// ִ���ϴ�����

						information = "�����ͼƬ�ɹ���";
					}
				} else {
				//	System.out.println("�ļ����Ͳ���ȷ��");
					information = "�ļ����Ͳ���ȷ��";
					break;
				}
			}

			if (information.equals("�����ͼƬ�ɹ���")) {
				PhotoInfo photoInfoTemp = new PhotoInfo();
				photoInfoTemp.setUsername(username);
				photoInfoTemp.setUrlname("");

				photoInfoTemp.setPhotoinfo(json.toString());

				boolean res = PhotoInfoDao.getInstance().savePhotoInfo(
						photoInfoTemp);
				if (!res) {
					information = "���ݿ�������ݴ���";
				}
			}

		} catch (Exception e) {
			information = "δ֪�����ϴ�ͼ�����ͼƬ��С��ò�Ҫ����0.5MB";
			e.printStackTrace();
		}

		
		if (information.equals("�����ͼƬ�ɹ���")) {
			sendErrorMessage(request,response,"��վ��ǩ�ϴ��ɹ�",2,true);
		} else {
			sendErrorMessage(request,response,"��վ��ǩ�ϴ�ʧ��"+information,2,false);
		}

	}

	/****
	 * �û������������� *�û�����������
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
			sendErrorMessage(request,response,"�ܱ�Ǹ����û�н��е�һ�����������Ƚ��е�һ��������",5,false);
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
			sendErrorMessage(request,response,"�ܱ�Ǹ��ϵͳ�����쳣������ʧ�ܣ�",3,false);
			return;
		}
		PhotoInfo photoInfoTemp = new PhotoInfo();
		photoInfoTemp.setUsername(username);
		photoInfoTemp.setUrlname("");

		photoInfoTemp.setPhotoinfo(json.toString());

		boolean res = PhotoInfoDao.getInstance().savePhotoInfo(photoInfoTemp);

		
		if (res) {
			sendErrorMessage(request,response,"������ֳɹ�!",3,true);
		} else {
			sendErrorMessage(request,response,"�������ʧ��,ϵͳ�ڲ�����!",3,false);
		}
	}
    /****
     *     �û��������Ĳ�
     * �û�����URL��õ��ҳ��
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
    	  sendErrorMessage(request,response,"�ܱ�Ǹ����û�н��е�һ�����������Ƚ��е�һ��������",5,false);
	        return;
          }
        if(!Encrypt.isValidInput(url)){
        	 sendErrorMessage(request,response,"�ܱ�Ǹ���������URL���Ϸ���URL��\"���֣���ĸ��_\"���!",5,false);
 	        return;
        }
        if(!PhotoInfoDao.getInstance().isUniqueUrl(url, username)){
        	sendErrorMessage(request,response,"�������ʧ�ܣ���URL�ѱ�����ʹ�ã������URL��",4,false);
        	return;
        }
        PhotoInfo photoInfoTemp = new PhotoInfo();
		photoInfoTemp.setUsername(username);
		photoInfoTemp.setUrlname(url);
        photoInfoTemp.setHasRose(intHasRose);
		photoInfoTemp.setPhotoinfo(photoInfo.getPhotoinfo());
		boolean res = PhotoInfoDao.getInstance().savePhotoInfo(photoInfoTemp);
		if (res) {
			sendErrorMessage(request,response,"������óɹ���",4,true);
		} else {
			sendErrorMessage(request,response,"�������ʧ�ܣ�ϵͳ�ڲ�����",4,false);
		}
	}
	/**
	 * ����ϴ���Ƭ��Ŀ¼�Ƿ���ڣ�Ŀ¼Ϊ��FOLDER/foldName
	 * 
	 * @author ZJQ
	 * **/
	public String checkUploadFolder(HttpServletRequest request,
			HttpServletResponse response, String foldName)
			throws ServletException, IOException {
		String fileFolder = request
				.getRealPath(java.io.File.separator + FOLDER);
		java.io.File file = new java.io.File(fileFolder);
		// ����ļ��в������򴴽�
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir(); // ���ﲻ��ͬʱ��������Ŀ¼
		}
		fileFolder = request.getRealPath(java.io.File.separator + FOLDER
				+ java.io.File.separator + foldName);
		file = new java.io.File(fileFolder);
		// ����ļ��в������򴴽�
		if (!file.exists() && !file.isDirectory()) {

			file.mkdir();
			return fileFolder;
		}
		return null;
	}
    /****
     * ��ϵͳĬ��Icon�������û�Ŀ¼���棬���û���һ���ϲ�ͼƬʱʹ�ã�����û��ϴ����Լ���Icon��ϵͳIcon���ᱻ����
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
	 * ϵͳ�쳣
	 * @author zjq
	 * @param erreromessage ������Ϣ
	 * @param order �ڼ�������Ϣ
	 * @param flag �Ƿ����
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
	 * ����Ԥ��
	 * @author zjq
	 * @param  username �û���
	 * @param  order �ڼ���Ԥ��
	 * **/
	public void forPreview(HttpServletRequest request,
			HttpServletResponse response,String username,int order) throws ServletException, IOException {
	//	System.out.println("���ڵ�"+order+"��Ԥ��Ч��...");
		PhotoInfo photoInfo = PhotoInfoDao.getInstance()
				.getPhotoInfoByUserName(username);
		if (photoInfo == null){//�������û���Ϣ������ȴ��������������϶�ϵͳ�������⣬������
			sendErrorMessage(request,response,"�ܱ�Ǹ��ϵͳ�����쳣�����������Ĳ������Ϸ���",5,false);
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
