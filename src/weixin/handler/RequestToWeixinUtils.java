package weixin.handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import DB.DBAccess;
import DB.dao.TokenDAO;
import common.ConsistentValue;
import common.FuncString;
import common.SysConfiguration;

public class RequestToWeixinUtils{
	private static Logger logger = Logger.getLogger(RequestToWeixinUtils.class);
	
	public String getToken(HttpServletRequest request){
		String token="";
		//1:调用之前先看看本地的token是否失效
		token = this.getTokenFormDB(request);
		if(FuncString.isEmpty(token)){
			token=this.getTokenFromWeixin();
			logger.debug("token come from weixin:"+token);
			TokenDAO tokenDao=new TokenDAO();
    		tokenDao.updateToken(token);
		}else{
			logger.debug("token come from Database:"+token);
		}
		
		return token;
	}
	private String getTokenFormDB(HttpServletRequest request){
		String token="";
		Connection conn =null;
		Statement st = null;
		ResultSet rs=null;
		DBAccess dbAccess =new DBAccess();
		try{
			String sql="select token_value,(unix_timestamp(now())-unix_timestamp(begin_time)) as lost from token where id=1";
			conn = dbAccess.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			long time_lost=ConsistentValue.EXPIRETIME_TOKEN;
			if(rs.next()){
				time_lost = rs.getLong("lost");
				if(time_lost < ConsistentValue.EXPIRETIME_TOKEN){
					token = rs.getString("token_value");
				}
			}
		}catch(Exception e){
			logger.error("从数据库中查询token发生错误");
			e.printStackTrace();
		}finally{
			DBAccess.closeRsAndPs(rs, st);
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			conn=null;
		}
		return token;
	}
	private String getTokenFromWeixin(){
		String token="";
		String appid=SysConfiguration.getConfigParam("appID_test");
		String appSecret=SysConfiguration.getConfigParam("appsecret_test");
        InputStream is = null;
        try {
        	String action="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appSecret;
        	URL url = new URL(action);
        	HttpURLConnection http =   (HttpURLConnection) url.openConnection();    
             
        	http.setRequestMethod("POST");        
        	http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
        	http.setDoOutput(true);        
        	http.setDoInput(true);
        	System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
        	System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
 
        	http.connect();
         
        	is =http.getInputStream();
        	int size =is.available();
        	byte[] jsonBytes =new byte[size];
        	is.read(jsonBytes);
        	String message=new String(jsonBytes,"UTF-8");
        	logger.debug("response from weixin:"+message);
        	JSONObject jsonObject = new JSONObject(message);
        	if(!FuncString.isEmpty(jsonObject.getString("access_token"))){
        		token=jsonObject.getString("access_token");
        		ConsistentValue.EXPIRETIME_TOKEN=jsonObject.getInt("expires_in");
        		logger.debug("token 失效市场变为:"+ConsistentValue.EXPIRETIME_TOKEN);
        		
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return token;
	}
	
}
