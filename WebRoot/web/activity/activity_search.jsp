<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%
    String projectPath=request.getContextPath();
	String hql=(String)request.getAttribute("hql");
%>
<link href="<%=projectPath%>/css/web/common.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=projectPath%>/commonRes/tcal.css" />
<script type="text/javascript" src="<%=projectPath%>/commonRes/tcal.js"></script>
<script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
<script type="text/javascript">

function searchActivity(){
	var title = $("#title").val();
	var endTime = $("#end_time").val();
	
    fm=document.forms[0];	
    fm.target="result";
    var action='<%=projectPath%>/HandlerManager?handler=activityHandler&methodName=activity_search';
    action+="&title="+title+"&endTime="+endTime;
    fm.action=action;
    fm.submit();
}
function keyDown(event){
	if (event.keyCode == 13){                                    
		searchActivity();
    } 
} 

</script>

<title>counter browse</title>
</head>

<body>
<form action="" method="post">
<table width=100% cellspacing="0" cellpadding="0" >
	<tr  height=25>
		<td><font class="headline">&gt;&gt;Browse &amp; Export Counter</font></td>				
	</tr>
	<tr bgcolor=#0066FF height=25 class="bar">
				<td><b>&nbsp;Search Condition</b></td>				
	</tr>
	<tr height=60 valign=top>
		<td><table class="editTable" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td width=10% nowrap align=right class="editTdName">标题:</td>
				<td width=15% nowrap class="editTdValue">
					<input type="text" id="title" onkeydown="keyDown(event);">
				</td>
				<td width=10% nowrap align=right class="editTdName">结束时间:</td>
				<td width=15% nowrap class="editTdValue"><input type="text" id="end_time" name="end_time" class="tcal" size="20" readOnly>
				</td>
			</tr>
			<tr>
				<td colspan="8" align="center">
					<input name="search" id="search" type="button" value="Search" onclick="javascript:searchActivity();" class="button"/>
				</td>
			</tr>
			
		</table>
	</td>
	</tr>
	<tr>
	<td>
	
	</td>
	</tr>
</table>
	<iframe name="result" src="" height=90%  width=100% frameborder=0 ></iframe>
<form>
</body>
</html>