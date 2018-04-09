/**
 * 加载城市
 */
//默认先加载一下城市
	window.onload = loadCity;
	function loadCity(){
		//获取项目绝对路径
	  var curWwwPath=window.document.location.href;
	  var pathName=window.document.location.pathname;
	  var pos=curWwwPath.indexOf(pathName);
	  var localhostPaht=curWwwPath.substring(0,pos);
	  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	  var realPath=localhostPaht+projectName;

		var big_area = document.getElementById("big_area").value;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function (){
			if(xmlhttp.readyState == 4){
				//清除城市子节点
				var b =  document.getElementById("city");
				var nodes = b.childNodes;
				for(var i = nodes.length-1;i>=0;i--){
					b.removeChild(nodes[i]);
				}
				//接收回传值
				var data = xmlhttp.responseText;
				var array = eval("("+data+")");
				var parent = document.getElementById("city");
				for(var i = 0;i< array.length ;i++){
					var option = document.createElement("option");
					option.innerText = array[i];
					parent.appendChild(option);
				}
			}
		};
		xmlhttp.open("post",realPath+"/common/loadCity.action",true);
		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlhttp.send("big_area="+big_area);
		
	}