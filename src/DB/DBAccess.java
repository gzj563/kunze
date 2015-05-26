package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import common.SysConfiguration;

public class DBAccess {
	private static Logger logger = Logger.getLogger(DBAccess.class);
	public Connection getConn(){
		if ("false".equals(SysConfiguration.getConfigParam("needSAP"))) {
			return this.getLocalConn();
		}else{
			return this.getSAEConn();
		}
	}
	private Connection getLocalConn(){
		logger.debug("->DBAccess.getLocalConn");
		String dbDriver = SysConfiguration.getConfigParam("dbDriver");   // 与本地设置相同
		// app_helloworld20141116为新浪app数据库名称
		String dbUrl = SysConfiguration.getConfigParam("dbUrl"); 
		String dbUser = SysConfiguration.getConfigParam("dbUserID"); 
		String dbPassword = SysConfiguration.getConfigParam("dbPasswd"); 
		Connection con = null;
		try{
			Class.forName(dbDriver);
			logger.debug("加载数据库驱动成功");
			con=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			logger.debug("数据库链接成功");
		}catch(Exception e){
			logger.error("数据库链接失败");
			e.printStackTrace();
		}
		logger.debug("DBAccess.getLocalConn-->");
		return con;
	}
	private Connection getSAEConn(){
		logger.debug("->DBAccess.getSAEConn");
		String dbDriver = SysConfiguration.getConfigParam("dbDriver");   // 与本地设置相同
		// app_helloworld20141116为新浪app数据库名称
		String dbUrl = SysConfiguration.getConfigParam("sae_dbUrl"); 
		String dbUser = SysConfiguration.getConfigParam("sae_dbUserID"); 
		String dbPassword = SysConfiguration.getConfigParam("sae_dbPasswd"); 
		Connection con = null;
		try{
			Class.forName(dbDriver);
			logger.debug("加载sae数据库驱动成功");
			con=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			logger.debug("sae数据库链接成功");
		}catch(Exception e){
			logger.error("sae数据库链接失败");
			e.printStackTrace();
		}
		logger.debug("DBAccess.getSAEConn-->");
		return con;
	}
	public static void closeRsAndPs(ResultSet rs, Statement st)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
			rs = null;
		} catch (Exception e)
		{
		}
		try
		{
			if (st != null)
			{
				st.close();
			}
			st = null;
		} catch (Exception e)
		{
		}
	}
}
