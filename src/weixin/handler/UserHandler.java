package weixin.handler;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import DB.DBAccess;
import servlet.handler.Handler;

public class UserHandler extends Handler{
	private static Logger logger = Logger.getLogger(UserHandler.class);
	@Override
	public void execute() throws Exception {
		logger.debug("-->UserHandler");
		String methodName =(String)this.request.getParameter("method");
		if(methodName!=null){
			if("reg_act_save".equals(methodName)){
				Method method = this.getClass().getDeclaredMethod("save_registerActivities");
				method.invoke(this);
			}
		}
	}
	private void save_registerActivities() throws Exception{
		logger.debug("-->UserHandler.save_registerActivities");
		// 插入数据库
		String sql = "insert into user(name) values('jet1')";
		Connection conn =null;
		ResultSet rs = null;
		Statement st=null;
		//PreparedStatement ps = null;
		DBAccess dbAccess =new DBAccess();
		try
		{
			conn = dbAccess.getConn();
			if(conn == null){
				return;
			}
			st = conn.createStatement();
			st.executeUpdate(sql);
			//rs = ps.executeQuery();
			//int rowNb=rs.getRow();
			logger.debug("insert sql 执行完毕");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			conn.rollback();
			throw ex;
		}
		finally
		{
			dbAccess.closeRsAndPs(rs, st);
			conn.close();
			conn= null;
		}
		String resp="报名成功";
		PrintWriter pw=this.response.getWriter();
		pw.write(resp);
		pw.close();
		logger.debug("save_registerActivities end");
	}
}
