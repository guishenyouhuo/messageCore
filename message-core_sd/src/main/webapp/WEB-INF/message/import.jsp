<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
        var isCommitted = false;//表单是否已经提交标识，默认为false
        function dosubmit(){
            if(isCommitted==false){
                isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
                return true;//返回true让表单正常提交
            }else{
                return false;//返回false那么表单将不提交
            }
        }
    </script>
</head>

<body>

<p align="center">导入留言</p>
<form enctype="multipart/form-data"  action="<%=request.getContextPath() %>/message/importMessage" method="post" onsubmit="return dosubmit();">
<table align="center">
<tr align="center"><td>
请选择您要导入的文件(Excel 97-2003文档)：<input type = "file" name = "excel"/>
</td></tr>
<tr></tr>
<tr align="center"><td>
分配给：
 <select name="select" id="sel">
 	<option selected="selected" value="autoly">自动分配</option>
				<c:forEach items="${users}" var="user">
					<c:if test="${user.gsNum==num}">
						<option  value="${user.gsNum}">${user.gsName}</option>
					</c:if>
					<c:if test="${user.gsNum!=num}">
						<option value="${user.gsNum}">${user.gsName}</option>
					</c:if>
				</c:forEach>
         </select>
   </td></tr><tr></tr>
   <tr><td>来源：<label>
        <select name="fromtag">
        	<c:forEach items="${tags}" var="tag">
        	<option value="${tag.tagId}">${tag.tagId}</option>
        	</c:forEach>
        </select>
      </label></td></tr><tr><td><a href="<%=request.getContextPath() %>/message/toAddTag">添加来源</a></td></tr>
   <tr align="center"><td>
<input type = "submit"  value="导入"/>
</td></tr>
</table>
 </form>
</body>
</html>