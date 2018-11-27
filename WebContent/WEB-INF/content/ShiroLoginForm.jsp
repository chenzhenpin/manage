<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>                                                          
</head>
<body>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<form:form  modelAttribute="user" method="post" action="shirologin">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	<table>
		
		<tr>
			<td>姓名：</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<td>性别：</td>
			<td><form:input path="sex"/></td>
			<td><form:errors path="sex" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<td>年龄：</td>
			<td><form:input path="age"/></td>
			<td><form:errors path="age" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><form:input path="username"/></td>
			<td><form:errors path="username" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><form:input path="password"/></td>
			<td><form:errors path="password" cssStyle="color:red"/></td>
		</tr>
		<tr>
			<td>验证码：</td>
			<td><input name="randomcode"/></td>
			<td><img id="randomcode_img" src="${baseurl}validateCode" alt="" width="56" height="20" align='absMiddle'  />
			  <a href="javascript:" onclick="refresh()">刷新</a></td>
		</tr>
		<tr>
			<td>记住我：</td>
			<td><input type="checkbox" name=“rememberMe"/></td>
			
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit" value="登陆"/></td>
		</tr>
		
	</table>
</form:form>
</body>
<script type="text/javascript">
	function refresh(){
		console.log("test");
		var randomcode_img=document.getElementById("randomcode_img");
		console.log("${baseurl}validateCode?id="+Math.random());
		randomcode_img.src="${baseurl}validateCode?id="+Math.random();
	
	}
</script>
</html>