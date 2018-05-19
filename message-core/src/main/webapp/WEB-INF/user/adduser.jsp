<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			var new_num = $("#gs_num").val();
			var gs_name = $("#gs_name").val();
			var pws = $("#pws").val();
			$.post(baseUrl + "/user/checkUser", {"num":new_num}, function(data){
				if(data==1)
				{
					alert("该用户编号已存在，请重新分配");
				}
				else
				{
					var param = {"gsNum":new_num,"gsName":gs_name,"pws":pws};
					$.post(baseUrl + "/user/addUser", param, function(data){
						if(data==1)
							window.location.href = baseUrl + "/user/showUsers";
						else
						 	alert("添加失败！");
					});
				}
		});
		});
	});
</script>
<body>
<form id="form1" name="form1">
  <p>&nbsp;</p>
  <table width="500" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#FFCC00">
    <tr>
      <td height="30" colspan="7" bgcolor="#FFFF99"><div align="center">添加招商用户</div>        <div align="center"></div>        <div align="center"></div>        <div align="center"></div>        <div align="center"></div></td>
    </tr>
    <tr>
	  <td width="48" height="30" bgcolor="#FFFFFF"><div align="center">编号</div></td>
      <td width="90" height="30" bgcolor="#FFFFFF"><div align="center">
        <input name="gs_num" type="text" id="gs_num" size="10" maxlength="10" />
      </div></td>
      <td width="45" height="30" bgcolor="#FFFFFF"><div align="center">姓名</div></td>
      <td width="93" height="30" bgcolor="#FFFFFF"><div align="center">
        <label>
        <input name="gs_name" type="text" id="gs_name" size="10" maxlength="10" />
        </label>
</div></td>
      
      
      <td width="44" height="30" bgcolor="#FFFFFF"><div align="center">密码</div></td>
      <td width="97" height="30" bgcolor="#FFFFFF"><div align="center">
        <label>
        <input name="pws" type="text" id="pws" size="10" maxlength="10" />
        </label>
</div></td>
	  <td width="61" height="30" bgcolor="#FFFFFF"><div align="center">
        <label>
        <input type="button" id="subBtn" name="Submit" value="提交" />
        </label>
</div></td>
    </tr>
  </table>

  <input type="hidden" name="MM_insert" value="form1">
</form>
</body>
</html>