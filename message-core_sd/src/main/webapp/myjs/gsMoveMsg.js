function moveMsg(msgId){
	if(window.confirm("确定移动这条留言？")){
		var prefix = "#move_" + msgId;
		var select = $(prefix + " #sel").val();
		var last_num = $(prefix + " #last_num").val();
		var messageid = $(prefix + " #messageid").val();
		var pageNow = $("#pageNow").val();
		var flag = $("#flag").val();
		var param = {"select":select,"id":messageid,"last_num":last_num};
		$.post(getContextPath() + "/message/changeUser", param, function(data){
			if(data==1){
				window.location.href = getContextPath() + "/message/showUserMssage?flag=" + flag + "&pageNow=" + pageNow;
			} else {
				alert("移动失败！");
			}
		});
	}
}
function delMsg(msgId){
	var prefix = "#move_" + msgId;
	var last_num = $(prefix + " #last_num").val();
	var messageid = $(prefix + " #messageid").val();
	var pageNow = $("#pageNow").val();
	var flag = $("#flag").val();
	var param = {"id":messageid,"last_num":last_num};
	if(window.confirm("确定删除这条留言？")){
		$.post(getContextPath() + "/message/delMessage", param, function(data){
			if(data==1){
				window.location.href = getContextPath() + "/message/showUserMssage?flag=" + flag + "&pageNow=" + pageNow;
			} else {
				alert("删除失败！");
			}
		});
	}
}
function intentMsg(msgId){
	var prefix = "#move_" + msgId;
	var select = $(prefix + " #sel").val();
	var last_num = $(prefix + " #last_num").val();
	var messageid = $(prefix + " #messageid").val();
	var pageNow = $("#pageNow").val();
	var flag = $("#flag").val();
	var param = {"messageid":messageid, "usernum":last_num}
	$.post(getContextPath() + "/message/mssageIntent", param, function(data){
		 if(data==1){
			alert("添加成功");
			window.location.href = getContextPath() + "/message/showUserMssage?flag=" + flag + "&pageNow=" + pageNow;
		 } else {
			alert("添加意向失败！");
		 }
	});
}
function outtentMsg(msgId){
	var prefix = "#move_" + msgId;
	var select = $(prefix + " #sel").val();
	var last_num = $(prefix + " #last_num").val();
	var messageid = $(prefix + " #messageid").val();
	var pageNow = $("#pageNow").val();
	var flag = $("#flag").val();
	var param = {"messageid":messageid}
	$.post(getContextPath() + "/message/mssageOutIntent", param, function(data){
		 if(data==1){
			alert("移出成功");
			window.location.href = getContextPath() + "/message/showUserMssage?flag=" + flag + "&pageNow=" + pageNow;
		 } else {
			alert("移出失败！");
		 }
	});
}

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}