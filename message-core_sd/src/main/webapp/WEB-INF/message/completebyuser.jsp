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
<p>&nbsp;</p>
<p align="center">按人员分配查看已做客户</p>
    <table width="300" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FF9900">
	
 <tr>
    <td width="100" height="30" bgcolor="#FFFF99"><div align="center">编号</div></td>
        <td width="100" bgcolor="#FFFF99"><div align="center">招商人员姓名</div></td>
        <td width="100" bgcolor="#FFFF99"><div align="center">查看分配留言</div></td>
      </tr>
	<c:forEach items="${users}" var="user">
      <tr>
        <td width="100" height="30" bgcolor="#FFFFFF"><div align="center">${user.gsNum}</div></td>
        <td width="100" bgcolor="#FFFFFF"><div align="center">${user.gsName}</div></td>
        <td width="100" bgcolor="#FFFFFF"><div align="center"><a href="<%=request.getContextPath() %>/message/showCompleteMessage?id=${user.gsNum}">查看分配留言</a></div></td>
      </tr>
	</c:forEach>
</table>
<p align="center">&nbsp;</p>
</body>
</html>