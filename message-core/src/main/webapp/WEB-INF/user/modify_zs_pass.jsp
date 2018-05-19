<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/user/modifyGsUser">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p align="center">当前用户名：${ub.gsName} 编号：${ub.gsNum} 密码：
    <label>
    <input name="pws" type="text" id="pws" value="${ub.pws}" size="15" maxlength="20" />
    </label>
    <label>
    <input type="submit" name="Submit" value="修改" />
    </label>
  </p>
  <p align="center">&nbsp;</p>
  <input type="hidden" name="mid" value="${ub.id}" />
</form>
</body>

</html>