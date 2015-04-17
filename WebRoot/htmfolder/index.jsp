<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登录界面</title>
		<style type="text/css">
<!--
.style1 {
	font-family: "华文行楷";
	font-size: 36px;
}

input {
	font-family: "宋体";
	font-size: 9pt;
	color: #333333;
	border: 1px solid #000000;
	background-color: #ffffff;
}


-->
</style>
<script type="text/javascript">
  	/**
	*注册
	*/
	function register(){
		window.location.href="register.jsp";
	}
</script>
	</head>

	<body background="img/whole_bg.jpg">
    <div align="center" >
      <p class="style1"> 登录界面<br></p>
   </div>
   <div align="center">
   <form id="loginform">
      <table width="317" height="129" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="106" align="right" class="td">用户名：</td>
				<td colspan="2" valign="middle"><label> <input
					name="username" id="username" type="text" /> </label></td>
			</tr>
			<tr>
				<td align="right" class="td">密 &nbsp;码：</td>
				<td colspan="2"><input name="userpwd" id="userpwd"
					type="password" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td width="100" height="40"><input style="cursor: pointer;" onClick="enter()" type="button" value="登录"/></td>
				<td width="101"><input style="cursor: pointer;" onClick="register()" type="button" value="注册"/></td>
			</tr>
		</table>
   </form>
   </div>
	</body>
</html>
