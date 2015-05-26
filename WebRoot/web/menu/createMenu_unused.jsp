<%@ page language="java" contentType="text/html;  charset=gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
 <%
    	String projectPath=request.getContextPath();
    %>
<script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
    <link href="<%=projectPath%>/css/common.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
$(document).ready(function(){
	jQuery.support.cors = true;
	
	$("#getToken").click(function(){
		var url = $("#url").val();
		if(url == ""){
			$("#url").focus();
			return false;
		}
		$.ajax({
			url : url+"&data="+new Date(),
			method : 'GET',
			dataType : "text", //
			success : function(data,status){
				//var json = eval('('+data+')');
				$("#resp_token").val(data);
			},
			error : function(data,status,e)
			{
				alert(data);
				alert(status);
				alert(e);
			}
		});
	});
	//create munu
	$("#createMenu").click(function(){
		var token = $("#token").val();
		if(token == ""){
			$("#token").focus();
			return false;
		}
		$.ajax({
			url : "<%=projectPath%>/HandlerManager?handler=menuhandler&method=createMenu&token="+token+"&data="+new Date(),
			method : 'POST',
			dataType : "text", //
			success : function(data,status){
				//var json = eval('('+data+')');
				$("#respMsg").val(data);
			},
			error : function(data,status,e)
			{
				alert("error:"+e);
			}
		});
	});
});
</script>
<title>create weixin menu</title>
</head>
<body>
<table width="293" border="1" align="center">
  <tr>
  <td colspan="2">http请求方式必须是 GET
  </tr>
  <tr>
    <td width="150">http url</td>
    <td width="291"><label>
      <input name="textfield2" type="text" id="url" size="45">
    </label></td>
  </tr>
  
  <tr>
    <td colspan="2" align="center"><input name="button" type="button"  id="getToken" value="getToken"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">result of the requestion </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><label>
      <textarea name="textarea2" id="resp_token" cols="70"></textarea>
    </label></td>
  </tr>
</table>

<p>&nbsp;</p>
<table width="421" border="1" align="center">
  <tr>
    <td width="151">token</td>
    <td width="254"><label>
      <input type="text" id="token" size=45 name="textfield">
    </label></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><label>
      <input type="button" id="createMenu" value="创建菜单">
    </label></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><label>
      <textarea name="textarea" id="respMsg" cols="70"></textarea>
    </label></td>
  </tr>
</table>
</body>
</html>