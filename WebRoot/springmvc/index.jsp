
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/jquery-1.11.1.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function go(value){  
    var url = "/kunze/rest/model/10001";  
    
	jQuery.ajax({
		url : url , 
		dataType : "text", 
		data:"123",
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(modelId,status){
			console.log("s");
		},
		error : function(data,status,e)
		{
			console.log("2");
		}
	});
    
  
}  
</script>
</head>
<body>
Input the id of you will access object:<input id="id" type="text" size="7"><input type="button" value="Go" onclick="go()">  
    <div id="text">
    </div>  
</body>
</html>