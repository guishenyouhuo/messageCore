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
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/autoLy">
  <p>&nbsp;</p>
  <table width="400" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#FFCC00">
    <tr>
      <td height="30" colspan="4" bgcolor="#FFFF99"><div align="center">自动分配留言设置</div></td>
    </tr>
    <tr>
      <td width="96" height="30" bgcolor="#FFFFFF"><div align="center">总共招商人数</div></td>
      <td width="82" height="30" bgcolor="#FFFFFF"><div align="center">
        <label>
        <input name="maxuser" type="text" id="maxuser" value="${ab.maxuser}" size="5" maxlength="5" />
        </label>
</div></td>
      <td width="119" height="30" bgcolor="#FFFFFF"><div align="center">当前分配到的编号</div></td>
      <td width="90" height="30" bgcolor="#FFFFFF"><div align="center">
        <input name="nownum" type="text" id="nownum" value="${ab.nownum}" size="5" maxlength="5" />
      </div></td>
    </tr>
    <tr>
      <td height="30" colspan="4" bgcolor="#FFFFFF"><div align="center">
        <label>
        <input type="submit" name="Submit" value="修改" />
        </label>
</div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p align="center">注意：1、总共招商人数必须和招商人员总数一致。</p>
  <p>&nbsp;</p>

  <input type="hidden" name="id" value="${ab.id}"/>
</form>
</body>
</html>