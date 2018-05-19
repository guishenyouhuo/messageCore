<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看用户登录记录</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../myjs/jquery-1.6.2.js"></script>
<script type="text/javascript">
function lookIpDetail(loginIp){
	$.post(getContextPath() + "/loginRecord/lookIpDetail", {"loginIp":loginIp}, function(data){
		alert(data);
	});
}
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>
</head>

<body>
	<p align="center">查看所有登录记录</p>
	<c:if test="${empty records}">
   	无记录!
   </c:if>
	<c:if test="${!empty records}">
		<form id="form1" name="form1" method="post"
			action="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageNow}">
			<p align="center">
				<c:if test="${pageNow>1}">
					<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=1">第一页</a> || 
	   				<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageNow-1}">上一页</a> ||
	   			</c:if>
				<c:if test="${pageNow<pageCount}">
					<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageNow+1}">下一页</a> || 
					<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageCount}">最后一页</a>
				</c:if>
				【共 ${recordCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
				<label>
					&nbsp;&nbsp;跳转至 <input name="offset" type="text" id="offset"
					size="3" maxlength="3" /> 页
				</label> 
				<label> <input type="submit" name="Submit" value="提交" /></label>
			</p>
		</form>
		<table width="800" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFCC00">
			<caption>访问记录</caption>
			<tr>
				<td width="180" height="30" bgcolor="#FFFF66"><div align="center">编号</div></td>
				<td width="180" bgcolor="#FFFF66"><div align="center">用户姓名</div></td>
				<td width="180" bgcolor="#FFFF66"><div align="center">用户IP</div></td>
				<td width="180" bgcolor="#FFFF66"><div align="center">登录时间</div></td>
			</tr>
			<c:forEach items="${records}" var="record">
				<tr>
					<td width="180" height="30" bgcolor="#FFFFFF"><div align="center">${record.recordId}</div></td>
					<td width="180" bgcolor="#FFFFFF"><div align="center">${record.loginUser}</div></td>
					<td width="180" bgcolor="#FFFFFF">
						<div align="center">
							<a href="javascript:void(0);" onclick="lookIpDetail('${record.loginIp}')">${record.loginIp}</a>
						</div>
					</td>
					<td width="180" bgcolor="#FFFFFF"><div align="center">${record.loginTime}</div></td>
				</tr>
			</c:forEach>
		</table>
			<form id="form1" name="form1" method="post"
				action="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageNow}">
				<p align="center">
					<c:if test="${pageNow>1}">
						<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=1">第一页</a> || 
	   					<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageNow-1}">上一页</a> ||
	   				</c:if>
					<c:if test="${pageNow<pageCount}">
						<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageNow+1}">下一页</a> || 
						<a href="<%=request.getContextPath() %>/loginRecord/showLoginRecords?pageNow=${pageCount}">最后一页</a>
					</c:if>
					【共 ${recordCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】
					 <label>
						&nbsp;&nbsp;跳转至 <input name="offset" type="text" id="offset"
						size="3" maxlength="3" /> 页
					</label> 
					<label> <input type="submit" name="Submit" value="提交" /></label>
				</p>
			</form>
	</c:if>
	<br />
</body>
</html>