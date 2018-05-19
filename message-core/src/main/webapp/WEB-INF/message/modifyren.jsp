<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
      <form id="moveForm" name="form1">
        <label>
          <select name="select" id="sel">
		<c:forEach items="${userList}" var="user">
			<c:if test="${user.gsNum == param.userNum}">
				<option selected="selected" value="${user.gsNum}">${user.gsName}</option>
			</c:if>
			<c:if test="${user.gsNum != param.userNum}">
				<option value="${user.gsNum}">${user.gsName}</option>
			</c:if>
		</c:forEach>
          </select>
        </label>
        <input type="button" name="Submit" id="moveBtn" value="移动" onclick="moveMsg(${param.msgId})"/>

          <input type="hidden" name="MM_update" value="form1">
          <input type="hidden" name="MM_recordId" id = "messageid" value="${param.msgId}">
          <input type="hidden" name="last_num" id = "last_num" value="${param.userNum}">
      </form>
</div>
<br/>
<br/>
<div align="center"><a href=" javascript:void(0);" id="delhref" onclick="delMsg(${param.msgId})">删除留言</a></div>
