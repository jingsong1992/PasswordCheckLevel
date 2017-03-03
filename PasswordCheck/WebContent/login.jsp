<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var xmlHttp;
var flag;
function createXMLHttp(){
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
		}else{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
}
function move(password){
	createXMLHttp();
	xmlHttp.open("POST","PasswordCheckLevel?password="+password);
	xmlHttp.onreadystatechange = moveCallback;
	xmlHttp.send(null);
	}
function moveCallback(){
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){
			var level = xmlHttp.responseText;
			if(level == "0"){
				alert("密码不能小于8位或者是常用单词");
				}else if(level == "1"){
					document.getElementById("level").innerHTML = "弱";
				}else if(level == "2"){
					document.getElementById("level").innerHTML = "中";
				}else{
					document.getElementById("level").innerHTML = "强";
				}
			}
		}
}
</script>
</head>
<body>
	<form method="post">
		<table border="0" align="center" width="300" height="80">
		<tr>
			<td width="150">UserName:</td>
			<td><input type="text" name="userName" id="userName"/></td>
		</tr>
		<tr>
			<td>PassWord:</td>
			<td><input type="text" name="passWord" id="passWord" onblur="move(this.value)"/></td>
		</tr>
		<tr>
			<td>Security Level:</td>
			<td id="level"></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input type="submit" value="Login"/>
			</td>
		</tr>		
		</table>
	</form>
</body>
</html>