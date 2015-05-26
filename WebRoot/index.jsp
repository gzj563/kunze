<%@ page language="java" pageEncoding="gbk"%>
<%@ page import="common.FuncString" %>
<html> 
	<head>
		 <%
    		String projectPath=request.getContextPath();
   		 %>
		<title> µÇÂ½ </title>
		<link href="<%=projectPath%>/css/web/common.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
	<center>
	    <table width="100%" height="100%" align="center">
	    <tr>	    
	    	<td align="center" valign="middle">
				<form action="HandlerManager?handler=loginHandler"  method="post"> 
					<table class="bgcTitle" width="550" height="400" align="center">
					<tr>
						<td colspan="3"><h4><img width="160" align="left" src="images/logo.jpg"></td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<strong>
								<font color="#660099">Welcome to  KunZe</font>
							</strong>
						</td>
					</tr>	
							
					<tr>
						<td align="center" colspan="3">
							<table class="bgcTitle" style="color: #fff;">
							
							<tr height="33"><td colspan="2" align="center">
								<input type="button" style="cursor: pointer;" value="  Read Only with Guest Account  " onclick="javascript:reader();" />
							</td>
							</tr>
							<tr height="20">				
								<td colspan="2" nowrap="nowrap" align="center"><font color="red"> <b><%=FuncString.toString(request.getAttribute("errorMsg")) %></b></font></td>
							</tr>	
							
							<tr>
								<td align="right">UserName :</td>
								<td nowrap="nowrap"><input type="text" name="userName" id="name"/></td>
							</tr>
							<tr>
								<td align="right">Password :</td>
								<td nowrap="nowrap"><input type="password" name="userPwd" id="password"/></td>
							</tr>
							<tr><td align="center" colspan="2">
								<input type="submit" style="cursor: pointer;" value="µÇÂ½"/>
							</td>
							</tr>
							</table>
						</td>
					</tr>
					</table>
				</form>		
			</td>
		</tr>		
		</table>
	</center>
	</body>

	<script type="text/javascript">
	
	function reader(){
		document.getElementsByName("userName")[0].value = "kunze";
		document.getElementsByName("userPwd")[0].value = "kunze";
		fm=document.forms[0];
		fm.submit();
	}
	</script>
</html>		
