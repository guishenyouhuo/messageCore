<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../myjs/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(function(){
		var baseUrl = '<%=request.getContextPath()%>';
		$(".openbtn").click(function(){
			var trid = $(this).parent().parent().parent().attr("id");
			$.post(baseUrl + "/user/openCloseUser", {"id":$("#"+trid+" #idfield").val()}, function(data){
				if(data==1)
				{
					window.location.href = baseUrl + "/user/showUsers";
				}
				else
				{
					alert("启用失败！");
				}
		});
		});
		$(".closebtn").click(function(){
			var trid = $(this).parent().parent().parent().attr("id");
			$.post(baseUrl + "/user/openCloseUser" , {"id":$("#"+trid+" #idfield").val()}, function(data){
				if(data==1)
				{
					window.location.href = baseUrl + "/user/showUsers";
				}
				else
				{
					alert("停用失败！");
				}
		});
		});
		$(".delBtn").click(function(){
			if(window.confirm("确定删除？"))
			{
				var trid = $(this).parent().parent().parent().attr("id");
				$.post(baseUrl + "/user/delUser", {"id":$("#"+trid+" #idfield").val()}, function(data){
					if(data==1)
					{
						window.location.href = baseUrl + "/user/showUsers";
					}
					else
					{
						alert("删除失败！");
					}
				});
			}
		});
	});
</script>
</head>

<body>
    <p>&nbsp;</p>
    <table width="500" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFCC00">
    <caption>员工管理</caption>
	<tr>
      <td width="114" height="30" bgcolor="#FFFF66"><div align="center">编号</div></td>
        <td width="120" bgcolor="#FFFF66"><div align="center">姓名</div></td>
		<td width="156" bgcolor="#FFFF66"><div align="center">密码</div></td>
        <td width="156" bgcolor="#FFFF66"><div align="center">用户状态</div></td>
        <td width="105" bgcolor="#FFFF66"><div align="center">操作</div></td>
         <td width="105" bgcolor="#FFFF66"><div align="center">停用/启用</div></td>
         <td width="105" bgcolor="#FFFF66"><div align="center">删除</div></td>
       
	</tr>
	<c:if test="${empty users}">
	无用户！
	</c:if>
	<c:if test="${!empty users}">
	<c:forEach items="${users}" var="user">
      <tr id="tr${user.id}">
      	<input type="hidden" id="idfield" value="${user.id}"/>
        <td width="114" height="30" bgcolor="#FFFFFF"><div align="center">${user.gsNum}</div></td>
        <td width="120" bgcolor="#FFFFFF"><div align="center">${user.gsName}</div></td>
		<td width="156" bgcolor="#FFFFFF"><div align="center">${user.pws}</div></td>
        <td width="156" bgcolor="#FFFFFF"><div align="center">
			<c:if test="${user.tyFlag==1}">
				<p style="color:green;">启用</p>
			</c:if>
			<c:if test="${user.tyFlag==0}">
				<p style="color:red;">停用</p>
			</c:if>
        
        </div></td>
        <td width="105" bgcolor="#FFFFFF"><div align="center"><a href="<%=request.getContextPath() %>/user/toModify?id=${user.id}">修改</a></div></td>
       <td width="105" bgcolor="#FFFFFF"><div align="center"> 
       <c:if test="${user.tyFlag==1}">
       <input type="button" class="closebtn" value = "停用" />
       </c:if>
       <c:if test="${user.tyFlag==0}">
		<input type="button" class="openbtn" value = "启用" />
       </c:if>      
        </div></td>
        <td width="105" bgcolor="#FFFFFF">
        <c:if test="${user.gsNum!='20'}">
        <div align="center"><input type="button" value="删除" class="delBtn"/></div>
        </c:if>
        </td>
      </tr>
	</c:forEach>
	</c:if>
</table>
<!--     <p align="center"><a href="autoly.asp">修改招商人数</a></p> -->
    <p>&nbsp;</p>
    <p align="center">提示：1、编号是每位招商人员分配留言的依据，请在修改或删除前确定操作，否则会让部分留言无法显示。</p>
</body>
</html>