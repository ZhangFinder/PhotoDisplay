/**重新加载验证码****/
function reload() {
	document.checkCode.src = "imageCode.jsp";
}
function checkemail(email) {
	var str = email;
  //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	var objExp = new RegExp(Expression);
	if (objExp.test(str) == true) {
		return true;
	} else {
		return false;
	}
}
/****检验注册表单数据是否为空******/
function checkRegister(form) {
	if (form.username.value == "") {
		window.alert("\u8bf7\u8f93\u5165\u7528\u6237\u540d\uff01");
		return false;
	}
	if (form.password.value == "") {
		window.alert("\u8bf7\u8f93\u5165\u5bc6\u7801\uff01");
		return false;
	}else{
		if(form.password.value.length<6||form.password.value.length>16){
			alert("\u5BC6\u7801\u683C\u5F0F\u4E3A 6-16 \u4F4D\uFF01;");
			return false;
		}
	}
	if (form.repassword.value == "") {
		window.alert("\u8bf7\u8f93\u5165\u5bc6\u7801\u786e\u8ba4");
		return false;
	}
	if (form.password.value != form.repassword.value) {
		window.alert("\u60a8\u8f93\u5165\u7684\u5bc6\u7801\u4e0e\u786e\u8ba4\u5bc6\u7801\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
		return false;
	}
	if (form.email.value == "") {
		window.alert("\u8bf7\u8f93\u5165Email\u5730\u5740\uff01");
		return false;
	}
	if (!checkemail(form.email.value)) {
		window.alert("\u60a8\u8f93\u5165\u7684email\u5730\u5740\u683c\u5f0f\u4e0d\u6b63\u786e\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
		return false;
	}
	if (form.answer.value == "") {
		window.alert("\u8bf7\u8f93\u5165\u95ee\u9898\u7b54\u6848\uff01");
		return false;
	}
	if (form.code.value == "") {
		window.alert("\u8bf7\u8f93\u5165\u6821\u9a8c\u7801\uff01");
		return false;
	}
	return true;
}
