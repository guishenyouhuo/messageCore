<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>客户意向管理系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<c:if test="${empty username }">
        	<script>window.location='index.jsp'</script>
        	</c:if>
<frameset rows="*" cols="216,*" framespacing="0" frameborder="no" border="0">
  <frame src="<%=request.getContextPath() %>/common/importMeum" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="<%=request.getContextPath() %>/common/importWelcome" name="mainFrame" id="mainFrame" title="mainFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>