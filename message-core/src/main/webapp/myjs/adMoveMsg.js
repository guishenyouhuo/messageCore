function moveMsg(msgId){
	if(window.confirm("确定移动这条留言？")){
		var prefix = "#move_" + msgId;
		var select = $(prefix + " #sel").val();
		var last_num = $(prefix + " #last_num").val();
		var messageid = $(prefix + " #messageid").val();
		var pageNow = $("#pageNow").val();
		var param = {"select":select,"id":messageid,"last_num":last_num};
		$.post(getContextPath() + "/message/changeUser", param, function(data){
			if(data==1){
				window.location.href = getContextPath() + "/message/showAllMessage?pageNow=" + pageNow;
			}else{
				alert("移动失败！");
			}
		});
	}
}
function delMsg(msgId){
	var prefix = "#move_" + msgId;
	var num = $(prefix + " #last_num").val();
	var last_num = $(prefix + " #last_num").val();
	var messageid = $(prefix + " #messageid").val();
	var pageNow = $("#pageNow").val();
	var param = {"id":messageid,"last_num":last_num};
	if(num==20){
		if(window.confirm("回收站的留言删除将会是永久删除，删除之后将无法恢复，数据无价，请慎重！确定要永久删除？")){
			$.post(getContextPath() + "/message/delMessage", param ,function(data){
				if(data==1){
					window.location.href = getContextPath() + "/message/showAllMessage?pageNow=" + pageNow;
				} else {
					alert("删除失败！");
				}
	  		});
		}
	}
	else if(window.confirm("确定删除这条留言(到回收站)？")){
		$.post(getContextPath() + "/message/delMessage", param, function(data){
			if(data==1){
				window.location.href = getContextPath() + "/message/showAllMessage?pageNow=" + pageNow;
			} else {
				alert("删除失败！");
			}
	  	});
	 }
}
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}