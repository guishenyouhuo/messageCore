<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="../myjs/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(function(){
		var baseUrl = '<%=request.getContextPath()%>';
		$("#subBtn").click(function(){
			var id = $("#idRecord").val();
			var old_num = $("#oldnum").val();
			var new_num = $("#gs_num").val();
			var gs_name = $("#gs_name").val();
			var pws = $("#pws").val();
			var gsType = $("#gsType").val();
			$.post(baseUrl + "/user/checkUser", {"num":new_num}, function(data){
				if(data==1&&old_num!=new_num){
					alert("该用户编号已存在，请重新分配");
				}
				else{
					var param = {"id":id, "gsNum":new_num, "gsName":gs_name, "pws":pws, "gsType":gsType};
					$.post(baseUrl + "/user/modifyUser", param, function(data){
						if(data==1)
							window.location.href = baseUrl + "/user/showUsers";
						else
						 	alert("修改失败！");
					});
				}
		});
		});
	});
</script>
<body>
<p>&nbsp;</p>
<form id="form1" name="form1">
  <table width="500" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#FFCC00">
    <tr>
      <td height="30" colspan="7" bgcolor="#FFFF99"><div align="center">添加招商用户</div>
          <div align="center"></div>
        <div align="center"></div>
        <div align="center"></div>
        <div align="center"></div></td>
    </tr>
    <tr>
      <td width="95" height="30" bgcolor="#FFFFFF"><div align="center">编号</div></td>
      <td width="134" height="30" bgcolor="#FFFFFF"><div align="center">
          <input name="gs_num" type="text" id="gs_num" value="${user.gsNum}" size="10" maxlength="10" />
      </div></td>
      <td width="75" height="30" bgcolor="#FFFFFF"><div align="center">姓名</div></td>
      <td width="106" height="30" bgcolor="#FFFFFF"><div align="center">
          <label>
          <input name="gs_name" type="text" id="gs_name" value="${user.gsName}" size="10" maxlength="10" />
          </label>
      </div></td>
      <td width="75" height="30" bgcolor="#FFFFFF"><div align="center">密码</div></td>
      <td width="106" height="30" bgcolor="#FFFFFF"><div align="center">
          <label>
          <input name="pws" type="text" id="pws" value="${user.pws}" size="10" maxlength="10" />
          </label>
      </div></td>
      <td width="74" height="30" bgcolor="#FFFFFF"><div align="center">
          <label>
          <input type="button" id="subBtn" name="Submit" value="修改" />
          </label>
      </div></td>
    </tr>
  </table>
  <input type="hidden" id="oldnum" name="oldnum" value="${user.gsNum}">
  <input type="hidden" id="idRecord" name="MM_recordId" value="${user.id}">
  <input type="hidden" id="gsType" name="MM_recordId" value="${user.tyFlag}">
</form>
<p>&nbsp;</p>
</body>
</html>
