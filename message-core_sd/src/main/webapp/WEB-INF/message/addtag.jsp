<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>

<p align="center">添加来源</p>
<form  action="<%=request.getContextPath() %>/message/addTag" method="post" >
<table align="center">
<tr align="center"><td>
请输入来源名称：<input type ="text" name = "tag"/>
</td></tr>
   <tr><td>选择对应的模板（如没有模板可自行添加）：<label>
        <select name="template">
        	<c:forEach items="${templates}" var="key">
        	<option value="${key.tmpKey}">${key.tmpKey}</option>
        	</c:forEach>
        </select>
      </label></td></tr><tr><td><a href="<%=request.getContextPath() %>/message/toAddTemplate">添加模板</a></td></tr>
   <tr align="center"><td>
<input type = "submit"  value="添加"/>
</td></tr>
</table>
 </form>
</body>
</html>