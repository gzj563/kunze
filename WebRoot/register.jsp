<%@ page language="java" contentType="text/html; charset=gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <title>注册</title>
    <%
    	String projectPath=request.getContextPath();
    %>
    <script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
    <link href="<%=projectPath%>/css/wap/common.css" type="text/css" rel="stylesheet" />
    <!-- kindeditor -->
    <link rel="stylesheet" href="<%=projectPath%>/commonRes/kindeditor/default.css" />
	<script charset="utf-8" src="<%=projectPath%>/commonRes/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="<%=projectPath%>/commonRes/kindeditor//zh_CN.js"></script>
		
	<!--  -->
   <style type="text/css">
   	#pic{
		  margin:0 auto;
		  width:100%;
		  padding:0;
		  border:1px solid #333;
		}
	#pic img{
	    max-width:100%;
		width:expression(document.body.clientWidth>document.getElementById("pic").scrollWidth*9/10? "780px": "auto" );
		border:1px dashed #000;
	}
   </style>
  
   <script type="text/javascript">
   		$(document).ready(function(){
   			$("#register_activity").click(function(){
   				var name = $("#name").val();
   				var phone = $("#phone").val();
   				var vocation = $("#vocation").val();
   				if(name == ""){
   					$("#name").focus();
   					return false;
   				}
   				if(phone == ""){
   					$("#phone").focus();
   					return false;
   				}
   				if(vocation == ""){
   					$("#vocation").focus();
   					return false;
   				}
   				$.ajax({
   					url : "<%=projectPath%>/HandlerManager?handler=userHandler&method=reg_act_save&data="+new Date(),
   					method : 'post',
   					dataType : "text", //
   					success : function(data,status){
   						var json = eval('('+data+')');
   						alert("报名成功");
   					},
   					error : function(data,status,e)
   					{
   						alert(e);
   					}
   				});
   			});
   		});
   </script>
  </head>
  <body>
  <center>
  <table class="editTable" width="80%" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width=40% nowrap align=right class="editTdName">姓名:</td>
		<td width=60% nowrap class="editTdValue"><input type="text" id="name"/></td>
	</tr>
	<tr>
		<td width=40% nowrap align=right class="editTdName">手机:</td>
		<td width=60% nowrap class="editTdValue"><input type="text" id="phone"/></td>
	</tr>
	<tr>
		<td width=40% nowrap align=right class="editTdName">职业:</td>
		<td width=60% nowrap class="editTdValue"><input type="text" id="vocation"/></td>
	</tr>
	<tr>
		<td width=100% nowrap align=right class="editTdName" colspan=2>
			<div id="pic">
				<img src="<%=projectPath%>/images/menuShow.jpg" alt="感谢blueidea被我盗链图片！"/>
			</div>
		</td>
	</tr>
	<tr>
		<td width=100% nowrap align=right class="editTdName" colspan=2>
			<div id="pic">
				<img src="<%=projectPath%>/images/menuShow.jpg" alt="感谢blueidea被我盗链图片！"/>
			</div>
		</td>
	</tr>
	<tr>
	  	<td colspan=2 align="center">
		  	<input type="button" value="活动报名"  id="register_activity">
		  </td>
	  </tr>
</table>

   
	
	</center>	
  </body>
</html>