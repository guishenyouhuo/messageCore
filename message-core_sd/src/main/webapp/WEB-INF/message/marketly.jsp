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
<div style="height: 30px;"></div>
<c:if test="${empty messages}">
   无记录!
   </c:if>
   <c:if test="${!empty messages}">
 <center>
	   <c:if test="${pageNow>1}">
	   <a href="marketaction.do?method=showMarLy&pageNow=1">第一页</a> || 
	   <a href="marketaction.do?method=showMarLy&pageNow=${pageNow-1}">上一页</a> ||
	   </c:if>
	   <c:if test="${pageNow<pageCount}">
	    <a href="marketaction.do?method=showMarLy&pageNow=${pageNow+1}">下一页</a> 
	    || <a href="marketaction.do?method=showMarLy&pageNow=${pageCount}">最后一页</a> 
	   </c:if>
		【共 ${messageCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
</center>
<c:forEach items="${messages}" var="message">
    <table width="961" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FF9900">
      <tr>
        <td width="40" bgcolor="#FFFF99"><div align="center">编号</div></td>
        <td width="53" height="25" bgcolor="#FFFF99"><div align="center">客户姓名</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_name}</div></td>
        <td width="140" bgcolor="#FFFFFF"><div align="center">${message.intime}</div></td>
        <td width="200" bgcolor="#FFFF99"><div align="center">回访痕迹</div></td>
        <td width="239" bgcolor="#FFFF99"><div align="center">回访情况</div></td>
      </tr>
      <tr>
        <td rowspan="2" bgcolor="#FFFFFF" width="40"><div align="center">${message.id}</div></td>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户电话</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_tel}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.kh_ly}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.firstinfo}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>${message.fp_hf}</td>
  </tr>
  <tr>
    <td><div align="right"><a href="marketaction.do?method=modifyMessageView&id=${message.id}&pageNow=${pageNow}">修改回访</a></div></td>
  </tr>
</table>
</td>
      </tr>
      <tr>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户地址</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_address}</div></td>
      </tr>
    </table>
	<br />
</c:forEach>
<center>
	   <c:if test="${pageNow>1}">
	   <a href="marketaction.do?method=showMarLy&pageNow=1">第一页</a> || 
	   <a href="marketaction.do?method=showMarLypageNow=${pageNow-1}">上一页</a> ||
	   </c:if>
	   <c:if test="${pageNow<pageCount}">
	    <a href="marketaction.do?method=showMarLy&pageNow=${pageNow+1}">下一页</a> 
	    || <a href="marketaction.do?method=showMarLy&pageNow=${pageCount}">最后一页</a> 
	   </c:if>
		【共 ${messageCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
	    </center>
	    </c:if>
<br />
</body>
</html>