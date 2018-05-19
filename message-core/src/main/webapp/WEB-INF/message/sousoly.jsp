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
<p>&nbsp;</p>
<p align="center">搜索留言</p>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/searchMessage">
  <div align="center">按电话搜索
  ：
    <label>
    <input name="tel" type="text" id="tel" size="20" maxlength="20" />
    </label>
    <label>
     　 
    <input type="submit" name="Submit" value="电话搜索" />
    </label>
  </div>
</form>
<p></p><p></p>
<form id="form2" name="form2" method="post" action="<%=request.getContextPath() %>/message/searchMessage">
  <div align="center">按编号搜索 ：
    <input name="id" type="text" id="id" size="20" maxlength="20"/>
   　  
   <input type="submit" name="Submit2" value="编号搜索" />
  </div>
</form>
<p></p><p></p>
<form id="form3" name="form3" method="post" action="<%=request.getContextPath() %>/message/searchMessage">
  <div align="center">按姓名搜索 ：
    <input name="name" type="text" id="id" size="20" maxlength="20"/>
   　  
   <input type="submit" name="Submit2" value="姓名搜索" />
  </div>
</form>
<p align="center">&nbsp;</p>
</body>


</html>