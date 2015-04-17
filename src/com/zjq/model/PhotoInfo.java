package com.zjq.model;

public class PhotoInfo {
	private Integer id = -1;
	private String username;
	private String urlname="";
	private int hasRose;
	private String photoinfo;// json.toString()主要保存照片对应的后缀名

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public int getHasRose() {
		return hasRose;
	}

	public void setHasRose(int hasRose) {
		if(hasRose>0)
			hasRose=1;
		else 
			hasRose=0;
		this.hasRose = hasRose;
	}

	public String getPhotoinfo() {
		return photoinfo;
	}

	public void setPhotoinfo(String photoinfo) {
		this.photoinfo = photoinfo;
	}

}
