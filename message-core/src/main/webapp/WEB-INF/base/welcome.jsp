<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>客户意向跟踪管理系统</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {font-size: 18pt}
.STYLE2 {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<div align="center">
  <p>&nbsp;</p>
  <p class="STYLE1">&nbsp;</p>
  <c:if test="${!empty username}">
        	<span id="currentuser"  style = "font-size: 20px;;">欢迎：${username}</span>
   </c:if>
  <p class="STYLE1">本系统是--<span class="STYLE2">客户意向跟踪</span>--管理系统</p>
  <p class="STYLE1">&nbsp;</p>
  <p class="STYLE1">&nbsp;</p>
</div>
</body>
</html>