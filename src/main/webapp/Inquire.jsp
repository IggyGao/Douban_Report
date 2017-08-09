<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>豆瓣年度电影报告</title>
</head>
<body>
	<s:form action="inquire">  
        <s:textfield name="userName" label="请输入Id"></s:textfield>  
        <s:textfield name="year" label="请输入查询年份"></s:textfield>  
        <s:submit value="查询"></s:submit>  
    </s:form>  
</body>
</html>