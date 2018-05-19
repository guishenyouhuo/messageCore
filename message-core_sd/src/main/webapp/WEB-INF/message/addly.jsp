<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>电动车添加留言</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="../myjs/jquery-1.6.2.js"></script>
<script type="text/javascript">
var isCommitted = false;//表单是否已经提交标识，默认为false
$(function(){
	var baseUrl = '<%=request.getContextPath()%>';
	$("#subBtn").click(function(){
		var tel = $.trim($("#kh_tel").val());
		$.post(baseUrl + "/message/checkMessage", {"tel":tel}, function(data){
			if(data==1){
				alert("已经存在不能重复添加。");
			}
			else {
				if(!isCommitted){
					$("#form1").submit();
				}
			}
		});
	});
});
        function dosubmit(){
            if(isCommitted==false){
                isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
                return true;//返回true让表单正常提交
            }else{
                return false;//返回false那么表单将不提交
            }
        }
</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/message/addMessage" onsubmit="return dosubmit();">
<table width="400" border="0" align="center" cellpadding="1" cellspacing="0">
<caption><br/><br/>添加留言</caption>
<tr>
      <td height="5" colspan="2">
      </td>
      </tr>
    <tr>
      <td width="62" height="25"><div align="right">客户姓名：</div></td>
      <td width="334"><label>
        <input name="kh_name" type="text" id="kh_name" />
      </label></td>
    </tr>
    <tr>
      <td height="25"><div align="right">客户电话：</div></td>
      <td><label>
        <input name="kh_tel" type="text" id="kh_tel" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
      </label></td>
    </tr>
    <tr>
      <td height="25"><div align="right">客户地址：</div></td>
      <td><label>
        <input name="kh_address" type="text" id="kh_address" size="40" />
      </label></td>
    </tr>
    <tr>
      <td height="25"><div align="right">客户留言：</div></td>
      <td><label>
        <textarea name="kh_ly" cols="40" rows="3" id="kh_ly" ></textarea>
        
        <input name="fp_user" type="hidden" id="fp_user" 
        value="11" />
        <input name="intime" type="hidden" id="intime" value="11" />
      </label></td>
    </tr>
    <c:if test="${!empty users}">
    	<tr>
	      <td height="25"><div align="right">分配给：</div></td>
	      <td><label>
	        <select name="select" id="sel">
	        <option selected="selected" value="autoly">自动分配</option>
				<c:forEach items="${users}" var="user">
					<c:if test="${user.gsNum==num}">
						<option selected="selected" value="${user.gsNum}">${user.gsName}</option>
					</c:if>
					<c:if test="${user.gsNum!=num}">
						<option value="${user.gsNum}">${user.gsName}</option>
					</c:if>
				</c:forEach>
          	</select>
	      </label></td>
    	</tr>
    </c:if>
    <tr><td>来源：</td><td><label>
        <input name="fromtag" type="text" id="fromtag" size="40" />
      </label></td></tr>
    <tr>
      <td height="34" colspan="2"><label>
        <div align="center">
          <input type="button" name="Submit" id="subBtn" value="提交"/>
        </div>
      </label></td>
    </tr>
  </table>

    <input type="hidden" name="MM_insert" value="form1">
</form>
<script>
//form1.kh_tel.focus(); 
</script>
</body>


</html>