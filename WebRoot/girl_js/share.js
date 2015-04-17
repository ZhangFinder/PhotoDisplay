function shareSina(url,title,imageUrl){
	 window.open("http://v.t.sina.com.cn/share/share.php?url=" + url + "&title=" + title + "&content=utf-8&source=&sourceUrl=&pic="+imageUrl);
}
function shareRenren(url,title,imageUrl,content){
	 window.open("http://widget.renren.com/dialog/share?resourceUrl="+url+"&srcUrl=&title="+title+"&content=utf-8&pic="+imageUrl+"&description="+content);
}
function shareTecentWeibo(url,title){
	window.open("http://share.v.t.qq.com/index.php?c=share&a=index&title="+title+"&url="+url);
}
function shareQQZone(url,title,imageUrl,content){
	window.open("http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+url+"&title="+title+"&pics="+imageUrl+"&summary="+content);
}