<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../myjs/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(function(){
		var baseUrl = '<%=request.getContextPath()%>';
		$("#srcUser").change(function(){
			var srcUserNum = $(this).children('option:selected').val();
			$.post(baseUrl + "/dividmsg/showDestUsers?srcUser=" + srcUserNum, function(data){
				 $("#appendDest").remove();
				 $("#trBtn").remove();
		    	 $("#destUsers").append(data);
		    	 $("#transferBtn").append("<input type='button' id='trBtn' value='开始转移' />")
		    	});
		});
		$("#trBtn").live('click', function(){
			var destGsNums = "";
			$('input[name=acceptUser]:checked').each(function(){
				destGsNums += $(this).val() + ",";
            });
			if(destGsNums.length > 0){ //如果获取到
				destGsNums = destGsNums.substring(0, destGsNums.length - 1); //把最后一个逗号去掉
			}
			var srcUserNum = $("#srcUser").children('option:selected').val();
			window.location.href = baseUrl + "/dividmsg/dividMessage?srcUser=" + srcUserNum + "&destUsers=" + destGsNums;
		});
	});
</script>
<body>
备份成功！转移资源：<br/>
<table style="text-align: center;border: 1px;">
	<tr>
		<td style="padding-right: 50px;"><p>请选择要转移的资源所属人：</p>
			<select name="users" id="srcUser">
				<option value="-1">请选择</option>
				<c:forEach items="${userList}" var="gsUser">
					<option value="${gsUser.gsNum}">${gsUser.gsName}</option>
				</c:forEach>
			</select>
		</td>
		<td id="destUsers" style="width: 100;padding-left: 50px;"></td>
	</tr>
	<tr><td colspan="2" id="transferBtn"></td></tr>
</table>
</body>
</html>