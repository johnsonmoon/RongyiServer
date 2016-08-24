<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的web测试</title>
</head>
<body>
你好！Johnson！
	<form method="post" enctype="application/x-www-form-urlencoded" action="IPContentTest">
		<br>
		<br>
		用户名：<input type="text" name="Acc_name"/>
		<br>
		密码：<input type="text" name="Acc_pwd"/>
		<br>
		<input type="text" name="adlhu"/>
		<br>
		<input type="text" name="adhafu"/>
		<br>
		<input type="text" name="ajkf"/>
		<br>
		<input type="submit" value="登录"/>
		<br>
		<br>
	</form>
	<form method="post" enctype="multipart/form-data" action="IPContentTest">
		<input type="text" name="das"/>
		<br>
		<input type="text" name="daa"/>
		<br>
		<input type="text" name="dad"/>
		<br>
		<input type="file" name="file"/>
		<br>
		<input type="submit" value="登录"/>
		<br>
		<br>
	</form>
			
	<form method="post" enctype="multipart/form-data" action="Upload">  
       	<br>
       	<input type="text" id="name" name="name"/>  
       	<br>
       	<input type="text" name="UserName"/>
       	<br>
       	<input type="text" name="UserPwd"/>
       	<br>
       	<input type="file" id="file" name="file"/>  
       	<br>
       	<input type="submit" value="提交"/>  
   	</form>
</body>
</html>