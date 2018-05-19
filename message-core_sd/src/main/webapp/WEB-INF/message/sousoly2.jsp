<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../myjs/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../myjs/linkData.js"></script>
</head>
<body>
<p>&nbsp;</p>
<p align="center">搜索留言</p>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/searchUserMessage">
  <div align="center">按电话搜索 ：
    <label>
    <input name="tel" type="text" id="tel" size="20" maxlength="20" autocomplete="off"/>
    </label>
    <div id="popup" style="position: absolute;z-index:1001;">
       <table width="100%" bgcolor="#fffafa">
           <tbody id="popupBody"></tbody>
       </table>
     </div>
    <label>
    <input type="submit" name="Submit" value="电话搜索" />
    </label>
  </div>
</form>
<p align="center">&nbsp;</p>
</body>
</html>