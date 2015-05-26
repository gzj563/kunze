<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="DB.mo.ActivityMO,common.FuncString" %>
<html>
<head>
<title>添加活动</title>

<%
    String projectPath=request.getContextPath();
%>
	
	<link rel="stylesheet" type="text/css" href="<%=projectPath%>/commonRes/tcal.css" />
	<link rel="stylesheet" type="text/css"  href="<%=projectPath%>/css/web/common.css" />
	<script type="text/javascript" src="<%=projectPath%>/commonRes/tcal.js"></script> 
    <script type="text/javascript" src="<%=projectPath%>/JS/jquery-1.11.1.js"></script>
    <!-- kindeditor -->
    <link rel="stylesheet" href="<%=projectPath%>/commonRes/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=projectPath%>/commonRes/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=projectPath%>/commonRes/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=projectPath%>/commonRes/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=projectPath%>/commonRes/kindeditor/plugins/code/prettify.js"></script>
    
	<!--  -->
    <style type="text/css">
    	.input{font-size:16px;color:#000000;line-height:20px;padding:10px 0;margin:-10px 0;}
    </style>
	<SCRIPT type="text/javascript">
	
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="contentDesc"]', {
			cssPath : '<%=projectPath%>/commonRes/kindeditor/plugins/code/prettify.css', //modify according to actual situation
			uploadJson : '<%=projectPath%>/upload_json.jsp', //modify according to actual situation
			fileManagerJson : '<%=projectPath%>/file_manager_json.jsp', //modify according to actual situation
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					if(before_submit()){
						document.forms['actForm'].submit();//实际form表单的名字
					}
					
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					if(before_submit()){
						document.forms['actForm'].submit();//实际form表单的名字
					}
				});
			}
		});
		prettyPrint();
		
		K("#activity_title").blur(function(){
			if(this.value==''){
				K("#title_alert").show();
			}else{
				K("#title_alert").hide();
			}
		  
		});
	});
	
	function before_submit(){
		var title = $("#activity_title").val();
		if(title == undefined || title ==''){
			$("#title_alert").show();
			return false;
		}
	}
	
	
	</SCRIPT>
</head>

<body>
 <center>
	<form action="<%=projectPath%>/HandlerManager?handler=activityHandler&method=activity_add_save" styleId="frm" name="actForm" method="post">
		<table width=80% cellspacing="0" cellpadding="0" >
			<tr  height=25>
				<td><font class="headline">&gt;&gt;活动添加 </font></td>				
			</tr>
			<tr  height=25>
						<td></td>				
			</tr>
			<tr height=60 valign=top>
				<td><table class="editTable" width="100%" cellspacing="0" cellpadding="0">
					
					<tr class="bar">
						<td class="editTdName">
							活动标题
						</td>
					</tr>
					<tr>
						<td class="editTdValue">
							<input type="text" id="activity_title" name="activity_title"  size="110" maxlength="70"  /><span style="color:red;font-size:16">*</span>
							<div id="title_alert" style="display:none"> <font color="red"> 标题不能为空 </font></div>
						</td>
					</tr>
					<tr  class="bar">
						<td class="editTdName">活动主要内容描述:	</td>
					</tr>	
							
					<tr>
						<td >
						   <textarea  id="contentDesc"  name="contentDesc"  style="width:700px;height:300px;visibility:hidden;"></textarea>
		          		</td>
					</tr>
					<tr class="bar">
						<td>
							活动截止时间
						</td>
					</tr>
					<tr>
						<td  class="editTdValue">
							<input type="text" id="end_time" name="end_time" class="tcal" size="20"  />
						</td>
					</tr>
					
							<tr>
								<td>
									<input type="submit" style="cursor: pointer;" value="保存" onclick="javascript:return before_submit();"/>
								</td>
							</tr>
				
					
					
				</table>
			</td>
			</tr>
	</table>
 </form>
 </center>
</body>
<script type="text/javascript">

</script>
</html>
