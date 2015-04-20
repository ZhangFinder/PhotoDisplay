<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="error.jsp"%>
<%@ page import="com.zjq.model.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>声明</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="shortcut icon" href="img/Icon.png"/> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="3D, photos, zhangfinder ,announce" />
<meta name="description" content="This page is AnnouncePage of 3D photos">
<meta name="author" content="ZhangFinder">
<meta http-equiv="Window-target" content="_top">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<script src="js/jquery.min.js"></script>
<script src="js/jquery.easydropdown.js"></script>
<script src="js/modernizr.custom.js"></script>
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
						<li ><a href="userForm.jsp">制&nbsp;&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;&nbsp;相&nbsp;&nbsp;&nbsp;&nbsp;册</a></li>
						<li class="active"><a href="#">声&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明</a>
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
				<h2>announce</h2>
			</div>
		</div>
	</div>
<!-- header -->
<!-- blog -->
<div class="blog" >
		<div class="container" >

			<div class="row" >
			   <div class="col-md-8" >
<div class="blog_bottom">
				 	<div class="badge"><div>声明<em>Announce</em></div></div>
					<div class="cont span_2_of_single">
					  <h4 class="blog_title"><a href="">此功能尚未开通！</a></h4>
					 
					  
					  <h5 class="m_head"><a href="#">注意：</a></h5>
					  <p class="m_para">1.本网站无任何商业盈利行为，属Finder课余时间作品，如有任何侵权行为，请联系ZhangFinder!</p>
                       <p class="m_para">2.请不要在本网站泄漏个人敏感信息，如因在本网站泄漏信息导致损失的，本网站概不负责！</p>
					   <p class="m_para">3.使用本网站提供的任何功能时，请遵守国家相关法律法规！对于违反国家相关法律法规的用户(欢迎各位网友举报)，本网站将记录黑名单,并取消相应资格。</p>
					   <p class="m_para">4.本网站诸多功能还在开发中，如有用户想一同参与本网站的开发，请联系ZhangFinder!</p>
					   <p class="m_para">5.ZhangFinder：ZhangFinder@126.com</p>
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