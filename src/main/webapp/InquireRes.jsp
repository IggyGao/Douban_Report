<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
<h1>
<font color="red">Users List</font>
	</h1>
	<s:a href="/ssh2/index.jsp">返回</s:a>
	<table border="1" width="80%" align="center">
		<tr>
			<th>电影名</th>
			<th>id</th>
			<th>豆瓣id</th>
		</tr>
		<s:iterator value="#request.list" id="us">
			<tr>
				<td><s:property value="#us.title" /></td>
				<td><s:property value="#us.id" /></td>
				<td><s:property value="#us.doubanId" /></td>
				<td align="center">
					<s:a href="deleteUser_success.action?user.id=%{#us.id}" onclick="return del()">Delete</s:a>
				</td>
				<td align="center">
					<s:a href="updatePUser_success.action?user.id=%{#us.id}">Update</s:a>
				</td>
			</tr>
		</s:iterator>
		</table>
</body>
</html>