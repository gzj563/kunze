<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="DB.mo.ActivityMO,common.FuncString" %>
<html>
<head>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>活动呈现</title>

<%
    String projectPath=request.getContextPath();
	ActivityMO mo = (ActivityMO)request.getAttribute("activity");
	if(mo==null){
		mo=new ActivityMO();
	}
	
%>

</head>
	
	<style type="text/css">
    	p img {
    		max-width:100%;
    	}
    </style>
    <script type="text/javascript">
    <%-- 隐藏微信右上角转发按钮  开始--%>
    function onBridgeReady(){
    	 WeixinJSBridge.call('hideOptionMenu');
    }

   	if (typeof WeixinJSBridge == "undefined"){
   	    if( document.addEventListener ){
   	        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
   	    }else if (document.attachEvent){
   	        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
   	        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
   	    }
   	}else{
   	    onBridgeReady();
   	}
   	<%-- 隐藏微信右上角转发按钮   结束--%>
    </script>
<body>
 <center>
	<%=FuncString.toString(mo.getContent())%>
 </center>
</body>

</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>