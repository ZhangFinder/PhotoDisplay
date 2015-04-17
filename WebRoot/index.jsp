<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<meta name="keywords" content="4useri Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Alice' rel='stylesheet' type='text/css'>
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
					   <li><a href="#" >欢迎您,<%=username %></a></li> /
						<li><a href="UserInfoServlet?action=logout">注销</a></li>
					<% } else{%>
					    <li><a href="login.html" >登录</a></li> /
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
						<li class="active"><a href="#">首&nbsp;&nbsp;页</a></li>
						<li><a href="portfolio.html" >相册展示</a>

						</li>
						<li><a href="userForm.jsp" >制作相册</a>
						</li>
						<li><a href="announce.html">声&nbsp;&nbsp;明</a></li>
						<li><a href="about.html">关&nbsp;&nbsp;于</a></li>
							<li><a href="contact.html">联&nbsp;&nbsp;系</a></li>
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
					<h1>“WE LOVE TO MAKE YOUR WEB WAY”</h1>
				</div>
			</div>
				</article>
				 <article style="position: absolute; width: 100%; opacity: 0;"> 
			   	   	<div class="banner-info">
				<div class="container">
					<h1>“Lorem ipsum dolor sit amet”</h1>
				</div>
			</div>
				</article>
				 <article style="position: absolute; width: 100%; opacity: 0;"> 
			   	   		<div class="banner-info">
				<div class="container">
					<h1>“Claritas est etiam processus”</h1>
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
				<h3>MEET WITH OUR TEAM</h3>
				<div class="team">
					  <ul id="flexiselDemo3">
						<li>
							<div class="team1">
								<a href="#small-dialog6" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim">
								<img src="images/team1.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5>Sheak Reza</h5>
									<p>CEO, Both kicu</p>
									</div>
									<div class="both-right">
											<h6><a href="#">facebook</a> /<a href="#">twiter</a>
											<a href="#">dribbled </a>/<a href="#"> linkedin</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="team1">
								<a href="#small-dialog6" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim">
								<img src="images/team2.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5>Sheak Reza</h5>
									<p>CEO, Both kicu</p>
									</div>
									<div class="both-right">
											<h6><a href="#">facebook</a> /<a href="#">twiter</a>
											<a href="#">dribbled </a>/<a href="#"> linkedin</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="team1">
								<a href="#small-dialog6" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim">
								<img src="images/team3.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5>Sheak Reza</h5>
									<p>CEO, Both kicu</p>
									</div>
									<div class="both-right">
											<h6><a href="#">facebook</a> /<a href="#">twiter</a>
											<a href="#">dribbled </a>/<a href="#"> linkedin</a><h6>
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="team1">
								<a href="#small-dialog6" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim">
								<img src="images/team2.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5>Sheak Reza</h5>
									<p>CEO, Both kicu</p>
									</div>
									<div class="both-right">
										
											<h6><a href="#">facebook</a> /<a href="#">twiter</a>
											<a href="#">dribbled </a>/<a href="#"> linkedin</a><h6>
										
									</div>
										<div class="clearfix"></div>
								</div>
							</div>
					    </li>
						<li>
							<div class="team1">
								<a href="#small-dialog6" class="b-link-stripe b-animate-go  thickbox play-icon popup-with-zoom-anim">
								<img src="images/team1.jpg" class="img-responsive" alt="" /></a>
								<div class="both">
									<div class="both-left">
									<h5>Sheak Reza</h5>
									<p>CEO, Both kicu</p>
									</div>
									<div class="both-right">
										
											<h6><a href="#">facebook</a> /<a href="#">twiter</a>
											<a href="#">dribbled </a>/<a href="#"> linkedin</a><h6>
										
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