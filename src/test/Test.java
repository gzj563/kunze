package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;




import org.json.JSONException;
import org.json.JSONObject;

import weixin.msg.MessageUtil;
import weixin.msg.TextMessage_resp;

public class Test {
	public static void main(String[] args) throws JSONException{
		String a="{'errcode':'0','errmsg':'ok'}";
		
		JSONObject jsonObject = new JSONObject(a);
		System.out.println(jsonObject.get("errmsg"));;
//		testMysqlConn();
	}
	private static void testMysqlConn(){
		String driver = "com.mysql.jdbc.Driver";  
        String url = "jdbc:mysql://localhost:3306/kunze";  
        String user = "root";  
        String password = "root";  
        try {  
            Class.forName(driver);  
            Connection conn = DriverManager.getConnection(url, user, password);  
            Statement stmt = conn.createStatement();  
            stmt.executeUpdate("insert into activity(title,contentDesc,createTime) values('²âÊÔmysql','¹þ¹þ','2014-11-12')");  
            ResultSet rs = stmt.executeQuery("select * from activity");  
            while (rs.next()) {  
                int id = rs.getInt("id");  
                String message = rs.getString("title");  
                System.out.println(id + " " + message);  
            }  
            rs.close();  
            stmt.close();  
            conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}
