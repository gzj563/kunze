<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,DB.mo.MenuMO,common.FuncString" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
 	String projectPath=request.getContextPath();
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	
 %>
<title>Insert title here</title>
<link href="<%=projectPath%>/css/web/common.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
<style type="text/css">

.btn1_mouseout {     
	BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7EBF4F 1px solid; 
	PADDING-LEFT: 2px; FONT-SIZE: 18px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#B3D997);
	BORDER-LEFT: #7EBF4F 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7EBF4F 1px solid;
	height: 40px;width:120px;
}
.btn1_mouseover {     
	BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7EBF4F 1px solid; PADDING-LEFT: 2px; 
	FONT-SIZE: 18px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#CAE4B6); 
	BORDER-LEFT: #7EBF4F 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7EBF4F 1px solid;
	height: 40px;width:120px;     
}
</style>
</head>

<body>
<table width="100%" cellspacing="0" cellpadding="0" class="editTable">
	<tr>
		<td colspan=6 ><button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" 
			onmouseout="this.className='btn1_mouseout'"  title="删除菜单">   删除菜单       </button>     </td>
		
	</tr>
	
	<tr class="subBar">
		<td align=center class="editTd" ><input type="checkbox" id="chall" name="chall" onclick="checkall();"></td>
		<td align=center class="editTd" >菜单名</td>
		<td align=center class="editTd" >关联关键词</td>
		<td align=center class="editTd" >关联URL</td>
		<td align=center class="editTd" >排序号</td>
		<td align=center class="editTd" >操作</td>
	</tr>
	<%
		if(resultList == null || resultList.size()==0){
	%>
	
		<TR ALIGN="LEFT" VALIGN="TOP">
	          <td NOWRAP colspan=6>没有找到记录! </TD>
	    </TR>
	<% }else{
			MenuMO menuMo=null;
			for(int i=0;i<resultList.size();i++){
				menuMo = (MenuMO)resultList.get(i);
	%>		
	<tr valign="middle" id="menu_<%=menuMo.getId()%>" class="subBar">	
		<td  align=center class="editTd" ><input type="checkbox" id="menu_<%=menuMo.getId()%>" ></td>			
		<td align=center class="editTd" ><%=FuncString.toString(menuMo.getName())%></td>
		<td align=center class="editTd" ><%=FuncString.toString(menuMo.getKeyword())%></td>
		<td align=center class="editTd" ><%=FuncString.toString(menuMo.getUrl())%></td>
		<td align=center class="editTd" ><%=FuncString.toString(menuMo.getIndex())%></td>
		<td align=center class="editTd" >
			<input type="button" value="编辑"  onclick="javascript:edit_activity(<%=menuMo.getId()%>);"/>
			 <input type="button" onclick="javascript:delActivity(<%=menuMo.getId()%>,'<%=menuMo.getName()%>');" value="删除" />
		</td>
	</tr>	
	<% 	}
	}
	%>		
	<tr>
		<td colspan=6>
			
		</td>
	</tr>
</table>
</body>
</html>