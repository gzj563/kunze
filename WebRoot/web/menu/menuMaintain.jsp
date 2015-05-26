<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
 <%
    	String projectPath=request.getContextPath();
    %>
<script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
<link href="<%=projectPath%>/css/web/common.css" type="text/css" rel="stylesheet" />

<title>简洁Tab</title>  

<style type="text/css">  
body{font-size:12px;} 
a:link,a:visited{font-size:12px;color:#666;text-decoration:none;} 
a:hover{color:#ff0000;text-decoration:underline;} 
#Tab{margin:0 auto;width:98%;border:1px solid #BCE2F3;} 
.Menubox{height:28px;border-bottom:1px solid #64B8E4;background:#E4F2FB;} 
.Menubox ul{list-style:none;margin:7px 40px;padding:0;position:absolute;} 
.Menubox ul li{float:left;background:#64B8E4;line-height:20px;display:block;cursor:pointer;width:120px;text-align:center;color:#fff;font-weight:bold;border-top:1px solid #64B8E4;border-left:1px solid #64B8E4;border-right:1px solid #64B8E4;} 
.Menubox ul li.hover{background:#fff;border-bottom:1px solid #fff;color:#147AB8;} 
.Contentbox{clear:both;margin-top:0px;border-top:none;height:181px;padding-top:8px;height:100%;}
.Contentbox ul{list-style:none;margin:7px;padding:0;} 
.Contentbox ul li{line-height:24px;border-bottom:1px dotted #ccc;} 


.tr_title{
		background-color: #CCFFFF;		
	}
</style> 
<script type="text/javascript"> 


$(document).ready(function(){
	$("#menu_index").keyup(function(){
		var val=this.value;
		var pattern = /^[0-9]+$/;
		if(!pattern.exec(val)){
			$("#menu_index_error").css({"display":""});
		}else{
			$("#menu_index_error").css({"display":"none"});
		}
	});
	//$("#menu_content").src="/kunze/HandlerManager?handler=menuhandler&method=queryAllMenu&data="+new Date();
	$("#menu_create").click(function(){
		$.ajax({
			url : "/kunze/HandlerManager?handler=menuhandler&method=createMenu&data="+new Date(),
			dataType : "text", //
			data	: "&token="+$("#token").val(),
			success : function(data,status){
				//var json = eval('('+data+')');
				$("#menu_create_result").html(data);
				$("#loading").hide();
			},
			error : function(data,status,e)
			{
				$("#loading").hide();// hide the image when upload ending
				alert(e);
			}
		});
	});
	$("#loading").hide();
	
});

function setTab(name,cursel,n){ 
	for(i=1;i<=n;i++){ 
		var menu=document.getElementById(name+i); 
		var con=document.getElementById("con_"+name+"_"+i); 
		menu.className=i==cursel?"hover":""; 
		con.style.display=i==cursel?"block":"none"; 
	} 
} 

</script> 
</head> 
<body> 


<div id="Tab"> 
	<div class="Menubox"> 
		<ul> 
		<li id="menu1" onMouseOver="setTab('menu',1,3)" class="hover">新增菜单</li> 
		<li id="menu2" onMouseOver="setTab('menu',2,3)" >删除菜单</li> 
		<li id="menu3" onMouseOver="setTab('menu',3,3)" >生成微信菜单</li>
		</ul> 
	</div> 
	<div class="Contentbox"> 
		<div id="con_menu_1" class="hover"> 
			<table width="100%" cellspacing="0" cellpadding="0" class="editTable">
				<tr>
					<td>
						<b>菜单序号</b>
					</td>
				</tr>
				<tr>
					<td>
						<input type=text id="menu_index"/><span id="menu_index_error" style="display:none;"><font color=red>请输入数字</font></span>
					</td>
				</tr>
				<tr>
					<td>
						是否一级菜单(如果是一级菜单，选择“无”即可)
					</td>
				</tr>		
				<tr>
					<td>
						<select id="menu_level"><option value="">无</option></select>
					</td>
				</tr>
				<tr>
					<td>
						菜单名 (最多可创建三个一级菜单，每个一级菜单最多创建5个2级菜单。编辑中的菜单不会马上被用户看到)
					</td>
				</tr>
				<tr>
					<td>
						<input type=text id="menu_name"/>
					</td>
				</tr>
				<tr>
					<td>
						关联关键词(关联关键词和关联URL可选填一项)
					</td>
				</tr>
				<tr>
					<td>
						<input type=text id="menu_keyword"/>
					</td>
				</tr>
				<tr>
					<td>
						关联URL
					</td>
				</tr>
				<tr>
					<td>
						<input type=text id="menu_url"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type=button value="确定" id="menu_save"/>
					</td>
				</tr>
			</table>
			
		</div> 
		<div id="con_menu_2" style="display:none"> 
			<iframe name="result" id="menu_content" src="/kunze/HandlerManager?handler=menuhandler&method=queryAllMenu&data="+new Date() width=100% frameborder=0 ></iframe>
		</div> 
		
		
		<div id="con_menu_3" style="display:none"> 
			
			<table width="100%" cellspacing="0" cellpadding="0" class="editTable">
				<tr>
					<td>
						Token
					</td>
				</tr>		
				<tr>
					<td>
						<input type="text" id="token" size="120" value="Hv14jrl-gD6l-NEPfonWrt3f6N0_xuituZjPG9Ovm4DXHHZrvymQoWV1xR0_vJYcy3YFOGdo6QlUueuncDRF3HW4xXeKCi4d_hzyJgHMYoc"/>
					</td>
				</tr>
				<tr class="tr_title">
					<td  class="editTdName">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; executing result</td>
				</tr>
				
				<tr>
					<td  class="editTdName" id="menu_create_result"></td>		
				</tr>
				<tr>
					<td  class="editTdName"><input type=button id="menu_create" value="创建微信菜单"></td>		
				</tr>
				
			</table>
		</div>
	</div> 
</div> 
</body> 
</html>