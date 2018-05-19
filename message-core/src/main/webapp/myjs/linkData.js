            function checkLength()
            {
            	var linkDataName = document.getElementById("tel");
            	if(linkDataName.value.length>=3)
            		getLinkData();
            }
			function getLinkData() {
                    var popupDiv = document.getElementById("popup");//获得对应的div对象
                    var popupBody = document.getElementById("popupBody");//获得对应的tbody对象
                    var linkDataName = document.getElementById("tel"); //获得对应的联想输入框对象
                    clearModels();//清除联想输入提示框的内容
                    $.ajax({
                        type : "post",//提交的方法为post
                        url : "getLinkDataList.do",//提交的路径
                        dataType : "json",  //从Action中返回的值得类型为json类型的
                        data:{linkDataName:linkDataName.value},//从前台传递到后台的参数，可以避免出现乱码的情况
                        error:function(){
                            alert("没有对应的数据，请查看输入的查询条件！");
                        },
                        success : function(data) {//当Ajax提交成功时调用的方法
                                  if(data.length==0||data=="[]"){return;}
                                  setOffsets();//设置联想输入提示框的位置及样式
                                  var tr,td,text,td_,text_;
                                  for (var i = 0; i < data.length; i++) {//根据返回的值，手动的拼tbody的内容，此为此功能的核心代码                                
                                      text = document.createTextNode(data[i].linkDataName);//从Action中返回的数据中取出linkDataName的值
                                      td = document.createElement("td");//创建一个td的对象           
                                      tr = document.createElement("tr");//创建一个tr的对象   
                                      td.appendChild(text);
                                      tr.appendChild(td); 
                                      tr.onmouseover = function(){this.style.backgroundColor='#0000FF';this.style.color='#FFFFFF';};       
                                      tr.onmouseout = function(){this.style.backgroundColor='#FFFFFF';this.style.color='#000000';};    
                                      tr.onclick = function(){populateModel(this);};//tr单击事件对应的实现方法 
                                      popupBody.appendChild(tr);
                              }
                        }});
                    //获取下拉列表项的内容，初始化相关的数据
                    function populateModel(cell) {
                            clearSelect();         
                            linkDataName.value=cell.firstChild.firstChild.nodeValue;
                            clearModels();//清除自动完成行
                    }
                    //清除自动完成行，只要tbody有子节点就删除掉，并且将将外围的div的边框属性设置为不可见的
                    function clearModels() {
                        while (popupBody.childNodes.length > 0) {
                            popupBody.removeChild(popupBody.firstChild);
                        }
                        popupDiv.style.border = "none";
                    }
                    //设置下拉列表框的的位置及样式
                    function setOffsets() {
                        var width = linkDataName.offsetWidth;
                        var left = getLeft(linkDataName);
                        var top = getTop(linkDataName) + linkDataName.offsetHeight;
                        popupDiv.style.border = "black 1px solid";
                        popupDiv.style.left = left + "px";
                        popupDiv.style.top = top + "px";
                        popupDiv.style.width = width + "px";
                    }
                    //获取指定元素在页面中的宽度起始位置
                    function getLeft(e) {
                        var offset = e.offsetLeft;
                        if (e.offsetParent != null) {
                            offset += getLeft(e.offsetParent);
                        }
                        return offset;
                    }
                    //获取指定元素在页面中的高度起始位置
                    function getTop(e) {
                        var offset = e.offsetTop;
                        if (e.offsetParent != null) {
                            offset += getTop(e.offsetParent);
                        }
                        return offset;
                    }
                }
              //清空联想输入框的内容以及隐藏域的值
                function clearSelect() {
                    var linkDataName = document.getElementById("tel");
                    linkDataName.value = "";
                }