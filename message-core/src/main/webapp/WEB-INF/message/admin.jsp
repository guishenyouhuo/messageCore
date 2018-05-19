<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>维雅奇电动车留言管理系统</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<p align="center">后台用户管理</p>
<c:forEach items="${admins}" var="admin">
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/modifyAdmin?id=${admin.id}">
  <div align="center">
  后台用户名：
  <label>
  <input name="adminname" type="text" id="adminname" value="${admin.adminname}" size="15" maxlength="15" />
  </label> 
  后台密码：
  <label>
  <input name="adminpass" type="text" id="adminpass" value="${admin.adminpass}" size="15" maxlength="15" />
  </label> 
  当前版本信息：
  <label>
  <input name="var_gs" type="text" id="var_gs" value="${admin.varGs}" />
  </label>
  <label>

    <input type="submit" name="Submit" value="修改" />
  
  </label>
  </div>

  <input type="hidden" name="MM_update" value="form1">
  <input type="hidden" name="MM_recordId" value="11">
</form>
<br/>
</c:forEach>
<p>&nbsp;</p>
</body>


</html>