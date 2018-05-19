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
<%
	String userid = (String)request.getSession().getAttribute("userid");
 %>
<body>

<p align="center">查看已做客户 </p>
<c:if test="${empty messages}">
   无记录!
   </c:if>
   <c:if test="${!empty messages}">
   
	 <form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageNow}">
	   <p align="center">
	   <c:if test="${pageNow>1}">
	   <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=1">第一页</a> || 
	   <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageNow-1}">上一页</a> ||
	   </c:if>
	   <c:if test="${pageNow<pageCount}">
	    <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageNow+1}">下一页</a> 
	    || <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageCount}">最后一页</a> 
	   </c:if>
		【共 ${messageCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
	     <label>
	    &nbsp;&nbsp;跳转至
	          <input name="offset" type="text" id="offset" size="3" maxlength="3" />
	          页
	    </label>
	    <label>
	    <input type="submit" name="Submit" value="提交" />
	    </label></p></form>
	    
	<c:forEach items="${messages}" var="message">
    <table width="961" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FF9900">
      <tr>
        <td width="40" bgcolor="#FFFF99"><div align="center">编号</div></td>
        <td width="53" height="25" bgcolor="#FFFF99"><div align="center">客户姓名</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.khName}</div></td>
        <td width="140" bgcolor="#FFFFFF"><div align="center">${message.intime}</div></td>
        <td width="61" bgcolor="#FFFF99"><div align="center">前所属人</div></td>
        <td width="61" bgcolor="#FFFF99"><div align="center">分配人员</div></td>
        <td width="100" bgcolor="#FFFF99"><div align="center">来源</div></td>
        <td width="239" bgcolor="#FFFF99"><div align="center">回访情况</div></td>
      </tr>
      <tr>
        <td rowspan="2" bgcolor="#FFFFFF" width="40"><div align="center">${message.id}</div></td>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户电话</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.khTel}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.khLy}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${numUserMap[message.lastUser].gsName}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${numUserMap[message.fpUser].gsName}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.tag}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.fpHf}</div></td>
      </tr>
      <tr>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户地址</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.khAddress}</div></td>
      </tr>
    </table>
	<br/>
	</c:forEach>
 <input type="hidden" name="pageNow" id="pageNow" value="${pageNow}" />
 <form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageNow}">
	   <p align="center">
	   <c:if test="${pageNow>1}">
	   <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=1">第一页</a> || 
	   <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageNow-1}">上一页</a> ||
	   </c:if>
	   <c:if test="${pageNow<pageCount}">
	    <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageNow+1}">下一页</a> 
	    || <a href="<%=request.getContextPath() %>/message/showCompleteMessage?pageNow=${pageCount}">最后一页</a> 
	   </c:if>
		【共 ${messageCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
	     <label>
	    &nbsp;&nbsp;跳转至
	          <input name="offset" type="text" id="offset" size="3" maxlength="3" />
	          页
	    </label>
	    <label>
	    <input type="submit" name="Submit" value="提交" />
	    </label></p></form>
    </c:if>
<br />
</body>
</html>