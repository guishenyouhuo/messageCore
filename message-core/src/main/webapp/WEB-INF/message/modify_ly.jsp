<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../myjs/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="../myjs/jquery-calendar.js"></script> 
<link rel="stylesheet" type="text/css" href="../css/jquery-calendar.css" /> 
</head>
<!-- <script type="text/javascript" src="myjs/jquery-1.6.2.js"></script> -->
<script type="text/javascript">
var isCommitted = false;//表单是否已经提交标识，默认为false
$(function(){
	var baseUrl = '<%=request.getContextPath()%>';
	$("#finishBtn").click(function(){
		if(window.confirm("确定该客户已经做完？")){
			window.location.href = baseUrl + "/message/completeMsg?id=${message.id}&pageNow=${pageNow}";
		}
	});
	
	$("#unfinishBtn").click(function(){
		if(window.confirm("确定移出已做客户组？")){
			window.location.href = baseUrl + "/message/unCompleteMsg?id=${message.id}&pageNow=${pageNow}";
		}
	});
	});
</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/modifyHf?type=${type}&flag=${message.type}">
  <p>&nbsp;</p>
  <table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
  <caption> 修改回访 </caption>
    <tr>
      <td width="168" height="30">客户姓名：</td>
      <td width="332">${message.khName}</td>
    </tr>
    <tr>
      <td height="30">客户电话：</td>
      <td>${message.khTel}</td>
    </tr>
    <tr>
      <td height="30">客户地址：</td>
      <td>${message.khAddress}</td>
    </tr>
    <tr>
      <td height="30">客户留言：</td>
      <td>${message.khLy}</td>
    </tr>
    <tr>
      <td height="30">回访情况：</td>
      <td><label>
        <textarea name="textarea" cols="45" rows="10">${message.fpHf}</textarea>
      </label></td>
    </tr>
    <tr>
	  <td height="30">下次回访日期：</td>
	  <td><label>
	    <input name="lasthg" type="text" id="lasthg" maxlength="10"  value="${message.lasthf}" onfocus="$(this).calendar()" readonly="readonly"/>
	  （日期格式：2012-8-1）</label></td>
	</tr>
	<tr>
      <td height="80" colspan="2">注明：指定下次回访日期后，在左侧的菜单的“今日任务”会显示当天需要回访的留言记录。
	  在输入日期时，必须用小写英文输入状态输入，不可用大写中文输入数字，否则无效。</td>
    </tr>
    <tr>
      <td height="30" colspan="2"><label>
        <div align="center">
          <input type="submit" name="Submit" value="提交" />
        </div>
      </label></td>
      <td align="right">
      <c:if test="${message.type!='3'}">
      <input type="button" value="完成"  id="finishBtn"/><font color="red">(加入到已做客户组)</font>
      </c:if>
      <c:if test="${message.type=='3'}">
      <input type="button" value="未完成" id="unfinishBtn" /><font color="red">(从已做客户组移除)</font>
      </c:if>
      </td>
    </tr>
  </table>
  <input type="hidden" name="mid" value="${message.id}"/>
  <input type="hidden" name="pageNow" value="${pageNow}"/>
</form>
</body>
</html>