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
		String dbDriver = SysConfiguration.getConfigParam("dbDriver");   // �뱾��������ͬ
		// app_helloworld20141116Ϊ����app���ݿ�����
		String dbUrl = SysConfiguration.getConfigParam("dbUrl"); 
		String dbUser = SysConfiguration.getConfigParam("dbUserID"); 
		String dbPassword = SysConfiguration.getConfigParam("dbPasswd"); 
		Connection con = null;
		try{
			Class.forName(dbDriver);
			logger.debug("�������ݿ������ɹ�");
			con=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			logger.debug("���ݿ����ӳɹ�");
		}catch(Exception e){
			logger.error("���ݿ�����ʧ��");
			e.printStackTrace();
		}
		logger.debug("DBAccess.getLocalConn-->");
		return con;
	}
	private Connection getSAEConn(){
		logger.debug("->DBAccess.getSAEConn");
		String dbDriver = SysConfiguration.getConfigParam("dbDriver");   // �뱾��������ͬ
		// app_helloworld20141116Ϊ����app���ݿ�����
		String dbUrl = SysConfiguration.getConfigParam("sae_dbUrl"); 
		String dbUser = SysConfiguration.getConfigParam("sae_dbUserID"); 
		String dbPassword = SysConfiguration.getConfigParam("sae_dbPasswd"); 
		Connection con = null;
		try{
			Class.forName(dbDriver);
			logger.debug("����sae���ݿ������ɹ�");
			con=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			logger.debug("sae���ݿ����ӳɹ�");
		}catch(Exception e){
			logger.error("sae���ݿ�����ʧ��");
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
