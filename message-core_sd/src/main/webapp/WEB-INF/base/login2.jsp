<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>客户意向管理系统</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<p align="center">客户意向管理系统</p>
<center>
  <div style="font-size: 14px; color: red; margin: 0px auto;">
    <c:if test="${!empty erro}">
    ${erro}
    </c:if>
    </div>
    </center>

<form ACTION="<%=request.getContextPath() %>/loginout/gsLogin" id="form1" name="form1" method="post" >
  <table width="350" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td><fieldset><legend>LOGIN</legend>
          <table width="285" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="116" height="35"><div align="center">招商人名：</div></td>
              <td width="284" height="35"><input name="gs_name" type="text" id="gs_name" class="logintxt"/></td>
            </tr>
            <tr>
              <td height="35"><div align="center">招商密码：</div></td>
              <td height="35"><label>
                <input name="password" type="password" id="pws" class="logintxt"/>
              </label></td>
            </tr>
            <tr>
              <td height="35" colspan="2"><label>
                  <div align="center">
                    <input type="submit" name="Submit" value="登陆" class="buttoncss"/>
                  </div>
                </label></td>
            </tr>
          </table>
      </fieldset></td>
    </tr>
  </table>
</form>
</body>
</html>