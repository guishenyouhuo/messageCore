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

<p align="center">添加excel模板</p>
<form  action="<%=request.getContextPath() %>/message/addTemplate" method="post" >
<table align="center">
<tr><td>模板名称</td><td><input type="text" name="tmpname"/></td></tr>
<tr><td>是否有第一行</td><td><input type="checkbox" checked="checked" name="hasfirst"/>(类似于78网一类的模板中没有第一行需要去掉对勾)</td></tr>
<tr><td>姓名所在列</td><td><input type="text" name = "name"/></td></tr>
<tr><td>联系电话所在列</td><td><input type="text" name = "tel"/></td></tr>
<tr><td>地址所在列</td><td><input type="text" name = "address"/></td></tr>
<tr><td>留言所在列</td><td><input type="text" name = "message"/></td></tr>
<tr align="center"><td colspan="2"><input type="submit" value = "添加"/></td></tr>
</table>
 </form>
</body>
</html>