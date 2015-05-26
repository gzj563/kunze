<%@ page language="java" pageEncoding="gbk"%>
<%@ page import="DB.mo.ManagerUserMO,common.ConsistentValue,common.FuncString,java.util.Date" %>
<html>
<head>
<title>Management page </title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<%
    String projectPath=request.getContextPath();
	ManagerUserMO user = (ManagerUserMO) session.getAttribute("user");
	
	String adminShow="none";
	if(!FuncString.isEmpty(request.getAttribute("display_admin"))){
		adminShow="";
	}
%>	

<link href="<%=projectPath%>/css/web/common.css" type="text/css" rel="stylesheet" />
<style type="text/css">

.bb {border-style:solid;border-width:3px #ff9900;cursor:hand;background-color: #CCCCCC;}

</style>
</head>
<body style="marginwidth:0px;marginheight:0px;margin: 1px;">
<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" style="margin: 0px;">
	<tr>
		<td width="100%" colspan=3 height="50" valign="top">
			<iframe name=title src="<%=projectPath%>/title.jsp" height=100% width=100% frameborder=0 scrolling="no" style="marginwidth:0px;marginheight:0px;"></iframe>
		</td>
	</tr>
	<tr height="28px">
		<td colspan="3" class="bgcTitle">
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" style="margin: 0px;">
			<tr class="bgcTitle">
			<td width="20%">
				<img id="show" src="images/menuShow.jpg" onClick="showMenu();" style="display: none; cursor:pointer;" title="Show the menu!" alt="Show the menu!">
				<img id="unshow" src="images/menuUnshow.jpg" onClick="showMenu();" style="display: ''; cursor:pointer;" title="Hide the menu!" alt="Hide the menu!">
			</td>
			<td width="80%" align="right" style="color: #ccc;size: 12px;">
				<%if(user.getRole().equals(ConsistentValue.ROLE_ADMIN)){ %>
				<html:link action="user.do?buttonv=yes" target="content"><font color="#ffffff">User Profile</font></html:link>
				|
				<html:link action="chgpass.do" target="content"><font color="#ffffff">Change password</font></html:link>
				|
				<%} %>
				<a style="cursor:pointer;" href="#" onClick="javascript:if (window.confirm('Are you sure to logout?')){window.location.href='logon.do';}">
				  <font color="#990000"><b>Logout</b></font></a>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr valign="top">
		<td id="menu" style="display: '';" width="126" height="95%">		
		<table height="0" width="100%" style="font-size: 12px;">
			<tr height="10"><td>&nbsp;</td></tr>
			<tr id="activity"
				style="display:<%=request.getAttribute("display_user") %>">
				<td nowrap class="menu1">
					<img border="0" src="images/spacer.gif" width="1"	height="1">
					<img id="gif0" width=12 height=12 src="images/minus.gif" onClick="shrink(div0,this)"> 
					活动管理
				</td>
			</tr>
			<tr id="activity2"
				style="display:<%=request.getAttribute("display_user") %>">
				<td>
					<div id="div0">
						<table width="100%" height="0" style="font-size: 12px;">
							<tr>
								<td nowrap>
									<img border="0" src="./images/spacer.gif" width="10" height="1">
									<img width=12 height=12 src="./images/minus.gif">
									<a href="<%=projectPath%>/HandlerManager?handler=activityHandler&method=activity_add&data=<%=new Date().getTime()%>" target="content">活动添加</a>
								</td>
							</tr>
						
							<tr>
								<td nowrap>
									<img border="0" src="./images/spacer.gif" width="10" height="1">
									<img width=12 height=12 src="./images/minus.gif">
									<a href="<%=projectPath%>/HandlerManager?handler=activityHandler&method=activity_maintain&data=<%=new Date().getTime()%>" target="content">活动维护</a>
								</td>
							</tr>																
						</table>
					</div>
				</td>
			</tr>
		
			<tr id="menu_weixin"
				style="display:<%=request.getAttribute("display_user") %>">
				<td nowrap class="menu1">
					<img border="0" src="images/spacer.gif" width="1"	height="1">
					<img id="gif0" width=12 height=12 src="images/minus.gif" onClick="shrink(div1,this)"> 
					菜单管理
				</td>
			</tr>
			<tr id="menu_weixin_item"
				style="display:<%=request.getAttribute("display_user") %>">
				<td>
					<div id="div1">
						<table width="100%" height="0" style="font-size: 12px;">
							<tr>
								<td nowrap>
									<img border="0" src="./images/spacer.gif" width="10" height="1">
									<img width=12 height=12 src="./images/minus.gif">
									<a href="<%=projectPath%>/web/menu/menuMaintain.jsp?data=<%=new Date().getTime()%>" target="content">微信菜单</a>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		
			<tr id="admin"
				style="display:<%=adminShow%>">
				<td nowrap class="menu1">
					<img border="0" src="./images/spacer.gif" width="1"	height="1">
					<img id="gif1" width=12 height=12 src="./images/minus.gif" onClick="shrink(div30,this)"> 
					Admin
				</td>
			</tr>
			<tr id="adminmng"
				style="display:<%=adminShow%>">
				<td>
				<div id="div30">
				<table style="font-size: 12px;">
					
					<tr id="sysmanage" style="display:<%=request.getAttribute("display_usermanage")%>">
						<td nowrap>
							<img border="0" src="./images/spacer.gif" width="10" height="1">
							<img width=12 height=12 src="./images/minus.gif">
							<a href="systemManageAction.do?method=init" target="content" onClick="javascript:highlight(this);">
								<font color=#996633>System Management</font>
							</a>
						</td>
					</tr>
					<tr id="usermanage" style="display:<%=request.getAttribute("display_usermanage")%>">
						<td nowrap>
							<img border="0" src="./images/spacer.gif" width="10" height="1">
							<img width=12 height=12 src="./images/minus.gif">
							<a href="searchUserAction.do?method=init" target="content" onClick="javascript:highlight(this);">
								<font color=#996633>User Management</font>
							</a>
						</td>
					</tr>	
				</table>
				</div>
				</td>
			</tr>	
					
			<tr height=100%>
				<td></td>
			</tr>
		</table>
		</td>
		<td height="100%" width="3" valign="middle">
			<div style="valign: middle;height:100%;width:4px;" class="bgcTitle">
				<font size="2"></font>
			</div>
		</td>
		<td id="content" width="88%"><iframe name="content" src=""
			height=100% width=100% frameborder=0 scrolling="yes"></iframe>
		</td>
	</tr>

</table>
</body>
<script type="text/javascript">
	function userInfo(url){
		window.open(url,"_blank",'height=400,width=600,top=200,left=200,toolbar=no,menubar=no,resizable=no,location=no,scrollbars=yes');      
	}
	function highlight(_self){
		var menuItemL=document.links.length;
		for(i=0;i<menuItemL;i++){
			var munuItem=document.links[i];
			munuItem.className="";
		}

		_self.className='bb';
		
		
	}
	function shrink(divcon,img){
		if(divcon.style.display=="")
		{
		divcon.style.display="none";
		img.src="images/plus.gif";
		}
		else{
		divcon.style.display="";
		img.src="images/minus.gif";
		}
	}

	function showMenu(){				
		var menuDiv=document.getElementById("menu");
		var conTd=document.getElementById("con");
		var show=document.getElementById("show");
		var unshow=document.getElementById("unshow");
		//alert("showMenu"+menuDiv.style.display);
		if(menuDiv.style.display==''){
			menuDiv.style.display='none';
			show.style.display='';
			unshow.style.display='none';
			con.width="99%";
			menu.width="0"
		}else{
			menuDiv.style.display='';
			show.style.display='none';
			unshow.style.display='';
			con.width="88%";
			menu.width="126";
		}
	}
</script>
</html>
