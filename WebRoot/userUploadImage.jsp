<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>照片上传页面</title>
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
<body background="img/whole_bg.jpg">
 <form action="PhotoServlet?info=userUploadPhoto" method="post" enctype="multipart/form-data" name="form" onSubmit="return checkPhoto(form)">
<table align="center" border="5">
  <tr>
    <td colspan="2" align="center"  >上传图片</td>
  </tr>
  <tr>
    <td>相片1：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片2：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片3：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片4：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片5：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片6：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片7：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片8：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
    <tr>
    <td>相片9：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
      <tr>
    <td>相片10：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
      <tr>
    <td>相片11：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>
      <tr>
    <td>相片12：</td>
    <td><input name="file" type="file" maxlength="20"></td>
  </tr>

  <tr>
               <td colspan="2">             
               <input type="submit" name="Submit" value="上传" >
             &nbsp;&nbsp;
               <input type="reset" name="Submit2" value="重置">
             &nbsp;&nbsp;
               <input type="button" name="Submit3" value="返回" onClick="javascript:window.location.href='photoServlet?info=userQueryPhoto';">
            </td>
  </tr>
</table>
</form>
</body> 
</html>