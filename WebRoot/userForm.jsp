<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zjq.model.User" %>
 <%
    String isLogin;
    if(session.getAttribute("loginUser")!=null){
     isLogin="1";
    }else{
      isLogin="0";
     }
     %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>相册制作</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="shortcut icon" href="img/Icon.png"/> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="4useri Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<script src="js/jquery.min.js"></script>
<script src="js/jquery.easydropdown.js"></script>
<script src="js/modernizr.custom.js"></script>
<script type="text/javascript">
 function callback_1(msg1,flag){
   document.getElementById("msg1").innerHTML = "<font color=red>"+msg1+"</font>";
   if(flag=="true"){
       document.getElementById("preview_1").style.display="inline-block";
       document.getElementById("preview_2").style.display="none";
       document.getElementById("preview_3").style.display="none";
       document.getElementById("preview_4").style.display="none";
    }else{
       document.getElementById("preview_1").style.display="none";
    }
 }
  function callback_2(msg2,flag){
   document.getElementById("msg2").innerHTML = "<font color=red>"+msg2+"</font>";
    if(flag=="true"){
       document.getElementById("preview_2").style.display="inline-block";
       document.getElementById("preview_1").style.display="none";
       document.getElementById("preview_3").style.display="none";
       document.getElementById("preview_4").style.display="none";
    }else{
       document.getElementById("preview_2").style.display="none";
    }
 }
  function callback_3(msg3,flag){
   document.getElementById("msg3").innerHTML = "<font color=red>"+msg3+"</font>";
   if(flag=="true"){
       document.getElementById("preview_3").style.display="inline-block";
       document.getElementById("preview_1").style.display="none";
       document.getElementById("preview_2").style.display="none";
       document.getElementById("preview_4").style.display="none";
    }else{
       document.getElementById("preview_3").style.display="none";
       
    }
 }
   function callback_4(msg4,flag){
    document.getElementById("msg4").innerHTML = "<font color=red>"+msg4+"</font>";
    if(flag=="true"){
       document.getElementById("preview_4").style.display="inline-block";
       document.getElementById("preview_1").style.display="none";
       document.getElementById("preview_2").style.display="none";
       document.getElementById("preview_3").style.display="none";
    }else{
       document.getElementById("preview_4").style.display="none";
    }
 }
    function callback_5(msg5){
     alert(msg5);
 }
 function checkPhoto(){
   if(!isLogin())
              return false;
  	var file1=document.getElementById("file1").value;
    var file2=document.getElementById("file2").value;
    var file3=document.getElementById("file3").value;
    var file4=document.getElementById("file4").value;
    var file5=document.getElementById("file5").value;
    var file6=document.getElementById("file6").value;
    var file7=document.getElementById("file7").value;
    var file8=document.getElementById("file8").value;
    var file9=document.getElementById("file9").value;
    var file10=document.getElementById("file10").value;
    var file11=document.getElementById("file11").value;
    var file12=document.getElementById("file12").value;
	if(file1==null||file1==""||file2==null||file2==""||file3==null||file3==""||file4==null||file4==""||file5==null||file5==""||file6==null||file6==""||file7==null||file7==""||file8==null||file8==""||file9==null||file9==""||file10==null||file10==""||file11==null||file11==""||file12==null||file12==""){
		 alert("上传的图片总数少于12张");
		 
		 return false;
     }
   return true;
 }
 function checkIcon(){
   if(!isLogin())
              return false;
  	var fileIcon=document.getElementById("fileIcon").value;
	if(fileIcon==null||fileIcon==""){
		 alert("您没有选择标签图标文件！");
		 return false;
     }
   return true;
 }
 function checkUrl(){
           if(!isLogin())
              return false;
            var url=document.getElementById("urltext").value;
            var yes=document.getElementById("yes");
            var no=document.getElementById("no");
            if(url==null||url==""){
             alert("您没有填写URL！");
              return false;
            }
            if(!yes.checked&&!no.checked){
               alert("请选择是否生成玫瑰页面！");
               return false;
            }
         return true;
 }
 
 function isLogin(){
     var isLogin=document.getElementById("isLogin").value;
     if(isLogin==null||isLogin=="0"){
       alert("很抱歉，您没有登录，没有权限此页面的操作，请先在右上角点击登录。登录后请先刷新本页面，然后进行操作！");
       return  false;
     }
     return true;
 }
</script>
<link rel="stylesheet" href="css/swipebox.css">
 <!------ Light Box ------>
    <script src="js/jquery.swipebox.min.js"></script> 
    <script type="text/javascript">
		jQuery(function($) {
			$(".swipebox").swipebox();
		});
	</script>
    <!------ Eng Light Box ------>
	<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
				});
			});
		</script>
<style type="text/css">
<!--

input{
	font-family: "宋体";
	font-size: 9pt;
	color: #333333;
	border: 1px solid #000000;
	background-color: #ffffff;
}

-->
</style>
</head>
<body>
<!-- header -->
	<div class="banner">
	<div class="header">
  	    <div class="container">
			<div class="head-bann">
				<div class="logo">
					<a href="index.html"><img src="img/logo_my_3.png" class="img-responsive" alt="" /></a>
				</div>
				<div class="head-part">
					<ul>
					<% if(session.getAttribute("loginUser")!=null){
					    User user=(User) session.getAttribute("loginUser");
					    String username=user.getUsername();
					%>
					   <li><a href="#" >欢迎您,<%=username %></a></li> |
						<li><a href="UserInfoServlet?action=logout">注销</a></li>
					<% } else{%>
					    <li><a href="login.html" >登录</a></li> |
						<li><a href="reg.html">注册</a></li>
					<%} %> 
							<div class="clearfix"> </div>
					</ul>
				</div>	
					<div class="clearfix"> </div>
			</div>
					<!-- start h_menu4 -->
						<div class="h_menu4">
					<a class="toggleMenu" href="">Menu</a>
					<ul class="nav">
						<li><a href="index.jsp">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
						<li><a href="Displayexample.jsp" >相&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;&nbsp;&nbsp;展&nbsp;&nbsp;&nbsp;&nbsp;示</a>
						</li>
						<li class="active"><a href="userForm.jsp">制&nbsp;&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;&nbsp;相&nbsp;&nbsp;&nbsp;&nbsp;册</a></li>
						<li ><a href="announce.jsp">声&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明</a>
						</li>
						
						
							<li><a href="contact.jsp">联&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系</a></li>
						</ul>
						<script type="text/javascript" src="js/nav.js"></script>
				</div>
				<!-- end h_menu4 -->
			</div>
</div> 	
<div class="banner-info1">	
		<div class="banner-col">
		</div>
		
			<div class="container">
				<h2>Make your own photos!</h2>
			</div>
		</div>
	</div>
<!-- header -->
<!-- blog -->
<div class="blog">
		<div class="container">
		  <section class="title-section">
 			<ul class="breadcrumb breadcrumb__t"><li class="active">Begin to make you own Photos!</li></ul>  
		     <input type="hidden" name="isLogin" id="isLogin" value="<%=isLogin %>"/>
		  </section>
			<div class="row">
			   <div class="col-md-8">
				<div class="blog_top">
				    <div class="badge"><div>1<em>First</em></div></div>
				 	<div class="cont span_2_of_single">
					   <h4 class="blog_title"><a href="">第一步</a></h4>
					   <div class="blog_info">上传12张图片</div>
					  <h5 class="m_head"><a href="#">注意：</a></h5>
					  <p class="m_para">3D 相册共有4个面，每个面有3张图片，因此共有12张图片（<a href="#">点我查看示例</a>）。上传的图片尽量要高清，尺寸接近正方形，长宽尺寸不要相隔太大，700*700左右最佳。如果图片清晰度不够，或者尺寸不合适，生成的3D相册会出现失真情况。您可以上传后点击预览，观察3D相册效果是否符合您的要求。</p>
    <form action="PhotoServlet?info=userUploadPhoto" method="post" enctype="multipart/form-data" name="form" onSubmit="return checkPhoto()" target="hidden_frame_1">
<table align="center" border="5" width="456">
  <tr>
    <td colspan="2" align="center"  >上传图片</td>
  </tr>
  <tr>
    <td align="center">图&nbsp;&nbsp;片1：</td>
    <td><input id="file1" name="file1" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center">图&nbsp;&nbsp;片2：</td>
    <td ><input id="file2" name="file2" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center">图&nbsp;&nbsp;片3：</td>
    <td><input id="file3" name="file3" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center">图&nbsp;&nbsp;片4：</td>
    <td><input id="file4" name="file4" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center">图&nbsp;&nbsp;片5：</td>
    <td><input id="file5" name="file5" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center" >图&nbsp;&nbsp;片6：</td>
    <td><input id="file6" name="file6" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center" >图&nbsp;&nbsp;片7：</td>
    <td><input id="file7" name="file7" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center" >图&nbsp;&nbsp;片8：</td>
    <td><input id="file8" name="file8" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center" >图&nbsp;&nbsp;片9：</td>
    <td><input id="file9" name="file9" type="file" maxlength="20"></td>
  </tr>
      <tr>
    <td align="center">图&nbsp;&nbsp;片10：</td>
    <td><input id="file10" name="file10" type="file" maxlength="20"></td>
  </tr>
      <tr>
    <td align="center" >图&nbsp;&nbsp;片11：</td>
    <td><input id="file11" name="file11" type="file" maxlength="20"></td>
  </tr>
      <tr>
    <td align="center" >图&nbsp;&nbsp;片12：</td>
    <td><input id="file12" name="file12" type="file" maxlength="20"></td>
  </tr>

  <tr>
               <td colspan="2" align="center">        
                
               <input type="submit" name="Submit" value="上传" >
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="reset" name="Submit2" value="重置">
           
            </td>
  </tr>
</table>
 <div align="center"><span id="msg1" ></span> </div>
 <iframe name="hidden_frame_1" id="hidden_frame_1" style='display:none'></iframe>
 
</form>
			<a href="PhotoServlet?info=firstPreview" class="myblog_btn" id="preview_1" target="_blank">预览</a>
					</div>
					 <div class="clearfix"></div>
				 </div>
				 <div class="blog_top">
				 	<div class="badge"><div>2<em>Second</em></div></div>
					<div class="cont span_2_of_single">
					   <h4 class="blog_title"><a href="">第二步</a></h4>
					   <div class="blog_info">上传网站标签图标  </div>
					 
					   <img src="images/baidu_icon.png" class="img-responsive" alt=""/>
					  <h5 class="m_head"><a href="#">注意：</a></h5>
					  <p class="m_para">每一个网站都有一个标签图标。如上图左边的小熊掌就是百度网站的标签图标。3D相册也可以上传自己的网站标签图标。网站图标的尺寸为28*28最佳。</p>
                       <form action="PhotoServlet?info=userUploadIcon" method="post" enctype="multipart/form-data" name="form2" onSubmit="return checkIcon()" target="hidden_frame_2">
<table align="center" border="5" width="456">
  <tr>
    <td colspan="2" align="center"  >上传图标</td>
  </tr>
  <tr>
    <td align="center">网站标签图片：</td>
    <td><input name="fileIcon" id="fileIcon" type="file" maxlength="20"></td>
  </tr>


  <tr>
               <td colspan="2" align="center">        
                
               <input type="submit" name="Submit" value="上传" >
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="reset" name="Submit2" value="重置">
           
            </td>
  </tr>
</table>
 <div align="center"><span id="msg2" ></span> </div>
 <iframe name="hidden_frame_2" id="hidden_frame_2" style='display:none'></iframe>
</form>
					 <a href="PhotoServlet?info=secondPreview" class="myblog_btn" id="preview_2" target="_blank">预览</a>
					</div>
					 <div class="clearfix"></div>
				  </div>
                  <div class="blog_top">
				 					 	<div class="badge"><div>3<em>Third</em></div></div>
					<div class="cont span_2_of_single">
					   <h4 class="blog_title"><a href="">第三步</a></h4>
					   <div class="blog_info">网页文字
						
					   </div>
					   <img src="images/textImage2.png" class="img-responsive" alt=""/>
					   <img src="images/textImage1.png" class="img-responsive" alt=""/>
					  <h5 class="m_head"><a href="#">注意：</a></h5>
					  <p class="m_para">如上图，网页上共有三处文字，用户可以添加自己喜欢的文字内容。</p>
                         <form action="PhotoServlet?info=userText" method="post" name="formImageText" onSubmit="return isLogin()" target="hidden_frame_3" >
<table align="center" border="5" width="456">
  <tr>
    <td colspan="2" align="center"  >设置文字</td>
  </tr>
    <tr>
    <td align="center">网站标题：</td>
    <td><input name="titleText" id="titleText" type="text" maxlength="20"></td>
  </tr>
  <tr>
    <td align="center">文&nbsp;&nbsp;字1：</td>
    <td><input name="imageText1" id="imageText1" type="text" maxlength="20"></td>
  </tr>
    <tr>
    <td align="center">文&nbsp;&nbsp;字2：</td>
    <td ><input name="imageText2" id="imageText2" type="text" maxlength="20"></td>
  </tr>
  <tr>
               <td colspan="2" align="center">        
                
               <input type="submit" name="Submit" value="提交" >
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="reset" name="Submit2" value="重置">
           
            </td>
  </tr>
</table>
 <div align="center"><span id="msg3" ></span> </div>
 <iframe name="hidden_frame_3" id="hidden_frame_3" style='display:none'></iframe>
</form>
					   <a href="PhotoServlet?info=thirdPreview" class="myblog_btn" id="preview_3" target="_blank">预览</a>
					</div>
					 <div class="clearfix"></div>
				  </div>
				  <div class="blog_bottom">
				 	<div class="badge"><div>4<em>Fourth</em></div></div>
					<div class="cont span_2_of_single">
					  					   <h4 class="blog_title"><a href="">第四步</a></h4>
					   <div class="blog_info">设置URL，生成玫瑰页面
					   </div>
					  <h5 class="m_head"><a href="#">注意：</a></h5>
					  <p class="m_para">如上图，您可以自定义网页URL。同时可以 指定是否生成玫瑰页面。生成玫瑰页面后（<a href="#">点我查看示例</a>），网页将在前5秒显示玫瑰，5秒后跳转到3D相册页面!</p>
                         <form action="PhotoServlet?info=userSetting" method="post"  name="urlform" onSubmit="return checkUrl()" target="hidden_frame_4" >
<table align="center" border="5" width="456">
  <tr>
    <td colspan="2" align="center"  >设置URL，玫瑰页面</td>
  </tr>
  <tr>
    <td align="center">&nbsp;&nbsp;URL：</td>
    <td><input name="urltext" id="urltext" type="text" maxlength="20">.html&nbsp;&nbsp;(如 foryou.html)</td>
  </tr>
    <tr>
    <td align="center">是否添加玫瑰页面:</td>
    <td ><input name="rose" id="yes" value="y" type="radio"/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input name="rose" id="no" value="n" type="radio" checked="checked" />否
    </td>
  </tr>

  <tr>
               <td colspan="2" align="center">        
                
               <input type="submit" name="Submit" value="提交" >
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="reset" name="Submit2" value="重置">
           
            </td>
  </tr>
</table>
 <div align="center"><span id="msg4" ></span> </div>
 <iframe name="hidden_frame_4" id="hidden_frame_4" style='display:none'></iframe>
</form>
					    <a href="PhotoServlet?info=makePhotos" class="myblog_btn" id="preview_4" target="_blank">查看相册</a>
					</div>
					 <div class="clearfix"></div>
				  </div>
				</div>
				<div class="col-md-4 blog_sidebar">
				 <ul class="sidebar">
					<h3>推荐</h3>
		            <li><a href="#">毕业留言墙（暂未开通）</a></li>
		            <li><a href="#">团队通讯录(暂未开通)</a></li>
		            <li><a href="#">匿名聊天室(暂未开通)</a></li>
		            <li><a href="#">网页礼物定做（暂未开通）</a></li>
		            <li><a href="http://blog.sina.com.cn/u/2673932577" target="_blank" >ZhangFinder博客</a></li>
		           
		          </ul>
		          
		       </div>
			</div>
			</div>
	 	</div>
	


<!-- blog -->
<!-- footer -->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				<div class="subsc">
							<div class="clearfix"></div>
				</div>
			</div>
				<div class="footer-bottom">
					<div class="footer-nav">
				     Copyright &copy; 2015.ZhangFinder All rights reserved
					</div>
						
				</div>
		</div>
	</div>
<!-- footer -->

</body>
</html>