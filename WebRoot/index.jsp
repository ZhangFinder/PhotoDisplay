<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="error.jsp"%>
<%@ page import="com.zjq.model.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="shortcut icon" href="img/Icon.png"/> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="3D, photos, zhangfinder ,Home" />
<meta name="description" content="This page is HomePage of 3D photos"/>
<meta name="author" content="ZhangFinder"/>
<meta http-equiv="Window-target" content="_top"/>
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
						<li class="active"><a href="index.jsp">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
						<li><a href="Displayexample.jsp" >相&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;&nbsp;&nbsp;展&nbsp;&nbsp;&nbsp;&nbsp;示</a>
						</li>
						<li ><a href="userForm.jsp">制&nbsp;&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;&nbsp;相&nbsp;&nbsp;&nbsp;&nbsp;册</a></li>
						<li ><a href="announce.jsp">声&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明</a>
						</li>
						
						
							<li><a href="contact.jsp">联&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系</a></li>
						</ul>
						<script type="text/javascript" src="js/nav.js"></script>
				</div>
				<!-- end h_menu4 -->
						
				
				
			</div>
</div> 
<div class="banner-slider">	
		<div class="banner-col">
		</div>
		
			<div class="wmuSlider example1 section" id="section-1">
			   <article style="position: absolute; width: 100%; opacity: 0;"> 
			   	   	<div class="banner-info">
				<div class="container">
					<h1>“Open up your own place!”</h1>
				</div>
			</div>
				</article>
				 <article style="position: absolute; width: 100%; opacity: 0;"> 
			   	   	<div class="banner-info">
				<div class="container">
					<h1>“To show you pretty and confidence!”</h1>
				</div>
			</div>
				</article>
				 <article style="position: absolute; width: 100%; opacity: 0;"> 
			   	   		<div class="banner-info">
				<div class="container">
					<h1>“Good gifts for your friends! ”</h1>
				</div>
			</div>
				</article>
				<ul class="wmuSliderPagination">
                	<li><a href="#" class="">0</a></li>
                	<li><a href="#" class="">1</a></li>
                	<li><a href="#" class="">2</a></li>
                </ul>
		  </div>		
		
		<!-- script -->
          <script src="js/jquery.wmuSlider.js"></script> 
			<script>
       			$('.example1').wmuSlider();         
   		    </script>
		</div>	<!-- script -->		
		
	</div>
<!-- header -->
<!-- ourteam -->
<div class="ourteam">
			<div class="container">
				<h3>Good Examples</h3>
				<div class="team">
					  <ul id="flexiselDemo3">
						<li>
							<div class="team1">
								<a href="rosefolder/niuniu.html" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim" target="_blank" >
								<img src="images/display_niuniu.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5>妞妞&nbsp;&nbsp;&nbsp; 端午</h5>
									<p></p>
									</div>
									<div class="both-right">
											<h6><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> <a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
											<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a><a href="#"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="team1">
								<a href="htmfolder/angelababy.html" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim" target="_blank" >
								<img src="images/display_angelababy.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5> angelababy</h5>
									<p></p>
									</div>
									<div class="both-right">
											<h6><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> <a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
											<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a><a href="#"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="team1">
								<a href="htmfolder/zhangjie.html" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim" target="_blank">
								<img src="images/display_zhangjie.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5> 张杰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </h5>
									<p></p>
									</div>
									<div class="both-right">
											<h6><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> <a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
											<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a><a href="#"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="team1">
								<a href="htmfolder/koreagril.html" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim" target="_blank">
								<img src="images/display_koreagirl.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5> Korea Girl </h5>
									<p></p>
									</div>
									<div class="both-right">
											<h6><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> <a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
											<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a><a href="#"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
					    </li>
						<li>
							<div class="team1">
								<a href="rosefolder/lovelygril.html" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim" target="_blank">
								<img src="images/display_Littlegirl.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5> Litte Girl </h5>
									<p></p>
									</div>
									<div class="both-right">
											<h6><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> <a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
											<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a><a href="#"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
							</li>
													<li>
							<div class="team1">
								<a href="htmfolder/baymax.html" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim" target="_blank">
								<img src="images/display_Baymax.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5> Baymax  &nbsp;&nbsp;&nbsp;</h5>
									<p></p>
									</div>
									<div class="both-right">
											<h6><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> <a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
											<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a><a href="#"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
							</li>
						
					 </ul>
				</div>
				 <script type="text/javascript">
					$(window).load(function() {
						
						$("#flexiselDemo3").flexisel({
							visibleItems: 3,
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,    		
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: { 
								portrait: { 
									changePoint:480,
									visibleItems: 1
								}, 
								landscape: { 
									changePoint:640,
									visibleItems: 2
								},
								tablet: { 
									changePoint:768,
									visibleItems: 3
								}
							}
						});
						
					});
				    </script>
				    <script type="text/javascript" src="js/jquery.flexisel.js"></script>
		</div>
	</div>
<!-- ourteam -->

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