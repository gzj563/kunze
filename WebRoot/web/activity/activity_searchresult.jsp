<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,DB.mo.ActivityMO,common.FuncString" %>
<html>
 <%
 	String projectPath=request.getContextPath();
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	
 %>
<head>
<link href="<%=projectPath%>/css/web/common.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
<style type="text/css">
 td {
 	word-break:break-all;
 }
</style>
<script language="javascript">
<!-- 
function delActivity(id,title){
	if(confirm("您确认删除活动:"+title)){
		$.ajax({
				url : "<%=projectPath%>/HandlerManager?handler=activityHandler&methodName=del_activity&id="+id,
				method : 'post',
				dataType : "text", //
				success : function(data,status){
					if(data == '1'){
						$("#activity"+id).hide();
					}else{
						alert(data);
					}
				},
				error : function(data,status,e)
				{
					alert(e);
				}
			});
	}
}
function edit_activity(id){
	var height=500;
	var width=800;
	window.open ('<%=projectPath%>/HandlerManager?handler=activityHandler&methodName=edit_activity_init&id='+id+'&date='+new Date(), 'newwindow', 
			'height='+height+', width='+width+', top='+(screen.height-height)/2+', left='+(screen.width-width)/2+', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ;
}
-->
</script>
</head>

<body>

<table width=100% bgcolor=#FFFFD5 cellspacing="0" cellpadding="0" >
	<tr height=25 class="bar">
		<td>
			<b>&nbsp;Search Result</b>			
		</td>
	</tr>
     <%
		if(resultList == null || resultList.size()==0){
	%>
	
			<TR ALIGN="LEFT" VALIGN="TOP">
	          <TD NOWRAP>没有找到记录! </TD>
	         </TR>
	<% 	}else{
		
	%>
		<tr valign=top>
		<td>
		<table width="100%" cellspacing="0" style="table-layout:fixed;" cellpadding="0" class="editTable">			
			<tr valign="middle" class="subBar">
				<td width=20%  align=center class="editTd" >标题</td>			
				<td width=45% align=center class="editTd" >内容</td>
				<td width=10% align=center class="editTd" >创建时间</td>
				<td width=10% align=center class="editTd" >结束时间</td>
				<td width=15% align=center class="editTd" >操作</td>				
							
			</tr>
			<%
				ActivityMO activityMo=null;
				for(int i=0;i<resultList.size();i++){
					activityMo = (ActivityMO)resultList.get(i);
			%>
				<tr valign="middle" id="activity<%=activityMo.getId()%>">	
				<td  align=center class="editTd" ><%=FuncString.toString(activityMo.getTitle())%></td>			
				<td align=center class="editTd" ><%=FuncString.toString(activityMo.getContent())%></td>
				<td align=center class="editTd" ><%=FuncString.toString(activityMo.getCreateTime())%></td>
				<td align=center class="editTd" ><%=FuncString.toString(activityMo.getEndTime())%></td>
				<td align=center class="editTd" >
					<input type="button" value="编辑"  onclick="javascript:edit_activity(<%=activityMo.getId()%>);"/>
					 <input type="button" onclick="javascript:delActivity(<%=activityMo.getId()%>,'<%=activityMo.getTitle()%>');" value="删除" />
				</td>
				</tr>	
			<% 	}
			%>		
		</table>
		</td>
	</tr>
	<%	}
 	%>
	
</table>

<script language="javascript">

function checkall(){
//alert(chall.checked);
if(chall.checked){
	for(var i=0;i< document.getElementsByName("chk").length;i++ ){
		document.getElementsByName("chk")[i].checked="true";}
}else{
//alert(chall.checked);
	for(i=0;i< document.getElementsByName("chk").length;i++ ){
		//alert(txtdoc[i].checked);
		document.getElementsByName("chk")[i].checked=false;}
}
//checkall.checked="true";
}
</script>
</body>

</html>
